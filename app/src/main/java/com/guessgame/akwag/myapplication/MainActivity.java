package com.guessgame.akwag.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView gameDescriptionText = (TextView)findViewById(R.id.textView);
        Typeface customFont = Typeface.createFromAsset(getAssets(),  "fonts/Raleway-Regular.ttf");
        gameDescriptionText.setTypeface(customFont);

        TextView welcomeMessage = (TextView) findViewById(R.id.welcomeText);
        Typeface customWelcomeFontMessage = Typeface.createFromAsset(getAssets(), "fonts/Raleway-Bold.ttf");
        welcomeMessage.setTypeface(customWelcomeFontMessage);

        /*
        New activity is started to play game when the FAB button is clicked
        */

        View fabButton = (View) findViewById(R.id.floatingActionButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameStartActivity = new Intent(MainActivity.this, GameStartActivity.class);
                startActivity(gameStartActivity);
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
            }
        });

    }

}
