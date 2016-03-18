package com.demo.russell.bwsecurity;

import android.util.Log;

/**
 * Created by russell on 28/02/16.
 */
public class Row {
    private Double Amount;
    private Double Payout;
    private Double Expence;
    private boolean Warning;


    public void setAmount(Double amount){
        this.Amount = amount;
        Log.d("Russell", "Row:" + this.Amount);
    }
    public void setPayout(Double payout){
        this.Payout = payout;
    }
    public void setExpence(Double expence){
        this.Expence = expence;
    }
    public void setWarning(boolean warning){
        this.Warning = warning;
    }
    public double getAmount(){
        return this.Amount;
    }
    public double getPayout(){
        return  this.Payout;
    }
    public double getExpence(){
        return  this.Expence;
    }
    public boolean getWarinig(){
        return this.Warning;
    }
    public String getPayoutString(){
        //int intconv = (int) this.Payout;
        return  Payout.intValue()+"";
    }
}
