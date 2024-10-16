
package Server;   
   
//Caesar cipher encryption class   
public class Caesar_Cipher_Encryption
{   

    private String msg = "";//Unecrypted message from client 
    private String encmsg = "";//Encrypted message from client
    private int key = 10;//Key from user
    
    public Caesar_Cipher_Encryption(String Message){
        msg = Message;
        char ch;
        
        for(int i = 0; i < msg.length(); ++i){
            ch = msg.charAt(i);
            if(ch >= 'a' && ch <= 'z'){
                ch = (char)(ch + key);
                if(ch > 'z'){
                    ch = (char)(ch - 'z' + 'a' -1);
                }
	        encmsg += ch;
	    } else if(ch >= 'A' && ch <= 'Z'){
                ch = (char)(ch + key);
	        if(ch > 'Z'){
	            ch = (char)(ch - 'Z' + 'A' -1);
	        }
	        encmsg += ch;
	    } else {
                encmsg += ch;
	    }
	}
    }
    public String get_encrypted(){
        return encmsg;
    }
}

