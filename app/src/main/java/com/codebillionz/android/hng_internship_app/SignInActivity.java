package com.codebillionz.android.hng_internship_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codebillionz.android.hng_internship_app.Models.AppUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class SignInActivity extends AppCompatActivity {

    EditText emailEditText, passwordEditText;
    Button signInButton;
    TextView tv_sign_up;
    private FirebaseAuth mAuth;

    //paths
    DatabaseReference appUsersPath = FirebaseDatabase.getInstance().getReference("app_users");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_sign_in);
        initializeWidgets();


    }

    private void initializeWidgets(){
        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_password);
        signInButton = findViewById(R.id.btn_sign_in);
        tv_sign_up = findViewById(R.id.tv_sign_up_link);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();



                EditText[] fields ={emailEditText,passwordEditText};

                if(fieldsFilled(fields)){
                    login(email, password);
                }

            }
        });

        tv_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSignupPage();

            }
        });
    }

    private void  login(String email , String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LOGIN ", "signInWithEmail:success");
                            launchDashboard();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LOGIN", "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    private void launchDashboard(){

        String id= mAuth.getCurrentUser().getUid();
        appUsersPath.child(id).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String userType= dataSnapshot.getValue(String.class);
                Intent intent;
                if(userType.equals("mentor")){
                    intent = new Intent(SignInActivity.this, MentorDashboardActivity.class);

                }
                else {
                    intent = new Intent(SignInActivity.this, InternDashboardActivity.class);

                }
                startActivity(intent);
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    private void launchSignupPage(){
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean fieldsFilled(EditText[] fields){
        for (EditText field : fields){
            if (field.getText().toString().length()<=0){
                field.setError("field must be filled");
                return false;
            }

            if((field.getId()==R.id.et_password)&&passwordEditText.getText().length()<6){
                field.setError(" password should contain at least 6 characters");
                return false;
            }

        }

        if(!isValidEmail(emailEditText.getText().toString())){
            emailEditText.setError("Invalid Email");
            return false;
        }
        return true;
    }

    private boolean isValidEmail(CharSequence target){
        if (target== null) return false;
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

//    private void   checkUserType(){
//
//       String id= mAuth.getCurrentUser().getUid();
//       appUsersPath.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
//           @Override
//           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//               String userType= dataSnapshot.getValue(String.class);
//
//           }
//
//           @Override
//           public void onCancelled(@NonNull DatabaseError databaseError) {
//
//           }
//       });
//
//    }
}
