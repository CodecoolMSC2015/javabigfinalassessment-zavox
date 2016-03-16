package human;


public class Employee extends Person {
    public int salary;
    public String jobTitle;

    public Employee(String name, String email, int salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

}
