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
public class FlavorTextEntry {

    private String flavor_text;
    private DataInfo language;

    public FlavorTextEntry() {
    }

    public FlavorTextEntry(String flavor_text, DataInfo language) {
        this.flavor_text = flavor_text;
        this.language = language;
    }
}
