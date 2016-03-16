package human;

import java.util.ArrayList;
import java.util.List;


public class Person {
    public String name;
    public String email;
    public List<Skill> skillSet;

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
        skillSet = new ArrayList<>();
    }

    public Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Skill> getSkillset() {
        return skillSet;
    }

    public void setSkillset(List<Skill> skillSet) {
        this.skillSet = skillSet;
    }


    public void addSkill(Skill skill) {
        if (!skillSet.contains(skill)) {
            skillSet.add(skill);
        }
    }

    @Override
    public boolean equals(Object o) {
        Person person=null;
        if(o instanceof Person){
            person = (Person) o;
        }
        return getName().equals(person.getName());
    }

    @Override
    public int hashCode() {
        return -1;
    }
}
