package com.example.garage;

import com.example.garage.dop.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class MainController {

    public VBox carsByModel;
    public VBox infoVBox;
    public VBox buyCarVBox;
    public TextField nameField;
    public TextField moneyField;
    public Label labelInfo;
    public Button buttonGarage;
    public Button exitButton;

    public void initialize() {
        nameField.setText(UserInfo.getName());
        moneyField.setText(String.valueOf(UserInfo.getBalance()));
        List<String> brandModels = SellingCarModelsController.getSellingCars();
        for (String brandModel : brandModels) {
            String[] tokens = brandModel.split("-");
            String brand = tokens[0];
            String model = tokens[1];
            Label labelBrand = new Label(brand);
            labelBrand.setPrefWidth(50);
            Label labelModel = new Label(model);
            labelModel.setPrefWidth(50);
            Button buttonInfo = new Button("Show info");
            EventHandler<ActionEvent> buttonInfoClick = actionEvent -> {
                infoVBox.getChildren().clear();
                String answer = DetailInfoAboutModel.getSellingCars(model);
                String[] tokensAnswer = answer.split("\\*");
                Label modelInfo = new Label("Info about: " + model);
                Label labelLicense = new Label(tokensAnswer[0]);
                Label labelEngine = new Label(tokensAnswer[1]);
                Label labelCountry = new Label(tokensAnswer[2]);
                infoVBox.getChildren().addAll(modelInfo, labelLicense, labelEngine, labelCountry);
                setBuyBox(model);
            };
            buttonInfo.setOnAction(buttonInfoClick);
            HBox hBox = new HBox();
            hBox.setSpacing(2);
            hBox.getChildren().addAll(labelBrand, labelModel, buttonInfo);
            carsByModel.getChildren().add(hBox);
        }
    }

    private void setBuyBox(String model) {
        buyCarVBox.getChildren().clear();
        List<String> answer = SellingCarsByModel.getSellingCars(model);
        for (String car : answer) {
            String[] tokens = car.split("\\*");
            Label price = new Label(tokens[3]);
            Label year = new Label(tokens[4]);
            Label mileage = new Label(tokens[5]);
            Button buyButton = new Button("Buy");
            HBox hBox = new HBox();
            EventHandler<ActionEvent> buyButtonClick = actionEvent -> {
                if (UserInfo.getBalance() < Integer.parseInt(tokens[3].split(" ")[1])) {
                    labelInfo.setText("You haven't got enough money to buy!!!");
                } else {
                    buyCarVBox.getChildren().remove(hBox);
                    UserInfo.setBalance(UserInfo.getBalance() - Integer.parseInt(tokens[3].split(" ")[1]));
                    GarageCars.putCarToGarage(UserInfo.getId(), Integer.parseInt(tokens[0].split(" ")[1]));
                    ChangeBalance.changeCustomerBalance(UserInfo.getId(), UserInfo.getBalance());
                    moneyField.setText(String.valueOf(UserInfo.getBalance()));
                    labelInfo.setText("You successfully bought the car!");
                }
            };
            buyButton.setOnAction(buyButtonClick);
            hBox.setSpacing(10);
            hBox.getChildren().addAll(price, year, mileage, buyButton);
            buyCarVBox.getChildren().add(hBox);
        }
    }

    public void goToGarage() {
        WindowChanger.closeWindow(buttonGarage, "garage.fxml");
    }

    public void exitButtonClick() {
        WindowChanger.closeWindow(exitButton, "hello-view.fxml");
    }
}
