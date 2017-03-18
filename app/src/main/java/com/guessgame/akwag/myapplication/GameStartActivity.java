package com.guessgame.akwag.myapplication;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.app.AlertDialog;

import static com.guessgame.akwag.myapplication.R.id.guess_button;

public class GameStartActivity extends AppCompatActivity {

    public Button guessButton;
    MediaPlayer audioFile;
    Button reset;
    private TextView scoreCounter;
    private EditText userIn;
    private TextView output;
    private int randomNumber;
    private int attemptsLeft = 7;
    private int numberOfTriesUsed = 0;
    private String message = "";
    private int userScore = 0;

    public void checkTheUserInput() {
        String usersNumber = userIn.getText().toString();

        try {
            int theNumber = Integer.parseInt(usersNumber);
            attemptsLeft--;
            numberOfTriesUsed += 1;

            if (theNumber < 0 || theNumber > 100) {
                message = "Invalid! Your number is above 100.";
                output.setText(message);
                attemptsLeft++;

            } else if (theNumber > randomNumber) {
                message = theNumber + " is too high. You have " + attemptsLeft + " attempts left.";
                output.setText(message);

            } else if (theNumber < randomNumber) {
                message = theNumber + " is too low. You have " + attemptsLeft + " attempts left.";
                output.setText(message);

            } else if (theNumber == randomNumber) {
                audioFile = MediaPlayer.create(GameStartActivity.this, R.raw.congratsound);
                audioFile.start();
                userScore += userScore + 1;
                scoreCounter.setText("" + userScore);

                new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK)
                        .setMessage("CONGRATULATIONS! " + theNumber + " is correct. It took you " + numberOfTriesUsed + " tries. Do you want to play again?")
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
                                System.exit(1);
                            }
                        })
                        .show();
            }

            if (attemptsLeft < 1) {
                audioFile = MediaPlayer.create(GameStartActivity.this, R.raw.failedsound);
                audioFile.start();
                userScore = 0;
                scoreCounter.setText("" + userScore);
                message = "OUT OF ATTEMPTS! " + randomNumber + " was the number. \n Please tap RESET to play again.";
                output.setText(message);
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

    private void generateNewGame() {
        randomNumber = (int) (Math.random() * 100 + 1);
        attemptsLeft = 7;
        numberOfTriesUsed = 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_start);

        guessButton = (Button) findViewById(R.id.guess_button);
        userIn = (EditText) findViewById(R.id.userInput);
        output = (TextView) findViewById(R.id.outMessage);
        reset = (Button) findViewById(R.id.reset_button);
        scoreCounter = (TextView) findViewById(R.id.score_counter);

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

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateNewGame();
                message = "GAME HAS RESET!";
                userIn.setText("");
                output.setText(message);
            }
        });

        //Guess button pressed animation
        final Animation buttonPressedAnimation = AnimationUtils.loadAnimation(this, R.anim.buttonpressedeffect);
        guessButton = (Button) findViewById(guess_button);
        guessButton.setAnimation(buttonPressedAnimation);

    }

}
