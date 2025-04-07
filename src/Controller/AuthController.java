package Controller;

import Model.AppModel;
import View.DoctorView;
import View.Login;
import View.SecrView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthController implements ActionListener {
    Login login;
    AppModel appModel;
    public AuthController(Login login, AppModel appModel) {
        this.login = login;
        this.appModel = appModel;
        login.getLoginButton().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = login.getUsername();
        String password = login.getPassword();
        String result = appModel.authenticate(name, password);
        if(result.equals("none")) {
            System.out.println("Login Failed");
        }else{
            System.out.println("Login Successful");
            login.dispose();
            if(result.equals("secretary")) {SecrView secrView = new SecrView();
                                            new ManageDoctorsController(secrView.getDocMView(),appModel);
                                            new ManagePatientsController(secrView.getPatientView(), appModel);
                                            new ManageAppController(secrView.getAppView(),appModel);
            }
            else if(result.equals("admin")) {SecrView adminView = new SecrView();}
            else if (result.equals("doctor")){DoctorView doctorView = new DoctorView();}
        }

    }
}
