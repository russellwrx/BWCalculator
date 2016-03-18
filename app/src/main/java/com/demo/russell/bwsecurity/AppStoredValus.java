package com.demo.russell.bwsecurity;

/**
 * Created by russell on 18/03/2016.
 */
public class AppStoredValus {
    private Integer notes1k=30;
    private Integer notes20=20;
    private  Integer notes10=10;
    private Integer notes5=10;
    private Integer floatAmount = getFloatAmout();



    public Integer getFloatAmout() {
        makeTotal();
        return floatAmount;
    }

    public Integer getNotes1k() {
        return notes1k;
    }

    public void setNotes1k(Integer notes1k) {
        this.notes1k = notes1k;
    //    makeTotal();
    }

    public Integer getNotes20() {
        return notes20;
    }

    public void setNotes20(Integer notes20) {
        this.notes20 = notes20;
     //   makeTotal();
    }

    public Integer getNotes10() {
        return this.notes10;
    }

    public void setNotes10(Integer notes10) {
        this.notes10 = notes10;
     //   makeTotal();
    }

    public Integer getNotes5() {
        return notes5;
    }

    public void setNotes5(Integer notes5) {
        this.notes5 = notes5;
     //   makeTotal();
    }

    private void makeTotal(){
        this.floatAmount=this.notes1k*1000+this.notes20*20+this.notes5*5+this.notes10*10;
    }
}

