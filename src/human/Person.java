package human;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Person implements Serializable, Comparable<Person> {
    public String name;
    public String email;
    public List<Skill> skillSet = new ArrayList<>();

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
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
        skillSet.add(skill);
    }

    @Override
    public boolean equals(Object o) {
        Person person = null;
        if (o instanceof Person) {
            person = (Person) o;
        }
        return getName().equals(person.getName());
    }

    @Override
    public int hashCode() {
        return -1;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", skillSet=" + skillSet +
                '}';
    }

    @Override
    public int compareTo(Person otherPerson) {
        if (calcAvgRate() > otherPerson.calcAvgRate()) {
            return -1;
        } else if (calcAvgRate() < otherPerson.calcAvgRate()) {
            return 1;
        } else return 0;
    }

    private double calcAvgRate() {
        double avgSkillRate;
        double sum = 0;
        int i = 0;
        for (Skill skill : skillSet) {
            sum += skill.getRate();
            i++;
        }
        avgSkillRate = sum / i;
        return avgSkillRate;
    }
}
