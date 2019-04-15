package sk.itsovy.projectGLbank;

public class Client {

    private String FirstName;
    private String LastName;
    private String email;
    private int id;


    public Client(String firstName, String lastName, String email, int id) {
        FirstName = firstName;
        LastName = lastName;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
