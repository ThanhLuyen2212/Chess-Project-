package com.example.chessproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LobbyActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private String userId;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGamesDatabaseReference;
    private DatabaseReference mUsersDatabaseReference;

    private String username;


    private ListView mListViewMatchID;
    List<String> matchId = new ArrayList<>();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mGamesDatabaseReference = mFirebaseDatabase.getReference().child("games");
        mUsersDatabaseReference = mFirebaseDatabase.getReference().child("users");

        EditText editUsername = findViewById(R.id.username_edittext);

        // set preview board
//
//
//
//
//
        // get current user and sent to login screen if user is null
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        userId = user.getUid();

        Log.e("USER", "" + userId);

        // check account exit
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(user == null){
                    // user auth state is changed - user is null
                    // launch ligin activity
                    startActivity(new Intent(LobbyActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };


        // ensure user has username
        mUsersDatabaseReference.child(userId).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("USER", "Uname player 2 " + snapshot.getValue());

                if(snapshot.getValue() == null){
                    LinearLayout usernameLayout = findViewById(R.id.username_layout);
                    usernameLayout.setVisibility(View.VISIBLE);
                }

                if(snapshot.getValue() != null){
                    LinearLayout usernameLayout = findViewById(R.id.username_layout);
                    usernameLayout.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        // set user for player
        TextView setUsername = findViewById(R.id.set_username);
        setUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = editUsername.getText().toString();
                Log.e("user name", "" + username);
                if(username != null){
                    mUsersDatabaseReference.child(userId).child("username").setValue(username);
                }
            }
        });



        TextView findMatch = findViewById(R.id.random_button);
        findMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mGamesDatabaseReference.child("offers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // just get game key
                        String temp = (""+ snapshot.getValue()).split("=")[0];
                        Log.e("USER", "temp" + temp);
                        Log.e("USER"," " + snapshot.getValue());
                        // if offer/accept offer
                        // possibly allow offers to hold multiple offer games
                        // foreach

                        if(snapshot.getValue() != null) {
                            // remove stringbuilder
                            StringBuilder sb = new StringBuilder(temp);
                            sb.deleteCharAt(0);
                            final String offer = sb.toString();
                            Log.e("USEROH", "offer:" + offer);


                            //set list id for game to choose
                            matchId.add(offer.toString());
                            adapter = new ArrayAdapter(LobbyActivity.this, R.layout.support_simple_spinner_dropdown_item, matchId);
                            mListViewMatchID = findViewById(R.id.active_listview);
                            mListViewMatchID.setAdapter(adapter);


                            mGamesDatabaseReference.child(offer).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                    String player_white = snapshot.getValue(String.class);

                                    Map<String, Object> player_white = (Map<String, Object>) snapshot.getValue();

                                    Log.e("USER", "player_white " + player_white);

                                    if (player_white.equals(userId)) {
                                        Toast.makeText(LobbyActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();
                                    } else{
                                        mGamesDatabaseReference.child(offer).child("black").setValue(userId);
                                        mUsersDatabaseReference.child(userId).child("games").child(offer).setValue(true);
                                        mGamesDatabaseReference.child(offer).child("username_black").setValue(username);
                                        mGamesDatabaseReference.child("offers").removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        System.out.println("The read failed: " + error.getCode());
                    }
                });
            }
        });



       TextView createMatchbtn = findViewById(R.id.create_match_button);
        createMatchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // change button findmatch to enable
//                findMatch.setEnabled(false);

                mGamesDatabaseReference.child("offers").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        // get game key rather than splitting string
                        String temp = ("" + snapshot.getValue()).split("=")[0];

                        Log.e("USER", "temp" + temp);

                        // possibly allow offers to hold multiple offer games
                        if(snapshot.getValue()== null)
                        {
                            String eventId = mGamesDatabaseReference.push().getKey();
                            mGamesDatabaseReference.child("offers").child(eventId).setValue(true);

                            // visible layout waiting another player
                            FrameLayout frameLayout = findViewById(R.id.fragment_container);
                            frameLayout.setVisibility(View.GONE);

                            LinearLayout waiting_layout = findViewById(R.id.waiting_layout);
                            waiting_layout.setVisibility(View.VISIBLE);


                            //ensure these all happen
                            mGamesDatabaseReference.child(eventId).child("white").setValue(userId);
                            String newBoard = getResources().getString(R.string.new_board);
                            mGamesDatabaseReference.child(eventId).child("board").setValue(newBoard);
                            mGamesDatabaseReference.child(eventId).child("turn_color").setValue("white");
                            mUsersDatabaseReference.child(userId).child("games").child(eventId).setValue(true);
                            mGamesDatabaseReference.child("offers").child(eventId).setValue(true);
                            mGamesDatabaseReference.child(eventId).child("match_id").setValue(eventId);
                            mGamesDatabaseReference.child(eventId).child("username_white").setValue(username);

//                            //set list id for game to choose
//                            matchId.add(eventId.toString());
//                            adapter = new ArrayAdapter(LobbyActivity.this, R.layout.support_simple_spinner_dropdown_item, matchId);
//                            mListViewMatchID = findViewById(R.id.active_listview);
//                            mListViewMatchID.setAdapter(adapter);


                            // find another player to play
                            // and translate to activity game if find another player








                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });





//        mListViewMatchID.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//            }
//        });
    }


//    private void setAlerDialogButton(){
//
//        // create alertDialog to tell player wait, find another player, translate to activity game
//        AlertDialog.Builder builder = new AlertDialog.Builder(LobbyActivity.this);
//        builder.setTitle("Find another player").setMessage("Please wait to find another player to play!! finding!!!");
//
//        builder.setCancelable(true);
//
//        // create "postitive" button with OnclickListener.
//        builder.setPositiveButton("PLAY", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//                startActivity(new Intent(LobbyActivity.this, GameActivity.class));
//                finish();
//            }
//        });
//
//        // Create "negative" button with onClickListener
//        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                // task to do when canceling
//                mGamesDatabaseReference.child("offers").removeValue();
//                dialogInterface.cancel();
//                startActivity(new Intent(LobbyActivity.this, GameActivity.class));
//            }
//        });
//
//        // Create AlertDialog:
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
}