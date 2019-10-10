package com.codebillionz.android.hng_internship_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
        final RecyclerView memberRecycler = findViewById(R.id.members_recycler);
        MembersAdapter adapter = new MembersAdapter(members, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        memberRecycler.setLayoutManager(mLayoutManager);
        memberRecycler.setItemAnimator(new DefaultItemAnimator());
        memberRecycler.setAdapter(adapter);

        // Create a list of members

        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG 2019", "Hotels.ng",
                "Android Developer", "2348899403323", "hng@hng.com", "Status"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG Internship ", "Dubai Flys",
                "Mobile Developer", "198382330232", "hng@hng.com", "Status"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "Internship 2019", "Will Smith",
                "Web Developer", "08097000000", "hng@hng.com", "Status"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG", "Burger Vicks",
                "Digital Marketing", "08097000000", "hng@hng.com", "Status"));
        members.add(new Members("https://drive.google.com/open?id=19deoOFEDOjbNM_c2Ig1boL8mCNR5LKbx",
                "Internship", "Louis Gucci",
                "DevOps", "08282992390", "hng@hng.com", "Status"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNG 6.0", "Bill Gill",
                "Project Manager", "0818122277", "hng@hng.com", "Status"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "HNGi6.0", "Hebert John",
                "UI/UX Designer", "076732232400", "hng@hng.com", "Status"));
        members.add(new Members("https://drive.google.com/open?id=1DbWQs35dHg1RjFnckZyC-kZHpLBq00bS",
                "2048 2019", "The Mixture",
                "Android Developer", "082936734u", "hng@hng.com", "Status"));
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener(new MembersAdapter.OnItemClickListener() {
            @Override
            public void clickedMe(int position) {
                String name = members.get(position).getName();
                String usersName = members.get(position).getUsersName();
                String whatYouDo = members.get(position).getWhatYouDo();
                String phoneNo = members.get(position).getPhoneNumber();
                String email = members.get(position).getEmail();
                String status = members.get(position).getOtherDetails();
                String imgUrl = members.get(position).getImageUrl().trim();
                Intent intent = new Intent(MembersListActivity.this, MembersDetails.class);
                intent.putExtra("imageUrl", imgUrl);
                intent.putExtra("name", name);
                intent.putExtra("username", usersName);
                intent.putExtra("skill", whatYouDo);
                intent.putExtra("phoneNo", phoneNo);
                intent.putExtra("email", email);
                intent.putExtra("status", status);
                startActivity(intent);
            }
        });

    }


}
