package com.example.rohittamra.indiahackathron;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    public Button mEmailSignInButton,signupwithemail;
    private EditText mEmailView;
    private EditText mPasswordView;

    ProgressDialog progressdialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mPasswordView = (EditText) findViewById(R.id.password);
        mEmailView = (EditText) findViewById(R.id.email);
        progressdialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        mEmailSignInButton = (Button) findViewById(R.id.login_button);
        mEmailSignInButton.setOnClickListener(this);
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),PowerCalculation.class));
        }

        signupwithemail=(Button)findViewById(R.id.sign_up_email);
        signupwithemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpEmail.class));
            }
        });
    }



    public void onClick(View view){
        if(view==mEmailSignInButton)
        {
            attemptLogin();
        }
    }

    public void attemptLogin() {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(),"Email Address mustn't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password) )
        {
            Toast.makeText(getApplicationContext(),"Password mustn't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        progressdialog.setMessage("Login in Please Wait..");progressdialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                progressdialog.dismiss();
                if(task.isSuccessful())
                {
                    startActivity(new Intent(getApplicationContext(),PowerCalculation.class));
                    Toast.makeText(getApplicationContext(), "logged in.. successfull", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Email or Password is Incorrect",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }
}
