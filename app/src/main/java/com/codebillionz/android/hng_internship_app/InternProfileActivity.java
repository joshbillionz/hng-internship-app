package com.codebillionz.android.hng_internship_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.codebillionz.android.hng_internship_app.Models.Intern;
import com.codebillionz.android.hng_internship_app.Models.Mentor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class InternProfileActivity extends AppCompatActivity {
    public static final String KEY_INTERN_ID = "intern id";
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference internPath;

    Intern intern;

    TextView displayNameTextView,trackTextView, userTypeTextVView, nameTextView,emailTextView,
            phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_profile);
        String internId = getIntent().getStringExtra(KEY_INTERN_ID);
        internPath = database.getReference("interns/" + internId);


        initializeWidgets();
        loadInternFromDatabase();

    }

    public void initializeWidgets(){
        displayNameTextView=findViewById(R.id.displayNameTextView);
        trackTextView= findViewById(R.id.trackTextView);
        nameTextView= findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);

    }

    public void updateUI(){
        if (intern != null) {
            displayNameTextView.setText(intern.getName());
            trackTextView.setText(intern.getTrack());
            emailTextView.setText(intern.getEmail());
            nameTextView.setText(intern.getName());


        }
        else{
            displayNameTextView.setText("error in loading intern");
        }

    }


    public void loadInternFromDatabase(){
        internPath.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                intern = dataSnapshot.getValue(Intern.class);
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("ERROR", "loadInterns :onCancelled", databaseError.toException());
                // ...
            }
        });

    }
}
