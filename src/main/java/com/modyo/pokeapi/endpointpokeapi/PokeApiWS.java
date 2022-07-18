package com.modyo.pokeapi.endpointpokeapi;

import com.modyo.pokeapi.ConfigProperties;
import com.modyo.pokeapi.model.pokeapi.DescriptionPokemon;
import com.modyo.pokeapi.model.pokeapi.DetailPokemon;
import com.modyo.pokeapi.model.pokeapi.EvolutionResponse;
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

    @Autowired
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
            if (responseApi == null) {
                return null;
            }
            resultApi = responseApi.getBody();
        }
        catch (RestClientException e) {
            throw new RestClientException("Error to consume endpoint pokemon list", e);
        }
        catch (NullPointerException e) {
            throw new NullPointerException("Error to consume endpoint pokemon list " + e.getLocalizedMessage());
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
            if (responseApi == null) {
                return null;
            }
            detailPokemon = responseApi.getBody();
        }
        catch (RestClientException e) {
            throw new RestClientException("Error to consume endpoint pokemon list", e);
        }
        catch (NullPointerException e) {
            throw new NullPointerException("Error to consume endpoint pokemon list " + e.getLocalizedMessage());
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
            if (responseApi == null) {
                return null;
            }
            resultApi = responseApi.getBody();
        }
        catch (RestClientException e) {
            throw new RestClientException("Error to consume endpoint type list", e);
        }
        catch (NullPointerException e) {
            throw new NullPointerException("Error to consume endpoint pokemon list " + e.getLocalizedMessage());
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
            if (responseApi == null) {
                return null;
            }
            resultApi = responseApi.getBody();
        }
        catch (RestClientException e) {
            throw new RestClientException("Error to consume endpoint pokemon type list", e);
        }
        catch (NullPointerException e) {
            throw new NullPointerException("Error to consume endpoint pokemon list " + e.getLocalizedMessage());
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
            if (responseApi == null) {
                return null;
            }
            resultApi = responseApi.getBody();
        }
        catch (RestClientException e) {
            throw new RestClientException("Error to consume endpoint description pokemon", e);
        }
        catch (NullPointerException e) {
            throw new NullPointerException("Error to consume endpoint pokemon list " + e.getLocalizedMessage());
        }
        return resultApi;
    }

    @Cacheable("EvolutionPokemon")
    public EvolutionResponse getEvolutionsPokemon(String urlEvolution) {
        EvolutionResponse resultApi = null;
        try {
            String url = urlEvolution;

            HttpEntity<String> entity = new HttpEntity<>(this.httpHeaders);

            ResponseEntity<EvolutionResponse> responseApi = this.restTemplate.exchange(url, HttpMethod.GET, entity, EvolutionResponse.class);
            if (responseApi == null) {
                return null;
            }
            resultApi = responseApi.getBody();
        }
        catch (RestClientException e) {
            throw new RestClientException("Error to consume endpoint evolution pokemon", e);
        }
        catch (NullPointerException e) {
            throw new NullPointerException("Error to consume endpoint pokemon list " + e.getLocalizedMessage());
        }
        return resultApi;
    }
}
