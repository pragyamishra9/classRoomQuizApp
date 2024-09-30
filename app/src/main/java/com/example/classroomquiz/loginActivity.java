package com.example.classroomquiz;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class loginActivity extends AppCompatActivity {
    EditText Username,Password;
    Button Login;
    ProgressBar Pbar;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //assign the ids
        Pbar=(ProgressBar)findViewById(R.id.progressBar);
        Username=(EditText)findViewById(R.id.username);
        Password=(EditText)findViewById(R.id.password);
        Login=(Button)findViewById(R.id.login);
        fAuth=FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Uname=Username.getText().toString().trim();
                String Passw=Password.getText().toString().trim();

                if ((TextUtils.isEmpty(Uname))){
                    Username.setError("Email is Required");
                    return;
                }
                if ((TextUtils.isEmpty(Passw))){
                    Password.setError("Password is Required");
                    return;
                }
                Pbar.setVisibility(View.VISIBLE);

                //Authentication with firebase

                fAuth.signInWithEmailAndPassword(Uname,Passw).addOnCompleteListener(loginActivity.this,new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(getApplicationContext(),categoryActivity.class));
                        }else{
                            Toast.makeText(loginActivity.this, "Incorrect Username/Password", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });

    }
}