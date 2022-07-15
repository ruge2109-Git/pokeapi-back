/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.controller.service.imp;

import com.modyo.pokeapi.controller.service.PokeApiService;
import com.modyo.pokeapi.endpointpokeapi.PokeApiWS;
import com.modyo.pokeapi.model.pokeapi.DataInfo;
import com.modyo.pokeapi.model.pokeapi.ResultApiPokeApi;
import com.modyo.pokeapi.model.response.ResponseApi;
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
        ResponseApi<DataInfo> responseApi = new ResponseApi();
        try {
            ResultApiPokeApi apiPokeApi = apiWS.getListPokemon(limit, offset);
            if (apiPokeApi == null) {
                return responseApi;
            }
            responseApi.setBRta(true);
            responseApi.setSMsg("List found successfully");
            responseApi.setData(apiPokeApi.getResults());
        } catch (Exception e) {
            e.printStackTrace();
            responseApi.setBRta(false);
            responseApi.setSMsg("Error to find list pokemon");
        }
        return responseApi;
    }

}
