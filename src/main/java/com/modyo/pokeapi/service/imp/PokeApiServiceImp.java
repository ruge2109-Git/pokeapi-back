package com.modyo.pokeapi.service.imp;

import com.modyo.pokeapi.service.PokeApiService;
import com.modyo.pokeapi.endpointpokeapi.PokeApiWS;
import com.modyo.pokeapi.model.pokeapi.DataInfo;
import com.modyo.pokeapi.model.pokeapi.DescriptionPokemon;
import com.modyo.pokeapi.model.pokeapi.DetailPokemon;
import com.modyo.pokeapi.model.pokeapi.EvolutionResponse;
import com.modyo.pokeapi.model.pokeapi.EvolvesTo;
import com.modyo.pokeapi.model.pokeapi.ResultApiPokeApi;
import com.modyo.pokeapi.model.pokeapi.TypeObject;
import com.modyo.pokeapi.model.response.PokemonInfo;
import com.modyo.pokeapi.model.response.ResponseApi;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ruge2
 */
@Service
public class PokeApiServiceImp implements PokeApiService {

    @Autowired(required = true)
    PokeApiWS apiWS;

    @Override
    public ResponseApi apiPokeApi(int limit, int offset) {
        ResponseApi<PokemonInfo> responseApi = new ResponseApi();
        try {
            ResultApiPokeApi apiPokeApi = apiWS.getListPokemon(limit, offset);
            if (apiPokeApi == null) {
                return responseApi;
            }
            responseApi.setBRta(true);
            responseApi.setSMsg("List found succesfully");
            responseApi.setData(this.mapInfoPokemon(apiPokeApi.getResults()));
            responseApi.setCantData(apiPokeApi.getCount());
        }
        catch (Exception e) {
            responseApi.setBRta(false);
            responseApi.setSMsg("Error to find list pokemon");
        }
        return responseApi;
    }

    @Override
    public ResponseApi apiDetailPokemon(String namePokemon) {
        ResponseApi<DetailPokemon> responseApi = new ResponseApi();
        try {
            DetailPokemon apiPokeApi = apiWS.getInfoPokemon(namePokemon);
            if (apiPokeApi == null) {
                return responseApi;
            }
            responseApi.setBRta(true);
            responseApi.setSMsg("List found succesfully");
            apiPokeApi = this.getDescriptionPokemon(apiPokeApi);

            List<DetailPokemon> listDetail = new ArrayList<>();
            listDetail.add(apiPokeApi);
            responseApi.setData(listDetail);
        }
        catch (Exception e) {
            responseApi.setBRta(false);
            responseApi.setSMsg("Error to find pokemon");
        }
        return responseApi;
    }

    @Override
    public ResponseApi apiListType() {
        ResponseApi<String> responseApi = new ResponseApi();
        try {
            ResultApiPokeApi apiPokeApi = apiWS.getTypes();
            if (apiPokeApi == null) {
                return responseApi;
            }
            responseApi.setBRta(true);
            responseApi.setSMsg("List found succesfully");
            responseApi.setData(this.getNameOfDataType(apiPokeApi.getResults()));
            responseApi.setCantData(apiPokeApi.getCount());
        }
        catch (Exception e) {
            responseApi.setBRta(false);
            responseApi.setSMsg("Error to find list pokemon");
        }
        return responseApi;
    }

    @Override
    public ResponseApi apiPokemonFromType(int limit, int offset, String type) {
        ResponseApi<PokemonInfo> responseApi = new ResponseApi();
        try {
            TypeObject apiPokeApi = apiWS.getListPokemonFromType(type);
            if (apiPokeApi == null) {
                return responseApi;
            }
            responseApi.setBRta(true);
            responseApi.setSMsg("List found succesfully");
            responseApi.setData(this.mapInfoPokemon(apiPokeApi.getListDataPokemons(limit, offset)));
            responseApi.setCantData(apiPokeApi.getPokemon().size());
        }
        catch (Exception e) {
            responseApi.setBRta(false);
            responseApi.setSMsg("Error to find list pokemon from type");
        }
        return responseApi;
    }

    public List<PokemonInfo> mapInfoPokemon(List<DataInfo> listPokemons) {
        List<PokemonInfo> listInfo = new ArrayList<>();
        listPokemons.forEach(pokemonInfo -> {
            PokemonInfo pokInfo = this.getBasicInfoPokemon(pokemonInfo.getName());
            if (pokInfo != null) {
                listInfo.add(pokInfo);
            }
        });
        return listInfo;
    }

    public PokemonInfo getBasicInfoPokemon(String namePokemon) {
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setName(namePokemon);
        try {
            DetailPokemon basicInformation = apiWS.getInfoPokemon(pokemonInfo.getName());
            if (basicInformation == null) {
                return pokemonInfo;
            }
            List<String> types = new ArrayList<>();
            basicInformation.getTypes().forEach(type -> {
                types.add(type.getType().getName());
            });

            List<String> abilities = new ArrayList<>();
            basicInformation.getAbilities().forEach(abbility -> {
                abilities.add(abbility.getAbility().getName());
            });

            pokemonInfo.setType(types);
            String urlImage = basicInformation.getSprites().getOther().getDream_world().getFront_default();
            if (urlImage == null) {
                urlImage = basicInformation.getSprites().getOther().getHome().getFront_default();
            }
            pokemonInfo.setUrlImage(urlImage);
            pokemonInfo.setAbilities(abilities);
            pokemonInfo.setWeight(basicInformation.getWeight());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return pokemonInfo;
    }

    public List<String> getNameOfDataType(List<DataInfo> listData) {
        List<String> listInfo = new ArrayList<>();
        listData.forEach(data -> {
            listInfo.add(data.getName());
        });
        return listInfo;
    }

    public DetailPokemon getDescriptionPokemon(DetailPokemon detail) {
        try {
            DescriptionPokemon descPokemon = apiWS.getDescriptionPokemon(detail.getName());
            if (descPokemon == null) {
                return detail;
            }

            String descriptionEnglish = descPokemon.getFlavor_text_entries()
                    .stream()
                    .filter(c -> c.getLanguage().getName().equals("en"))
                    .collect(Collectors.toList()).get(0).getFlavor_text();

            detail.setDescription(descriptionEnglish);
            detail = this.getEvolutionsPokemon(detail, descPokemon);
        }
        catch (Exception e) {
        }
        return detail;
    }

    public DetailPokemon getEvolutionsPokemon(DetailPokemon detail, DescriptionPokemon descriptionPokemon) {
        try {
            EvolutionResponse descPokemon = apiWS.getEvolutionsPokemon(descriptionPokemon.getEvolution_chain().getUrl());
            if (descPokemon == null) {
                return detail;
            }
            List<PokemonInfo> evolutions = new ArrayList<>();
            evolutions.add(this.getBasicInfoPokemon(descPokemon.getChain().getSpecies().getName()));
            descPokemon.getChain().getEvolves_to().forEach(evolvesTo -> {
                evolutions.add(this.getBasicInfoPokemon(evolvesTo.getSpecies().getName()));
                DataInfo evolutionPlus = this.getEvolution(evolvesTo);
                if (evolutionPlus != null) {
                    evolutions.add(this.getBasicInfoPokemon(evolutionPlus.getName()));
                }
            });
            detail.setEvolutions(evolutions);
        }
        catch (Exception e) {
        }
        return detail;
    }

    public DataInfo getEvolution(EvolvesTo evolves_to) {

        if (evolves_to.getEvolves_to().isEmpty()) {
            return evolves_to.getSpecies();
        }

        return getEvolution(evolves_to.getEvolves_to().get(0));
    }
}
