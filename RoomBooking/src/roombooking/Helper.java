package roombooking;


public class Helper {
    public boolean checkLogin(String email, String password){
        if(email.equals("staff1@gmail.com") && password.equals("staff1")){
            return true;
        }
        if(email.equals("staff2@gmail.com") && password.equals("staff2")){
            return true;
        }
        if(email.equals("staff3@gmail.com") && password.equals("staff3")){
            return true;
        }
        
        return false;
    }
}
