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
public class Other {

    private DreamWorld dream_world;
    private Home home;
    private OfficialArtwork officialArtwork;

    public Other() {
    }

    public Other(DreamWorld dream_world) {
        this.dream_world = dream_world;
    }
}
