package com.guessgame.akwag.myapplication;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;

public class GameStartActivity extends AppCompatActivity {

    public Button guessButton;
    private EditText userIn;
    private TextView output;
    private int randomNumber;
    private int attemptsLeft = 7;
    private String message = "";

    public void checkTheUserInput() {
        String usersNumber = userIn.getText().toString();

        try {
            int theNumber = Integer.parseInt(usersNumber);
            attemptsLeft--;

            if (theNumber < 0 || theNumber > 100) {
                message = "Sorry, the number can't be above 100. Please try again.";
                output.setText(message);
                attemptsLeft++;
            }

            else if (theNumber > randomNumber) {
                message = theNumber + " is too high. You have " + attemptsLeft + " attempts left.";
                output.setText(message);

            } else if (theNumber < randomNumber) {
                message = theNumber + " is too low. You have " + attemptsLeft + " attempts left.";
                output.setText(message);

            } else if (theNumber == randomNumber) {
                new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                        .setMessage("CONGRATULATIONS! " + theNumber + " is correct. Do you want to play again?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                userIn.setText("");
                                generateNewGame();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                finish();
                                System.exit(0);
                            }
                        })
                        .show();
            }

            if (attemptsLeft <= 0) {
                askToPlayAgain();
            }

        } catch (Exception e) {
            message = "Please, enter a real number!";
            output.setText(message);

        } finally {
            //Refocuses and selects all text so user doesn't have to clear them one by one
            userIn.requestFocus();
            userIn.selectAll();
        }
    }

    //Alert Dialogue
    public void askToPlayAgain() {
        new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                .setMessage("Sorry, you're out of attempts! The number was: " + randomNumber + "." + " Do you want to try again?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        userIn.setText("");
                        generateNewGame();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        overridePendingTransition(R.anim.slide_out, R.anim.slide_out);
                        System.exit(0);
                    }
                })
                .show();
    }

    private void generateNewGame() {
        randomNumber = (int) (Math.random() * 100 + 1);
        attemptsLeft = 7;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //overridePendingTransition(R.anim.slide_out, R.anim.slide_in);
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
