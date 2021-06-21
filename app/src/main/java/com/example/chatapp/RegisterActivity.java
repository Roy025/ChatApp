package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
  public class RegisterActivity extends AppCompatActivity {
      EditText useReg,passReg,emailReg;
      Button registerBtn;
      FirebaseAuth auth;
      DatabaseReference myRef;
      ProgressBar progressBar;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_register);
          useReg=findViewById(R.id.useredittext);
          passReg=findViewById(R.id.passedit);
          emailReg=findViewById(R.id.emailedit);
          progressBar = findViewById(R.id.progressBar);
          registerBtn=findViewById(R.id.btnreg);
          auth =FirebaseAuth.getInstance();
          registerBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String useRegTxt=useReg.getText().toString();
                  String emailRegTxt=emailReg.getText().toString();
                  String passRegTxt=passReg.getText().toString();
                  //To check if any of the field is empty
                  if(TextUtils.isEmpty(useRegTxt)||TextUtils.isEmpty(emailRegTxt)||TextUtils.isEmpty(passRegTxt)){
                      Toast.makeText(RegisterActivity.this,"Please fill all Fields",Toast.LENGTH_SHORT).show();

                  }else {
                      RegisterNow(useRegTxt,emailRegTxt,passRegTxt);
                  }
                  progressBar.setVisibility(View.VISIBLE);

              }
          });



      }
      private void RegisterNow(final String username ,String email,String password){
          auth.createUserWithEmailAndPassword(email,password)
                  .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful()){
                                  Toast.makeText(RegisterActivity.this,"User Created",Toast.LENGTH_SHORT).show();
                                  Intent i=new Intent(RegisterActivity.this,MainActivity.class);
                              }
                              else {
                              Toast.makeText(RegisterActivity.this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                              progressBar.setVisibility(View.GONE);
                              }

                      }
                  });
      }
  }