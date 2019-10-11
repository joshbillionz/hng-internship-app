package com.codebillionz.android.hng_internship_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.codebillionz.android.hng_internship_app.Models.Announcement;
import com.codebillionz.android.hng_internship_app.Models.GradingComplaint;
import com.codebillionz.android.hng_internship_app.Models.Intern;
import com.codebillionz.android.hng_internship_app.Models.Mentor;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.UUID;

public class InternDashboardActivity extends AppCompatActivity {
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference complaintsPath = database.getReference("complaints");
    DatabaseReference internPath = database.getReference("interns/"+ user.getUid());
    Intern intern;


    Button profileButton, complainButton, classButton, submitTaskButton;
    TextView announcementsTextView;
   /// TextView complaintsText /// View,probedInternsTextView,evictedInternsTextView,probedCountTextView,evictedCountTextView,complaintsCountTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intern_dashboard);
        initializeWidgets();
        getInternFromId(user.getUid());
        initializeListeners();
    }


    private void initializeListeners(){

    }
    private void initializeWidgets(){
        profileButton = findViewById(R.id.profileButton);
        complainButton = findViewById(R.id.complainButton);
        classButton = findViewById(R.id.classButton);
        submitTaskButton = findViewById(R.id.submitTaskButton);
        announcementsTextView = findViewById(R.id.announcementsTextView);
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
        announcementsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAnnouncementsActivity();
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

    public void getInternFromId(String id){
        ;
        internPath.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                intern = dataSnapshot.getValue(Intern.class);
                internPath.keepSynced(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("ERROR", "loadInterns :onCancelled", databaseError.toException());
                // ...
            }
        });
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
    private void launchAnnouncementsActivity(){
        Intent intent = new Intent(this, AnnouncementsActivity.class);
        startActivity(intent);
    }

    private void launchComplainDialogue(){
        new ComplainDialog().showDialog(this);
    }

    private void launchClassActivity(){
        Intent intent = new Intent(this,ChatForumActivity.class);
        startActivity(intent);
    }
    private void launchSubmitTaskDialog(){

    }


    class ComplainDialog{

        public void showDialog(Activity activity){

            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_complain);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



            final EditText description = dialog.findViewById(R.id.complainDescription);
            TextView cancel = dialog.findViewById(R.id.cancel);
            TextView submit = dialog.findViewById(R.id.submit);

            //setup spinner
            final Spinner spinner = dialog.findViewById(R.id.spinner);
            ArrayAdapter<CharSequence> userTypeAdapter = ArrayAdapter.createFromResource(InternDashboardActivity.this,
                    R.array.complain_type,android.R.layout.simple_spinner_item);
            userTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(userTypeAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String announcementId = UUID.randomUUID().toString();
                    if(description.getText()!=null){
                        GradingComplaint complaint =
                                new GradingComplaint(new Date(),
                                        intern,
                                        spinner.getSelectedItem().toString(),
                                        description.getText().toString(),
                                        1) ;
                        database.getReference().child("complaints").child(announcementId).setValue(complaint);

                    }
                    dialog.dismiss();



                }
            });

            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });




            dialog.show();
        }
    }
}

