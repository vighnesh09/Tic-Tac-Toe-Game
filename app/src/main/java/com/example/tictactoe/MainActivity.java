package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean gameActive;
    int winX = 0;
    int winO = 0;
    //0 - X
    //1 - O
    int count = 0;
    //counting tap on image
    int activePlayer = 0;
    // state means
    // 0 - X
    // 1- O
    // 2 - NULL
     int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winPositions = { {0,1,2} , {3,4,5} , {6,7,8},
                             {0,3,6} , {1,4,7} , {2,5,8},
                             {0,4,8} , {2,4,6} };


    public void playerTap(View view){
        TextView warn = findViewById(R.id.warning);

        ImageView img = (ImageView) view;
        Button b1 = findViewById(R.id.button);


        int tappedImg = Integer.parseInt(img.getTag().toString());
       if(count >= 8){
           TextView status = findViewById(R.id.status);
           status.setVisibility(View.INVISIBLE);
           b1.setVisibility(View.VISIBLE);

           if(count >= 9){
                if(gameState[tappedImg]==0 || gameState[tappedImg]==1||gameState[tappedImg]==2) {

                    warn.setText("CLick on RESET to Play");

                }
            }
       }

        if (!gameActive){
            gameReset(view);
            bClick(view);
        }
        if(gameState[tappedImg] == 2 )
        {
            gameState[tappedImg] = activePlayer;

            if(activePlayer == 0)
            {
                TextView status = findViewById(R.id.status);
                status.setText("O's turn - Tap to Play");
                img.setImageResource(R.drawable.cross);
                activePlayer = 1;
                count++;

            }else
            {
                TextView status = findViewById(R.id.status);
                status.setText("X's turn - Tap to Play");
                img.setImageResource(R.drawable.zero);
               activePlayer = 0;
               count++;

            }
            Animation alpha = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.alpha);
            img.startAnimation(alpha);

            for (int[] winPosition : winPositions ){

                if(gameState[winPosition[0]] == gameState[winPosition[1]] &&
                        gameState[winPosition[1]] == gameState[winPosition[2]] &&
                        gameState[winPosition[0]] != 2)
                {
                    TextView status = findViewById(R.id.status);
                    status.setVisibility(View.INVISIBLE);
                    b1.setVisibility(View.VISIBLE);
                    //spmebodys has won
                    String winstr;
                    if(gameState[winPosition[0]] == 0){
                        winstr = "X is Winner..!";
                        gameActive = false;
                        winX++;
                        String winXX = String.valueOf(winX);
                        TextView sX = findViewById(R.id.sX);
                        sX.setText(winXX);


                    }else {
                            winstr = "O is Winner..!";
                            gameActive = false;
                            winO++;
                        String winOO = String.valueOf(winO);
                        TextView sO = findViewById(R.id.sO);
                        sO.setText(winOO);
                    }
                    //update the status
                    TextView fStatus = findViewById(R.id.welcome);
                    fStatus.setText(winstr);
                   // String check = "Winner";
                    String check = "Winner";


                }
            }
        }
    }

    public void bClick(View view){

        Button b1 = findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameReset(view);
            }
        });

    }
    public void gameReset(View view){
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length;i++){
            gameState[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's turn - Tap to Play");
        count = 0;
        Button b1 = findViewById(R.id.button);
        b1.setVisibility(View.INVISIBLE);
        status.setVisibility(View.VISIBLE);

        TextView fStatus = findViewById(R.id.welcome);
        fStatus.setText("Welcome To Tic-Tac-Toe");
        TextView warn = findViewById(R.id.warning);
        warn.setText(" ");

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
}
