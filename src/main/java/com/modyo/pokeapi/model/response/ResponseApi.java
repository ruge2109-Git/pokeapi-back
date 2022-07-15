/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modyo.pokeapi.model.response;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author ruge2
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseApi<T> {

    private boolean bRta;
    private String sMsg;
    private List<T> data;

}
