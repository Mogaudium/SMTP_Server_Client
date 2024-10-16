
package Server;

import java.util.ArrayList;

public class ClientData {
    public ArrayList<String> username = new ArrayList<String>();
    public ArrayList<String> password = new ArrayList<String>();
    
    public ArrayList user(String input){//Add user to the list 
        username.add(input);
        return username;
    }
    
    public ArrayList pass(String input){//Add password to the list
        password.add(input);
        return password;
    }
    
    public ArrayList get_username(){//Return username
        return username;
    }
    
    public ArrayList get_password(){//Return password
        return password;
    }
}
