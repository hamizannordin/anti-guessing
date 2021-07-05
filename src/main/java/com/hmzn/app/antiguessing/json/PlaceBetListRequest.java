/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.json;

import java.util.List;

/**
 * JSON structure for creating new bet list
 * 
 * @author hamizan
 */
public class PlaceBetListRequest {
    
    private String roundId;
    private List<String> combination;

    public String getRoundId() {
        return roundId;
    }

    public void setRoundId(String roundId) {
        this.roundId = roundId;
    }

    public List<String> getCombination() {
        return combination;
    }

    public void setCombination(List<String> combination) {
        this.combination = combination;
    }
}
