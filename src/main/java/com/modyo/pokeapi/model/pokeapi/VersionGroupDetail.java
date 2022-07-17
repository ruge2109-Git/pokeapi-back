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
public class VersionGroupDetail {

    private int level_learned_at;
    private DataInfo move_learn_method;
    private DataInfo version_group;
}
