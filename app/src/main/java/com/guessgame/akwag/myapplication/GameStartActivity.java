package com.guessgame.akwag.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameStartActivity extends AppCompatActivity {

    public Button guessButton;
    private EditText userIn;
    private TextView output;

    private int randomNumber;

    private int attemptsLeft = 6;

    public void checkTheUserInput() {
        String usersNumber = userIn.getText().toString();
        String message;

        try {
            int theNumber = Integer.parseInt(usersNumber);
            attemptsLeft--;

            if (theNumber > randomNumber) {
                message = "too high. You have " + attemptsLeft + " attempts left.";
                output.setText(message);
            }
            else if (theNumber < randomNumber) {
                message = "too low. You have " + attemptsLeft + " attempts left.";
                output.setText(message);
            }
            else {
                message = theNumber + " is correct. WINNER! Play Again!";
                output.setText(message);
                generateNewGame();
            }

            if (attemptsLeft <= 0) {
                message = "You run out of attempts! \n" +
                        "The correct number was : " + randomNumber;
                output.setText(message);
            }
        }
        catch (Exception e) {
            message = "real numbers only";
            output.setText(message);
        }
        finally {
            //Refocuses and selects all text so user doesn't have to clear them one by one
            userIn.requestFocus();
            userIn.selectAll();
        }
    }

    private void generateNewGame() {
        randomNumber = (int) (Math.random() * 100 + 1);
        attemptsLeft = 6;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);

        guessButton = (Button) findViewById(R.id.guess_button);
        userIn = (EditText) findViewById(R.id.userInput);
        output = (TextView) findViewById(R.id.outMessage);

        generateNewGame();

        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkTheUserInput();
            }
        });

        //When ENTER is hit on the keyboard, performs the same function as the GUESS button
        userIn.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                checkTheUserInput();
                return true;
            }
        });

    }
}