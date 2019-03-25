package com.zafintermedit.myapplication;

import android.os.Bundle;
import android.os.LocaleList;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TTSActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.btnSpeech)
    Button btnSpeech;
    @BindView(R.id.activity_tts)
    RelativeLayout activityTts;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        ButterKnife.bind(this);
        tts = new TextToSpeech(this, this);

    }

    @OnClick(R.id.btnSpeech)
    public void onViewClicked() {
        String text =editText.getText().toString();
        tts.speak((text), TextToSpeech.QUEUE_FLUSH, null);


    }

    @Override
    public void onInit(int status) {
        if (status==TextToSpeech.SUCCESS){
            Locale bahasa = new Locale("id", "ID");
            int bahasa2 = tts.setLanguage(Locale.CANADA_FRENCH);
            if(bahasa2==TextToSpeech.LANG_MISSING_DATA|| bahasa2==TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this, "bahsa tidak mendukung", Toast.LENGTH_SHORT).show();

            }else {
                onViewClicked();
                btnSpeech.setEnabled(true);
            }
        }else {
            Toast.makeText(this, "TTS tidak support", Toast.LENGTH_SHORT).show();
        }

    }
}
