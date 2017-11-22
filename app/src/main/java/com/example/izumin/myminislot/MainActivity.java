package com.example.izumin.myminislot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //起動時にデータをクリアする
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();

        Button betUp = (Button)findViewById(R.id.up_button);
        betUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                editData(1);
            }
        });

        Button betDown = (Button)findViewById(R.id.down_button);
        betDown.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editData(2);
            }
        });

        Button start = (Button)findViewById(R.id.start_button);
        start.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

    }

    public void editData(int num){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();

        int defaultHadCoins = pref.getInt("DEFAULT_HAD", 1000);
        int defaultBetCoincs = pref.getInt("DEFAULT_BET", 10);
        int hadCoins = pref.getInt("HAD_COINS", 1000);
        int betCoins = pref.getInt("BET_COINS", 10);

        switch(num){
            case 1:
                TextView betText = (TextView) findViewById(R.id.bet_text);
                if(betCoins < hadCoins) {
                    betCoins += 10;
                    betText.setText(String.valueOf(betCoins));
                    editor.putInt("BET_COINS", betCoins);
                }
                break;

            case 2:
                TextView betText2 = (TextView) findViewById(R.id.bet_text);
                if(betCoins > 10) {
                    betCoins -= 10;
                    betText2.setText(String.valueOf(betCoins));
                    editor.putInt("BET_COINS", betCoins);
                }
                break;
        }

        editor.commit();
    }
}
