package com.example.chatapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login_Activity extends Activity {
        EditText useLgn,passLgn;
        Button LoginBtn,RegisterBtn;
        FirebaseAuth auth;
        FirebaseUser firebaseUser;


        @Override
        protected void onStart() {
            super.onStart();
            firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
            if(firebaseUser!=null){
                Intent i =new Intent(Login_Activity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);
            useLgn=findViewById(R.id.username);
            passLgn=findViewById(R.id.PassTxt);
            LoginBtn =findViewById(R.id.loginBtn);
            RegisterBtn=findViewById(R.id.registerBtn);


            RegisterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(Login_Activity.this,RegisterActivity.class);

                }
            });

            LoginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email_text = useLgn.getText().toString();
                    String pass_text = passLgn.getText().toString();
                    if (TextUtils.isEmpty(email_text) || TextUtils.isEmpty(pass_text)) {
                        Toast.makeText(Login_Activity.this, "Please fill the Fields", Toast.LENGTH_SHORT).show();
                    } else {
                        auth.signInWithEmailAndPassword(email_text, pass_text)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Login_Activity.this,"Logged In Successfully",Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(Login_Activity.this, MainActivity.class);
                                        } else {
                                            Toast.makeText(Login_Activity.this, "Login Failed!", Toast.LENGTH_SHORT).show();

                                        }

                                    }
                                });

                    }
                }
            });
        }
}


