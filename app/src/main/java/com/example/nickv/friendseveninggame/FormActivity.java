package com.example.nickv.friendseveninggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FormActivity extends AppCompatActivity {

   private ArrayList<String> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);


        Intent i = getIntent();
         players =  i.getStringArrayListExtra("players");

        if(players.size()>=2)
        {
            TextView ingameclick = (TextView) findViewById(R.id.inGameClick);
            ingameclick.setVisibility(View.VISIBLE);
        }
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

    public void textClick(View v)
    {
        if(players.size()<2)
        {
            Toast.makeText(FormActivity.this, "Voeg meer spelers toe om te beginnen",Toast.LENGTH_LONG).show();
        }else{
        Intent i = new Intent(FormActivity.this, inGame.class);
        i.putExtra("gamedata", new GameData(players));
        startActivity(i);
        overridePendingTransition(R.anim.transition, R.anim.transition2);
        finish();
        }
    }

    public void nextButtonClick(View v)
    {
        EditText editname = (EditText) findViewById(R.id.nameInput);
        String name = editname.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(FormActivity.this, "Er is geen naam ingevoerd",Toast.LENGTH_LONG).show();
        }else {
            players.add(name);
            System.out.println("player size :" + players.size());
            Intent i = new Intent(FormActivity.this, FormActivity.class);
            i.putStringArrayListExtra("players", players);
            startActivity(i);
            overridePendingTransition(R.anim.push_up_in, R.anim.push_up_out);
            finish();


        }
    }
}
