package com.example.connect3game;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     // 0: yellow, 1: red
    int activePlayer = 0;

    // gameState[2] = empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winConditions = {{0, 1, 2},{3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    boolean gameActive = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tokenDrop(View view) {
        ImageView token = (ImageView) view;

        int tappedToken = Integer.parseInt(token.getTag().toString());


        if(gameState[tappedToken] == 2 && gameActive) {

            gameState[tappedToken] = activePlayer;

            token.setTranslationY(-1500);

            if (activePlayer == 0) {
                token.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                token.setImageResource(R.drawable.red);
                activePlayer = 0;
            }


            token.animate().translationYBy(1500).rotation(3600).setDuration(300);

            for (int[] winCondition : winConditions) {
                if (gameState[winCondition[0]] == gameState[winCondition[1]] && gameState[winCondition[1]] == gameState[winCondition[2]] && gameState[winCondition[0]] != 2) {

                    String winner;

                    gameActive = false;

                    if (activePlayer == 1) {
                        winner = "Yellow";
                    } else {
                        winner = "Red";
                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

                    winnerTextView.setText(winner + " has won!");

                    winnerTextView.setVisibility(View.VISIBLE);

                    playAgainButton.setVisibility(View.VISIBLE);
                }
            }

        }

    }

    public void playAgain(View view) {
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);

        winnerTextView.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int x=0; x < gridLayout.getChildCount(); x++) {
            ImageView tokens = (ImageView) gridLayout.getChildAt(x);

            tokens.setImageDrawable(null);

        }

        activePlayer = 0;

        for(int y = 0; y < gameState.length; y++) {
            gameState[y] = 2;
        }

        gameActive = true;


    }

}
