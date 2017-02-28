package com.example.mxhung.fcmchat;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by MXHung on 2/15/2017.
 */

public class ChatRoom extends AppCompatActivity {
    @Bind(R.id.tvChat) TextView tvChat;
    @Bind(R.id.etChatRoom) EditText etChatRoom;
    private String user_name;
    private String room_name;
    DatabaseReference db;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        user_name = getIntent().getExtras().getString("user_name").toString();
        room_name = getIntent().getExtras().getString("room_name").toString();
        setTitle("Room " + room_name);
        db = FirebaseDatabase.getInstance().getReference();

        db.child(room_name).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                append_chat_conver(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                append_chat_conver(dataSnapshot);

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    //doc du lieu chat theo Oject TinNhan
    private void append_chat_conver(DataSnapshot dataSnapshot) {
        //doc du lieu cua nut con
        TinNhan msg = dataSnapshot.getValue(TinNhan.class);
        tvChat.append(msg.name + ": " + msg.msg + "\n");
    }

    //day tn len tren firebase
    @OnClick(R.id.btAdd)
    public void addMessage(){
        Map<String,String> msg = new HashMap<>();
        msg.put("name", user_name);
        msg.put("msg", etChatRoom.getText().toString());
        db.child(room_name).push().setValue(msg);
        etChatRoom.setText("");
    }
}
