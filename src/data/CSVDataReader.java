package data;

import human.Employee;
import human.Person;
import human.Skill;

import java.io.File;
import java.util.*;

public class CSVDataReader extends DataReader{
    private String csvFilePath;
    private List<Person> persons = new ArrayList<>();

    public CSVDataReader(String csvFilePath){
        this.csvFilePath = csvFilePath;
        readPersons();
    }

    @Override
    public Set<Person> getPersons() {
        Set<Person> chosenPersons = new HashSet<>();
        if(searchType.equals(SearchType.OPTIONAL)){
            for(Person person : persons){
                for(Skill skill: person.getSkillset()){
                    if(searchCriteria.contains(skill.getName())){
                        chosenPersons.add(person);
                        break;
                    }
                }
            }
        }else if(searchType.equals(SearchType.MANDATORY)){
            Set<String> testCriterias = new HashSet<>();
            for(Person person : persons) {
                for (Skill skill : person.getSkillset()) {
                    if (searchCriteria.contains(skill.getName())) {
                        testCriterias.add(skill.getName());
                    }
                }
                if(searchCriteria.equals(testCriterias)){
                    chosenPersons.add(person);
                }
                testCriterias.clear();
            }
        }
        return chosenPersons;
    }

    private Person createPerson(String csvLine){
        String[] csvLineSplitted = csvLine.split(",");
        String name = csvLineSplitted[0];
        String email = csvLineSplitted[1];

        if(name.equals("Name")){
            return null;
        }
        else if(!csvLine.endsWith(",")){
            int salary = Integer.parseInt(csvLineSplitted[5]);
            return new Employee(name,email,salary);

        }else{
            return new Person(name, email);
        }

    }

    private Skill createSkill(String csvLine){

        String[] csvLineSplitted = csvLine.split(",");
        String skill = csvLineSplitted[2];
        String skillDescription = csvLineSplitted[3];
        double skillRate = Double.parseDouble(csvLineSplitted[4]);

        return new Skill(skill,skillDescription,skillRate);
    }

    private void readPersons(){
        File csvFile = new File(csvFilePath);
        try {
            Scanner fileScanner = new Scanner(csvFile);
            while(fileScanner.hasNext()){
                String csvLine = fileScanner.nextLine();

                Person person = createPerson(csvLine);
                if(person == null){
                    continue;
                }

                Skill skill= createSkill(csvLine);

                if(persons.contains(person)){
                    for(Person p : persons){
                        if(p.getName().equals(person.getName())){
                            p.addSkill(skill);
                        }
                    }
                }else{
                    person.addSkill(skill);
                    persons.add(person);
                }
            }
            fileScanner.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
