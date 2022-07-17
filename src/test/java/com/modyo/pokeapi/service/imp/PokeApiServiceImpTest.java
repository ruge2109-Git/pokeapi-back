/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.service.imp;

import com.modyo.pokeapi.endpointpokeapi.PokeApiWS;
import com.modyo.pokeapi.model.pokeapi.Ability;
import com.modyo.pokeapi.model.pokeapi.Chain;
import com.modyo.pokeapi.model.pokeapi.DataInfo;
import com.modyo.pokeapi.model.pokeapi.DataInfoTwo;
import com.modyo.pokeapi.model.pokeapi.DescriptionPokemon;
import com.modyo.pokeapi.model.pokeapi.DetailPokemon;
import com.modyo.pokeapi.model.pokeapi.DreamWorld;
import com.modyo.pokeapi.model.pokeapi.EvolutionChain;
import com.modyo.pokeapi.model.pokeapi.EvolutionResponse;
import com.modyo.pokeapi.model.pokeapi.EvolvesTo;
import com.modyo.pokeapi.model.pokeapi.FlavorTextEntry;
import com.modyo.pokeapi.model.pokeapi.Other;
import com.modyo.pokeapi.model.pokeapi.ResultApiPokeApi;
import com.modyo.pokeapi.model.pokeapi.Sprites;
import com.modyo.pokeapi.model.pokeapi.Type;
import com.modyo.pokeapi.model.pokeapi.TypeObject;
import com.modyo.pokeapi.model.response.PokemonInfo;
import com.modyo.pokeapi.model.response.ResponseApi;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author ruge2
 */
@ExtendWith(MockitoExtension.class)
public class PokeApiServiceImpTest {
    
    @Mock
    PokeApiWS apiWS;
    
    @InjectMocks
    private final PokeApiServiceImp pokeApiServiceImp = new PokeApiServiceImp();
    
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void getApiPokeApi(){
        ResponseApi responseApi = this.dataMockResponseApiPokemon();
        ResultApiPokeApi typeObject = this.dataMockApiPokeApi();
        when(apiWS.getListPokemon(8,0)).thenReturn(typeObject);
        
        List<Ability> skills = new ArrayList<>();
        skills.add(new Ability(new DataInfo("SKILL_ONE", "")));
        skills.add(new Ability(new DataInfo("SKILL_TWO", "")));
        
        List<Type> types = new ArrayList<>();
        types.add(new Type(new DataInfo("TYPE_ONE", "")));
        types.add(new Type(new DataInfo("TYPE_TWO", "")));
        Sprites sprite = new Sprites();
        sprite.setOther(new Other(new DreamWorld("URL_IMG")));
        DetailPokemon detailPokemonMock = new DetailPokemon();
        detailPokemonMock.setName("charmander");
        detailPokemonMock.setAbilities(skills);
        detailPokemonMock.setTypes(types);
        detailPokemonMock.setSprites(sprite);
        detailPokemonMock.setWeight(80);
        DetailPokemon detailPokemonMock2 = new DetailPokemon();
        detailPokemonMock2.setName("charmeleon");
        detailPokemonMock2.setAbilities(skills);
        detailPokemonMock2.setTypes(types);
        detailPokemonMock2.setSprites(sprite);
        detailPokemonMock2.setWeight(80);
        DetailPokemon detailPokemonMock3 = new DetailPokemon();
        detailPokemonMock3.setName("charizard");
        detailPokemonMock3.setAbilities(skills);
        detailPokemonMock3.setTypes(types);
        detailPokemonMock3.setSprites(sprite);
        detailPokemonMock3.setWeight(80);
        when(apiWS.getInfoPokemon("charmander")).thenReturn(detailPokemonMock);
        when(apiWS.getInfoPokemon("charmeleon")).thenReturn(detailPokemonMock2);
        when(apiWS.getInfoPokemon("charizard")).thenReturn(detailPokemonMock3);
        assertEquals(responseApi, pokeApiServiceImp.apiPokeApi(8, 0));
    }
    
    @Test
    public void getApiDetailPokemon(){
        ResponseApi responseApi = this.dataMockResponseDetailPokemon();
        DetailPokemon resultMockApi = this.dataMockDetailPokemon();
        when(apiWS.getInfoPokemon("charmander")).thenReturn(resultMockApi);
        assertEquals(responseApi, pokeApiServiceImp.apiDetailPokemon("charmander"));
    }
    
    @Test
    public void getApiListType() {
        ResponseApi responseApi = this.dataMockApiTypes();
        ResultApiPokeApi resultMockApi = this.dataMockTypes();
        when(apiWS.getTypes()).thenReturn(resultMockApi);
        assertEquals(responseApi, pokeApiServiceImp.apiListType());
    }
    
