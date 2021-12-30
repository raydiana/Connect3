package com.example.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    boolean gameActive=true;
    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        counter.setTranslationY(-1500);
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tappedCounter] == 2 && gameActive) {
            gameState[tappedCounter] = activePlayer;
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }
            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
            Log.i("info", "image tapped");

            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                    String winner = "";
                    if (activePlayer == 1) {
                        winner = "yellow";
                    } else {
                        winner = "red";
                    }
                    Button PlayAgainButton=(Button) findViewById(R.id.PlayAgainButton);
                    TextView WinnerTextView=(TextView) findViewById(R.id.WinnerTextView);
                    WinnerTextView.setText(winner +" has won");
                    PlayAgainButton.setVisibility(view.VISIBLE);
                    WinnerTextView.setVisibility(view.VISIBLE);
                }
            }
        }
    }
    public void PlayAgain(View view){
        int i;
        Button PlayAgainButton=(Button) findViewById(R.id.PlayAgainButton);
        TextView WinnerTextView=(TextView) findViewById(R.id.WinnerTextView);
        PlayAgainButton.setVisibility(view.INVISIBLE);
        WinnerTextView.setVisibility(view.INVISIBLE);
        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        for(i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter=(ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);

        }
        for (i=0; i<gameState.length; i++){
            gameState[i]=2;
        }
        activePlayer=0;
        gameActive=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}