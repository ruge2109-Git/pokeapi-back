/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.model.pokeapi;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 *
 * @author ruge2
 */
@Data
public class TypeObject {

    private List<DataInfoTwo> pokemon;

    public List<DataInfo> getListDataPokemons(int limit, int offset) {
        List<DataInfoTwo> newData = pokemon.subList(limit, offset);
        List<DataInfo> listRta = new ArrayList<>();
        newData.forEach(dataInfo -> {
            listRta.add(dataInfo.getPokemon());
        });
        return listRta;
    }
}
