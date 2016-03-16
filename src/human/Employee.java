package human;


public class Employee extends Person {
    public int salary;
    public String jobTitle;

    public Employee(String name, String email) {
        this.name = name;
        this.email = email;
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

    @Override
    public String toString() {
        return "Employee{" +
                "salary=" + salary +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
