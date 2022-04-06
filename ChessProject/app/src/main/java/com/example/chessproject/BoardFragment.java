package com.example.chessproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

public class BoardFragment extends Fragment {

    private String match_id;
    private String gameset;
    private TextView logoCover;

    private ImageView a1;
    private ImageView a2;
    private ImageView a3;
    private ImageView a4;
    private ImageView a5;
    private ImageView a6;
    private ImageView a7;
    private ImageView a8;

    private ImageView b1;
    private ImageView b2;
    private ImageView b3;
    private ImageView b4;
    private ImageView b5;
    private ImageView b6;
    private ImageView b7;
    private ImageView b8;

    private ImageView c1;
    private ImageView c2;
    private ImageView c3;
    private ImageView c4;
    private ImageView c5;
    private ImageView c6;
    private ImageView c7;
    private ImageView c8;

    private ImageView d1;
    private ImageView d2;
    private ImageView d3;
    private ImageView d4;
    private ImageView d5;
    private ImageView d6;
    private ImageView d7;
    private ImageView d8;

    private ImageView e1;
    private ImageView e2;
    private ImageView e3;
    private ImageView e4;
    private ImageView e5;
    private ImageView e6;
    private ImageView e7;
    private ImageView e8;

    private ImageView f1;
    private ImageView f2;
    private ImageView f3;
    private ImageView f4;
    private ImageView f5;
    private ImageView f6;
    private ImageView f7;
    private ImageView f8;

    private ImageView g1;
    private ImageView g2;
    private ImageView g3;
    private ImageView g4;
    private ImageView g5;
    private ImageView g6;
    private ImageView g7;
    private ImageView g8;

