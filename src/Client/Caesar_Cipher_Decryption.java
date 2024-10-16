
package Client;

import Server.Caesar_Cipher_Encryption;
import java.util.ArrayList;

public class Caesar_Cipher_Decryption 
{
    
    private String encmsg = "";//Encrypted message that should be decryted
    private String decmsg = "";//Decrypted message
    private int key;
    
    public Caesar_Cipher_Decryption (String message){
        encmsg = message;
        key = 10;
        char ch;
        
        for(int i = 0; i < encmsg.length(); ++i){
            ch = encmsg.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch - key);
                if(ch > 'z'){
	            ch = (char)(ch + 'z' - 'a' + 1);
                }
	        decmsg += ch;
	    } else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch - key);
	        if(ch > 'Z'){
	            ch = (char)(ch + 'Z' - 'A' + 1);
	        }
	        decmsg += ch;
	    } else {
                decmsg += ch;
	    }
	}
    }
    public String get_decrypted(){
        return decmsg;
    }  
   
}
