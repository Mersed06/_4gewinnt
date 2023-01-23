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






    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { //Wird beim Start ausgeführt (Box wird befüllt)
        try {
            currencyList = Converter.getCurrencyListLongName();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ObservableList<String> currencyOptions =
                FXCollections.observableArrayList(
                        currencyList
                );

        firstCurrencyComboBox.setItems(currencyOptions);
        secondCurrencyComboBox.setItems(currencyOptions);






    }





    public void convert(ActionEvent actionEvent) throws IOException {


        String firstCurrency = (String) firstCurrencyComboBox.getValue(); //First currency von der Auswahl
        String secondCurrency = (String) secondCurrencyComboBox.getValue(); //Second currency von der Auswahl
        String amount = amountTextField.getText(); //Amount vom Textfeld
        String convertedAmount;


        if (firstCurrency == null || secondCurrency == null) {
            outputText.setText("Please choose a currency...");
        } else if (firstCurrency.equals(secondCurrency)) {
                outputText.setText("Please choose a different currency...");
        }
        else if (amount == "") {
        outputText.setText("Please choose an amount...");
        }
        else if (!amount.matches(".*[0-9]+.*")) {
            outputText.setText("The amount has to be a number...");
        }
        else
        {

            try {
                convertedAmount = Converter.ConversionRate(amountTextField.getText(), Converter.getShortNameOfCurrency(firstCurrency), Converter.getShortNameOfCurrency(secondCurrency));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


            outputText.setText(amount + " " + firstCurrency + " equals to " + convertedAmount + " " + secondCurrency + ".");
        }
    }

}