package com.example.codenotes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    private Hyperlink resetLink;

    @FXML
    private VBox loginForm;

    @FXML
    private Text appTitle;

    @FXML
    private Text appSubtitle;

    @FXML
    private Button loginTab;

    @FXML
    private Button signUpTab;

    @FXML
    private Button resetTab;

    @FXML
    private VBox signupForm;

    @FXML
    private TextField fullNameField;

    @FXML
    private TextField signupEmailField;

    @FXML
    private PasswordField signupPasswordField;

    @FXML
    private Button createAccountButton;

    @FXML
    private Hyperlink signupLoginLink;

    @FXML
    private Hyperlink signupResetLink;

    @FXML
    private VBox resetForm;

    @FXML
    private TextField resetEmailField;

    @FXML
    private Button resetPasswordButton;

    @FXML
    private Hyperlink resetLoginLink;

    @FXML
    private Hyperlink resetSignupLink;

    @FXML
    private void initialize() {
        // Start with login form visible and other forms hidden
        showLoginForm();

        // Set up event handlers for tabs
        loginTab.setOnAction(e -> showLoginForm());
        signUpTab.setOnAction(e -> showSignupForm());
        resetTab.setOnAction(e -> showResetForm());

        // Set up event handlers for form actions
        loginButton.setOnAction(e -> handleLogin());
        createAccountButton.setOnAction(e -> handleSignup());
        resetPasswordButton.setOnAction(e -> handleResetPassword());

        // Set up event handlers for links in login form
        signUpLink.setOnAction(e -> showSignupForm());
        resetLink.setOnAction(e -> showResetForm());

        // Set up event handlers for links in signup form
        signupLoginLink.setOnAction(e -> showLoginForm());
        signupResetLink.setOnAction(e -> showResetForm());

        // Set up event handlers for links in reset form
        resetLoginLink.setOnAction(e -> showLoginForm());
        resetSignupLink.setOnAction(e -> showSignupForm());

        // Enter key support
        setupEnterKeySupport();
    }

    @FXML
    private void showLoginForm() {
        loginForm.setVisible(true);
        loginForm.setManaged(true);
        signupForm.setVisible(false);
        signupForm.setManaged(false);
        resetForm.setVisible(false);
        resetForm.setManaged(false);

        // Update tab styles
        loginTab.setStyle("-fx-background-color: transparent; -fx-text-fill: #667eea; -fx-font-weight: bold; -fx-font-size: 14; -fx-underline: true; -fx-cursor: hand;");
        signUpTab.setStyle("-fx-background-color: transparent; -fx-text-fill: #a0aec0; -fx-font-size: 14; -fx-cursor: hand;");
        resetTab.setStyle("-fx-background-color: transparent; -fx-text-fill: #a0aec0; -fx-font-size: 14; -fx-cursor: hand;");

        appTitle.setText("Code Notes");
        appSubtitle.setText("Your intelligent code snippet manager");
    }

    @FXML
    private void showSignupForm() {
        loginForm.setVisible(false);
        loginForm.setManaged(false);
        signupForm.setVisible(true);
        signupForm.setManaged(true);
        resetForm.setVisible(false);
        resetForm.setManaged(false);

        // Update tab styles
        loginTab.setStyle("-fx-background-color: transparent; -fx-text-fill: #a0aec0; -fx-font-size: 14; -fx-cursor: hand;");
        signUpTab.setStyle("-fx-background-color: transparent; -fx-text-fill: #667eea; -fx-font-weight: bold; -fx-font-size: 14; -fx-underline: true; -fx-cursor: hand;");
        resetTab.setStyle("-fx-background-color: transparent; -fx-text-fill: #a0aec0; -fx-font-size: 14; -fx-cursor: hand;");

        appTitle.setText("Code Notes");
        appSubtitle.setText("Your intelligent code snippet manager");
    }

    @FXML
    private void showResetForm() {
        loginForm.setVisible(false);
        loginForm.setManaged(false);
        signupForm.setVisible(false);
        signupForm.setManaged(false);
        resetForm.setVisible(true);
        resetForm.setManaged(true);

        // Update tab styles
        loginTab.setStyle("-fx-background-color: transparent; -fx-text-fill: #a0aec0; -fx-font-size: 14; -fx-cursor: hand;");
        signUpTab.setStyle("-fx-background-color: transparent; -fx-text-fill: #a0aec0; -fx-font-size: 14; -fx-cursor: hand;");
        resetTab.setStyle("-fx-background-color: transparent; -fx-text-fill: #667eea; -fx-font-weight: bold; -fx-font-size: 14; -fx-underline: true; -fx-cursor: hand;");

        appTitle.setText("Code Notes");
        appSubtitle.setText("Reset your password");
    }

    private void setupEnterKeySupport() {
        emailField.setOnAction(e -> passwordField.requestFocus());
        passwordField.setOnAction(e -> handleLogin());

        fullNameField.setOnAction(e -> signupEmailField.requestFocus());
        signupEmailField.setOnAction(e -> signupPasswordField.requestFocus());
        signupPasswordField.setOnAction(e -> handleSignup());

        resetEmailField.setOnAction(e -> handleResetPassword());
    }

    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please enter both email and password.");
            return;
        }

        // For demo purposes - accept any non-empty credentials
        if (!email.isEmpty() && !password.isEmpty()) {
            try {
                // Load dashboard
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/codenotes/dashboard-view.fxml"));
                Parent root = loader.load();

                Stage stage = (Stage) loginButton.getScene().getWindow();
                stage.setScene(new Scene(root, 1200, 800));
                stage.setTitle("Code Notes - Dashboard");
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "Failed to load dashboard.");
            }
        } else {
            showAlert("Login Failed", "Invalid email or password.");
        }
    }

    private void handleSignup() {
        String fullName = fullNameField.getText();
        String email = signupEmailField.getText();
        String password = signupPasswordField.getText();

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        if (password.length() < 6) {
            showAlert("Error", "Password must be at least 6 characters long.");
            return;
        }

        // For demo purposes - successful signup
        showAlert("Success", "Account created successfully! You can now login.");
        showLoginForm();

        // Clear signup fields
        fullNameField.clear();
        signupEmailField.clear();
        signupPasswordField.clear();
    }

    private void handleResetPassword() {
        String email = resetEmailField.getText();

        if (email.isEmpty()) {
            showAlert("Error", "Please enter your email address.");
            return;
        }

        // For demo purposes - simulate password reset
        showAlert("Reset Email Sent", "Password reset instructions have been sent to: " + email);
        showLoginForm();

        // Clear reset field
        resetEmailField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}