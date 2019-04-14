package sk.itsovy.projectGLbank;

public class Client {

    private String FirstName;
    private String LastName;
    private int id;

    public Client(String firstName, String lastName, int id) {
        FirstName = firstName;
        LastName = lastName;
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