    @Test
    public void getApiPokemonFromType() {
        ResponseApi responseApi = this.dataMockResponseApiPokemon();
        TypeObject typeObject = this.dataMockTypeObject();
        when(apiWS.getListPokemonFromType("normal")).thenReturn(typeObject);
        
        List<Ability> skills = new ArrayList<>();
        skills.add(new Ability(new DataInfo("SKILL_ONE", "")));
        skills.add(new Ability(new DataInfo("SKILL_TWO", "")));
        
        List<Type> types = new ArrayList<>();
        types.add(new Type(new DataInfo("TYPE_ONE", "")));
        types.add(new Type(new DataInfo("TYPE_TWO", "")));
        Sprites sprite = new Sprites();
        sprite.setOther(new Other(new DreamWorld("URL_IMG")));
        DetailPokemon detailPokemonMock = new DetailPokemon();
        detailPokemonMock.setName("charmander");
        detailPokemonMock.setAbilities(skills);
        detailPokemonMock.setTypes(types);
        detailPokemonMock.setSprites(sprite);
        detailPokemonMock.setWeight(80);
        DetailPokemon detailPokemonMock2 = new DetailPokemon();
        detailPokemonMock2.setName("charmeleon");
        detailPokemonMock2.setAbilities(skills);
        detailPokemonMock2.setTypes(types);
        detailPokemonMock2.setSprites(sprite);
        detailPokemonMock2.setWeight(80);
        DetailPokemon detailPokemonMock3 = new DetailPokemon();
        detailPokemonMock3.setName("charizard");
        detailPokemonMock3.setAbilities(skills);
        detailPokemonMock3.setTypes(types);
        detailPokemonMock3.setSprites(sprite);
        detailPokemonMock3.setWeight(80);
        when(apiWS.getInfoPokemon("charmander")).thenReturn(detailPokemonMock);
        when(apiWS.getInfoPokemon("charmeleon")).thenReturn(detailPokemonMock2);
        when(apiWS.getInfoPokemon("charizard")).thenReturn(detailPokemonMock3);
        assertEquals(responseApi, pokeApiServiceImp.apiPokemonFromType(0, 3, "normal"));
    }
    
    @Test
    public void getMapInfoPokemonIsSuccesful() {
        List<PokemonInfo> listSpy = new ArrayList<>();
        listSpy.add(this.dataPokemonInfo("charmander"));
        listSpy.add(this.dataPokemonInfo("charmeleon"));
        listSpy.add(this.dataPokemonInfo("charizard"));
        
        List<DataInfo> listParam = new ArrayList<>();
        listParam.add(new DataInfo("charmander", "url_data_1"));
        listParam.add(new DataInfo("charmeleon", "url_data_2"));
        listParam.add(new DataInfo("charizard", "url_data_3"));
        
        List<Ability> skills = new ArrayList<>();
        skills.add(new Ability(new DataInfo("SKILL_ONE", "")));
        skills.add(new Ability(new DataInfo("SKILL_TWO", "")));
        
        List<Type> types = new ArrayList<>();
        types.add(new Type(new DataInfo("TYPE_ONE", "")));
        types.add(new Type(new DataInfo("TYPE_TWO", "")));
        
        Sprites sprite = new Sprites();
        sprite.setOther(new Other(new DreamWorld("URL_IMG")));
        DetailPokemon detailPokemonMock = new DetailPokemon();
        detailPokemonMock.setName("charmander");
        detailPokemonMock.setAbilities(skills);
        detailPokemonMock.setTypes(types);
        detailPokemonMock.setSprites(sprite);
        detailPokemonMock.setWeight(80);
        DetailPokemon detailPokemonMock2 = new DetailPokemon();
        detailPokemonMock2.setName("charmeleon");
        detailPokemonMock2.setAbilities(skills);
        detailPokemonMock2.setTypes(types);
        detailPokemonMock2.setSprites(sprite);
        detailPokemonMock2.setWeight(80);
        DetailPokemon detailPokemonMock3 = new DetailPokemon();
        detailPokemonMock3.setName("charizard");
        detailPokemonMock3.setAbilities(skills);
        detailPokemonMock3.setTypes(types);
        detailPokemonMock3.setSprites(sprite);
        detailPokemonMock3.setWeight(80);
        
        when(apiWS.getInfoPokemon("charmander")).thenReturn(detailPokemonMock);
        when(apiWS.getInfoPokemon("charmeleon")).thenReturn(detailPokemonMock2);
        when(apiWS.getInfoPokemon("charizard")).thenReturn(detailPokemonMock3);
        assertEquals(listSpy, pokeApiServiceImp.mapInfoPokemon(listParam));
    }
    