    private ImageView h1;
    private ImageView h2;
    private ImageView h3;
    private ImageView h4;
    private ImageView h5;
    private ImageView h6;
    private ImageView h7;
    private ImageView h8;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_board, container, false);

        // Get gameset bundle
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            gameset = bundle.getString("gameset");
            match_id = bundle.getString("match_id");

            logoCover = (TextView) v.findViewById(R.id.logo_cover);

            LinearLayout gameBoard = (LinearLayout) v.findViewById(R.id.board);
            gameBoard.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Log.e("GAMESET", "gameset" + gameset);
                    Log.e("GAMESET", "withintent" + match_id);

                    Intent intent = new Intent(getActivity(), GameActivity.class);
                    intent.putExtra("MATCH_ID", match_id);
                    startActivity(intent);

                }
            });
        }

        // TODO: Best way to reverse image view order depending on playerColor.
        a1 = (ImageView) v.findViewById(R.id.img_a1);
        a2 = (ImageView) v.findViewById(R.id.img_a2);
        a3 = (ImageView) v.findViewById(R.id.img_a3);
        a4 = (ImageView) v.findViewById(R.id.img_a4);
        a5 = (ImageView) v.findViewById(R.id.img_a5);
        a6 = (ImageView) v.findViewById(R.id.img_a6);
        a7 = (ImageView) v.findViewById(R.id.img_a7);
        a8 = (ImageView) v.findViewById(R.id.img_a8);

        b1 = (ImageView) v.findViewById(R.id.img_b1);
        b2 = (ImageView) v.findViewById(R.id.img_b2);
        b3 = (ImageView) v.findViewById(R.id.img_b3);
        b4 = (ImageView) v.findViewById(R.id.img_b4);
        b5 = (ImageView) v.findViewById(R.id.img_b5);
        b6 = (ImageView) v.findViewById(R.id.img_b6);
        b7 = (ImageView) v.findViewById(R.id.img_b7);
        b8 = (ImageView) v.findViewById(R.id.img_b8);

        c1 = (ImageView) v.findViewById(R.id.img_c1);
        c2 = (ImageView) v.findViewById(R.id.img_c2);
        c3 = (ImageView) v.findViewById(R.id.img_c3);
        c4 = (ImageView) v.findViewById(R.id.img_c4);
        c5 = (ImageView) v.findViewById(R.id.img_c5);
        c6 = (ImageView) v.findViewById(R.id.img_c6);
        c7 = (ImageView) v.findViewById(R.id.img_c7);
        c8 = (ImageView) v.findViewById(R.id.img_c8);

        d1 = (ImageView) v.findViewById(R.id.img_d1);
        d2 = (ImageView) v.findViewById(R.id.img_d2);
        d3 = (ImageView) v.findViewById(R.id.img_d3);
        d4 = (ImageView) v.findViewById(R.id.img_d4);
        d5 = (ImageView) v.findViewById(R.id.img_d5);
        d6 = (ImageView) v.findViewById(R.id.img_d6);
        d7 = (ImageView) v.findViewById(R.id.img_d7);
        d8 = (ImageView) v.findViewById(R.id.img_d8);

        e1 = (ImageView) v.findViewById(R.id.img_e1);
        e2 = (ImageView) v.findViewById(R.id.img_e2);
        e3 = (ImageView) v.findViewById(R.id.img_e3);
        e4 = (ImageView) v.findViewById(R.id.img_e4);
        e5 = (ImageView) v.findViewById(R.id.img_e5);
        e6 = (ImageView) v.findViewById(R.id.img_e6);
        e7 = (ImageView) v.findViewById(R.id.img_e7);
        e8 = (ImageView) v.findViewById(R.id.img_e8);

        f1 = (ImageView) v.findViewById(R.id.img_f1);
        f2 = (ImageView) v.findViewById(R.id.img_f2);
        f3 = (ImageView) v.findViewById(R.id.img_f3);
        f4 = (ImageView) v.findViewById(R.id.img_f4);
        f5 = (ImageView) v.findViewById(R.id.img_f5);
        f6 = (ImageView) v.findViewById(R.id.img_f6);
        f7 = (ImageView) v.findViewById(R.id.img_f7);
        f8 = (ImageView) v.findViewById(R.id.img_f8);

        g1 = (ImageView) v.findViewById(R.id.img_g1);
        g2 = (ImageView) v.findViewById(R.id.img_g2);
        g3 = (ImageView) v.findViewById(R.id.img_g3);
        g4 = (ImageView) v.findViewById(R.id.img_g4);
        g5 = (ImageView) v.findViewById(R.id.img_g5);
        g6 = (ImageView) v.findViewById(R.id.img_g6);
        g7 = (ImageView) v.findViewById(R.id.img_g7);
        g8 = (ImageView) v.findViewById(R.id.img_g8);

        h1 = (ImageView) v.findViewById(R.id.img_h1);
        h2 = (ImageView) v.findViewById(R.id.img_h2);
        h3 = (ImageView) v.findViewById(R.id.img_h3);
        h4 = (ImageView) v.findViewById(R.id.img_h4);
        h5 = (ImageView) v.findViewById(R.id.img_h5);
        h6 = (ImageView) v.findViewById(R.id.img_h6);
        h7 = (ImageView) v.findViewById(R.id.img_h7);
        h8 = (ImageView) v.findViewById(R.id.img_h8);

        setBoard();


        return v;
    }

    private void setBoard() {

        if (gameset != null) {
            Log.e("HEREWEGO", "" + gameset);

            List<String> gamesetList = Arrays.asList(gameset.split("\\s*,\\s*"));

            a1.setImageResource(getResources().getIdentifier(gamesetList.get(0), "drawable", getActivity().getPackageName()));
            b1.setImageResource(getResources().getIdentifier(gamesetList.get(1), "drawable", getActivity().getPackageName()));
            c1.setImageResource(getResources().getIdentifier(gamesetList.get(2), "drawable", getActivity().getPackageName()));
            d1.setImageResource(getResources().getIdentifier(gamesetList.get(3), "drawable", getActivity().getPackageName()));
            e1.setImageResource(getResources().getIdentifier(gamesetList.get(4), "drawable", getActivity().getPackageName()));
            f1.setImageResource(getResources().getIdentifier(gamesetList.get(5), "drawable", getActivity().getPackageName()));
            g1.setImageResource(getResources().getIdentifier(gamesetList.get(6), "drawable", getActivity().getPackageName()));
            h1.setImageResource(getResources().getIdentifier(gamesetList.get(7), "drawable", getActivity().getPackageName()));

            a2.setImageResource(getResources().getIdentifier(gamesetList.get(8), "drawable", getActivity().getPackageName()));
            b2.setImageResource(getResources().getIdentifier(gamesetList.get(9), "drawable", getActivity().getPackageName()));
            c2.setImageResource(getResources().getIdentifier(gamesetList.get(10), "drawable", getActivity().getPackageName()));
            d2.setImageResource(getResources().getIdentifier(gamesetList.get(11), "drawable", getActivity().getPackageName()));
            e2.setImageResource(getResources().getIdentifier(gamesetList.get(12), "drawable", getActivity().getPackageName()));
            f2.setImageResource(getResources().getIdentifier(gamesetList.get(13), "drawable", getActivity().getPackageName()));
            g2.setImageResource(getResources().getIdentifier(gamesetList.get(14), "drawable", getActivity().getPackageName()));
            h2.setImageResource(getResources().getIdentifier(gamesetList.get(15), "drawable", getActivity().getPackageName()));

            a3.setImageResource(getResources().getIdentifier(gamesetList.get(16), "drawable", getActivity().getPackageName()));
            b3.setImageResource(getResources().getIdentifier(gamesetList.get(17), "drawable", getActivity().getPackageName()));
            c3.setImageResource(getResources().getIdentifier(gamesetList.get(18), "drawable", getActivity().getPackageName()));
            d3.setImageResource(getResources().getIdentifier(gamesetList.get(19), "drawable", getActivity().getPackageName()));
            e3.setImageResource(getResources().getIdentifier(gamesetList.get(20), "drawable", getActivity().getPackageName()));
            f3.setImageResource(getResources().getIdentifier(gamesetList.get(21), "drawable", getActivity().getPackageName()));
            g3.setImageResource(getResources().getIdentifier(gamesetList.get(22), "drawable", getActivity().getPackageName()));
            h3.setImageResource(getResources().getIdentifier(gamesetList.get(23), "drawable", getActivity().getPackageName()));

            a4.setImageResource(getResources().getIdentifier(gamesetList.get(24), "drawable", getActivity().getPackageName()));
            b4.setImageResource(getResources().getIdentifier(gamesetList.get(25), "drawable", getActivity().getPackageName()));
            c4.setImageResource(getResources().getIdentifier(gamesetList.get(26), "drawable", getActivity().getPackageName()));
            d4.setImageResource(getResources().getIdentifier(gamesetList.get(27), "drawable", getActivity().getPackageName()));
            e4.setImageResource(getResources().getIdentifier(gamesetList.get(28), "drawable", getActivity().getPackageName()));
            f4.setImageResource(getResources().getIdentifier(gamesetList.get(29), "drawable", getActivity().getPackageName()));
            g4.setImageResource(getResources().getIdentifier(gamesetList.get(30), "drawable", getActivity().getPackageName()));
            h4.setImageResource(getResources().getIdentifier(gamesetList.get(31), "drawable", getActivity().getPackageName()));

            a5.setImageResource(getResources().getIdentifier(gamesetList.get(32), "drawable", getActivity().getPackageName()));
            b5.setImageResource(getResources().getIdentifier(gamesetList.get(33), "drawable", getActivity().getPackageName()));
            c5.setImageResource(getResources().getIdentifier(gamesetList.get(34), "drawable", getActivity().getPackageName()));
            d5.setImageResource(getResources().getIdentifier(gamesetList.get(35), "drawable", getActivity().getPackageName()));
            e5.setImageResource(getResources().getIdentifier(gamesetList.get(36), "drawable", getActivity().getPackageName()));
            f5.setImageResource(getResources().getIdentifier(gamesetList.get(37), "drawable", getActivity().getPackageName()));
            g5.setImageResource(getResources().getIdentifier(gamesetList.get(38), "drawable", getActivity().getPackageName()));
            h5.setImageResource(getResources().getIdentifier(gamesetList.get(39), "drawable", getActivity().getPackageName()));

            a6.setImageResource(getResources().getIdentifier(gamesetList.get(40), "drawable", getActivity().getPackageName()));
            b6.setImageResource(getResources().getIdentifier(gamesetList.get(41), "drawable", getActivity().getPackageName()));
            c6.setImageResource(getResources().getIdentifier(gamesetList.get(42), "drawable", getActivity().getPackageName()));
            d6.setImageResource(getResources().getIdentifier(gamesetList.get(43), "drawable", getActivity().getPackageName()));
            e6.setImageResource(getResources().getIdentifier(gamesetList.get(44), "drawable", getActivity().getPackageName()));
            f6.setImageResource(getResources().getIdentifier(gamesetList.get(45), "drawable", getActivity().getPackageName()));
            g6.setImageResource(getResources().getIdentifier(gamesetList.get(46), "drawable", getActivity().getPackageName()));
            h6.setImageResource(getResources().getIdentifier(gamesetList.get(47), "drawable", getActivity().getPackageName()));

            a7.setImageResource(getResources().getIdentifier(gamesetList.get(48), "drawable", getActivity().getPackageName()));
            b7.setImageResource(getResources().getIdentifier(gamesetList.get(49), "drawable", getActivity().getPackageName()));
            c7.setImageResource(getResources().getIdentifier(gamesetList.get(50), "drawable", getActivity().getPackageName()));
            d7.setImageResource(getResources().getIdentifier(gamesetList.get(51), "drawable", getActivity().getPackageName()));
            e7.setImageResource(getResources().getIdentifier(gamesetList.get(52), "drawable", getActivity().getPackageName()));
            f7.setImageResource(getResources().getIdentifier(gamesetList.get(53), "drawable", getActivity().getPackageName()));
            g7.setImageResource(getResources().getIdentifier(gamesetList.get(54), "drawable", getActivity().getPackageName()));
            h7.setImageResource(getResources().getIdentifier(gamesetList.get(55), "drawable", getActivity().getPackageName()));

            a8.setImageResource(getResources().getIdentifier(gamesetList.get(56), "drawable", getActivity().getPackageName()));
            b8.setImageResource(getResources().getIdentifier(gamesetList.get(57), "drawable", getActivity().getPackageName()));
            c8.setImageResource(getResources().getIdentifier(gamesetList.get(58), "drawable", getActivity().getPackageName()));
            d8.setImageResource(getResources().getIdentifier(gamesetList.get(59), "drawable", getActivity().getPackageName()));
            e8.setImageResource(getResources().getIdentifier(gamesetList.get(60), "drawable", getActivity().getPackageName()));
            f8.setImageResource(getResources().getIdentifier(gamesetList.get(61), "drawable", getActivity().getPackageName()));
            g8.setImageResource(getResources().getIdentifier(gamesetList.get(62), "drawable", getActivity().getPackageName()));
            h8.setImageResource(getResources().getIdentifier(gamesetList.get(63), "drawable", getActivity().getPackageName()));

            logoCover.setVisibility(View.GONE);
        } else {
//            logoCover.setVisibility(View.VISIBLE);
        }

    }
}
