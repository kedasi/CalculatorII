package com.edasi.kerli.androidkalkulaatorloogikastatistika;

/**
 * Created by Kerli on 13.05.2016.
 */
public class OperationType implements IEntity {

    private int id;
    private String operation;
    private int lifeTimeCounter;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getOperation() {
        return operation;
    }
    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getLifeTimeCounter() {
        return lifeTimeCounter;
    }
    public void setLifeTimeCounter(int lifeTimeCounter) {
        this.lifeTimeCounter = lifeTimeCounter;
    }

    @Override
    public String toString() {
        return operation + " " + lifeTimeCounter;
    }
}