    @Test
    public void getBasicInfoPokemonIsSuccesful() {
        List<Ability> skills = new ArrayList<>();
        skills.add(new Ability(new DataInfo("SKILL_ONE", "")));
        skills.add(new Ability(new DataInfo("SKILL_TWO", "")));
        
        List<Type> types = new ArrayList<>();
        types.add(new Type(new DataInfo("TYPE_ONE", "")));
        types.add(new Type(new DataInfo("TYPE_TWO", "")));
        
        Sprites sprite = new Sprites();
        sprite.setOther(new Other(new DreamWorld("URL_IMG")));
        
        DetailPokemon detailPokemonMock = new DetailPokemon();
        detailPokemonMock.setName("charmander");
        detailPokemonMock.setAbilities(skills);
        detailPokemonMock.setTypes(types);
        detailPokemonMock.setSprites(sprite);
        detailPokemonMock.setWeight(80);
        
        when(apiWS.getInfoPokemon("charmander")).thenReturn(detailPokemonMock);
        PokemonInfo hopeSpy = this.dataPokemonInfo("charmander");
        assertEquals(hopeSpy, pokeApiServiceImp.getBasicInfoPokemon("charmander"));
    }
    
    @Test
    public void getNameOfDataTypeIsSuccessful() {
        ArrayList<DataInfo> skillsDataInfo = new ArrayList<>();
        skillsDataInfo.add(new DataInfo("SKILL_ONE", ""));
        skillsDataInfo.add(new DataInfo("SKILL_TWO", ""));
        
        List<String> expectList = new ArrayList<>();
        expectList.add("SKILL_ONE");
        expectList.add("SKILL_TWO");
        assertEquals(expectList, pokeApiServiceImp.getNameOfDataType(skillsDataInfo));
        
    }
    
    @Test
    public void getEvolutionOfOnePokemon() {
        DataInfo dataThree = new DataInfo("charizard", "");
        EvolvesTo mockDataOne = this.dataMockEvolvesTo();
        assertEquals(dataThree, pokeApiServiceImp.getEvolution(mockDataOne));
    }
    
    @Test
    public void getDescriptionPokemonInEnglishIsSuccesful() {
        DetailPokemon detailPokemon = this.dataMockDetailPokemon();
        DescriptionPokemon descriptionPokemon = this.dataMockDescriptionPokemon();
        when(apiWS.getDescriptionPokemon("charmander")).thenReturn(descriptionPokemon);
        assertEquals("English description", pokeApiServiceImp.getDescriptionPokemon(detailPokemon).getDescription());
    }
    
    @Test
    public void getEvolutionsPokemonIsSuccesful() {
        DetailPokemon detailPokemon = this.dataMockDetailPokemon();
        DescriptionPokemon descriptionPokemon = this.dataMockDescriptionPokemon();
        EvolutionResponse evolutionResponse = this.dataMockEvolutionResponse();
        when(apiWS.getEvolutionsPokemon("https://pokeapi.co/api/v2/evolution-chain/2/")).thenReturn(evolutionResponse);
        assertEquals(3, pokeApiServiceImp.getEvolutionsPokemon(detailPokemon, descriptionPokemon).getEvolutions().size());
    }

    // ***********************
    // Mocks
    // ***********************
    public PokemonInfo dataPokemonInfo(String namePokemon) {
        ArrayList<String> skills = new ArrayList<>();
        skills.add("SKILL_ONE");
        skills.add("SKILL_TWO");
        
        ArrayList<String> types = new ArrayList<>();
        types.add("TYPE_ONE");
        types.add("TYPE_TWO");
        
        PokemonInfo pokemonInfo = new PokemonInfo();
        pokemonInfo.setName(namePokemon);
        pokemonInfo.setAbilities(skills);
        pokemonInfo.setType(types);
        pokemonInfo.setWeight(80);
        pokemonInfo.setUrlImage("URL_IMG");
        return pokemonInfo;
    }
    
    public EvolvesTo dataMockEvolvesTo() {
        DataInfo dataOne = new DataInfo("charmander", "");
        DataInfo dataTwo = new DataInfo("charmeleon", "");
        DataInfo dataThree = new DataInfo("charizard", "");
        
        EvolvesTo mockDataOne = new EvolvesTo();
        mockDataOne.setSpecies(dataOne);
        mockDataOne.set_baby(false);
        
        EvolvesTo mockDataTwo = new EvolvesTo();
        mockDataTwo.setSpecies(dataTwo);
        mockDataTwo.set_baby(false);
        
        EvolvesTo mockDataThree = new EvolvesTo();
        mockDataThree.setSpecies(dataThree);
        mockDataThree.set_baby(false);
        
        ArrayList<EvolvesTo> evolutionDataTwo = new ArrayList<>();
        evolutionDataTwo.add(mockDataThree);
        mockDataTwo.setEvolves_to(evolutionDataTwo);
        
        ArrayList<EvolvesTo> evolutionDataOne = new ArrayList<>();
        evolutionDataOne.add(mockDataTwo);
        mockDataOne.setEvolves_to(evolutionDataOne);
        return mockDataOne;
    }
    
