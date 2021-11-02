package roombooking;

import java.time.LocalDate;
import java.util.Date;

public class Room {
    private int number, floor, total, duration;
    private boolean isBooked;
    private LocalDate start;
    private String guest, email, passport, phone, bookedBy;
    
    public void setBookedBy(String bookedBy){
        this.bookedBy = bookedBy;
    }
    public void setNumber(int number){
        this.number = number;
    }
    public void setFloor(int floor){
        this.floor = floor;
    }
    public void setBooked(boolean booked){
        this.isBooked = booked;
    }
    public void setGuest(String guest){
        this.guest = guest;
    }
    public void setStart(LocalDate start){
        this.start = start;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public void setTotal(int total){
        this.total = total;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassport(String passport){
        this.passport = passport;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }
    
    
    public String getBookedBy(){
        return bookedBy;
    }
    public int getNumber(){
        return number;
    }
    public int getFloor(){
        return floor;
    }
    public boolean getBooked(){
        return isBooked;
    }
    public String getGuest(){
        return guest;
    }
    public LocalDate getStart(){
        return start;
    }
    public int getDuration(){
        return duration;
    }
    public int getTotal(){
        return total;
    }
    public String getEmail(){
        return email;
    }
    public String getPassport(){
        return passport;
    }
    public String getPhone(){
        return phone;
    }
    
    public void checkout(){
        this.total = 0;
        this.duration = 0;
        this.isBooked = false;
        this.start = null;
        this.guest = "";
        this.email = "";
        this.passport = "";
        this.phone = "";
        this.bookedBy = "";
    }
    public void checkin(int duration, LocalDate start, String guest, String email, String passport, String phone, String bookedBy){
        this.total = duration*395;
        this.duration = duration;
        this.isBooked = true;
        this.start = start;
        this.guest = guest;
        this.email = email;
        this.passport = passport;
        this.phone = phone;
        this.bookedBy = bookedBy;
    }
}
