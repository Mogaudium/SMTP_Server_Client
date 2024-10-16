
package Server;

import Server.Data;
import Server.SMTPInput;
import Server.ClientData;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SMTPServer {
    
                public static void main(String[] args) throws IOException{
                int port = 5000;//Port of the server
                Data data = new Data();
                ClientData clientdata = new ClientData();
                System.out.println("SMTP Server started at port: " + port);
		try {
                    ServerSocket server = new ServerSocket(port);
                    server.setReuseAddress(true);
			while (true){
				Socket socket = server.accept();
                                System.out.println("Client connected...\nClient's information: " + socket);
                                NewConnection clientsock = new NewConnection(data, clientdata, socket);
                                Thread th = new Thread(clientsock);
                                th.start();
                                }
                    }catch (IOException e){e.printStackTrace();}
                }
	
	private static class NewConnection implements Runnable{
                private final Socket clientsocket;
		DataOutputStream outputStream;
		DataInputStream inputStream;
                Data data;
                ClientData clientdata;
                public NewConnection(Data data, ClientData clientdata, Socket socket)
                {
                    this.clientsocket = socket;
                    this.data = data;
                    this.clientdata = clientdata;
                }
                public void run(){
		try {
                        System.out.println("Ready to receive messages from client: " + clientsocket);
			outputStream = new DataOutputStream(clientsocket.getOutputStream());
			inputStream  = new DataInputStream(clientsocket.getInputStream());
                        
                        //Username and password authentication
			outputStream.writeUTF("Server: 220 Ready to recieve...\nPlease enter your username:");//Message to client
                        
                        String username = "";
                        String password = "";
                        username = inputStream.readUTF();
                        clientdata.user(username);
                        outputStream.writeUTF("Please enter your password:");//Message to client
                        password =inputStream.readUTF();
                        clientdata.pass(password);
                        
                        outputStream.writeUTF("Please type 'HELO' to start composing an email: ");
                        
			EHLO(inputStream, outputStream);//Ehlo method calling
                        
                        SMTPInput smtpinput = new SMTPInput(data, inputStream, outputStream);//Imput methods calling
                        
                        System.out.println("Encrypted client's message: " + smtpinput.get_encrypted());
			
                    } catch(IOException e){}
                
                System.out.println("Sendre's mails: " + data.get_sndr());
                System.out.println("Recipients mails: " + data.get_rcpt());
                System.out.println( "Usernames" + clientdata.get_username());
                System.out.println( "Passwords" + clientdata.get_password());
                
                CloseSocket(clientsocket);
            }
	}
	
	private static void EHLO(DataInputStream inputStream, DataOutputStream outputStream) throws IOException {
		String ehlo = inputStream.readUTF();//Input from client "helo" expected
                ehlo = ehlo.toLowerCase();
                
                while(true)
                {
                if(ehlo.startsWith("ehlo") || ehlo.startsWith("helo")){
                    outputStream.writeUTF("Server: 250 Success...\n\nType 'MAIL FROM:' to enter your email");//Success input from client
                    break;
                }
                else {
                    outputStream.writeUTF("501: Error in syntax, try again: ");
                    ehlo = inputStream.readUTF();
                    }
                }
	}
		
 	private static void CloseSocket(Socket socket) {
		try {
			socket.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}

