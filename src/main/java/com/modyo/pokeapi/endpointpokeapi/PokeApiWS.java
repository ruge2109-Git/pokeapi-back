/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.endpointpokeapi;

import com.modyo.pokeapi.ConfigProperties;
import com.modyo.pokeapi.model.pokeapi.ResultApiPokeApi;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ruge2
 */
public class PokeApiWS {

    @Autowired
    private ConfigProperties configProperties;
    
    @Autowired(required = false)
    private RestTemplate restTemplate;
    
    private String endPoint;
    
    public PokeApiWS(){}
    
    @PostConstruct
    public void initUrl(){
        this.endPoint = this.configProperties.getConfigValue("pokeapi.endpoint");
    }
    
    public ResultApiPokeApi getListPokemon(int limit, int finish){
        ResultApiPokeApi resultApi = new ResultApiPokeApi();
        return resultApi;
    }
    
    public void getInfoPokemon(String pokemon){
        
    }
    
}
