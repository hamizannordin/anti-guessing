/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.database;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hamizan
 */
@Entity
@Table(name = "ROUND")
public class Round implements Serializable {
    
    @Id
    private BigInteger round_id;
    private String round_name;
    private boolean status;

    public Round() {
    }

    public BigInteger getRound_id() {
        return round_id;
    }

    public void setRound_id(BigInteger round_id) {
        this.round_id = round_id;
    }

    public String getRound_name() {
        return round_name;
    }

    public void setRound_name(String round_name) {
        this.round_name = round_name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
