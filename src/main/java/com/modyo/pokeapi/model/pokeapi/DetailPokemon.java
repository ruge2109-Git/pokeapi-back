/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.model.pokeapi;

import com.modyo.pokeapi.model.response.PokemonInfo;
import java.util.List;
import lombok.Data;

/**
 *
 * @author ruge2
 */
@Data
public class DetailPokemon {

    private List<Ability> abilities;
    private int base_experience;
    private int height;
    private List<HeldItem> held_items;
    private int id;
    private boolean is_default;
    private String location_area_encounters;
    private String name;
    private int order;
    private List<Object> past_types;
    private DataInfo species;
    private Sprites sprites;
    private List<Stat> stats;
    private List<Type> types;
    private int weight;
    private String description;
    private List<PokemonInfo> evolutions;
}
