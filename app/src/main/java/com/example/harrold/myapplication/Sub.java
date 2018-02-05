package com.example.harrold.myapplication;

import java.util.Date;

/**
 * Created by Harrold on 2018-02-03.
 */

public class Sub {

    private String name;
    private String dateCreated;
    private String costMonthly;
    private String comment;

    public Sub(String name, String date, String costMonthly, String comment) {
        this.name = name;
        this.dateCreated = date;
        this.costMonthly = costMonthly;
        this.comment = comment;
    }

    public Sub(String name, String date, String costMonthly) {
        this.name = name;
        this.dateCreated = date;
        this.costMonthly = costMonthly;
        this.comment = "";
    }

    public String getDate() {
        return dateCreated;
    }

    public void setDateCreated(String date) {
        this.dateCreated = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
        // error check
    }

    public String getCostMonthly() {
        return costMonthly;
    }

    public void setCostMonthly(String newCostMonthly) {
        costMonthly = newCostMonthly;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String newComment) {
        comment = newComment;
    }

    public String toString() {
        String str = new String(name + "\t\t\t$" + costMonthly + "\n"
                + dateCreated + "\n" + comment);
        return str;
    }
}
