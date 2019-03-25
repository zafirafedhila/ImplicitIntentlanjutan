package com.zafintermedit.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    //terima alarm

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.

        Toast.makeText(context, " alrm berbunyi", Toast.LENGTH_SHORT).show();
        MediaPlayer player = MediaPlayer.create(context, R.raw.alarm);
        player.start();

        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
