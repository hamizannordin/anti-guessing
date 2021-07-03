/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hmzn.app.antiguessing.database;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author hamizan
 */
@Entity
@Table(name = "BETTING")
public class Betting implements Serializable {
    
    @Id
    private BigInteger id;
    private int digit_0;
    private int digit_1;
    private int digit_2;
    private int digit_3;
    private int digit_4;
    private int digit_5;
    private int digit_6;
    private int digit_7;
    private Date insert_datetime;

    public Betting() {
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public int getDigit_0() {
        return digit_0;
    }

    public void setDigit_0(int digit_0) {
        this.digit_0 = digit_0;
    }

    public int getDigit_1() {
        return digit_1;
    }

    public void setDigit_1(int digit_1) {
        this.digit_1 = digit_1;
    }

    public int getDigit_2() {
        return digit_2;
    }

    public void setDigit_2(int digit_2) {
        this.digit_2 = digit_2;
    }

    public int getDigit_3() {
        return digit_3;
    }

    public void setDigit_3(int digit_3) {
        this.digit_3 = digit_3;
    }

    public int getDigit_4() {
        return digit_4;
    }

    public void setDigit_4(int digit_4) {
        this.digit_4 = digit_4;
    }

    public int getDigit_5() {
        return digit_5;
    }

    public void setDigit_5(int digit_5) {
        this.digit_5 = digit_5;
    }

    public int getDigit_6() {
        return digit_6;
    }

    public void setDigit_6(int digit_6) {
        this.digit_6 = digit_6;
    }

    public int getDigit_7() {
        return digit_7;
    }

    public void setDigit_7(int digit_7) {
        this.digit_7 = digit_7;
    }

    public Date getInsert_datetime() {
        return insert_datetime;
    }

    public void setInsert_datetime(Date insert_datetime) {
        this.insert_datetime = insert_datetime;
    }
}
