package com.example.calculator;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.widget.TextView;

import java.io.Serializable;

public class Calculator implements Parcelable {
    private Editable calculationString;
    private StringBuilder definitionNumber = new StringBuilder("");
    private int numberFirst;
    private int numberSecond;
    private TextView calculationText;
    private String operation;
    private Integer resultCalculation;
    private TextView resultCalculationView;
    private String SIGN_PLUS = "+";
    private String SIGN_MINUS = "-";
    private String SIGN_MULTIPLY = "*";
    private String SIGN_DIVISION = "/";
    private String SIGN_EQUALLY = "=";
    private String errorMassage = "Упс! Что-то пошло не так :(";

    Calculator(TextView calculationText, TextView resultCalculationView) {
        this.calculationText = calculationText;
        this.resultCalculationView = resultCalculationView;
    }

    protected Calculator(Parcel in) {
        numberFirst = in.readInt();
        numberSecond = in.readInt();
        operation = in.readString();
        if (in.readByte() == 0) {
            resultCalculation = null;
        } else {
            resultCalculation = in.readInt();
        }
        SIGN_PLUS = in.readString();
        SIGN_MINUS = in.readString();
        SIGN_MULTIPLY = in.readString();
        SIGN_DIVISION = in.readString();
        SIGN_EQUALLY = in.readString();
        errorMassage = in.readString();
    }

    public static final Creator<Calculator> CREATOR = new Creator<Calculator>() {
        @Override
        public Calculator createFromParcel(Parcel in) {
            return new Calculator(in);
        }

        @Override
        public Calculator[] newArray(int size) {
            return new Calculator[size];
        }
    };

    public TextView getCalculationText(){
        return calculationText;
    }

    public TextView getResultCalculationView(){
        return resultCalculationView;
    }


    public void addSymbolInCalculationText(String symbol) {
        calculationText.append(symbol);
        definitionNumber.append(symbol);
        if (symbol.equals(SIGN_PLUS) || symbol.equals(SIGN_MINUS) || symbol.equals(SIGN_MULTIPLY)
                || symbol.equals(SIGN_DIVISION)) {
            definitionFirstNumber();
            operation = symbol;
        } else if (symbol.equals(SIGN_EQUALLY)) {
            resultCalculationView.setText("");
            definitionSecondNumber();
            try {
                calculation();
                resultCalculationView.append(resultCalculation.toString());
                calculationText.setText("");
            } catch (ArithmeticException exception){
                resultCalculationView.setText(errorMassage);
            }
        }
    }

    public void deleteLastSymbolInCalculationText() {
        calculationString = (Editable) calculationText.getText();
        int calculationStringLength = calculationString.length();
        if (calculationStringLength > 0) {
            calculationString.delete(calculationStringLength - 1, calculationStringLength);
        }
        calculationText.setText(calculationString);
    }

    private void definitionFirstNumber() {
        definitionNumber.delete(definitionNumber.length() - 1, definitionNumber.length());
        numberFirst = Integer.parseInt(definitionNumber.toString());
        definitionNumber.delete(0, definitionNumber.length());
    }

    private void definitionSecondNumber() {
        definitionNumber.delete(definitionNumber.length() - 1, definitionNumber.length());
        numberSecond = Integer.parseInt(definitionNumber.toString());
        definitionNumber.delete(0, definitionNumber.length());
    }

    private void calculation() throws ArithmeticException {
        switch (operation) {
            case ("+"):
                resultCalculation = numberFirst + numberSecond;
                break;
            case ("-"):
                resultCalculation = numberFirst - numberSecond;
                break;
            case ("*"):
                resultCalculation = numberFirst * numberSecond;
                break;
            case ("/"):
                    resultCalculation = numberFirst / numberSecond;
                break;
        }
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(numberFirst);
        parcel.writeInt(numberSecond);
        parcel.writeString(operation);
        if (resultCalculation == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(resultCalculation);
        }
        parcel.writeString(SIGN_PLUS);
        parcel.writeString(SIGN_MINUS);
        parcel.writeString(SIGN_MULTIPLY);
        parcel.writeString(SIGN_DIVISION);
        parcel.writeString(SIGN_EQUALLY);
        parcel.writeString(errorMassage);
    }
}
