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
    public String back_default;
    public Object back_female;
    public String back_shiny;
    public Object back_shiny_female;
    public String front_default;
    public Object front_female;
    public String front_shiny;
    public Object front_shiny_female;
    public Other other;
}
