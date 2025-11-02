package aydin.firebasedemo;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;

public class WelcomeController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    private void onRegister(ActionEvent event) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(emailField.getText())
                    .setPassword(passwordField.getText());
            UserRecord user = DemoApp.fauth.createUser(request);
            messageLabel.setText("Registered: " + user.getEmail());
        } catch (FirebaseAuthException e) {
            messageLabel.setText("Registration failed: " + e.getMessage());
        }
    }

    @FXML
    private void onSignIn(ActionEvent event) throws IOException {
        // Normally you'd verify with Firebase client SDK, but this demo skips that
        FXMLLoader loader = new FXMLLoader(getClass().getResource("primary.fxml"));
        Stage stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(loader.load()));
        stage.setTitle("Register / Data Access");
    }
}