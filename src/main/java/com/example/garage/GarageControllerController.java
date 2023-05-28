package com.example.garage;

import com.example.garage.dop.GarageController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class GarageControllerController {
    public VBox carsVBox;
    public Button exitButton;

    public void initialize() {
        List<String> answer = GarageController.getBoughtCars(UserInfo.getId());
        if (!answer.get(0).equals("You don't have any cars")) {
            for (String car : answer) {
                String[] tokens = car.split("\\*");
                Label brand = new Label(tokens[0]);
                Label model = new Label(tokens[1]);
                Label price = new Label(tokens[2]);
                Label year = new Label(tokens[3]);
                Label mileage = new Label(tokens[4]);
                HBox hBox = new HBox();
                hBox.setSpacing(5);
                hBox.getChildren().addAll(brand, model, price, year, mileage);
                carsVBox.getChildren().add(hBox);
            }
        }
    }

    public void exitButtonClick() {
        WindowChanger.closeWindow(exitButton, "main.fxml");
    }
}
