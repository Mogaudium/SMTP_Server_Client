
package Client;

import Client.Caesar_Cipher_Decryption;
import Server.Data;
import java.io.*;
import java.net.*;
import java.util.*;

class Client{
    public static void main(String[] args){
        
        System.out.println("Client started...\n");
        
        DataOutputStream outputStream;//Method to output data
	DataInputStream inputStream;//Method to input data
        //Data data = new Data();
        
        try{
            Socket socket = new Socket("localhost", 5000);//Creation of client's socket
             
            outputStream = new DataOutputStream(socket.getOutputStream());//Sending data to server
            
	    inputStream  = new DataInputStream(socket.getInputStream());//Accepting data from server

            System.out.println(inputStream.readUTF());//Message from server
            
            Scanner kbinput = new Scanner(System.in);//Keyboard input from user
            String input = kbinput.nextLine();
  
            while (true){//Infinite while loop
                outputStream.writeUTF(input);
                System.out.println(inputStream.readUTF());
                input = kbinput.nextLine();
                if(input.contains("quit")){
                    //Caesar_Cipher_Decryption cce = new Caesar_Cipher_Decryption(data.get_msg());//Used to decrypt messages
                    //System.out.println(cce.get_decrypted());//Output decryoted message
                    kbinput.close();
                    break;
                  }
                }
            
        }catch (IOException e){e.printStackTrace();}
    }
}
