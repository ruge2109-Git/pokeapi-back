package com.modyo.pokeapi.controller.service.imp;

import com.modyo.pokeapi.controller.service.PokeApiService;
import com.modyo.pokeapi.endpointpokeapi.PokeApiWS;
import com.modyo.pokeapi.model.pokeapi.DataInfo;
import com.modyo.pokeapi.model.pokeapi.DescriptionPokemon;
import com.modyo.pokeapi.model.pokeapi.DetailPokemon;
import com.modyo.pokeapi.model.pokeapi.ResultApiPokeApi;
import com.modyo.pokeapi.model.pokeapi.TypeObject;
import com.modyo.pokeapi.model.response.PokemonInfo;
import com.modyo.pokeapi.model.response.ResponseApi;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
            detail.setDescription(descPokemon.getFlavor_text_entries().get(0).getFlavor_text());
        }
        catch (Exception e) {
        }
        return detail;
    }
}
