package com.example.nickv.friendseveninggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class settingsActivity extends AppCompatActivity {

    private GameData gamedata;
    private ArrayList<EditText> players = new ArrayList<EditText>();
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);

        Intent intent = getIntent();
        gamedata = (GameData) intent.getSerializableExtra("gamedata");

        ScrollView scrollView = (ScrollView) findViewById(R.id.scrollview);

// Create a LinearLayout element
        linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

// Add Buttons
        for(String spelers : gamedata.getPlayers()) {
            EditText button = new EditText(this);
            button.setText(spelers);
            linearLayout.addView(button);
        }


// Add the LinearLayout element to the ScrollView
        scrollView.addView(linearLayout);
    }

    public void finishedSettings(View v)
    {
        ArrayList<String> players = new ArrayList<String>();

        for( int i = 0; i < linearLayout.getChildCount(); i++ )
            if( linearLayout.getChildAt( i ) instanceof EditText )
                players.add( ((EditText) linearLayout.getChildAt(i)).getText().toString() );

        gamedata.setPlayers(players);

        Intent i = new Intent(settingsActivity.this, inGame.class);
        i.putExtra("gamedata", gamedata);
        startActivity(i);
        overridePendingTransition(R.anim.transition, R.anim.transition2);
        finish();
    }



    @Override
    public void onWindowFocusChanged(boolean hasFocas){
        super.onWindowFocusChanged(hasFocas);
        View decorView = getWindow().getDecorView();
        if(hasFocas) {
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    }

}
