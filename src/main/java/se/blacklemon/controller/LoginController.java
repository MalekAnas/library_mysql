package se.blacklemon.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import se.blacklemon.App;
import se.blacklemon.model.user.User;
import se.blacklemon.repository.UserRepository;
import se.blacklemon.repository.UserRepositoryImpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
    private final UserRepository userRepository = new UserRepositoryImpl();

    @FXML
    private TextField emali_tf;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField pass_tf;

    @FXML
    private Button register_btn;

    public LoginController() throws IOException {
    }

    @FXML
    void login(ActionEvent event) {
        try {

            User user = userRepository.getUser(emali_tf.getText());
            if (isAuthenticated(user)){

                Parent homePage = FXMLLoader.load(Objects.requireNonNull(App.class.getResource("user_dashboard.fxml")));
                Scene homePageScene = new Scene(homePage);
                Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                appStage.setScene(homePageScene);
                appStage.show();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isAuthenticated(User user) {
        return user.getPassword().equals(pass_tf.getText());
    }

    @FXML
    private void switchToRegister() throws IOException, SQLException {
        App.setRoot("register");
    }

}
