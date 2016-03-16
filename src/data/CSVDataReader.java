package data;

import human.Employee;
import human.Person;
import human.Skill;

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
        Set<Person> choosenPersons = new HashSet<>();
        HashMap<String,String> personData;
        List<Person> persons = new ArrayList<>();
        File csvFile = new File(csvFilePath);
        try {
            Scanner fileScanner = new Scanner(csvFile);
            while(fileScanner.hasNext()){
                String csvLine = fileScanner.nextLine();
                personData = getPersonData(csvLine);
                if(personData.get("Name").equals("Name")){continue;}

//                if(personData.get("SkillRate != "))
//                double skillRate = Double.parseDouble(personData.get("SkillRate"));

                Skill skill = new Skill(personData.get("Skill"), personData.get("SkillDescription"), 5);


                if (personData.get("Salary") != null) {
                    Employee employee = new Employee(personData.get("Name"), personData.get("Email"));
                    int salary = Integer.parseInt(personData.get("Salary"));
                    employee.setSalary(salary);
                    System.out.printf("Salary: "+personData.get("Salary"));
                } else {
                    Person person = new Person(personData.get("Name"), personData.get("Email"));
                }
                System.out.println("SkillRate: "+personData.get("SkillRate"));

//                if(persons.contains()){
//                    human.addSkill(skill);
//                }else{
//                    persons.add(human);
//                }
            }
            fileScanner.close();

            System.out.println(persons);
            choosenPersons = (Set)persons;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return choosenPersons;
    }

    public HashMap<String, String> getPersonData(String csvLine){
        HashMap<String,String> personData;
        String[] csvLineSplitted = csvLine.split(",");
        personData = new HashMap<>();
        personData.put("Name", csvLineSplitted[0]);
        personData.put("Email", csvLineSplitted[1]);
        personData.put("Skill", csvLineSplitted[2]);
        personData.put("SkillDescription", csvLineSplitted[3]);
        personData.put("SkillRate", csvLineSplitted[4]);
        if(!csvLine.endsWith(",")){
            personData.put("Salary",csvLineSplitted[5]);
        }
        return personData;
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
