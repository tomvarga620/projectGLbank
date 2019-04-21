package sk.itsovy.projectGLbank;

public class Card {

    private int id;
    private int ida;
    private String PIN;
    private int expireM;
    private int expireY;
    private boolean active;

    public Card(int id, int ida, String PIN, int expireM, int expireY, boolean active) {
        this.id = id;
        this.ida = ida;
        this.PIN = PIN;
        this.expireM = expireM;
        this.expireY = expireY;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIda() {
        return ida;
    }

    public void setIda(int ida) {
        this.ida = ida;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    public int getExpireM() {
        return expireM;
    }

    public void setExpireM(int expireM) {
        this.expireM = expireM;
    }

    public int getExpireY() {
        return expireY;
    }

    public void setExpireY(int expireY) {
        this.expireY = expireY;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
