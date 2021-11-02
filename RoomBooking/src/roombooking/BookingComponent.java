/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package roombooking;

import java.time.LocalDate;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.scene.layout.VBox;

/**
 *
 * @author SaaD
 */
public class BookingComponent {
    public void getBookingPage(BorderPane container, List<Room> roomList, String staffName){
        container.setMinHeight(450);
        container.setMinWidth(700);
        container.setTop(null);
        container.setCenter(null);
        
        //top element
        setTopOfBookingPage(container, roomList, staffName);
        setMiddleOfBookingPage(container, roomList, 1, false, null);
        setBottomOfBookingPage(container, roomList, staffName);
    }
    
    private static void setTopOfBookingPage(BorderPane container, List<Room> roomList, String staffName){
        Component component = new Component();
        LoginComponent loginComponent = new LoginComponent();
        
        HBox top = new HBox();
        top.setMinHeight(120);
        top.setMinWidth(USE_COMPUTED_SIZE);
        top.setStyle("-fx-background-color: #000000; -fx-border-color: #5A5B5B; -fx-border-width: 0 0 1 0");
        
        VBox topLeft = new VBox();
        topLeft.setMinHeight(120);
        topLeft.setMinWidth(550);
        topLeft.setAlignment(Pos.CENTER_LEFT);
        topLeft.setStyle("-fx-background-color: #000000; -fx-border-color: #5A5B5B; -fx-border-width: 0 0 1 0");
        
        VBox topMiddle = new VBox();
        topMiddle.setMinHeight(120);
        topMiddle.setMinWidth(150);
        topMiddle.setAlignment(Pos.CENTER);
        topMiddle.setSpacing(10);
        topMiddle.setStyle("-fx-background-color: #000000; -fx-border-color: #5A5B5B; -fx-border-width: 0 0 1 0");
        
        Label name = new Label(staffName);
        name.setStyle("-fx-text-fill: #FFFFFF; -fx-padding: 5 20 5 20; -fx-border-color: #5A5B5B; -fx-border-width: 0 0 2 0; -fx-font-size: 18;");
        Button logout = new Button("Logout");
        component.button(logout);
        
        topMiddle.getChildren().addAll(name, logout);
        top.getChildren().addAll(topLeft, topMiddle);
        
        logout.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                loginComponent.getLoginPage(container, roomList);
            }
        }); 
        
        container.setTop(top);
    }
    
    private static void setMiddleOfBookingPage(BorderPane container, List<Room> roomList, int number, boolean isSearched, LocalDate date){
        Component component = new Component();
        
        HBox center = new HBox();
        center.setMinHeight(USE_COMPUTED_SIZE);
        center.setMinWidth(USE_COMPUTED_SIZE);
        center.setStyle("-fx-background-color: #0A0A0A; -fx-opacity:0.95; -fx-padding: 10");
        center.setSpacing(5);
        center.setAlignment(Pos.CENTER);
        
        VBox left = new VBox();
        left.setMinHeight(100);
        left.setMinWidth(100);
        left.setAlignment(Pos.CENTER_LEFT);
        left.setSpacing(10);
        
        Button floor1 = new Button("First floor");
        component.button(floor1);
        Button floor2 = new Button("Second floor");
        component.button(floor2);
        
        Label startLabel = new Label("Start date");
        component.setLabel(startLabel, 300);
        DatePicker datePicker = new DatePicker();
        
        Button checkRoom = new Button("Check available room");
        component.button(checkRoom);
        
        left.getChildren().addAll(floor1, floor2, startLabel, datePicker, checkRoom);
        
        ScrollPane right = new ScrollPane();
        right.setMinHeight(100);
        right.setMinWidth(500);
        right.setStyle("-fx-background: #0A0A0A; -fx-border: #5A5B5B");
        right.setFitToWidth(true);
        setRoom(roomList, right, number, null);
        
        floor1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                right.setContent(null);
                setRoom(roomList, right, 1, null);
            }
        });
        floor2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                right.setContent(null);
                setRoom(roomList, right, 2, null);
            }
        });
        
        checkRoom.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                LocalDate date = datePicker.getValue();
                
                setRoom(roomList, right, number, date);
            }
        });
        
        center.getChildren().addAll(left, right);
        container.setCenter(center);
    }
    
    private static void setRoom(List<Room> roomList, ScrollPane container, int number, LocalDate date){
        VBox allRoom = new VBox();
        allRoom.setMinWidth(450);
        allRoom.setStyle("-fx-padding: 10");
        
        for(int i=0; i<roomList.size(); i++){
            Room room =roomList.get(i);
            String bookText = room.getBooked() ? "This room is booked by "+room.getBookedBy() : "This room is available";
            String guestText = room.getGuest().equals("") ? "No guest" : room.getGuest();
            String textColor = room.getBooked() ? "#F70505" : "#1FE304";
            
            if(number == room.getFloor()){
                VBox singleRoom = new VBox();
                singleRoom.setMinWidth(450);
                singleRoom.setSpacing(2);
                singleRoom.setAlignment(Pos.CENTER_LEFT);
                singleRoom.setStyle("-fx-border-color: #5A5B5B; -fx-border-width: 0 0 1 0; -fx-padding: 10 0 10 20");
       
                Label roomNumber = new Label("Room:  "+room.getNumber());
                Label booked = new Label(bookText);
                booked.setStyle("-fx-font-size: 15; -fx-text-fill: "+textColor);
                
                if(room.getStart() == null){
                    singleRoom.getChildren().addAll(roomNumber, booked);
                }
                else{
                    Label guest = new Label("Guest name:  "+guestText);
                    Label email = new Label("Email:  "+room.getEmail());
                    Label passport = new Label("Passport:  "+room.getPassport());
                    Label phone = new Label("Phone:  "+room.getPhone());
                    Label startDate = new Label("Checkin:  "+ room.getStart()+"");
                    Label endDate = new Label("Checkout:  "+ room.getStart().plusDays(room.getDuration()));
                
                    singleRoom.getChildren().addAll(roomNumber, guest, email, passport, phone, startDate, endDate, booked);
                }
                
                if(date==null){
                    allRoom.getChildren().addAll(singleRoom);
                }
                else{
                    if(room.getStart() == null){
                        allRoom.getChildren().addAll(singleRoom);
                        System.out.println(room.getStart()+"   "+date);
                    }
                    else{
                        LocalDate tmp = room.getStart();
                        int duration = room.getTotal();
                        
                        LocalDate nextFree = tmp.plusDays(duration);
                        
                        if(!date.isBefore(tmp)){
                            allRoom.getChildren().addAll(singleRoom);
                        }
                    }
                }
            }
        }
        
        container.setContent(allRoom);
    }
    
    private static void setBottomOfBookingPage(BorderPane container, List<Room> roomList, String staffName){
        Component component = new Component();
        
        HBox bottom = new HBox();
        bottom.setMinHeight(50);
        bottom.setMinWidth(USE_COMPUTED_SIZE);
        bottom.setAlignment(Pos.CENTER);
        bottom.setStyle("-fx-background-color: #000000; -fx-border-color: #5A5B5B; -fx-border-width: 1 0 0 0");
        
        Label roomLabel = new Label("Room");
        roomLabel.setStyle("-fx-padding: 0 10 0 0; -fx-text-fill: #FFFFFF");
        TextField room = new TextField();
        room.setStyle("-fx-background-color: transparent; -fx-border-color: #5A5B5B; -fx-padding: 5 5 5 15; -fx-text-fill: #FFFFFF");
        room.setMinWidth(200);
        
        Label guestLabel = new Label("Guest");
        guestLabel.setStyle("-fx-padding: 0 10 0 40; -fx-text-fill: #FFFFFF");
        TextField guest = new TextField();
        guest.setStyle("-fx-background-color: transparent; -fx-border-color: #5A5B5B; -fx-padding: 5 5 5 15; -fx-text-fill: #FFFFFF");
        guest.setMinWidth(200);
        
        Label nothing = new Label();
        nothing.setMinWidth(10);
        Label nothing2 = new Label();
        nothing2.setMinWidth(10);
                
        Button book = new Button("Book");
        component.button(book);
        
        Button clearRoom = new Button("Clear");
        component.button(clearRoom);
        
        bottom.getChildren().addAll(roomLabel, room, guestLabel, guest, nothing, book, nothing2, clearRoom); 
        container.setBottom(bottom);
        
        book.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                int roomText = -1;
                String guestText = guest.getText().toString().trim();
                
                try{
                    roomText = Integer.parseInt(room.getText().toString().trim());
                }
                catch(Exception a){
                    roomText = -1;
                }
                
                if(roomText==-1 || guestText.equals("")){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Empty field", ButtonType.OK);
                    alert.showAndWait();
                }
                else{
                    for(int i=0; i<roomList.size(); i++){
                        if(roomList.get(i).getNumber() == roomText){
                            if(roomList.get(i).getBooked()){
                                Alert alert = new Alert(Alert.AlertType.WARNING, "This room is booked", ButtonType.OK);
                                alert.showAndWait();
                            }
                            else{

                               takeInput(container, roomList, i, roomText, guestText, staffName);
                            }
                            break;
                        }
                    }
                }
                
                room.setText("");
                guest.setText("");
            }
        }); 
        
        clearRoom.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                int roomText = Integer.parseInt(room.getText().toString().trim());
                
                for(int i=0; i<roomList.size(); i++){
                    if(roomList.get(i).getNumber() == roomText){
                        if(!roomList.get(i).getBooked()){
                            Alert alert = new Alert(Alert.AlertType.WARNING, "This room isn't booked", ButtonType.OK);
                              alert.showAndWait();
                        }
                        else{
                            roomList.get(i).setBooked(false);
                            roomList.get(i).setGuest("");
                            roomList.get(i).setBookedBy("");
                            setMiddleOfBookingPage(container, roomList, roomText/100, false, null);
                        }
                        break;
                    }
                }
                room.setText("");
                guest.setText("");
            }
        });
    }
    
    private static void takeInput(BorderPane container, List<Room> roomList, int index, int room, String name, String staffName){
        Component component = new Component();
        
        VBox vbox = new VBox();
        vbox.setMinHeight(USE_COMPUTED_SIZE);
        vbox.setMinWidth(USE_COMPUTED_SIZE);
        vbox.setStyle("-fx-background-color: #0A0A0A; -fx-opacity:0.95");
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(3);
        
        Label emailLabel = new Label("Email");
        component.setLabel(emailLabel, 300);
        TextField email = new TextField();
        component.setTextField(email, 300);
        
        Label passportLabel = new Label("Passport");
        component.setLabel(passportLabel, 300);
        TextField passport = new TextField();
        component.setTextField(passport, 300);
        
        Label phoneLabel = new Label("Phone");
        component.setLabel(phoneLabel, 300);
        TextField phone = new TextField();
        component.setTextField(phone, 300);
        
        Label startLabel = new Label("Start date");
        component.setLabel(startLabel, 300);
        DatePicker start = new DatePicker();
        
        Label durationLabel = new Label("Duration");
        component.setLabel(durationLabel, 300);
        TextField duration = new TextField();
        component.setTextField(duration, 300);
        
        Button save = new Button("Save");
        component.button(save);
        
        vbox.getChildren().addAll(emailLabel, email, passportLabel, passport, phoneLabel, phone, startLabel, start, durationLabel, duration, save);
        container.setCenter(vbox);
        
        save.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                String emailText = email.getText().toString().trim();
                String passportText = passport.getText().toString().trim();
                String phoneText = phone.getText().toString().trim();
                int durationText = -1;
                try{
                    durationText = Integer.parseInt(duration.getText().toString().trim());
                }
                catch(Exception a){
                    durationText = -1;
                }
                
                LocalDate date = start.getValue();
                
                if(emailText==null || passportText==null || phoneText==null || durationText==-1 || date==null){
                    component.showWarningMessage("Empty field");
                }
                else{
                    roomList.get(index).checkin(durationText, date, name, emailText, passportText, phoneText, staffName);
                    setMiddleOfBookingPage(container, roomList, room/100, false, null);
                }
            }
        });
    } 
}
