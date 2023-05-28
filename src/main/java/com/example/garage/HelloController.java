package com.example.garage;

import com.example.garage.dop.EntryController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

    public TextField logEnter;
    public TextField passEnter;
    public Button buttonEnter;
    public TextField passReg;
    public Button regButton;
    public Label infoLabel;
    public TextField surnameReg;
    public TextField nameReg;
    public TextField balanceReg;

    public void enterTry() {
        int login;
        String password = passEnter.getText();
        try {
            login = Integer.parseInt(logEnter.getText());
        } catch (Exception e) {
            infoLabel.setText("Invalid login!");
            return;
        }
        String answer = EntryController.tryToEnter(login, password);
        if (answer.equals("Invalid password or id, please, check your data")) {
            infoLabel.setText("Invalid password or id!");
        } else {
            infoLabel.setText("Success!");
            UserInfo.setId(Integer.parseInt(answer.split("-")[0]));
            UserInfo.setName(answer.split("-")[1] + " " + answer.split("-")[2]);
            UserInfo.setBalance(Integer.parseInt(answer.split("-")[3]));
            WindowChanger.closeWindow(buttonEnter, "main.fxml");
        }
    }

    public void registrationTry() {
        String name = nameReg.getText();
        String surname = surnameReg.getText();
        int balance;
        String password = passReg.getText();
        try {
            balance = Integer.parseInt(balanceReg.getText());
        } catch (Exception e) {
            infoLabel.setText("Invalid balance!");
            return;
        }
        String answer = EntryController.tryToRegistrate(name, surname, password, balance);
        if (answer.equals("Please, check your data")) {
            infoLabel.setText("Balance must be positive!");
        } else {
            infoLabel.setText("Your id is " + answer.split(" ")[6]);
        }
        System.out.println(answer);
    }
}