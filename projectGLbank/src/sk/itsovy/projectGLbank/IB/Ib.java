package sk.itsovy.projectGLbank.IB;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import sk.itsovy.projectGLbank.Globals;
import sk.itsovy.projectGLbank.afterLogWindow.AfterLog;

import javax.xml.soap.Text;

public class Ib {
    private int idc;
    public TextField oldPass;
    public TextField newPass;

    public void getId(int idc){
        //System.out.println(ida);
        this.idc = idc;

    }

   /* public void unblockCard(ActionEvent actionEvent){
        //System.out.println("id account "+ida);
        Globals.db.unblockCard(ida);
    }*/


    public void resetAccount(ActionEvent actionEvent) {
        boolean userValid = Globals.db.checkPassExists(idc,oldPass.getText());

        if(userValid){
            Globals.db.resetPass(idc,newPass.getText());
            oldPass.setText("");
            newPass.setText("");
        }
        else
        {
            System.out.println("bad old pass");
            oldPass.setText("");
        }
    }
}
