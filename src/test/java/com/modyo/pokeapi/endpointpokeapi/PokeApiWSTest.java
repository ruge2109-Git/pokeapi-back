/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.endpointpokeapi;

import com.modyo.pokeapi.ConfigProperties;
import com.modyo.pokeapi.model.pokeapi.DescriptionPokemon;
import com.modyo.pokeapi.model.pokeapi.DetailPokemon;
import com.modyo.pokeapi.model.pokeapi.EvolutionResponse;
import com.modyo.pokeapi.model.pokeapi.ResultApiPokeApi;
import com.modyo.pokeapi.model.pokeapi.TypeObject;
import java.lang.reflect.InvocationTargetException;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author ruge2
 */
@ExtendWith(MockitoExtension.class)
public class PokeApiWSTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    ConfigProperties configProperties;

    @InjectMocks
    private final PokeApiWS pokeApiWS = new PokeApiWS();

    private final HttpHeaders httpHeaders = new HttpHeaders();
    private HttpEntity<String> entity;

    @Before
    public void init() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("user-agent", "Application");
        entity = new HttpEntity<>(this.httpHeaders);
        when(configProperties.getConfigValue("pokeapi.endpoint")).thenReturn("https://pokeapi.co/api/v2/");
        this.pokeApiWS.initUrl();
    }

    @Test
    public void getListPokemonIsSuccessful() {
        ResultApiPokeApi pokeMock = mock(ResultApiPokeApi.class);
        ResponseEntity reMock = mock(ResponseEntity.class);
        when(reMock.getBody()).thenReturn(pokeMock);
        when(restTemplate.exchange("https://pokeapi.co/api/v2/pokemon?limit=8&offset=0", HttpMethod.GET, entity, ResultApiPokeApi.class)).thenReturn(reMock);
        assertEquals(reMock.getBody(), pokeApiWS.getListPokemon(8, 0));
    }

    @Test
    public void getInfoPokemonIsSuccessful() {
        DetailPokemon pokeMock = mock(DetailPokemon.class);
        ResponseEntity reMock = mock(ResponseEntity.class);
        when(reMock.getBody()).thenReturn(pokeMock);
        when(restTemplate.exchange("https://pokeapi.co/api/v2/pokemon/charmander", HttpMethod.GET, entity, DetailPokemon.class)).thenReturn(reMock);
        assertEquals(reMock.getBody(), pokeApiWS.getInfoPokemon("charmander"));
    }

    @Test
    public void getTypesIsSuccessful() {
        ResultApiPokeApi pokeMock = mock(ResultApiPokeApi.class);
        ResponseEntity reMock = mock(ResponseEntity.class);
        when(reMock.getBody()).thenReturn(pokeMock);
        when(restTemplate.exchange("https://pokeapi.co/api/v2/type", HttpMethod.GET, entity, ResultApiPokeApi.class)).thenReturn(reMock);
        assertEquals(reMock.getBody(), pokeApiWS.getTypes());
    }

    @Test
    public void getListPokemonFromTypeIsSuccessful() { 
        TypeObject pokeMock = mock(TypeObject.class);
        ResponseEntity reMock = mock(ResponseEntity.class);
        when(reMock.getBody()).thenReturn(pokeMock);
        when(restTemplate.exchange("https://pokeapi.co/api/v2/type/normal", HttpMethod.GET, entity, TypeObject.class)).thenReturn(reMock);
        assertEquals(reMock.getBody(), pokeApiWS.getListPokemonFromType("normal"));
    }

    @Test
    public void getDescriptionPokemonIsSuccessful() {
        DescriptionPokemon pokeMock = mock(DescriptionPokemon.class);
        ResponseEntity reMock = mock(ResponseEntity.class);
        when(reMock.getBody()).thenReturn(pokeMock);
        when(restTemplate.exchange("https://pokeapi.co/api/v2/pokemon-species/charmander", HttpMethod.GET, entity, DescriptionPokemon.class)).thenReturn(reMock);
        assertEquals(reMock.getBody(), pokeApiWS.getDescriptionPokemon("charmander"));
    }

    @Test
    public void getEvolutionsPokemonIsSuccessful() {
        EvolutionResponse pokeMock = mock(EvolutionResponse.class);
        ResponseEntity reMock = mock(ResponseEntity.class);
        when(reMock.getBody()).thenReturn(pokeMock);
        when(restTemplate.exchange("https://pokeapi.co/api/v2/evolution-chain/349/", HttpMethod.GET, entity, EvolutionResponse.class)).thenReturn(reMock);
        assertEquals(reMock.getBody(), pokeApiWS.getEvolutionsPokemon("https://pokeapi.co/api/v2/evolution-chain/349/"));
    }
}
