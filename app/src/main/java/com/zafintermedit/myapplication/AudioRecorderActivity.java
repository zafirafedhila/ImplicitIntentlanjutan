package com.zafintermedit.myapplication;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioRecorderActivity extends MyFunction {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.btnPlay)
    Button btnPlay;
    @BindView(R.id.btnRecordStop)
    Button btnRecordStop;
    @BindView(R.id.activity_aaudio_recorder)
    RelativeLayout activityAaudioRecorder;
    private MediaRecorder recoder;
    private String lokasifile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recorder);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnPlay, R.id.btnRecordStop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnPlay:
                MediaPlayer player = new MediaPlayer();
                try{


                    player.setDataSource(lokasifile);
                    player.prepare();
                    player.start();
                }catch (IOException e){
                    e.printStackTrace();
                }
                break;
            case R.id.btnRecordStop:
                if (btnRecordStop.getText().toString().equalsIgnoreCase("RECORD")){
                    recoder = new MediaRecorder();
                    lokasifile = Environment.getExternalStorageDirectory().getAbsolutePath()+
                            "/REC"+currentDate()+".3gp";
                    recoder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
                    recoder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    recoder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    recoder.setOutputFile(lokasifile);

                    try{
                        recoder.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    recoder.start();
                    btnRecordStop.setText("STOP");
                }else if (btnRecordStop.getText().toString().equalsIgnoreCase("STOP")){
                    recoder.stop();
                    recoder.release();
                    btnPlay.setEnabled(true);
                    btnRecordStop.setText("RECORD");
                }

                break;
        }
    }
}
