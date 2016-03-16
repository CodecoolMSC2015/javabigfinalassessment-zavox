package data;

import human.Employee;
import human.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class CSVDataReader extends DataReader{
    public String csvFilePath;


    public CSVDataReader(String csvFilePath){
        this.csvFilePath = csvFilePath;

    }

    @Override
    public Set<Person> getPersons() {
        try {
            Set<Person> choosenPersons = new HashSet<>();
            HashMap<String,String> personData;
            List<Person> persons = new ArrayList<>();
            File csvFile = new File(csvFilePath);
            Scanner fileScanner = new Scanner(csvFile);
            while(fileScanner.hasNext()){
                String csvLine = fileScanner.nextLine();
                String[] csvLineSplitted = csvLine.split(",");
                personData = new HashMap<>();
                personData.put("Name",csvLineSplitted[0]);
                personData.put("Email",csvLineSplitted[1]);
                personData.put("Skill",csvLineSplitted[2]);
                personData.put("SkillDescription",csvLineSplitted[3]);
                personData.put("SkillRate",csvLineSplitted[4]);
                personData.put("Salary",csvLineSplitted[5]);

                Person human;
                if (personData.get("Salary") != "") {
                    human = new Employee(personData.get("Name"), personData.get("Email"));
//                    human = (Employee)human;
                    int salary = Integer.parseInt(personData.get("Salary"));
                    ((Employee)human).setSalary(salary);
                } else {
                    human = new Person(personData.get("Name"), personData.get("Email"));
                }

                if(persons.contains(human)){
//                    human.addSkill();

                }




            }
            fileScanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

//    public Person checkPersonOptional(HashMap<String, String> personData){
//        Person person;
//        if
//        person = new Person(personData.get("Name"), personData.get("Email"));
//
//
//        return person;
//    }



}
