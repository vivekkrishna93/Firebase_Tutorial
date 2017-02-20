package com.example.vivek.firebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText name, emailid, password, age;
    Button bt1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        name = (EditText) findViewById(R.id.name);
        emailid = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.pwd);
        age = (EditText) findViewById(R.id.age);

        bt1 = (Button) findViewById(R.id.button3);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
            }
        });



    }


    public void registerUser() {

        String names = name.getText().toString().trim();
        String emails = emailid.getText().toString().trim();
        String ages = age.getText().toString().trim();
        String pass = password.getText().toString().trim();

        if (TextUtils.isEmpty(names)) {
            Toast.makeText(this, "Please enter name", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(emails)) {
            Toast.makeText(this, "Please enter email-id", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(ages)) {
            Toast.makeText(this, "Please enter age", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(pass)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }


        mAuth.createUserWithEmailAndPassword(emails , pass )
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "success",
                                    Toast.LENGTH_SHORT).show();
                            Intent inte = new Intent(MainActivity.this,Home_Screen.class);
                            startActivity(inte);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Registration Error", Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }
}
