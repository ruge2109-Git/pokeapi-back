/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.endpointpokeapi;

import com.modyo.pokeapi.ConfigProperties;
import com.modyo.pokeapi.model.pokeapi.DetailPokemon;
import com.modyo.pokeapi.model.pokeapi.ResultApiPokeApi;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ruge2
 */
@Component
public class PokeApiWS {
    
    @Autowired
    private ConfigProperties configProperties;
    
    @Autowired(required = false)
    private RestTemplate restTemplate;
    
    private String endPoint;
    private HttpHeaders httpHeaders;
    
    public PokeApiWS() {
    }
    
    @PostConstruct
    public void initUrl() {
        this.endPoint = this.configProperties.getConfigValue("pokeapi.endpoint");
        this.httpHeaders = new HttpHeaders();
        this.httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        this.httpHeaders.add("user-agent", "Application");
    }
    
    @Cacheable("pokemonList")
    public ResultApiPokeApi getListPokemon(int limit, int offset) {
        ResultApiPokeApi resultApi = null;
        try {
            String url = this.endPoint + "pokemon?limit=" + limit + "&offset=" + offset;
            
            HttpEntity<String> entity = new HttpEntity<>(this.httpHeaders);
            
            ResponseEntity<ResultApiPokeApi> responseApi = this.restTemplate.exchange(url, HttpMethod.GET, entity, ResultApiPokeApi.class);
            resultApi = responseApi.getBody();
        } catch (RestClientException e) {
            throw new RestClientException("Error to consume endpoint pokemon list", e);
        }
        return resultApi;
    }
    
    public DetailPokemon getInfoPokemon(String pokemon) {
        DetailPokemon detailPokemon = new DetailPokemon();
        try {
            
        } catch (Exception e) {
            return null;
        }
        return detailPokemon;
    }
    
}
