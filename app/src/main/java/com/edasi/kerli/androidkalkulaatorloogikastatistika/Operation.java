package com.edasi.kerli.androidkalkulaatorloogikastatistika;

/**
 * Created by Kerli on 13.05.2016.
 */
public class Operation implements IEntity {

    private int id;
    private int operationTypeId;
    private String fullOperation;
    private float result;
    private long timeStamp;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getFullOperation() {
        return fullOperation;
    }
    public void setFullOperation(String fullOperation) {
        this.fullOperation = fullOperation;
    }

    public int getOperationTypeId() {
        return operationTypeId;
    }
    public void setOperationTypeId(int operationTypeId) {
        this.operationTypeId = operationTypeId;
    }



    public float getResult() {
        return result;
    }
    public void setResult(float result) {
        this.result = result;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return timeStamp + " " + fullOperation + " " + result;
    }
}