    public DetailPokemon dataMockDetailPokemon() {
        DetailPokemon detailPokemon = new DetailPokemon();
        detailPokemon.setName("charmander");
        detailPokemon.setHeight(60);
        detailPokemon.setWeight(85);
        return detailPokemon;
    }
    
    public DescriptionPokemon dataMockDescriptionPokemon() {
        DescriptionPokemon descriptionPokemon = new DescriptionPokemon();
        
        ArrayList<FlavorTextEntry> flavor_text_entries = new ArrayList<>();
        FlavorTextEntry flavorEn = new FlavorTextEntry("English description", new DataInfo("en", "url"));
        FlavorTextEntry flavorEs = new FlavorTextEntry("Español descripción", new DataInfo("es", "url"));
        flavor_text_entries.add(flavorEs);
        flavor_text_entries.add(flavorEn);
        descriptionPokemon.setFlavor_text_entries(flavor_text_entries);
        
        descriptionPokemon.setEvolution_chain(new EvolutionChain("https://pokeapi.co/api/v2/evolution-chain/2/"));
        
        return descriptionPokemon;
    }
    
    public EvolutionResponse dataMockEvolutionResponse() {
        ArrayList<EvolvesTo> listEvolutions = new ArrayList<>();
        listEvolutions.add(this.dataMockEvolvesTo());
        EvolutionResponse evolutionResponse = new EvolutionResponse(new Chain(listEvolutions, new DataInfo("charmander", "URL")));
        return evolutionResponse;
    }
    
    public ResponseApi dataMockResponseApiPokemon() {
        ResponseApi<PokemonInfo> responseApi = new ResponseApi();
        responseApi.setBRta(true);
        responseApi.setSMsg("List found succesfully");
        List<PokemonInfo> listSpy = new ArrayList<>();
        listSpy.add(this.dataPokemonInfo("charmander"));
        listSpy.add(this.dataPokemonInfo("charmeleon"));
        listSpy.add(this.dataPokemonInfo("charizard"));
        responseApi.setData(listSpy);
        responseApi.setCantData(listSpy.size());
        
        return responseApi;
    }
    
    public ResponseApi dataMockApiTypes() {
        ResponseApi<String> responseApi = new ResponseApi();
        responseApi.setBRta(true);
        responseApi.setSMsg("List found succesfully");
        
        List<String> listTypes = new ArrayList<>();
        listTypes.add("fire");
        listTypes.add("normal");
        responseApi.setData(listTypes);
        responseApi.setCantData(listTypes.size());
        return responseApi;
    }
    
    public TypeObject dataMockTypeObject() {
        TypeObject typeObject = new TypeObject();
        
        List<DataInfoTwo> pokemon = new ArrayList<>();
        pokemon.add(new DataInfoTwo(new DataInfo("charmander", "URL_1")));
        pokemon.add(new DataInfoTwo(new DataInfo("charmeleon", "URL_2")));
        pokemon.add(new DataInfoTwo(new DataInfo("charizard", "URL_3")));
        typeObject.setPokemon(pokemon);
        
        return typeObject;
    }
    
    public ResultApiPokeApi dataMockTypes() {
        ResultApiPokeApi apiPokeApi = new ResultApiPokeApi();
        apiPokeApi.setCount(2);
        apiPokeApi.setNext("");
        apiPokeApi.setPrevious("");
        ArrayList<DataInfo> listTypes = new ArrayList<>();
        listTypes.add(new DataInfo("fire", "URL_FIRE"));
        listTypes.add(new DataInfo("normal", "URL_NORMAL"));
        apiPokeApi.setResults(listTypes);
        return apiPokeApi;
    }
    
    public ResultApiPokeApi dataMockApiPokeApi(){
        ResultApiPokeApi apiPokeApi = new ResultApiPokeApi();
        apiPokeApi.setCount(3);
        apiPokeApi.setNext("");
        apiPokeApi.setPrevious("");
        ArrayList<DataInfo> listTypes = new ArrayList<>();
        listTypes.add(new DataInfo("charmander", "URL_IMG"));
        listTypes.add(new DataInfo("charmeleon", "URL_IMG"));
        listTypes.add(new DataInfo("charizard", "URL_IMG"));
        apiPokeApi.setResults(listTypes);
        return apiPokeApi;
    }
    
    public ResponseApi dataMockResponseDetailPokemon() {
        ResponseApi<DetailPokemon> responseApi = new ResponseApi();
        List<DetailPokemon> listDetail = new ArrayList<>();
        listDetail.add(this.dataMockDetailPokemon());
        responseApi.setBRta(true);
        responseApi.setSMsg("List found succesfully");
        responseApi.setData(listDetail);
        return responseApi;
    }
}
