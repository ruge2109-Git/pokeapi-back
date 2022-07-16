package com.modyo.pokeapi.controller.service.imp;

import com.modyo.pokeapi.controller.service.PokeApiService;
import com.modyo.pokeapi.endpointpokeapi.PokeApiWS;
import com.modyo.pokeapi.model.pokeapi.DataInfo;
import com.modyo.pokeapi.model.pokeapi.DetailPokemon;
import com.modyo.pokeapi.model.pokeapi.ResultApiPokeApi;
import com.modyo.pokeapi.model.response.PokemonInfo;
import com.modyo.pokeapi.model.response.ResponseApi;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<PokemonInfo> mapInfoPokemon(List<DataInfo> listPokemons) {
        List<PokemonInfo> listInfo = new ArrayList<>();
        listPokemons.forEach(pokemonInfo -> {
            listInfo.add(this.getBasicInfoPokemon(pokemonInfo.getName()));
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
            pokemonInfo.setUrlImage(basicInformation.getSprites().getOther().getDream_world().getFront_default());
            pokemonInfo.setAbilities(abilities);
            pokemonInfo.setWeight(basicInformation.getWeight());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return pokemonInfo;
    }
    
}
