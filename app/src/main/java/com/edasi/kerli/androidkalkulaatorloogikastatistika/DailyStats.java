package com.edasi.kerli.androidkalkulaatorloogikastatistika;

/**
 * Created by Kerli on 13.05.2016.
 */
public class DailyStats implements IEntity {

    private int id;
    private String dayStamp;
    private int operationTypeId;
    private int dayCounter;

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public String getDayStamp() {
        return dayStamp;
    }
    public void setDayStamp(String dayStamp) {
        this.dayStamp = dayStamp;
    }

    public int getOperationTypeId(){
        return operationTypeId;
    }
    public void setOperationTypeId(int operationTypeId) {
        this.operationTypeId = operationTypeId;
    }

    public int getDayCounter() {
        return dayCounter;
    }
    public void setDayCounter(int dayCounter){
        this.dayCounter = dayCounter;
    }

    @Override
    public String toString() {
        return dayStamp + " " + dayCounter;
    }
}
