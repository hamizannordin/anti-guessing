/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Contain method use by Betting and Round DAO
 * 
 * @author hamizan
 */
public class ListConverter {
    
    /**
     * Use to convert result list from database to an array list with int type
     * @param listObject from DAO
     * @return array list with int type
     */
    public static List<Integer> convertToListString (List<Object> listObject){
        List<Integer> listInteger = new ArrayList<>();
        for(Object o : listObject){
            listInteger.add((int) o);
        }
        return listInteger;
    }
}
