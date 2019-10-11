package com.codebillionz.android.hng_internship_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.codebillionz.android.hng_internship_app.Models.Intern;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InternsListActivity extends AppCompatActivity {

    Context context = this;

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference internsPath = database.getReference("interns");

    private RecyclerView internRecyclerView;
    TextView internDisplayName,internTrack,internStage ;
    ProgressBar progressBar;
    private InternAdapter mAdapter;
    ArrayList<Intern> userInterns ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interns_list);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"#616161\">" + "Interns List" + "</font>"));

        userInterns = new ArrayList<>();
        progressBar = findViewById(R.id.progressBar);

        internRecyclerView = findViewById(R.id.internsListRecyclerView);
        internRecyclerView.setLayoutManager(new LinearLayoutManager(InternsListActivity.this));
        loadInternsFromFirebase();


    }



    public void loadInternsFromFirebase(){
        internsPath.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Intern object and use the values to update the UI
                progressBar.setVisibility(View.VISIBLE);
//                //todo :empty arrayList in in case of second call to prevent duplicates
//                userInterns = new ArrayList<>();
                for (DataSnapshot snapshotNode: dataSnapshot.getChildren()) {
                    userInterns.add(snapshotNode.getValue(Intern.class));
                }
                progressBar.setVisibility(View.GONE);
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

    public void updateUI(){

        mAdapter = new InternAdapter(userInterns);
        internRecyclerView.setAdapter(mAdapter);


    }


    private class InternAdapter extends RecyclerView.Adapter<InternHolder> {
        private ArrayList<Intern> interns;

        InternAdapter(ArrayList<Intern> interns) {
            this.interns = interns;
        }

        @Override
        public InternHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            return new InternHolder(layoutInflater, parent);
        }
        @Override
        public void onBindViewHolder(InternHolder holder, int position) {
            Intern intern = interns.get(position);
            holder.bind(intern);
        }
        @Override
        public int getItemCount() {
            return interns.size();
        }


    }
    
    
    

    private class InternHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Intern intern;
        InternHolder(LayoutInflater inflater, ViewGroup parent) {
            
            super(inflater.inflate(R.layout.intern_list_item, parent, false));
            internDisplayName= itemView.findViewById(R.id.internDisplayNameTextView);
            internTrack = itemView.findViewById(R.id.internTrackTextView);
            internStage = itemView.findViewById(R.id.internStageTextView);

            itemView.setOnClickListener(this);
        }



        void bind(Intern intern) {
            this.intern = intern;
            internDisplayName.setText(intern.getDisplayName());
            internTrack.setText(intern.getTrack());
            //internStage.setText(intern.getStage());
            


        }

        @Override
        public void onClick(View view) {
            //clickedIntern = intern;
            Intent intent = new Intent(InternsListActivity.this, InternProfileActivity.class);
            intent.putExtra(InternProfileActivity.KEY_INTERN_ID, intern.getId());

            startActivity(intent);
        }
    }







    ///paths
    public DatabaseReference getInternPath(String id){
        DatabaseReference path = database.getReference("interns/"+ id);
        return path;
    }
    public DatabaseReference getMentorPath(String id){
        DatabaseReference path = database.getReference("mentors/"+ id);
        return path;
    }

    public DatabaseReference getAppUserPath(String id){
        DatabaseReference path = database.getReference("app_users/"+ id);
        return path;
    }

    public DatabaseReference getAllInternsPath(){
        DatabaseReference path = database.getReference("interns");
        return path;
    }
}
