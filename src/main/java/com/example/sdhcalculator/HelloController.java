package com.example.sdhcalculator;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public Label firstValueLabel;
    @FXML
    public TextField firstValue;
    @FXML
    public Label secondValueLabel;
    @FXML
    public TextField secondValue;
    @FXML
    public Label thirdValueLabel;
    @FXML
    public TextField thirdValue;
    @FXML
    public Label fourthValueLabel;
    @FXML
    public TextField fourthValue;
    @FXML
    public Label resultLabel;
    @FXML
    public Label errorLabel;

    @FXML
    VBox mainPane;
    /*@FXML
    VBox localizationPane;*/
    @FXML
    VBox thicknessPane;
    @FXML
    VBox prescriptionPane;
    @FXML
    VBox calculatorPane;
    @FXML
    VBox resultPane;

    double result;
    int maxLength=20;
    VBox currentPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            firstValueLabel.setText(AppState.getInstance().getFirstValueLabelText());
            secondValueLabel.setText(AppState.getInstance().getSecondValueLabelText());
            thirdValueLabel.setText(AppState.getInstance().getThirdValueLabelText());
            fourthValueLabel.setText(AppState.getInstance().getFourthValueLabelText());
        } catch (NullPointerException e) {
            System.out.println("Заголовки пусты");
        }

        try {
            result = AppState.getInstance().getResult();
            resultLabel.setText(String.valueOf(result));
        } catch (Exception e) {
            System.out.println("result равен null");
        }

        try {
            if (mainPane != null) {
                currentPane = mainPane;
            } /*else if (localizationPane != null) {
                currentPane = localizationPane;
            }*/ else if (thicknessPane != null) {
                currentPane = thicknessPane;
            } else if (prescriptionPane != null) {
                currentPane = prescriptionPane;
            } else if (calculatorPane != null) {
                currentPane = calculatorPane;
            } else if (resultPane != null) {
                currentPane = resultPane;
            } else {
                System.out.println("???");
            }
            System.out.println(currentPane);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onStartButtonClick() throws IOException {
        ViewState.getInstance().getViewHistory().push("main.fxml");

        Parent nextPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("thickness.fxml")));
        mainPane.getChildren().setAll(nextPane);
    }

    /*@FXML
    public void onPeripheralButtonClick() throws IOException {
        ViewState.getInstance().getViewHistory().push("localization.fxml");

        Parent nextPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("thickness.fxml")));
        localizationPane.getChildren().setAll(nextPane);
    }*/

    /*@FXML
    public void onInterhemisphericButtonClick() {
        //TODO
    }*/

    @FXML
    public void onLessOneButtonClick() throws IOException {
        ViewState.getInstance().getViewHistory().push("thickness.fxml");

        Parent nextPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("prescription.fxml")));
        thicknessPane.getChildren().setAll(nextPane);
    }

    @FXML
    public void onOverOneButtonClick() throws IOException {
        AppState.getInstance().setFormulaType(1);
        AppState.getInstance().setFirstValueLabelText("Толщина СДГ (см)");
        AppState.getInstance().setSecondValueLabelText("Средняя КТ плотность площади СДГ");
        AppState.getInstance().setThirdValueLabelText("КТ плотность Базилярной артерии");
        AppState.getInstance().setFourthValueLabelText("КТ плотность крови в затылочной части сагиттального синуса");

        ViewState.getInstance().getViewHistory().push("thickness.fxml");

        Parent nextPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calculator.fxml")));
        thicknessPane.getChildren().setAll(nextPane);
    }

    @FXML
    public void onLessFortyEightButtonClick() throws IOException {
        AppState.getInstance().setFormulaType(2);
        AppState.getInstance().setFirstValueLabelText("Средняя КТ плотность площади СДГ");
        AppState.getInstance().setSecondValueLabelText("Минимальная КТ плотность площади СДГ");
        AppState.getInstance().setThirdValueLabelText("Максимальная КТ плотность площади СДГ");
        AppState.getInstance().setFourthValueLabelText("КТ плотность крови в затылочной части сагиттального синуса");

        ViewState.getInstance().getViewHistory().push("prescription.fxml");

        Parent nextPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calculator.fxml")));
        prescriptionPane.getChildren().setAll(nextPane);
    }

    @FXML
    public void onOverFortyEightButtonClick() throws IOException {
        AppState.getInstance().setFormulaType(3);
        AppState.getInstance().setFirstValueLabelText("Средняя КТ плотность площади СДГ");
        AppState.getInstance().setSecondValueLabelText("Минимальная КТ плотность площади СДГ");
        AppState.getInstance().setThirdValueLabelText("Максимальная КТ плотность площади СДГ");
        AppState.getInstance().setFourthValueLabelText("КТ плотность крови в затылочной части сагиттального синуса");

        ViewState.getInstance().getViewHistory().push("prescription.fxml");

        Parent nextPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("calculator.fxml")));
        prescriptionPane.getChildren().setAll(nextPane);
    }

    @FXML
    public void calculateButtonClick() throws IOException {
        if (firstValue.getText().length()>maxLength || secondValue.getText().length()>maxLength ||
                thirdValue.getText().length()>maxLength || fourthValue.getText().length()>maxLength) {
            errorLabel.setText("Значения должны содержать менее " + maxLength + " символов!");
            return;
        }

        if (firstValue.getText().trim().isEmpty() || secondValue.getText().trim().isEmpty() ||
                thirdValue.getText().trim().isEmpty() || fourthValue.getText().trim().isEmpty()) {
            errorLabel.setText("Пожалуйста, заполните все поля!");
            return;
        }

        double firstVal;
        double secondVal;
        double thirdVal;
        double fourthVal;
        try {
            firstVal = Double.parseDouble(firstValue.getText());
            secondVal = Double.parseDouble(secondValue.getText());
            thirdVal = Double.parseDouble(thirdValue.getText());
            fourthVal = Double.parseDouble(fourthValue.getText());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введено не число");
            errorLabel.setText("Пожалуйста, вводите только числа!");
            return;
        }

        int formulaType = AppState.getInstance().getFormulaType();
        switch (formulaType){
            case 1:
                result = 0.80376184*secondVal - 1.59361133*firstVal  +
                        0.84611881*thirdVal - 0.7687774*fourthVal - 38.01761064277179;
                AppState.getInstance().setResult(Math.round(result * 100.0) / 100.0);
                break;
            case 2:
                result = 0.71876509*firstVal - 0.10202001*secondVal +
                        0.13755295*thirdVal - 1.53616305*fourthVal + 30.687518541773446;
                AppState.getInstance().setResult(Math.round(result * 100.0) / 100.0);
                break;
            case 3:
                result = 32.89308042*firstVal - 23.04789161*secondVal -
                        13.67184965*thirdVal + 4.02812587*fourthVal + 15.872744755239239;
                AppState.getInstance().setResult(Math.round(result * 100.0) / 100.0);
                break;
            default:
                errorLabel.setText("Извините, что-то пошло не так!");
                return;
        }

        ViewState.getInstance().getViewHistory().push("calculator.fxml");

        Parent nextPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("result.fxml")));
        calculatorPane.getChildren().setAll(nextPane);
    }

    @FXML
    public void onHomeButtonClick() throws IOException {
        ViewState.getInstance().getViewHistory().clear();

        Parent homePane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("main.fxml")));
        currentPane.getChildren().setAll(homePane);
    }

    @FXML
    public void onBackButtonClick() throws IOException {
        if (!ViewState.getInstance().getViewHistory().isEmpty()) {
            String previousPanePath = ViewState.getInstance().getViewHistory().pop();
            Parent previousPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(previousPanePath)));
            currentPane.getChildren().setAll(previousPane);
        }
        else {
            System.out.println("История пуста. Невозможно вернуться назад");
        }
    }
}
//(с) 2025 Tumanov Nikita Alekseevich All Rights Reserved