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
public class DreamWorld {

    private String front_default;
    private Object front_female;

    public DreamWorld() {
    }

    public DreamWorld(String front_default) {
        this.front_default = front_default;
    }
}
