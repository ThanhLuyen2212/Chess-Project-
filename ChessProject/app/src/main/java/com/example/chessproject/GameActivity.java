package com.example.chessproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    public static ImageView a1;
    public static ImageView a2;
    public static ImageView a3;
    public static ImageView a4;
    public static ImageView a5;
    public static ImageView a6;
    public static ImageView a7;
    public static ImageView a8;

    public static ImageView b1;
    public static ImageView b2;
    public static ImageView b3;
    public static ImageView b4;
    public static ImageView b5;
    public static ImageView b6;
    public static ImageView b7;
    public static ImageView b8;

    public static ImageView c1;
    public static ImageView c2;
    public static ImageView c3;
    public static ImageView c4;
    public static ImageView c5;
    public static ImageView c6;
    public static ImageView c7;
    public static ImageView c8;

    public static ImageView d1;
    public static ImageView d2;
    public static ImageView d3;
    public static ImageView d4;
    public static ImageView d5;
    public static ImageView d6;
    public static ImageView d7;
    public static ImageView d8;

    public static ImageView e1;
    public static ImageView e2;
    public static ImageView e3;
    public static ImageView e4;
    public static ImageView e5;
    public static ImageView e6;
    public static ImageView e7;
    public static ImageView e8;

    public static ImageView f1;
    public static ImageView f2;
    public static ImageView f3;
    public static ImageView f4;
    public static ImageView f5;
    public static ImageView f6;
    public static ImageView f7;
    public static ImageView f8;

    public static ImageView g1;
    public static ImageView g2;
    public static ImageView g3;
    public static ImageView g4;
    public static ImageView g5;
    public static ImageView g6;
    public static ImageView g7;
    public static ImageView g8;

    public static ImageView h1;
    public static ImageView h2;
    public static ImageView h3;
    public static ImageView h4;
    public static ImageView h5;
    public static ImageView h6;
    public static ImageView h7;
    public static ImageView h8;

    private static TextView logs;

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseDatabase mFirebaseDatabase;

    private String userId;

    // All static for Movement.java
    public static String gamesetString = "";
    public static String playerColor;
    public static String turn = "";
    public static TextView currentTurn;
    public static DatabaseReference mGamesDatabaseReference;
    public static String match_id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mGamesDatabaseReference = mFirebaseDatabase.getReference().child("games");

        match_id = getIntent().getStringExtra("MATCH_ID");

        //get current user and send to login screen if user is null
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        userId = user.getUid();

        // check exit user
        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(GameActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        currentTurn = (TextView) findViewById(R.id.current_turn);

        final TextView currentSide = (TextView) findViewById(R.id.current_side);

        // Get user games for Active list
        mGamesDatabaseReference.child(match_id).child("white").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String white_player = dataSnapshot.getValue(String.class);
                if (userId.equals(white_player)) {
                    playerColor = "white";

                    currentSide.setText("you are: WHITE");
                    currentSide.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    currentSide.setTextColor(Color.parseColor("#000000"));

                } else {
                    playerColor = "black";

                    currentSide.setText("you are: BLACK");
                    currentSide.setBackgroundColor(Color.parseColor("#000000"));
                    currentSide.setTextColor(Color.parseColor("#FFFFFF"));

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        logs = (TextView) findViewById(R.id.log);

        //Best way to reverse image view order depending on playerColor.
        a1 = (ImageView) findViewById(R.id.img_a1);
        a2 = (ImageView) findViewById(R.id.img_a2);
        a3 = (ImageView) findViewById(R.id.img_a3);
        a4 = (ImageView) findViewById(R.id.img_a4);
        a5 = (ImageView) findViewById(R.id.img_a5);
        a6 = (ImageView) findViewById(R.id.img_a6);
        a7 = (ImageView) findViewById(R.id.img_a7);
        a8 = (ImageView) findViewById(R.id.img_a8);

        b1 = (ImageView) findViewById(R.id.img_b1);
        b2 = (ImageView) findViewById(R.id.img_b2);
        b3 = (ImageView) findViewById(R.id.img_b3);
        b4 = (ImageView) findViewById(R.id.img_b4);
        b5 = (ImageView) findViewById(R.id.img_b5);
        b6 = (ImageView) findViewById(R.id.img_b6);
        b7 = (ImageView) findViewById(R.id.img_b7);
        b8 = (ImageView) findViewById(R.id.img_b8);

        c1 = (ImageView) findViewById(R.id.img_c1);
        c2 = (ImageView) findViewById(R.id.img_c2);
        c3 = (ImageView) findViewById(R.id.img_c3);
        c4 = (ImageView) findViewById(R.id.img_c4);
        c5 = (ImageView) findViewById(R.id.img_c5);
        c6 = (ImageView) findViewById(R.id.img_c6);
        c7 = (ImageView) findViewById(R.id.img_c7);
        c8 = (ImageView) findViewById(R.id.img_c8);

        d1 = (ImageView) findViewById(R.id.img_d1);
        d2 = (ImageView) findViewById(R.id.img_d2);
        d3 = (ImageView) findViewById(R.id.img_d3);
        d4 = (ImageView) findViewById(R.id.img_d4);
        d5 = (ImageView) findViewById(R.id.img_d5);
        d6 = (ImageView) findViewById(R.id.img_d6);
        d7 = (ImageView) findViewById(R.id.img_d7);
        d8 = (ImageView) findViewById(R.id.img_d8);

        e1 = (ImageView) findViewById(R.id.img_e1);
        e2 = (ImageView) findViewById(R.id.img_e2);
        e3 = (ImageView) findViewById(R.id.img_e3);
        e4 = (ImageView) findViewById(R.id.img_e4);
        e5 = (ImageView) findViewById(R.id.img_e5);
        e6 = (ImageView) findViewById(R.id.img_e6);
        e7 = (ImageView) findViewById(R.id.img_e7);
        e8 = (ImageView) findViewById(R.id.img_e8);

        f1 = (ImageView) findViewById(R.id.img_f1);
        f2 = (ImageView) findViewById(R.id.img_f2);
        f3 = (ImageView) findViewById(R.id.img_f3);
        f4 = (ImageView) findViewById(R.id.img_f4);
        f5 = (ImageView) findViewById(R.id.img_f5);
        f6 = (ImageView) findViewById(R.id.img_f6);
        f7 = (ImageView) findViewById(R.id.img_f7);
        f8 = (ImageView) findViewById(R.id.img_f8);

        g1 = (ImageView) findViewById(R.id.img_g1);
        g2 = (ImageView) findViewById(R.id.img_g2);
        g3 = (ImageView) findViewById(R.id.img_g3);
        g4 = (ImageView) findViewById(R.id.img_g4);
        g5 = (ImageView) findViewById(R.id.img_g5);
        g6 = (ImageView) findViewById(R.id.img_g6);
        g7 = (ImageView) findViewById(R.id.img_g7);
        g8 = (ImageView) findViewById(R.id.img_g8);

        h1 = (ImageView) findViewById(R.id.img_h1);
        h2 = (ImageView) findViewById(R.id.img_h2);
        h3 = (ImageView) findViewById(R.id.img_h3);
        h4 = (ImageView) findViewById(R.id.img_h4);
        h5 = (ImageView) findViewById(R.id.img_h5);
        h6 = (ImageView) findViewById(R.id.img_h6);
        h7 = (ImageView) findViewById(R.id.img_h7);
        h8 = (ImageView) findViewById(R.id.img_h8);


        // Get gamesetString from firebase
        mGamesDatabaseReference.child(match_id).child("board").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Log.e("GAMESET", "" + match_id);
                Log.e("GAMESET", "" + dataSnapshot.getValue(String.class));

                gamesetString = dataSnapshot.getValue(String.class);



                // Get turn color from firebase
                mGamesDatabaseReference.child(match_id).child("turn_color").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        turn = dataSnapshot.getValue(String.class);

                        Log.e("turn_color-ref", turn);

                        if (turn.equals("black")) {
                            currentTurn.setText("turn: BLACK");
                            currentTurn.setBackgroundColor(Color.parseColor("#000000"));
                            currentTurn.setTextColor(Color.parseColor("#FFFFFF"));

                        } else if (turn.equals("white")) {
                            currentTurn.setText("turn: WHITE");
                            currentTurn.setBackgroundColor(Color.parseColor("#FFFFFF"));
                            currentTurn.setTextColor(Color.parseColor("#000000"));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        System.out.println("The read failed: " + databaseError.getCode());
                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

    }

}