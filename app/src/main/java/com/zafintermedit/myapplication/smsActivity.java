package com.zafintermedit.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class smsActivity extends AppCompatActivity {

    @BindView(R.id.edt)
    EditText edt;
    @BindView(R.id.edtmessage)
    EditText edtmessage;
    @BindView(R.id.btnsmsintent)
    Button btnsmsintent;
    @BindView(R.id.btnkirimsms)
    Button btnkirimsms;
    @BindView(R.id.activity_sms)
    LinearLayout activitySms;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnsmsintent, R.id.btnkirimsms})
    public void onViewClicked(View view) {

        String isipesan = edtmessage.getText().toString();
        switch (view.getId()) {
            case R.id.edt:
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(i, 2);
                break;
            case R.id.btnsmsintent:
                if (TextUtils.isEmpty(phone)) {
                    edt.setError(("no hp tidak boleh kosong"));
                    edt.requestFocus();
                } else if (TextUtils.isEmpty(isipesan)) {
                    edtmessage.setError("pesna itdak boleh kososng");
                    edtmessage.requestFocus();
                } else {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setType("vnd.android-dir/mms-sms");
                    intent.putExtra("address", phone);
                    intent.putExtra("sms_bodu", isipesan);
                    startActivity(intent);
                }


//            }
//                }
//                Intent intent = new Intent(Intent.ACTION_SEND, Uri)

                break;
            case R.id.btnkirimsms:
                if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.SEND_SMS)) {
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.SEND_SMS},
                                MyConstants.REQUESTSMS);
                    }
                } else
//    private String isipesan;

                {
                    try {
                        SmsManager manager = SmsManager.getDefault();
                        manager.sendTextMessage(phone, null, isipesan, null, null);
                        Toast.makeText(this, "pesan ini terkirim", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "pesan tidak terkirim" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        //biat tau kenpaa tidak terkirim
                    }

                    break;
                }
        }
    }
        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 2 && requestCode == RESULT_OK) {
                Cursor cursor = null;
                try {
                    Uri uri = data.getData();
                    cursor = getContentResolver().query(uri, new String[]{
                            ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
                    if (cursor != null && cursor.moveToNext()) {
                        phone = cursor.getString(0);
                        edt.setText(phone);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }