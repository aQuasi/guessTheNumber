package com.guessgame.akwag.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import static com.guessgame.akwag.myapplication.R.id.play_button;

public class MainActivity extends AppCompatActivity {

    Button playButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        New activity is started to play game when the FAB button is clicked
        */
        playButton = (Button) findViewById(play_button);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gameStartActivity = new Intent(MainActivity.this, GameStartActivity.class);
                startActivity(gameStartActivity);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

        //PLAY button animation
        final Animation buttonPressedAnimation = AnimationUtils.loadAnimation(this, R.anim.buttonpressedeffect);
        playButton = (Button) findViewById(play_button);
        playButton.setAnimation(buttonPressedAnimation);

    }

    public void onBackPressed() {
        System.gc();
        System.exit(0);
    }

}
