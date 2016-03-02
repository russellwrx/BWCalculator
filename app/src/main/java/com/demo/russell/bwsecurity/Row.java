package com.demo.russell.bwsecurity;

import android.util.Log;

/**
 * Created by russell on 28/02/16.
 */
public class Row {
    private double Amount;
    private double Payout;
    private double Expence;
    private boolean Warning;

//    private int notes1k;
//    private int notes50;
//    private int notes20;
//    private int notes10;
//    private int notes5;

    public void setAmount(double amount){
        this.Amount = amount;
        Log.d("Russell", "Row:" + this.Amount);
    }
    public void setPayout(double payout){
        this.Payout = payout;
    }
    public void setExpence(double expence){
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
//    public void setNotes1k(int amount){
//        this.notes1k=amount;
//    }
//    public void setNotes50(int amount){
//        this.notes50=amount;
//    }
//    public void setNotes20(int amount){
//        this.notes20=amount;
//    }
//    public void setNotes10(int amount){
//        this.notes10=amount;
//    }
//    public void setNotes5(int amount){
//        this.notes5=amount;
//    }
//    public int getNotes1k(){
//        return this.notes1k;
//    }
//    public int getNotes50(){
//        return this.notes50;
//    }
//    public int getNotes20(){
//        return this.notes20;
//    }
//    public int getNotes10(){
//        return this.notes10;
//    }
//    public int getNotes5(){
//        return this.notes5;
//    }
}
