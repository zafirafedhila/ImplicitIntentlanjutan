package com.zafintermedit.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnaudiomanager)
    Button btnaudiomanager;
    @BindView(R.id.btnaudiorecorder)
    Button btnaudiorecorder;
    @BindView(R.id.btnbluetooth)
    Button btnbluetooth;
    @BindView(R.id.btnbrowser)
    Button btnbrowser;
    @BindView(R.id.btncamera)
    Button btncamera;
    @BindView(R.id.btnemail)
    Button btnemail;
    @BindView(R.id.btnphone)
    Button btnphone;
    @BindView(R.id.btnsms)
    Button btnsms;
    @BindView(R.id.btntts)
    Button btntts;
    @BindView(R.id.btnwifi)
    Button btnwifi;
    @BindView(R.id.btnvideo)
    Button btnvideo;
    @BindView(R.id.btnalarm)
    Button btnalarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnaudiomanager, R.id.btnaudiorecorder, R.id.btnbluetooth, R.id.btnbrowser, R.id.btncamera, R.id.btnemail, R.id.btnphone, R.id.btnsms, R.id.btntts, R.id.btnwifi, R.id.btnvideo, R.id.btnalarm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnaudiomanager:
                startActivity(new Intent(this,AudioManagerActivity.class));
                break;
            case R.id.btnaudiorecorder:
                startActivity(new Intent(this,AudioRecorderActivity.class));
                break;
            case R.id.btnbluetooth:
                startActivity(new Intent(this, BluetoothActivity.class));
                break;
            case R.id.btnbrowser:
                startActivity(new Intent(this,BrowserActivity.class));
                break;
            case R.id.btncamera:
                startActivity(new Intent(this,CameraActivity.class));
                break;
            case R.id.btnemail:
                startActivity(new Intent(this,EmailActivity.class));
                break;
            case R.id.btnphone:
                startActivity(new Intent(this,PhoneActivity.class));
                break;
            case R.id.btnsms:
                startActivity(new Intent(this,smsActivity.class));
                break;
            case R.id.btntts:
                startActivity(new Intent(this,TTSActivity.class));
                break;
            case R.id.btnwifi:
                startActivity(new Intent(this,WifiActivity.class));
                break;
            case R.id.btnvideo:
                startActivity(new Intent(this,VideoActivity.class));
                break;
            case R.id.btnalarm:
                startActivity(new Intent(this,AlarmActivity.class));
                break;
        }
    }
}
