package com.meyaz.mehmet.sureliseskontrol;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    public MediaPlayer mp;
    public Timer timer;
    private TextView sure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sure= (TextView) findViewById(R.id.sure);

    }

    public void cal(View view)
    {
        mp=MediaPlayer.create(this,R.raw.musicbox);
        mp.start();
    cal2(5);

    }

    private void cal2(final int sureSiniri) {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (mp != null && mp.isPlaying()) {
                            sure.post(new Runnable() {
                                @Override
                                public void run() {
                                    int zamanlama=mp.getCurrentPosition()/1000;
                                    sure.setText(String.valueOf(zamanlama));
                                    if (zamanlama>sureSiniri)
                                        mp.seekTo(111);
                                }
                            });
                        } else {
                            timer.cancel();
                            timer.purge();
                        }
                    }
                });
            }
        }, 0, 1000);
    }

    public void bekle(View view)
    {
        //  mp=MediaPlayer.create(this,R.raw.musicbox);
        mp.pause();
    }

    public void dur(View view)
    {
      //  mp=MediaPlayer.create(this,R.raw.musicbox);
        mp.stop();
    }
}
