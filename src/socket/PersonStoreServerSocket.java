package socket;

import data.*;
import human.Person;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

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
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
                objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

                Set<String> searchCriteria = (Set<String>) objectInputStream.readObject();
                SearchType searchType = (SearchType)objectInputStream.readObject();

                Set<Person> chosenPersons = readCSV(searchCriteria, searchType);

                objectOutputStream.writeObject(chosenPersons);

                objectInputStream.close();
                objectOutputStream.close();
                clientSocket.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Set<Person> readCSV(Set<String> searchCriteria, SearchType searchType){
        store = new CSVDataReader("Documentation/persons.csv");
        store.setSearchCriteria(searchCriteria);
        store.setSearchType(searchType);
        return store.getPersons();
    }


    public static void main(String[] args) {
        PersonStoreServerSocket personStoreServerSocket = new PersonStoreServerSocket(4444);
        personStoreServerSocket.start();
    }
}
