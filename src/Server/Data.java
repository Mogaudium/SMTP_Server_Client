package Server;

import java.util.ArrayList;

public class Data {
    
    public ArrayList<String> rcpt = new ArrayList<String>();
    public ArrayList<String> sndr = new ArrayList<String>();
    public String msg;
    
    
    public ArrayList rcpts(String input){//Add recepient to the list 
        rcpt.add(input);
        return rcpt;
    }
    
    public ArrayList sndrs(String input){//Add sender to the list
        sndr.add(input);
        return sndr;
    }
    
    public String msgs(String input){//Add encrypted message to the list 
        msg = input;
        return msg;
    }
    
    
    
    public ArrayList get_sndr(){
        return sndr;
    }
    
    public ArrayList get_rcpt(){
        return rcpt;
    }
    
    public String get_msg(){
        return msg;
    }
    
}
