 package roombooking;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import javafx.stage.Stage;

public class RoomBooking extends Application {
    private static List<Room> roomList;
    
    @Override
    public void start(Stage primaryStage) {
        LoginComponent loginComponent = new LoginComponent();
        BookingComponent bookingComponent = new BookingComponent();
        BorderPane borderPane = new BorderPane();
        
        loginComponent.getLoginPage(borderPane, roomList);
        //bookingComponent.getBookingPage(borderPane, roomList, "SaaaD");
                
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        roomList = new ArrayList();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, 5);
        String output = sdf.format(c.getTime());
        System.out.println(c.getTime()+"");
        
        
        for(int i=0; i<20; i++){
            Room room = new Room();
            
            room.setFloor(i/10+1);
            room.setNumber((i/10+1)*100+i%10+1);
            room.checkout();
            
            roomList.add(room);
        }
        
        launch(args);
    }
}
