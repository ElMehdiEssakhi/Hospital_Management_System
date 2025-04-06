import Controller.AuthController;
import Model.AppModel;
import View.Login;
import View.SecrView;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //SecrView secrView = new SecrView();
        Login login = new Login();
        AppModel appModel = new AppModel();
        AuthController authController = new AuthController(login,appModel);
    }
}