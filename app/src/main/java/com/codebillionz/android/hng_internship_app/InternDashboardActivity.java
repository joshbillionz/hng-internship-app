package com.codebillionz.android.hng_internship_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.codebillionz.android.hng_internship_app.Models.GradingComplaint;
import com.codebillionz.android.hng_internship_app.Models.Intern;
import com.codebillionz.android.hng_internship_app.Models.Mentor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class InternDashboardActivity extends AppCompatActivity {
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference complaintsPath = database.getReference("complaints");

    Button profileButton, complainButton, classButton, submitTaskButton;
   /// TextView complaintsTextView,probedInternsTextView,evictedInternsTextView,probedCountTextView,evictedCountTextView,complaintsCountTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_dashboard);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#616161\">" + "Dashboard" + "</font>"));
        initializeWidgets();
        initializeListeners();
    }


    private void initializeListeners(){

    }
    private void initializeWidgets(){
        profileButton = findViewById(R.id.profileButton);
        complainButton = findViewById(R.id.complainButton);
        classButton = findViewById(R.id.classButton);
        submitTaskButton = findViewById(R.id.submitTaskButton);
//        complaintsTextView = findViewById(R.id.complaintsTextView);
//        probedInternsTextView = findViewById(R.id.probedTextView);
//        evictedInternsTextView = findViewById(R.id.evictedTextView);
//        complaintsCountTextView = findViewById(R.id.complaintsCounts);
//        probedCountTextView = findViewById(R.id.probedCount);
//        evictedCountTextView = findViewById(R.id.evictedCount);

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProfileActivity();
            }
        });
        complainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchComplainDialogue();
            }
        });
        classButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchClassActivity();
            }
        });
        submitTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSubmitTaskDialog();
            }
        });


    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        super.onCreateOptionsMenu(menu);
        inflater.inflate(R.menu.dashboard_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.signout_item:
                mAuth.signOut();
                launchSignInPage();
                return super.onOptionsItemSelected(item);

            default: return super.onOptionsItemSelected(item);

        }
    }




    //
    private void addComplain(Date dateLaid, Intern complainant, String title, String description, int stage){
       GradingComplaint complaint = new GradingComplaint(dateLaid,complainant,title,description,stage);



        complaintsPath.child(user.getUid()).setValue(complaint);
    }




    //LAUNCHERS
    private void launchSignInPage() {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
        finish();
    }

    private void launchProfileActivity(){
        Intent intent = new Intent(this,InternProfileActivity.class);
        intent.putExtra(InternProfileActivity.KEY_INTERN_ID,user.getUid());
        startActivity(intent);
    }
    private void launchMentorsListActivity(){
        Intent intent = new Intent(this,MentorsListActivity.class);
        startActivity(intent);
    }

    private void launchComplainDialogue(){

    }

    private void launchClassActivity(){
        Intent intent = new Intent(this,ChatForumActivity.class);
        startActivity(intent);
    }
    private void launchSubmitTaskDialog(){

    }
}
