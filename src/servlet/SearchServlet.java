package servlet;

import data.SearchType;
import human.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

public class SearchServlet extends HttpServlet {
    Integer sessionAttributeID=0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();

        String skillsParam = req.getParameter("skills");
        Set<String> searchCriteria = searchCriteriaParse(skillsParam);

        String searchTypeParam = req.getParameter("searchType");
        SearchType searchType = null;
        if(searchTypeParam.equals("optional")){
            searchType = SearchType.OPTIONAL;
        }else if(searchTypeParam.equals("mandatory")){
            searchType = SearchType.MANDATORY;
        }

        resp.setContentType("text/html;charset=UTF-8");

        Set<Person> persons = getPersonsIfNecessary(searchCriteria, searchType);
        List<Person> orderedPersons = createOrderedList(persons);

        req.getRequestDispatcher("index.html").include(req,resp);

        for(Person p: orderedPersons){
            pw.println(p);
            pw.println("<br/>");
            pw.println("<br/>");


        }
//        storeDataInSession(req,resp,searchCriteria);
//        checkInSessionData(req,resp,searchCriteria);


    }

    private Set<Person> getPersonsIfNecessary(Set<String> searchCriteria, SearchType searchType){
        Set<Person> personSet=null;
        try {
            Socket socket = new Socket("localhost",4444);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(searchCriteria);
            objectOutputStream.writeObject(searchType);

            Object inputObject = objectInputStream.readObject();
            if(inputObject instanceof Set){
                personSet = (Set)inputObject;
            }
            objectInputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personSet;
    }

    private boolean checkInSessionData(HttpServletRequest req, HttpServletResponse resp, Set<String> searchCriteria){
        HttpSession session = req.getSession();
        for(Integer i=1;i<=sessionAttributeID;i++){
            Set<String> setAtCurrentID = (Set<String>) session.getAttribute(i.toString());
            if(setAtCurrentID.equals(searchCriteria)){
                return true;
            }
        }
        return false;
    }

    private void storeDataInSession(HttpServletRequest req, HttpServletResponse resp, Set<String> searchCriteria){
        HttpSession session = req.getSession();
        sessionAttributeID++;
        session.setAttribute(sessionAttributeID.toString(), searchCriteria);
    }

    private List<Person> createOrderedList(Set<Person> personSet){
        List<Person> personList = new ArrayList<>(personSet);
        Collections.sort(personList);
        return personList;
    }

    private void createResponse(){}

    private Set<String> searchCriteriaParse(String searchCriteria){
        String[] splitted = searchCriteria.split(";");
        return new HashSet<>(Arrays.asList(splitted));
    }

}
