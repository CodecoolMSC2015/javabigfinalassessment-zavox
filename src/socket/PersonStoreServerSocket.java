package socket;

import data.*;

import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PersonStoreServerSocket {

    public int port;
    public DataReader store;

    public PersonStoreServerSocket(int port){
//        try {
//            ServerSocket serversocket = new ServerSocket(port);
//            Socket clientSocket = serversocket.accept();
//
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void start(){
        readCSV();
    }

    public void readCSV(){
        store = new CSVDataReader("Documentation/persons.csv");
        store.getPersons();

    }



//    public Set<Person> getPersonsIfnecessary(){}
//





    public static void main(String[] args) {
        PersonStoreServerSocket personStoreServerSocket = new PersonStoreServerSocket(4444);
        personStoreServerSocket.readCSV();
    }
}
