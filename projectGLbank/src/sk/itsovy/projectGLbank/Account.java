package sk.itsovy.projectGLbank;

public class Account {

    private int IDacc;
    private String AccNum;
    private double money;
    private int IDc;


    public Account(int IDacc, String accName, double money, int IDc) {
        this.IDacc = IDacc;
        this.AccNum = accName;
        this.money = money;
        this.IDc = IDc;
    }

    public int getIDacc() {
        return IDacc;
    }

    public void setIDacc(int IDacc) {
        this.IDacc = IDacc;
    }

    public String getAccNum() {
        return AccNum;
    }

    public void setAccNum(String accNum) {
        AccNum = accNum;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getIDc() {
        return IDc;
    }

    public void setIDc(int IDc) {
        this.IDc = IDc;
    }
}
