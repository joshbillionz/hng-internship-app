package com.codebillionz.android.hng_internship_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.codebillionz.android.hng_internship_app.Adapters.MembersAdapter;
import com.codebillionz.android.hng_internship_app.Models.Members;

import java.util.ArrayList;

public class MembersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_list);

        final ArrayList<Members> members = new ArrayList<>();
        RecyclerView memberRecycler = findViewById(R.id.members_recycler);
        MembersAdapter adapter = new MembersAdapter(members, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        memberRecycler.setLayoutManager(mLayoutManager);
        memberRecycler.setItemAnimator(new DefaultItemAnimator());
        memberRecycler.setAdapter(adapter);

        // Create a list of members

        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship 2019", "08099993939"));
        adapter.notifyDataSetChanged();

    }
}
