package com.codebillionz.android.hng_internship_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MembersDetails extends AppCompatActivity {

    String name, username, skill, phoneNo, email, status, imgUrl;
    TextView nameTv, userNameTv, skillTv, phoneNoTv, emailTv, statusTv;
    CircleImageView userImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_details);

        initView();
        getExtra();
        setValues();
    }

    public void initView() {
        nameTv = findViewById(R.id.name_tv);
        userNameTv = findViewById(R.id.username_tv);
        skillTv = findViewById(R.id.skill_tv);
        phoneNoTv = findViewById(R.id.phone_tv);
        emailTv = findViewById(R.id.email_tv);
        statusTv = findViewById(R.id.status_tv);
        userImage = findViewById(R.id.user_image);
    }

    public void getExtra() {
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        username = intent.getStringExtra("username");
        imgUrl = intent.getStringExtra("imageUrl");
        skill = intent.getStringExtra("skill");
        phoneNo = intent.getStringExtra("phoneNo");
        email = intent.getStringExtra("email");
        status = intent.getStringExtra("status");

    }

    public void setValues() {
        nameTv.setText(name);
        userNameTv.setText(username);
        skillTv.setText(skill);
        phoneNoTv.setText(phoneNo);
        emailTv.setText(email);
        statusTv.setText(status);
//        Picasso.get()
//                .load(imgUrl)
//                .placeholder(R.drawable.message_btn)
//                .into(userImage);
    }
}
