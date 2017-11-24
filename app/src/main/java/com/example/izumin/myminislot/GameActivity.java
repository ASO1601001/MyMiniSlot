package com.example.izumin.myminislot;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by izumin on 2017/09/15.
 */

public class GameActivity extends AppCompatActivity {

    final int banana = 0;
    final int bar = 1;
    final int bigwin = 2;
    final int cherry = 3;
    final int grape = 4;
    final int lemon = 5;
    final int orange = 6;
    final int question = 7;
    final int seven = 8;
    final int waltermelon = 9;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    int bf;
    int bs;
    int bt;
    int ivf;
    int ivs;
    int ivt;
    Button first;
    Button second;
    Button third;
    TextView havcoin;
    int i;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        final Random r = new java.util.Random();
        final ImageView mSlot1 = (ImageView)findViewById(R.id.slot1);
        final ImageView mSlot2 = (ImageView)findViewById(R.id.slot2);
        final ImageView mSlot3 = (ImageView)findViewById(R.id.slot3);
        final int[] d = {R.drawable.banana,R.drawable.bar, R.drawable.bigwin,R.drawable.cherry,
                R.drawable.grape,R.drawable.lemon, R.drawable.orange,R.drawable.question,
                R.drawable.seven,R.drawable.waltermelon};

        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();

        bf = 0;
        bs = 0;
        bt = 0;
        ivf = 0;
        ivs = 0;
        ivt = 0;

        int hadCoins = pref.getInt("HAD_COINS",1000);
        int betCoins = pref.getInt("BET_COINS",10);

        havcoin = (TextView)findViewById(R.id.change_coins);
        TextView betcoin = (TextView)findViewById(R.id.textView8);
        havcoin.setText(String.valueOf(hadCoins));
        betcoin.setText(String.valueOf(betCoins));


        Button back = (Button)findViewById(R.id.back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                havcoin = (TextView)findViewById(R.id.change_coins);
                String result = havcoin.getText().toString();
                intent = new Intent();
                intent.putExtra("COINS",result);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        first = (Button)findViewById(R.id.stop1);
        first.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = r.nextInt(10);
                mSlot1.setImageResource( (d[i]) );
                ++bf;
                ivf = i;
                if(bf >= 1 && bs >= 1 && bt >= 1){
                    editor.putInt("HAD_COINS",pref.getInt("HAD_COINS",1000)-(pref.getInt("BET_COINS",10)));
                    editor.commit();
                    havcoin.setText(String.valueOf(pref.getInt("HAD_COINS",1000)));
                    check(ivf,ivs,ivt);
                    bf = 0;
                    bs = 0;
                    bt = 0;
                    second.setEnabled(true);
                    third.setEnabled(true);
                }else {
                    first.setEnabled(false);
                }
            }
        });

        second = (Button)findViewById(R.id.stop2);
        second.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = r.nextInt(10);
                mSlot2.setImageResource( (d[i]) );
                ++bs;
                ivs = i;
                if(bf >= 1 && bs >= 1 && bt >= 1){
                    editor.putInt("HAD_COINS",pref.getInt("HAD_COINS",1000)-(pref.getInt("BET_COINS",10)));
                    editor.commit();
                    havcoin.setText(String.valueOf(pref.getInt("HAD_COINS",1000)));
                    check(ivf,ivs,ivt);
                    bf = 0;
                    bs = 0;
                    bt = 0;
                    first.setEnabled(true);
                    third.setEnabled(true);
                }else {
                    second.setEnabled(false);
                }
            }
        });

        third = (Button)findViewById(R.id.stop3);
        third.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                i = r.nextInt(10);
                mSlot3.setImageResource( (d[i]) );
                ++bt;
                ivt = i;
                if(bf >= 1 && bs >= 1 && bt >= 1){
                    editor.putInt("HAD_COINS",pref.getInt("HAD_COINS",1000)-(pref.getInt("BET_COINS",10)));
                    editor.commit();
                    havcoin.setText(String.valueOf(pref.getInt("HAD_COINS",1000)));
                    check(ivf,ivs,ivt);
                    bf = 0;
                    bs = 0;
                    bt = 0;
                    first.setEnabled(true);
                    second.setEnabled(true);
                }else {
                    third.setEnabled(false);
                }
            }
        });
    }

    public void check(int one, int two,int three){
        if(one == banana && two == banana && three == banana){
            editor.putInt("HAD_COINS",pref.getInt("BET_COINS",10) * 3);
        }else if(one == bar && two == bar && three == bar){
            editor.putInt("HAD_COINS",pref.getInt("BET_COINS",10) * 10);
        }else if(one == bigwin && two == bigwin && three == bigwin){
            editor.putInt("HAD_COINS",pref.getInt("BET_COINS",10) * 100);
        }
        editor.commit();
        havcoin.setText(String.valueOf(pref.getInt("HAD_COINS",1000)));
    }
}
