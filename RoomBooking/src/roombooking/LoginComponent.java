/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roombooking;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;

/**
 *
 * @author SaaD
 */
public class LoginComponent {
    public void getLoginPage(BorderPane container, List<Room> roomList){
        Component component = new Component();
        BookingComponent bookingComponent = new BookingComponent();
        
        container.setMinHeight(450);
        container.setMinWidth(700);
        
        //setting the first vbox
        VBox top = new VBox();
        top.setMinHeight(120);
        top.setMinWidth(USE_COMPUTED_SIZE);
        top.setStyle("-fx-background-color: #000000; -fx-border-color: #5A5B5B; -fx-border-width: 0 0 1 0");
        container.setTop(top);
        
        //setting the input field container
        VBox down = new VBox();
        down.setMinHeight(USE_COMPUTED_SIZE);
        down.setMinWidth(USE_COMPUTED_SIZE);
        down.setStyle("-fx-background-color: #0A0A0A; -fx-opacity:0.95");
        down.setSpacing(5);
        down.setAlignment(Pos.CENTER);
        
        //all the inputs
        Label emailLabel = new Label("Email");
        emailLabel.setMaxWidth(300);
        emailLabel.setStyle("-fx-padding: 15 0 0 0; -fx-text-fill: #FFFFFF");
        TextField email = new TextField();
        email.setMaxWidth(300);
        email.setStyle("-fx-background-color: transparent; -fx-border-color: #5A5B5B; -fx-padding: 5 5 5 15; -fx-text-fill: #FFFFFF");
        
        Label passLabel = new Label("Password");
        passLabel.setMaxWidth(300);
        passLabel.setStyle("-fx-padding: 15 0 0 0; -fx-text-fill: #FFFFFF");
        PasswordField password = new PasswordField();
        password.setMaxWidth(300);
        password.setStyle("-fx-background-color: transparent; -fx-border-color: #5A5B5B; -fx-padding: 5 5 5 15; -fx-text-fill: #FFFFFF");
        
        Button submit = new Button("Submit");
        component.button(submit);
        Label message = new Label();
        
        down.getChildren().addAll(emailLabel, email, passLabel, password, submit, message);
        container.setCenter(down);
        
        submit.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                String emailText = email.getText().toString().trim();
                String passwordText = password.getText().toString().trim();
                Helper helper = new Helper();
                
                if(helper.checkLogin(emailText, passwordText)){
                    message.setText(emailText.split("@")[0]+" has logged in");
                    bookingComponent.getBookingPage(container, roomList, emailText.split("@")[0]);
                }
                else{
                    message.setText("Invalid credential");
                }
            }
        });   
    }
}
