package com.ianfield.geewhiz;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Ian Field on 15/03/2016.
 */
public class SoundService extends Service {
    private static final String TAG = "GeeWhizService";
    MediaPlayer mPlayer;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        playSound(startId);
        return START_STICKY_COMPATIBILITY;
    }

    private void playSound(final int startId) {
        mPlayer = MediaPlayer.create(this, R.raw.gee_whizz);
        mPlayer.start();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                stopSelf(startId);
            }
        });
    }

    @Override
    public void onDestroy() {
        if (mPlayer != null) {
            mPlayer.stop();
        }
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
