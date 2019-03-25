package com.zafintermedit.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmailActivity extends AppCompatActivity {
    @BindView(R.id.edtto)
    EditText edtto;
    @BindView(R.id.edtsubject)
    EditText edtsubject;
    @BindView(R.id.edtmessage)
    EditText edtmessage;
    @BindView(R.id.activity_email)
    RelativeLayout activityEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        String to = edtto.getText().toString();
        String msg = edtmessage.getText().toString();
        String sub = edtsubject.getText().toString();


        if (i == R.id.mn_send) {
            if (TextUtils.isEmpty(to)) {
                edtto.setError("tujuan tidak boleh kosong");
                edtto.requestFocus();
            } else if (TextUtils.isEmpty(msg)) {
                edtmessage.setError("pesan tidak boleh kosong");
                edtmessage.requestFocus();

            } else if (TextUtils.isEmpty(sub)) {
                edtsubject.setError("subjek tidak boleh ksoosng");
                edtsubject.requestFocus();

        } else {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            email.putExtra(Intent.EXTRA_SUBJECT, sub);
            email.putExtra(Intent.EXTRA_TEXT, msg);
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "silahkan pilih email client anda"));


        }
    }else {
            edtto.setText("");
            edtmessage.setText("");
            edtsubject.setText("");

        }
            return super.onOptionsItemSelected(item);
    }
}
