/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roombooking;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author SaaD
 */
public class Component {
    public void setLabel(Label label, int width){
        label.setMaxWidth(width);
        label.setStyle("-fx-padding: 15 0 0 0; -fx-text-fill: #FFFFFF");
    }
    
    public void showWarningMessage(String message){
        Alert alert = new Alert(Alert.AlertType.WARNING, message, ButtonType.OK);
        alert.showAndWait();
    }
    
    public void setTextField(TextField textField, int width){
        textField.setMaxWidth(width);
        textField.setStyle("-fx-background-color: transparent; -fx-border-color: #5A5B5B; -fx-padding: 5 5 5 15; -fx-text-fill: #FFFFFF");
    }
    
    public void button(Button button){
        button.setStyle("-fx-background-color: transparent; -fx-border-color: #5A5B5B; -fx-padding: 5 20 5 20; -fx-text-fill: #FFFFFF");
        
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: transparent; -fx-border-color: #5A5B5B; -fx-padding: 5 20 5 20; -fx-text-fill: #FFFFFF; -fx-cursor: hand"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: transparent; -fx-border-color: #5A5B5B; -fx-padding: 5 20 5 20; -fx-text-fill: #FFFFFF"));
        
    }
}
