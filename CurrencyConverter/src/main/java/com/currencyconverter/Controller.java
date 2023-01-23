package com.currencyconverter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;



public class Controller implements Initializable {
    @FXML
    private Label outputText;
    public TextField amountTextField;
    public ComboBox firstCurrencyComboBox;
    public ComboBox secondCurrencyComboBox;
    public Button convertButton;
    public List<String> currencyList = new ArrayList<>();//List to save all countries


    //initialize is executed at program start (In our case: fill ComboBox)
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            currencyList = Converter.getCurrencyListLongName(); //Make a list and get currencies
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObservableList<String> currencyOptions = //Make an ObservableList with the currencies
                FXCollections.observableArrayList(
                        currencyList
                );

        //Set items
        firstCurrencyComboBox.setItems(currencyOptions);
        secondCurrencyComboBox.setItems(currencyOptions);

    }

    //Takes the currencies and the amount, converts it and writes it in a label
    public void convert(ActionEvent actionEvent) throws IOException {

        String firstCurrency = (String) firstCurrencyComboBox.getValue(); //First currency (first ComboBox)
        String secondCurrency = (String) secondCurrencyComboBox.getValue(); //Second currency (second ComboBox)
        String amount = amountTextField.getText(); //Amount from text field
        String convertedAmount;


        if (firstCurrency == null || secondCurrency == null) { //If no currencies are selected
            outputText.setText("Please choose a currency...");
        } else if (firstCurrency.equals(secondCurrency)) {
                outputText.setText("Please choose a different currency...");
        }
        else if (amount == "") { //If no amount was typed in the text field
        outputText.setText("Please choose an amount...");
        }
        else if (!amount.matches(".*[0-9]+.*")) { //If the amount is not a number
            outputText.setText("The amount has to be a number...");
        }
        else
        {

            try {
                //Take the parameters and convert
                convertedAmount = Converter.Convert(amountTextField.getText(), Converter.getShortNameOfCurrency(firstCurrency), Converter.getShortNameOfCurrency(secondCurrency));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            //Output
            outputText.setText(amount + " " + firstCurrency + " equals to " + convertedAmount + " " + secondCurrency + ".");
        }
    }

}