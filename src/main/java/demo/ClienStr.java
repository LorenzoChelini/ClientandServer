package demo;
import java.io.*;
import java.net.*;
import java.rmi.UnknownHostException;
public class ClienStr {
    String serverName = "localhost";
    int serverPort = 3000;
    Socket  mySocket;
    BufferedReader tastiera;
    String userString;
    String stringReceivedFromtheServer;
    DataOutputStream outToServer;
    BufferedReader inFromServer;
    public Socket connetti(){
        System.out.println("2 CLIENT partito in esecuzione ...");
        try {
            //per l'input da tastiera
            tastiera = new BufferedReader(new InputStreamReader(System.in));
            //creo un socket
            mySocket= new Socket(serverName,serverPort);
            // mySocket = new Socket(InetAddress.getLocalHost(),3000);
            
            // associo due oggetti al socket per effettuare la scrittura e la lettura
            outToServer = new DataOutputStream(mySocket.getOutputStream());
            inFromServer = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host sconosciuto :(( ");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessioe!!!");
            System.exit(1);
        }
        return mySocket;
    }
    public void comunica(){
        try {
            System.out.println("4 ... inserisci la stringa da trasmettere al server:"+'\n');

            userString = tastiera.readLine();

            //la spedisco al server

            System.out.println("5... invio la stringa al server e attendo ..."); outToServer.writeBytes( userString+'\n');
            

            //leggo la risposta dal server

            stringReceivedFromtheServer = inFromServer.readLine();

            System.out.println("8... risposta dal server "+'\n' +stringReceivedFromtheServer );

            // chiudo la connessione

            System.out.println("9 CLIENT: termina elaborazione e chiude connessione" );

            mySocket.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione col server!!");
        }
    }
}
