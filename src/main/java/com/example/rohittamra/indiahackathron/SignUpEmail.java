package com.example.rohittamra.indiahackathron;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpEmail extends AppCompatActivity implements View.OnClickListener {


    AutoCompleteTextView email1;
    EditText password1;
    Button b1;
    TextView already;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_email);
        password1=(EditText)findViewById(R.id.password);
        email1=(AutoCompleteTextView) findViewById(R.id.email);
        already=(TextView)findViewById(R.id.textView);
        b1=(Button)findViewById(R.id.register_button);
        firebaseAuth=FirebaseAuth.getInstance();
        progressdialog=new ProgressDialog(this);
        b1.setOnClickListener(this);
        already.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==b1)
        {
            registerUser();
        }
        if(v==already)
        {
            Toast.makeText(getApplicationContext(),"Login Activity opening please wait",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }


    }



    private void registerUser()
    {
        String email=email1.getText().toString();
        String password= password1.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            Toast.makeText(getApplicationContext(),"Email Address mustn't be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            Toast.makeText(getApplicationContext(),"Password mustn't be empty",Toast.LENGTH_SHORT).show();
            return;
        }

        progressdialog.setMessage("Registering User..");progressdialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {


                    startActivity(new Intent(getApplicationContext(),PowerCalculation.class));
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Not Registered",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });


    }
}
