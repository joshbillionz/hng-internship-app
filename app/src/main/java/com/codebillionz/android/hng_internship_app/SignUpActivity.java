package com.codebillionz.android.hng_internship_app;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.codebillionz.android.hng_internship_app.Models.AppUser;
import com.codebillionz.android.hng_internship_app.Models.Intern;
import com.codebillionz.android.hng_internship_app.Models.Mentor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUpActivity extends AppCompatActivity {
    FirebaseAuth mAuth;

    EditText fullNameET,emailET,passwordET;
    Button signUpButton;
    Spinner userTypeSpinner, trackSpinner;
    TextView loginTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        initializeWidgets();


    }


    public void signUp(final String fullName , final String email, final String track, final String userType, final String password ){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("SIGNUP", "createUserWithEmail:success");
                            String uId = mAuth.getCurrentUser().getUid();
                            AppUser appUser = new AppUser(uId, userType);

                            addNewAccount(appUser);

                            if(userType.equals("mentor")){
                                addMentor(uId,fullName,fullName,email,track);
                            }else if (userType.equals("intern")){
                                addIntern(uId,fullName,fullName,email,track,1);
                            }else{

                            }

                            launchDashboard(appUser);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("SIGNUP", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    private void initializeWidgets(){
        fullNameET= findViewById(R.id.fullname_edittext);
        emailET = findViewById(R.id.emailEditTextS);
        passwordET = findViewById(R.id.password_edittextS);
        userTypeSpinner = findViewById(R.id.user_type_spinner);
        trackSpinner = findViewById(R.id.track_spinner);

        signUpButton = findViewById(R.id.signup_btn);
        loginTv=findViewById(R.id.login_tvS);


        //setup spinner
        ArrayAdapter<CharSequence> userTypeAdapter = ArrayAdapter.createFromResource(this,
                R.array.account_type,android.R.layout.simple_spinner_item);
        userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(userTypeAdapter);
        userTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        final ArrayAdapter<CharSequence> trackAdapter = ArrayAdapter.createFromResource(this,
                R.array.track,android.R.layout.simple_spinner_item);
        trackAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        trackSpinner.setAdapter(trackAdapter);
        trackSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullName=fullNameET.getText().toString();
                String email = emailET.getText().toString();
                String track = trackSpinner.getSelectedItem().toString();
                String appUserType = userTypeSpinner.getSelectedItem().toString();
                String password = passwordET.getText().toString();
                EditText fields[] = {fullNameET,emailET,passwordET};

                if (fieldsFilled(fields)){
                    signUp(fullName, email, track, appUserType, password);
                }

            }
        });


        loginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchLoginPage();
            }
        });


    }
    private void launchDashboard(AppUser appUser){

        Intent intent;
        if(appUser.getUserType().equals("mentor")){
            intent = new Intent(this, MentorDashboardActivity.class);
        }
        else {
            intent = new Intent(this, InternDashboardActivity.class);
        }
        startActivity(intent);
        finish();
    }

    private void launchLoginPage(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    private void addNewAccount(AppUser appUser){
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference appUsersPath = database.getReference("app_users");
        appUsersPath.keepSynced(true);
                 appUsersPath.child(appUser.getId()).setValue(appUser.getUserType());


    }

    private boolean fieldsFilled(EditText[] fields){
        for (EditText field : fields){

            if (field.getText().toString().length()<=0){
                field.setError("field must be filled");
                return false;
            }
            if((field.getId()==R.id.password_edittextS)&&passwordET.getText().length()<6){
                field.setError("password should contain at least 6 characters");
                return false;
            }
        }

        if(!isValidEmail(emailET.getText().toString())){
            emailET.setError("Invalid Email");
            return false;
        }
        return true;
    }

    private boolean isValidEmail(CharSequence target){
        if (target== null) return false;
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void addMentor(String id, String name, String displayName, String email, String track){
        Mentor mentor = new Mentor(id,name,displayName,email,track);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference mentorsPath = database.getReference("mentors");
        mentorsPath.keepSynced(true);
        mentorsPath.child(mentor.getId()).setValue(mentor);
    }
    private void addIntern(String id, String name, String displayName, String email, String track, int stage){
        Intern intern = new Intern(id,name,displayName,email,track,1);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference internsPath = database.getReference("interns");
        internsPath.keepSynced(true);
        internsPath.child(intern.getId()).setValue(intern);
    }
}

