/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.model.pokeapi;

import lombok.Data;

/**
 *
 * @author ruge2
 */
@Data
public class Sprites {

    private String back_default;
    private Object back_female;
    private String back_shiny;
    private Object back_shiny_female;
    private String front_default;
    private Object front_female;
    private String front_shiny;
    private Object front_shiny_female;
    private Other other;
}
