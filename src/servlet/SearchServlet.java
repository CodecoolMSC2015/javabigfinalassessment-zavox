package servlet;

import data.SearchType;
import human.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String skillsParam = req.getParameter("skills");
        String searchTypeParam = req.getParameter("searchType");
        SearchType searchType = null;
        System.out.println(skillsParam+"  "+searchTypeParam);

        if(searchTypeParam.equals("optional")){
            searchType = SearchType.OPTIONAL;
        }else if(searchTypeParam.equals("mandatory")){
            searchType = SearchType.MANDATORY;
        }

        getPersonsIfnecessary(skillsParam, searchType);

    }

    public Set<Person> getPersonsIfnecessary(String searchCriteria, SearchType searchType){
        Set<Person> personSet=null;
        try {
            Socket socket = new Socket("localhost",4444);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeUTF(searchCriteria);
            objectOutputStream.writeObject(searchType);

            Object inputObject = objectInputStream.readObject();
            if(inputObject instanceof Set){
                personSet = (Set<Person>)inputObject;
            }
            objectInputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personSet;
    }

    public void checkInSessionData(){}

    public void storeDataInSession(){}

    public void createOrderedList(){}

    public void createResponse(){}

}
