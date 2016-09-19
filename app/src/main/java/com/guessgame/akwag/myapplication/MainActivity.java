package com.guessgame.akwag.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        The App exits when the "NO" button is clicked
         */
        Button noButton = (Button) findViewById(R.id.no_button);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        /*
        New activity is started to play game when the "YES" button is clicked
         */
        Button yesButton = (Button) findViewById(R.id.button_yes);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameStartActivity = new Intent(MainActivity.this, GameStartActivity.class);
                startActivity(gameStartActivity);
            }
        });
    }

}
