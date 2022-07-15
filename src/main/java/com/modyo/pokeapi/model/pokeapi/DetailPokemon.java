/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.model.pokeapi;

import java.util.ArrayList;
import lombok.Data;

/**
 *
 * @author ruge2
 */
@Data
public class DetailPokemon {
    public ArrayList<Ability> abilities;
    public int base_experience;
    public ArrayList<DataInfo> forms;
    public ArrayList<GameIndex> game_indices;
    public int height;
    public ArrayList<HeldItem> held_items;
    public int id;
    public boolean is_default;
    public String location_area_encounters;
    public ArrayList<Move> moves;
    public String name;
    public int order;
    public ArrayList<Object> past_types;
    public DataInfo species;
    public Sprites sprites;
    public ArrayList<Stat> stats;
    public ArrayList<Type> types;
    public int weight;
}
