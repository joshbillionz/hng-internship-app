package com.codebillionz.android.hng_internship_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

public class MentorProfileActivity extends AppCompatActivity {
    public  static final String  KEY_MENTOR_ID = "mentor id";
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();


    DatabaseReference mentorPath ;
    Mentor mentor;

    TextView displayNameTextView,tractTextView, userTypeTextVView, nameTextView,emailTextView,
            phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_profile);
        String mentorId = getIntent().getStringExtra(KEY_MENTOR_ID);
        mentorPath= database.getReference("mentors/" + mentorId);
        initializeWidgets();
        loadMentorFromDatabase();

    }

    public void initializeWidgets(){
        displayNameTextView=findViewById(R.id.displayNameTextView);
        tractTextView= findViewById(R.id.trackTextView);
        nameTextView= findViewById(R.id.nameTextView);
        emailTextView = findViewById(R.id.emailTextView);

    }

    public void updateUI(){
        if (mentor != null) {
            displayNameTextView.setText(mentor.getName());
            tractTextView.setText(mentor.getTrack());
            emailTextView.setText(mentor.getEmail());
            nameTextView.setText(mentor.getName());


        }

        }


    public void loadMentorFromDatabase(){
        mentorPath.addValueEventListener(new ValueEventListener() {

        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
                mentor = dataSnapshot.getValue(Mentor.class);
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
