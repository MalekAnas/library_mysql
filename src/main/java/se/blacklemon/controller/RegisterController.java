package se.blacklemon.controller;

import java.io.IOException;
import java.sql.SQLException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import se.blacklemon.App;
import se.blacklemon.model.user.User;
import se.blacklemon.model.user.UserBuilder;
import se.blacklemon.repository.UserRepository;
import se.blacklemon.repository.UserRepositoryImpl;

public class RegisterController {


    private final UserRepository userRepository = new UserRepositoryImpl();

    @FXML
    private TextField emali_tf;

    @FXML
    private TextField fname_tf;

    @FXML
    private TextField lname_tf;

    @FXML
    private Button login_btn;

    @FXML
    private PasswordField pass_tf;

    @FXML
    private Button register_btn;

    @FXML
    private PasswordField repass_tf;

    @FXML
    private Label email_check;

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("login");
    }

    @FXML
    private void registerUser() throws IOException {
        if (validatePasswordMatching()) {
            User user = new UserBuilder()
                    .withFirstName(fname_tf.getText())
                    .withLastName(lname_tf.getText())
                    .withEmail(emali_tf.getText())
                    .withPassword(pass_tf.getText())
                    .isStaff(false)
                    .withUniqueId()
                    .build();

            checkEmailUsed();
            userRepository.saveUser(user);
        }
    }

    private boolean validatePasswordMatching() throws IOException {
        return !pass_tf.getText().isEmpty() &&
                pass_tf.getText().equals(repass_tf.getText());
    }

    @FXML
    private void checkEmailUsed(){
        emali_tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue)  {
                try {
                    if (userRepository.isEmailValid(newValue)){
                        email_check.setText("Email accepted!");
                        email_check.setTextFill( Color.color(0,0.8,0));
                    }
                    else if (!userRepository.isEmailValid(newValue)){
                        email_check.setText("Email is used!");
                        email_check.setTextFill( Color.color(1, 0 ,0));
                    }
                } catch (SQLException throwable) {
                    throwable.printStackTrace();
                }
            }
        });
    }
}