package com.codebillionz.android.hng_internship_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth= FirebaseAuth.getInstance();
    FirebaseUser user = auth.getCurrentUser();
    DatabaseReference appUsersPath = FirebaseDatabase.getInstance().getReference("app_users");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




                if(user==null) {
                    Intent  intent = new Intent(MainActivity.this, SignInActivity.class);

                    startActivity(intent);
                    finish();
                }
                else{
                    //todo check if user is a mentor or intern
                    String id= user.getUid();
                    appUsersPath.child(id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String userType= dataSnapshot.getValue(String.class);
                            Intent intent;
                            if(userType.equals("mentor")){
                                intent = new Intent(MainActivity.this, MentorDashboardActivity.class);
                            }
                            else {
                                intent = new Intent(MainActivity.this, InternDashboardActivity.class);
                            }
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    }


    }
}
