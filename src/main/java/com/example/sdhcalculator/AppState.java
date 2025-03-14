package com.example.sdhcalculator;

public class AppState {
    private static final AppState instance = new AppState();

    private int formulaType;
    private double result;
    private String firstValueLabelText;
    private String secondValueLabelText;
    private String thirdValueLabelText;
    private String fourthValueLabelText;

    private AppState() {}

    public static AppState getInstance() {
        return instance;
    }

    public int getFormulaType() {
        return formulaType;
    }

    public void setFormulaType(int formulaType) {
        this.formulaType = formulaType;
    }

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    public String getFirstValueLabelText() {
        return firstValueLabelText;
    }

    public void setFirstValueLabelText(String firstValueLabelText) {
        this.firstValueLabelText = firstValueLabelText;
    }

    public String getSecondValueLabelText() {
        return secondValueLabelText;
    }

    public void setSecondValueLabelText(String secondValueLabelText) {
        this.secondValueLabelText = secondValueLabelText;
    }

    public String getThirdValueLabelText() {
        return thirdValueLabelText;
    }

    public void setThirdValueLabelText(String thirdValueLabelText) {
        this.thirdValueLabelText = thirdValueLabelText;
    }

    public String getFourthValueLabelText() {
        return fourthValueLabelText;
    }

    public void setFourthValueLabelText(String fourthValueLabelText) {
        this.fourthValueLabelText = fourthValueLabelText;
    }
}
