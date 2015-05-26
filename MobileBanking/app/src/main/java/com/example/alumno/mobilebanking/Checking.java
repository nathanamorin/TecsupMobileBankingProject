package com.example.alumno.mobilebanking;

/**
 * Created by Alumno on 25/05/2015.
 */
public class Checking {
    String acc_num;
    Float balance;

    public Checking(String acc_num, Float balance) {
        this.acc_num = acc_num;
        this.balance = balance;
    }

    public String getAcc_num() {
        return acc_num;
    }

    public Float getBalance() {
        return balance;
    }
}
