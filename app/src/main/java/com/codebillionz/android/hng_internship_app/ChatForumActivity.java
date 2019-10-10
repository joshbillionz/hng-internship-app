package com.codebillionz.android.hng_internship_app;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.codebillionz.android.hng_internship_app.Models.Message;
import com.codebillionz.android.hng_internship_app.Models.MessageAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatForumActivity extends AppCompatActivity {

    //    TextView textView1, textView2;
    EditText editText;
    Button btn_send;
    ImageButton img_btn;
    String allText, all_text;
    List<Message> arrayList;
    MessageAdapter adapter;
    private ListView listView;
    private static final String ANONYMOUS = "user1";
    private String username;
    private static final int MESSAGE_LIMIT = 1000;
    private FirebaseDatabase user1, user2;
    private DatabaseReference ref1, ref2;
    private FirebaseUser FBUser;
    private ChildEventListener eventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_forum);

        user1 = FirebaseDatabase.getInstance();
        ref1 = user1.getReference().child("chat");


        eventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Message message = dataSnapshot.getValue(Message.class);
                adapter.add(message);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref1.addChildEventListener(eventListener);

        username = ANONYMOUS;

        // Initialize references to views
        listView = (ListView) findViewById(R.id.list_view);
        img_btn = (ImageButton) findViewById(R.id.photoPickerButton);
        editText = (EditText) findViewById(R.id.edit);
        btn_send = (Button) findViewById(R.id.send);

        // Initialize message ListView and its adapter
        arrayList = new ArrayList<>();
        adapter = new MessageAdapter(this, R.layout.list_item, arrayList);
        listView.setAdapter(adapter);

        // ImagePickerButton shows an image picker to upload a image for a message
        img_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Fire an intent to show an image picker
            }
        });

        // Enable Send button when there's text to send
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    btn_send.setEnabled(true);
                } else {
                    btn_send.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(MESSAGE_LIMIT)});

        // Send button sends a message and clears the EditText
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                all_text = editText.getText().toString();
                arrayList.add(new Message(all_text, username));
//                ref1.child("user1").push().setValue("chat");
                // Clear input box
                editText.setText("");
            }
        });
    }
}
