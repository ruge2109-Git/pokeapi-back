package com.modyo.pokeapi.endpointpokeapi;

import com.modyo.pokeapi.ConfigProperties;
import com.modyo.pokeapi.model.pokeapi.DescriptionPokemon;
import com.modyo.pokeapi.model.pokeapi.DetailPokemon;
import com.modyo.pokeapi.model.pokeapi.ResultApiPokeApi;
import com.modyo.pokeapi.model.pokeapi.TypeObject;
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
        }
        catch (RestClientException e) {
            throw new RestClientException("Error to consume endpoint pokemon list", e);
        }
        return resultApi;
    }

    @Cacheable("pokemonDetails")
    public DetailPokemon getInfoPokemon(String pokemon) {
        DetailPokemon detailPokemon = null;
        try {
            String url = this.endPoint + "pokemon/" + pokemon;

            HttpEntity<String> entity = new HttpEntity<>(this.httpHeaders);

            ResponseEntity<DetailPokemon> responseApi = this.restTemplate.exchange(url, HttpMethod.GET, entity, DetailPokemon.class);
            detailPokemon = responseApi.getBody();
        }
        catch (RestClientException e) {
            throw new RestClientException("Error to consume endpoint pokemon list", e);
        }
        return detailPokemon;
    }

    @Cacheable("pokemonTypes")
    public ResultApiPokeApi getTypes() {
        ResultApiPokeApi resultApi = null;
        try {
            String url = this.endPoint + "type";

            HttpEntity<String> entity = new HttpEntity<>(this.httpHeaders);

            ResponseEntity<ResultApiPokeApi> responseApi = this.restTemplate.exchange(url, HttpMethod.GET, entity, ResultApiPokeApi.class);
            resultApi = responseApi.getBody();
        }
        catch (RestClientException e) {
            throw new RestClientException("Error to consume endpoint type list", e);
        }
        return resultApi;
    }

    @Cacheable("ListPokemonTypes")
    public TypeObject getListPokemonFromType(String type) {
        TypeObject resultApi = null;
        try {
            String url = this.endPoint + "type/" + type;

            HttpEntity<String> entity = new HttpEntity<>(this.httpHeaders);

            ResponseEntity<TypeObject> responseApi = this.restTemplate.exchange(url, HttpMethod.GET, entity, TypeObject.class);
            resultApi = responseApi.getBody();
        }
        catch (RestClientException e) { 
            throw new RestClientException("Error to consume endpoint pokemon type list", e);
        }
        return resultApi;
    }
    
    @Cacheable("DescriptionPokemon")
    public DescriptionPokemon getDescriptionPokemon(String pokemon) {
        DescriptionPokemon resultApi = null;
        try {
            String url = this.endPoint + "pokemon-species/" + pokemon;

            HttpEntity<String> entity = new HttpEntity<>(this.httpHeaders);

            ResponseEntity<DescriptionPokemon> responseApi = this.restTemplate.exchange(url, HttpMethod.GET, entity, DescriptionPokemon.class);
            resultApi = responseApi.getBody();
        }
        catch (RestClientException e) { 
            throw new RestClientException("Error to consume endpoint description pokemon", e);
        }
        return resultApi;
    }
    
    @Cacheable("EvolutionPokemon")
    public DescriptionPokemon getEvolutionsPokemon(String urlEvolution) {
        DescriptionPokemon resultApi = null;
        try {
            String url = urlEvolution;

            HttpEntity<String> entity = new HttpEntity<>(this.httpHeaders);

            ResponseEntity<DescriptionPokemon> responseApi = this.restTemplate.exchange(url, HttpMethod.GET, entity, DescriptionPokemon.class);
            resultApi = responseApi.getBody();
        }
        catch (RestClientException e) { 
            throw new RestClientException("Error to consume endpoint evolution pokemon", e);
        }
        return resultApi;
    }
}
