package socket;

import data.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class PersonStoreServerSocket {

    public int port;
    public DataReader store;
    ServerSocket serversocket;
    Socket clientSocket;
    ObjectOutputStream objectOutputStream;
    ObjectInputStream objectInputStream;

    public PersonStoreServerSocket(int port){
        try {
            serversocket = new ServerSocket(port);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        try {
            while(true) {
                clientSocket = serversocket.accept();
                objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

                String searchCriteria = objectInputStream.readUTF();
                SearchType searchType = (SearchType)objectInputStream.readObject();
                System.out.println(searchCriteria);
                System.out.println(searchType);


                readCSV(searchCriteria, searchType);

                clientSocket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readCSV(String searchCriteria, SearchType searchType){
        store = new CSVDataReader("Documentation/persons.csv");
        store.setSearchCriteria(searchCriteria);
        store.setSearchType(searchType);
        System.out.println(store.getPersons());

    }




    public static void main(String[] args) {
        PersonStoreServerSocket personStoreServerSocket = new PersonStoreServerSocket(4444);
        personStoreServerSocket.start();
    }
}
