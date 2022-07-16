/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.model.pokeapi;

import java.util.List;
import lombok.Data;

/**
 *
 * @author ruge2
 */
@Data
public class DetailPokemon {
    public List<Ability> abilities;
    public int base_experience;
    public List<DataInfo> forms;
    public List<GameIndex> game_indices;
    public int height;
    public List<HeldItem> held_items;
    public int id;
    public boolean is_default;
    public String location_area_encounters;
    public List<Move> moves;
    public String name;
    public int order;
    public List<Object> past_types;
    public DataInfo species;
    public Sprites sprites;
    public List<Stat> stats;
    public List<Type> types;
    public int weight;
}
