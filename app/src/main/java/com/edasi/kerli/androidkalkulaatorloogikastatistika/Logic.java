package com.edasi.kerli.androidkalkulaatorloogikastatistika;

/**
 * Created by Kerli on 12.05.2016.
 */
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logic extends BroadcastReceiver {
private boolean stateErr;
    private boolean lastNum;
    private boolean lastDot;

    public String Answer(String calcFieldText) {
        //kui viimane sisestus on arv ja ekraan pole tühi
        if (lastNum && !stateErr) {
            //võta ekraanil olev tekst ja muudan stringiks
            String txt = calcFieldText;
            Expression expression = new ExpressionBuilder(txt).build();

            try {
                //arvutan vastuse ja näitan ekraanile
                double result = expression.evaluate();
                lastDot = true;
                return Double.toString(result);
                //numbris on punkt
                // calcField.setTextAlignment();

            } catch (ArithmeticException ex) {
                // kui on vale sisend

                stateErr = true;
                lastNum = false;
                return "Error";
            }
        }
        return "";
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String myCalcExpression = "";
        String result = "";
        if (isOrderedBroadcast()) {

            //// TODO: 14.05.2016 salvestan ära mis päev prg on stringina
            //// TODO: 14.05.2016 kontrollin kas andmebaasis on prgsel päeval antud tehte operationtype-ga väärtusi
            //// kui on siis suurendan counti, kui ei ole loon uue daily stats objekti ja panen counti üheks
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateStr = formatter.format(date);


            Bundle extras = intent.getExtras();
            if (extras != null) {
                Bundle sendBackValues = new Bundle();
                UOW database = new UOW(context);
                Operation operation = new Operation();
                String operationTypeString = "";

                boolean add = false;
                boolean sub = false;
                boolean mult = false;
                boolean div = false;



                myCalcExpression = extras.getString("Calculation");
                System.out.println("myCalcExpression: " + myCalcExpression);
                lastNum =  extras.getBoolean("lastNum");
                stateErr = extras.getBoolean("stateErr");
                lastDot = extras.getBoolean("lastDot");
                System.out.println("onReceive'isse joudsin");
                result =  Answer(myCalcExpression);

                if (myCalcExpression.contains("+")){
                   add = true;
                   operationTypeString += "+";
                }
                if (myCalcExpression.contains("-")){
                    sub = true;
                    operationTypeString += "-";
                }
                if (myCalcExpression.contains("/")) {
                   div = true;
                   operationTypeString += "/";
                }
                if (myCalcExpression.contains("*")){
                   mult = true;
                   operationTypeString += "*";
                }

                OperationType operationType = database.operationTypeRepo.GetOperationTypeByStringOperator(operationTypeString);
                System.out.println("OPERATION TYPE: " + operationType);
                if (operationType == null){
                    OperationType operationTypeNew = new OperationType();
                    operationTypeNew.setOperation(operationTypeString);
                    operationTypeNew.setLifeTimeCounter(1);

                    operationType = database.operationTypeRepo.add(operationTypeNew);

                } else {
                    //// TODO: 14.05.2016 uuenda operationtype lifetimecounterit
                    int count = operationType.getLifeTimeCounter();
                    operationType.setLifeTimeCounter(count+1);
                    database.operationTypeRepo.update(operationType);
                }
                System.out.println("LAST OPERATION TYPE: " + operationType);

               database.dailyStatsRepo.addOrUpdate(dateStr, operationType.getId());


                operation.setOperationTypeId(operationType.getId());
                operation.setFullOperation(myCalcExpression);
                operation.setResult(Float.parseFloat(result));
                //operation.setTimeStamp();

                database.operationRepo.add(operation);




                sendBackValues.putString("String Result", result);
                sendBackValues.putBoolean("lastNum", lastNum);
                sendBackValues.putBoolean("stateErr", stateErr);
                sendBackValues.putBoolean("lastDot", lastDot);
                setResultExtras(sendBackValues);
            }
            setResultCode(Activity.RESULT_OK);

        }
    }
}