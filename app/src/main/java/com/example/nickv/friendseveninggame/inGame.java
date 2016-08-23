package com.example.nickv.friendseveninggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;

public class inGame extends AppCompatActivity {

    private GameData gamedata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_game);




        Intent intent = getIntent();
        gamedata = (GameData) intent.getSerializableExtra("gamedata");

        changeBackgroundcolor();

        TextView questionField = (TextView)findViewById(R.id.gameQuestion);
        questionField.setText(getQuestion(generateRandomNumber()));



    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            if(gamedata.getChosenNumbers().size()==0)
            {
                Intent i = new Intent(inGame.this, FullscreenActivity.class);
                startActivity(i);
                overridePendingTransition(R.anim.transition, R.anim.transition2);
                finish();
            }else {
               View v = findViewById(R.id.framelayout);
                v.playSoundEffect(android.view.SoundEffectConstants.CLICK);
                TextView questionField = (TextView) findViewById(R.id.gameQuestion);
                questionField.setText(getQuestion(generateRandomNumber()));
                changeBackgroundcolor();
            }
        }

        return true;
    }

    public void settingsPlayers(View v){
        Intent i = new Intent(inGame.this, settingsActivity.class);
        i.putExtra("gamedata", gamedata);
        startActivity(i);
        overridePendingTransition(R.anim.transition, R.anim.transition2);
        finish();
    }

    public void exitToMainMenu(View v){
        Intent i = new Intent(inGame.this, FullscreenActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.transition, R.anim.transition2);
        finish();
    }

    public void addPlayers(){

    }

    public void changeBackgroundcolor()
    {
        FrameLayout fl = (FrameLayout) findViewById(R.id.framelayout);
        Random r = new Random();
        fl.setBackgroundColor(gamedata.getBgcolors().get(r.nextInt(gamedata.getBgcolors().size())));
    }

    public int generateRandomNumber()
    {
        Random r = new Random();
        int High = gamedata.getChosenNumbers().size();
        int result = r.nextInt(High);
        gamedata.getChosenNumbers().remove(result);
        return result;
    }

    public String getQuestion(int questionNumber)
    {
        Random r = new Random();
        int High = gamedata.getPlayers().size();
        int result = r.nextInt(High);
        int result2 = r.nextInt(High);
        String player1 = gamedata.getPlayers().get(result);
        String player2 = gamedata.getPlayers().get(result2);

        switch(questionNumber) {
            default: return player1 + " moet 2 keer drinken";
            case 1: return "De persoon die het meeste alcohol kan drinken drinkt 3 keer";
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


}
