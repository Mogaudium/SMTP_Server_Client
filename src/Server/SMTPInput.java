package Server;

import Server.Caesar_Cipher_Encryption;
import Server.Data;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.Math;

public class SMTPInput {
	public DataOutputStream outputStream;
	public DataInputStream inputStream;
        private String encmsg = "";
        
	public SMTPInput(Data data, DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
		this.inputStream = inputStream;
		this.outputStream = outputStream;
		listen(data);
	}
        
	private void listen(Data data) throws IOException {
		String mailinput = inputStream.readUTF();
                        while(true){
			if (mailinput.toUpperCase().startsWith("MAIL FROM:")){
                            outputStream.writeUTF("Server: 250 Success...\n\nType in your email in the following format 'egor@gmail.com'");
                            String sender = inputStream.readUTF();
                            data.sndrs(sender);
                            System.out.println("MAIL FROM: " + sender);
                            outputStream.writeUTF("Server: 250 Success...\n\nType 'RCPT TO:' to enter the receipent's email");
                            RCPT(data);
                            break;
                        }
                        else{
                            outputStream.writeUTF("Server: 501 Error in syntax, try again: ");
                            mailinput = inputStream.readUTF();
                           }
                        }
	}
	
	private void RCPT(Data data) throws IOException {
                String rcptinput = inputStream.readUTF();
                while(true){
                if (rcptinput.toUpperCase().equals("RCPT TO:")){
                    outputStream.writeUTF("Server: 250 Success...\n\nType in recipient in the following format 'egor@gmail.com'");
                    String rcpt = inputStream.readUTF();
                    data.rcpts(rcpt);
                    System.out.print("RCPT MAIL: " + rcpt);
                    outputStream.writeUTF("Server: 250 Success...\n\nType 'DATA' to start typing your message");
                    DATA(data);
                    break;
                }
                else{
                    outputStream.writeUTF("Server: 501 Error in syntax, try again: ");
                    rcptinput = inputStream.readUTF();
                    }
                }
	}
	
	private void DATA(Data data) throws IOException {
                String datainput = inputStream.readUTF();
                while(true){
                if(datainput.toUpperCase().equals("DATA")) {
                    outputStream.writeUTF("Server: 354 Success...\n\nType in your message and end data with <CR><LF>.<CR><LF>");
                    break;
                }
                    else{
                        outputStream.writeUTF("Server: 501 Error in syntax, try again: ");
                        datainput = inputStream.readUTF();
                        }
                }
                    while(true){
                        String messageinput = inputStream.readUTF();
                        if(messageinput.endsWith("<CR><LF>.<CR><LF>")){
                            String strNew = messageinput.substring(0, messageinput.length()-17);
                            System.out.print("\nUSER'S MESSAGE: " + strNew);
                            //Get encrypted
                            Caesar_Cipher_Encryption cce = new Caesar_Cipher_Encryption(strNew);
                            encmsg = cce.get_encrypted();
                            data.msgs(encmsg);
                            double id = Math.random();
                            System.out.println("\nMESSAGES ID: " + id);
                            outputStream.writeUTF("Server: 250 Success...\n\n" + "Message's id: " + id + "\nType 'QUIT' to quit the server");
                            break;
                            }
                         else{
                         outputStream.writeUTF("Server: 501 Error in syntax, try again: ");
                         messageinput = inputStream.readUTF();
                        }
                    }
                         while(true){
                         String quitinput = inputStream.readUTF();
                         if(quitinput.toUpperCase().equals("QUIT")){
                            outputStream.writeUTF("Server: 221 Success...\nGood bye and see you later :)");
                            break;
                        }
                        else{
                            outputStream.writeUTF("Server: 501 Error in syntax, try again");
                            quitinput = inputStream.readUTF();
                            }
                        }
                    }
        
        public String get_encrypted(){
            return encmsg;
        }
        
        
}
