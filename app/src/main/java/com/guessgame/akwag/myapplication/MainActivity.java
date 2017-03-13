package com.guessgame.akwag.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeText);
        Typeface customWelcomeFontMessage = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        welcomeMessage.setTypeface(customWelcomeFontMessage);

        /*
        New activity is started to play game when the FAB button is clicked
        */

        playButton = (Button) findViewById(R.id.play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameStartActivity = new Intent(MainActivity.this, GameStartActivity.class);
                startActivity(gameStartActivity);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

    }

    public void onBackPressed() {
        System.gc();
        System.exit(0);
    }

}
