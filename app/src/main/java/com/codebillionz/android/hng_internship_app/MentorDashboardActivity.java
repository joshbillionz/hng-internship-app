package com.codebillionz.android.hng_internship_app;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.codebillionz.android.hng_internship_app.Models.Announcement;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class MentorDashboardActivity extends AppCompatActivity {
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    Button profileButton, internsButton, classButton, announceButton;
    TextView complaintsTextView,probedInternsTextView,evictedInternsTextView,probedCountTextView,evictedCountTextView,complaintsCountTextView;

    String mentorName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentor_dashboard);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#616161\">" + "Dashboard" + "</font>"));
        initializeWidgets();
        initializeListeners();


    }


    @Override
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



    private void initializeListeners(){

    }
    private void initializeWidgets(){
        profileButton = findViewById(R.id.profileButton);
        internsButton = findViewById(R.id.internsButton);
        classButton = findViewById(R.id.classButton);
        announceButton = findViewById(R.id.announceButton);
        complaintsTextView = findViewById(R.id.complaintsTextView);
        probedInternsTextView = findViewById(R.id.probedTextView);
        evictedInternsTextView = findViewById(R.id.evictedTextView);
        complaintsCountTextView = findViewById(R.id.complaintsCounts);
        probedCountTextView = findViewById(R.id.probedCount);
        evictedCountTextView = findViewById(R.id.evictedCount);
        

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProfileActivity();
            }
        });
        internsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchInternsListActivity();
            }
        });
        
        classButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchClassActivity();
            }
        });
        
        announceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchAnnounceDialogue();
            }
        });
        complaintsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchComplaintsActivity();
            }
        });
        
        probedInternsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchProbedInternsActivity();
            }
        });

        evictedInternsTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchEvictedInternsActivity();
            }
        });


    }



    //launchers
    //i could easily have created just one activity launcher method but no
    private void launchSignInPage() {
        Intent intent = new Intent(this,SignInActivity.class);
        startActivity(intent);
        finish();
    }

    private void launchProfileActivity(){
        Intent intent = new Intent(this,MentorProfileActivity.class);
        intent.putExtra(MentorProfileActivity.KEY_MENTOR_ID,user.getUid());
        startActivity(intent);
    }

    private void launchInternsListActivity(){
        Intent intent = new Intent(this,InternsListActivity.class);
        startActivity(intent);
    }

    private void launchAnnounceDialogue(){
        new AnnounceDialog().showDialog(this);
    }

    private void launchClassActivity(){
        Intent intent = new Intent(this,ChatForumActivity.class);
        startActivity(intent);
    }
    
    private void launchComplaintsActivity(){
        Intent intent = new Intent(this,ComplaintsActivity.class);
        startActivity(intent);
    }
    
    private void launchProbedInternsActivity(){
        Intent intent = new Intent(this,ProbedInternsActivity.class);
        startActivity(intent);
    }
    
    private void launchEvictedInternsActivity(){
        Intent intent = new Intent(this,EvictedInternsActivity.class);
        startActivity(intent);
    }

    //inner classes

    public class AnnounceDialog{
        public void showDialog(Activity activity){

            final Dialog dialog = new Dialog(activity);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialogue_announce);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            EditText stage = findViewById(R.id.stageEditText);
            final EditText message = findViewById(R.id.annnouncementMessage);
            TextView cancel = dialog.findViewById(R.id.cancel);
            TextView submit = dialog.findViewById(R.id.submitAnnouncement);


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String announcementId = UUID.randomUUID().toString();
                    Announcement announcement = new Announcement(announcementId,message.getText().toString(), "mentor" );
                    database.getReference().child("announcements").child(announcementId).setValue(announcement);

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
