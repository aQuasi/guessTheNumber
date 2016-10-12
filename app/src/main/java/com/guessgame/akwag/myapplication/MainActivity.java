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
        New activity is started to play game when the FAB button is clicked
        */

        View fabButton = (View) findViewById(R.id.floatingActionButton);
        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameStartActivity = new Intent(MainActivity.this, GameStartActivity.class);
                startActivity(gameStartActivity);
            }
        });

    }

}
