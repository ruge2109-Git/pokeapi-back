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
public class ResultApiPokeApi {
    public int count;
    public String next;
    public Object previous;
    public ArrayList<DataInfo> results;
}
