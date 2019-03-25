package com.zafintermedit.myapplication;

import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends MyFunction{
    @BindView(R.id.ring)
    Button ring;
    @BindView(R.id.vibrate)
    Button vibrate;
    @BindView(R.id.silent)
    Button silent;
    private AudioManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);
        manager = (AudioManager) getSystemService(AUDIO_SERVICE);
        //buat global ctrl alt f
    }

    @OnClick({R.id.ring, R.id.vibrate, R.id.silent})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.ring:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //nougat
                    //buat global ctrl alt f
                    manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_UNMUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_UNMUTE, 0);
                }else{
                    manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    pesan("dalam mode ring");
                }
                break;
            case R.id.vibrate:
                break;
            case R.id.silent:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    //nougat
                    manager.adjustStreamVolume(AudioManager.STREAM_NOTIFICATION, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_ALARM, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_MUSIC, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_RING, AudioManager.ADJUST_MUTE, 0);
                    manager.adjustStreamVolume(AudioManager.STREAM_SYSTEM, AudioManager.ADJUST_MUTE, 0);
                    //shift alt
                }else{
                    manager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    pesan("dalam mode ring");
                }
                break;
        }
    }
}
