package com.codebillionz.android.hng_internship_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.codebillionz.android.hng_internship_app.Models.Announcement;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AnnouncementsActivity extends AppCompatActivity {

    Context context = this;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference announcementsPath = database.getReference("announcements");

    private RecyclerView announcementRecyclerView;
    TextView announcementSender, announcementMessage;
    ProgressBar progressBar;
    private AnnouncementAdapter mAdapter;
    ArrayList<Announcement> userAnnouncements;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#616161\">" + "Announcements" + "</font>"));

        userAnnouncements = new ArrayList<>();
        progressBar = findViewById(R.id.progressBar);

        announcementRecyclerView = findViewById(R.id.announcementsRecyclerView);
        announcementRecyclerView.setLayoutManager(new LinearLayoutManager(AnnouncementsActivity.this));
        loadAnnouncementsFromFirebase();


    }


    public void loadAnnouncementsFromFirebase() {
        announcementsPath.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Announcement object and use the values to update the UI
                progressBar.setVisibility(View.VISIBLE);
//                //todo :empty arrayList in in case of second call to prevent duplicates
//                userAnnouncements = new ArrayList<>();
                for (DataSnapshot snapshotNode : dataSnapshot.getChildren()) {
                    userAnnouncements.add(snapshotNode.getValue(Announcement.class));
                }
                progressBar.setVisibility(View.GONE);
                updateUI();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("ERROR", "loadAnnouncements :onCancelled", databaseError.toException());
                // ...
            }
        });
    }

    public void updateUI() {

        mAdapter = new AnnouncementAdapter(userAnnouncements);
        announcementRecyclerView.setAdapter(mAdapter);


    }


    private class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementHolder> {
        private ArrayList<Announcement> announcements;

        AnnouncementAdapter(ArrayList<Announcement> announcements) {
            this.announcements = announcements;
        }

        @Override
        public AnnouncementHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            return new AnnouncementHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(AnnouncementHolder holder, int position) {
            Announcement announcement = announcements.get(position);
            holder.bind(announcement);
        }

        @Override
        public int getItemCount() {
            return announcements.size();
        }


    }


    private class AnnouncementHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Announcement announcement;

        AnnouncementHolder(LayoutInflater inflater, ViewGroup parent) {

            super(inflater.inflate(R.layout.announcement_list_item, parent, false));
            announcementSender = itemView.findViewById(R.id.senderNameTextView);
            announcementMessage = itemView.findViewById(R.id.messageTextView);


            itemView.setOnClickListener(this);
        }


        void bind(Announcement announcement) {
            this.announcement = announcement;
            announcementSender.setText(announcement.getSenderName());
            announcementMessage.setText(announcement.getMessage());



        }

        @Override
        public void onClick(View view) {
            //
        }
    }


}
