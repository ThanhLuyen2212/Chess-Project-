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
import android.widget.ProgressBar;
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
import java.util.TooManyListenersException;

public class LobbyActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private String userId;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mGamesDatabaseReference;
    private DatabaseReference mUsersDatabaseReference;

    private String username;


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
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        // get current user and sent to login screen if user is null
        // check account exit
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(user == null){
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(LobbyActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        userId = user.getUid();
        Log.e("USER", "" + userId);


        // ensure user has username
        mUsersDatabaseReference.child(userId).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.e("USER1", "Uname player " + snapshot.getValue());
                Log.e("USER2", "Uname player " + snapshot.getKey());

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
                        Log.e("USER", "temp " + temp);

                        // if offer/accept offer
                        // possibly allow offers to hold multiple offer games
                        if(snapshot.getValue() == null){
                            Toast.makeText(LobbyActivity.this, "No match found!!!", Toast.LENGTH_SHORT).show();
                        }
                        else {

                            ListView mListViewMatchID;
                            List<String> matchId = new ArrayList<>();
                            ArrayAdapter adapter;

                            for (DataSnapshot item : snapshot.getChildren()) {


                                String offer = item.getKey();
                                Log.e("USEROH", "offer:" + offer);

                                // add id match to list match id
                                matchId.add(offer.toString());
                            }

                            //set list id for game to choose
                            adapter = new ArrayAdapter(LobbyActivity.this, R.layout.support_simple_spinner_dropdown_item, matchId);
                            mListViewMatchID = findViewById(R.id.active_listview);
                            mListViewMatchID.setAdapter(adapter);
                            Log.e("match id" , ""+matchId.toString());

                            mListViewMatchID.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    String offer = adapterView.getItemAtPosition(i).toString();
                                    Log.e("get offers" ,""+offer);

                                    mGamesDatabaseReference.child(offer).child("white").addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            final String player_white = snapshot.getValue(String.class).trim();

                                            Log.e("Player white", "" + player_white);
                                            Log.e("User Id", "" + userId.trim());

                                            if (player_white.equals(userId)) {
                                                Toast.makeText(LobbyActivity.this, "Please Wait...", Toast.LENGTH_SHORT).show();

                                            } else {

                                                mGamesDatabaseReference.child(offer).child("black").setValue(userId);
                                                mUsersDatabaseReference.child(userId).child("games").child(offer).setValue(true);
                                                mGamesDatabaseReference.child(offer).child("username_black").setValue(username);
                                                mGamesDatabaseReference.child("offers").child(offer).removeValue();

                                                // start activity activityGame
                                                Intent intent = new Intent(LobbyActivity.this, GameActivity.class);
                                                intent.putExtra("MATCH_ID",offer);
                                                startActivity(intent);


                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {
                                        }
                                    });
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

                mUsersDatabaseReference.child(userId).child("games").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        // check userId had player
                        if (snapshot.getValue() == null) {


                            //create match
                            mGamesDatabaseReference.child("offers");
                            mGamesDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {


                                    String eventId = mGamesDatabaseReference.push().getKey();
                                    mGamesDatabaseReference.child("offers").child(eventId).setValue(true);

                                    //ensure these all happen
                                    mGamesDatabaseReference.child(eventId).child("white").setValue(userId);
                                    String newBoard = getResources().getString(R.string.new_board);
                                    mGamesDatabaseReference.child(eventId).child("board").setValue(newBoard);
                                    mGamesDatabaseReference.child(eventId).child("turn_color").setValue("white");
                                    mUsersDatabaseReference.child(userId).child("games").child(eventId).setValue(true);
                                    mGamesDatabaseReference.child("offers").child(eventId).setValue(true);
                                    mGamesDatabaseReference.child(eventId).child("match_id").setValue(eventId);
                                    mGamesDatabaseReference.child(eventId).child("username_white").setValue(username);


                                    ProgressBar progressBar = findViewById(R.id.progress);
                                    progressBar.setVisibility(View.VISIBLE);

                                    Intent intent = new Intent(LobbyActivity.this, GameActivity.class);
                                    intent.putExtra("MATCH_ID",eventId);
                                    startActivity(intent);

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                        } else {
                            Log.e("test1", "" + snapshot.getValue().toString());
                            Toast.makeText(LobbyActivity.this, "You already had a match, please wait another player!!!", Toast.LENGTH_SHORT).show();

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