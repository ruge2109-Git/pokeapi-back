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
public class DescriptionPokemon {

    private int base_happiness;
    private int capture_rate;
    private EvolutionChain evolution_chain;
    private ArrayList<FlavorTextEntry> flavor_text_entries;
    private ArrayList<Object> form_descriptions;
    private boolean forms_switchable;
    private int gender_rate;
    private boolean has_gender_differences;
    private int hatch_counter;
    private int id;
    private boolean is_baby;
    private boolean is_legendary;
    private boolean is_mythical;
    private String name;
    private int order;
}
