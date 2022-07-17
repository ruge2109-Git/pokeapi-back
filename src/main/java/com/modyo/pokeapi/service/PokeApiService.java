/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.service;

import com.modyo.pokeapi.model.response.ResponseApi;

/**
 *
 * @author ruge2
 */
public interface PokeApiService {

    public ResponseApi apiPokeApi(int limit, int offset);

    public ResponseApi apiDetailPokemon(String namePokemon);

    public ResponseApi apiListType();

    public ResponseApi apiPokemonFromType(int limit, int offset,String type);
}
