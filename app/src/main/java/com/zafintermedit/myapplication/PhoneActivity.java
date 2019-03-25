package com.zafintermedit.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zafintermedit.myapplication.R.id.btntampilcall;

public class PhoneActivity extends AppCompatActivity {


    @BindView(R.id.btncall)
    Button btncall;
    @BindView(R.id.btntampilcall)
    Button btntampilcall;
    @BindView(R.id.btnlistcontact)
    Button btnlistcontact;
    @BindView(R.id.edtnumber)
    EditText edtnumber;
    @BindView(R.id.activity_call)
    RelativeLayout activityCall;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btncall, R.id.btntampilcall, R.id.btnlistcontact})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btncall:

                //otomatis
//                Intent intentcall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
//                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                startActivity(intentcall);

                //atau pake yg ini
                if (!TextUtils.isEmpty(phone)) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED
                            ) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            requestPermissions(
                                    new String[]{
                                            Manifest.permission.CALL_PHONE
                                    }, 100
                            );
                        }
                        return;
                    }
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone)));
                }
                break;


            case R.id.btntampilcall:
                Intent intentdial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                startActivity(intentdial);
                break;
            case R.id.btnlistcontact:
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                i.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
                startActivityForResult(i,2);
                break;
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode,data);
        if (requestCode ==2&&requestCode==RESULT_OK){
            Cursor cursor = null;
            try {
                Uri uri = data.getData();
                cursor = getContentResolver().query(uri, new String[]{
                        ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, null);
                if (cursor != null && cursor.moveToNext()) {
                    phone = cursor.getString(0);
                    edtnumber.setText(phone);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

