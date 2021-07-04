/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.util;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hamizan
 */
public class ListConverter {
    
    public static List<Integer> convertToListString (List<Object> listObject){
        List<Integer> listInteger = new ArrayList<>();
        for(Object o : listObject){
            listInteger.add((int) o);
        }
        return listInteger;
    }
}
