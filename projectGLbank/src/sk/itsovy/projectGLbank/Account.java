package sk.itsovy.projectGLbank;

public class Account {

    private int IDacc;
    private String AccName;
    private int money;
    private int IDc;


    public Account(int IDacc, String accName, int money, int IDc) {
        this.IDacc = IDacc;
        this.AccName = accName;
        this.money = money;
        this.IDc = IDc;
    }

    public int getIDacc() {
        return IDacc;
    }

    public void setIDacc(int IDacc) {
        this.IDacc = IDacc;
    }

    public String getAccName() {
        return AccName;
    }

    public void setAccName(String accName) {
        AccName = accName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getIDc() {
        return IDc;
    }

    public void setIDc(int IDc) {
        this.IDc = IDc;
    }
}
