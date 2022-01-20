package piraino.template.loginpage;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.shobhitpuri.custombuttons.GoogleSignInButton;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import static java.lang.String.format;

public class LoginPageActivity extends AppCompatActivity {

    MaterialButton loginButton, forgotPasswordButton, registerButton;
    GoogleSignInButton googleSignInButton;
    RelativeLayout imageRelativeLayout;
    ConstraintLayout screenLayout, infoLayout;
    TextInputEditText userEditText, passwordEditText;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page_main);

        SetContext();
    }

    private void SetContext()
    {
        screenLayout = findViewById(R.id.screen_layout);
        infoLayout = findViewById(R.id.login_page_info_layout);

        imageRelativeLayout = findViewById(R.id.login_page_image_layout);
        ScaleImage();

        userEditText = findViewById(R.id.login_page_username_input_edit_text);
        passwordEditText = findViewById(R.id.login_page_password_input_edit_text);

        loginButton = findViewById(R.id.login_page_login_button);
        loginButton.setOnClickListener(view -> {
            Boolean userInformationInserted = RetrieveUserInformation();

            if (userInformationInserted) {
                // try login, if fails dipslay error message
            }
        });

        forgotPasswordButton = findViewById(R.id.login_page_forgot_password);
        forgotPasswordButton.setOnClickListener(view -> {
            // Redirect to forgot passowrd page and pass email if inserted
        });

        registerButton = findViewById(R.id.login_page_register_button);
        registerButton.setOnClickListener(view -> {
            RetrieveUserInformation();
            // Redirect to register passing the information retrieved if any
        });

        googleSignInButton = findViewById(R.id.login_page_google_sing_in);
        googleSignInButton.setOnClickListener(view -> {
            // login with google API
        });
    }

    private void ScaleImage()
    {
       ViewGroup.LayoutParams parameters =  imageRelativeLayout.getLayoutParams();
       LoginPageModel loginPageModel = new LoginPageModel();

       int[] heightParameters = GetFieldsHeightParameter();

       parameters.height = loginPageModel.CalculateScaledImageHeight(heightParameters);
       parameters.width = imageRelativeLayout.getWidth();

       imageRelativeLayout.setLayoutParams(parameters);
    }

    private int[] GetFieldsHeightParameter()
    {
        int screenHeight = screenLayout.getMaxHeight();
        int infoLayoutHeight = infoLayout.getHeight();
        return new int[]{screenHeight, infoLayoutHeight};
    }

    private Boolean RetrieveUserInformation() {
        username = userEditText.getEditableText().toString();
        password = passwordEditText.getEditableText().toString();

        if (username.isEmpty()) {
            String USERNAME = "Username";
            DisplayInfoToastMessage(USERNAME);
            return false;
        }

        if (password.isEmpty()) {
            String PASSWORD = "Password";
            DisplayInfoToastMessage(PASSWORD);
            return false;
        }

        return true;
    }

    private void DisplayInfoToastMessage(String attribute)
    {
        Toast.makeText(LoginPageActivity.this,
                format(String.valueOf(R.string.toast_info_message), attribute),
                Toast.LENGTH_SHORT).show();
    }
}