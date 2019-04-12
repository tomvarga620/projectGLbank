package sk.itsovy.projectGLbank;

public class Employee {

    private String Firstname;
    private String Surname;
    private int position;
    private int id;

    public Employee(String firstname, String surname, int position, int id) {
        this.Firstname = firstname;
        this.Surname = surname;
        this.position = position;
        this.id = id;
    }


    public String getFirstname() {
        return Firstname;
    }

    public String getSurname() {
        return Surname;
    }

    public int getPosition() {
        return position;
    }

    public int getId() {
        return id;
    }
}
