package com.zafintermedit.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zafintermedit.myapplication.MyConstants.CAMERA;
import static com.zafintermedit.myapplication.MyConstants.GALERY;

public class CameraActivity extends MyFunction {
    private static final int REQCAMERA = 2; // panggil kelas my function
    private static final int REQGALERY = 3;
    @BindView(R.id.btncamera)
    Button btncamera;
    @BindView(R.id.btnshow1)
    Button btnshow1;
    @BindView(R.id.imgshow)
    ImageView imgshow;
    private Uri lokasifile;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        ButterKnife.bind(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(
                        new String[]{
                                Manifest.permission.CAMERA
                        }, 100
                );
            }
            return;
        }

    }

    @OnClick({R.id.btncamera, R.id.btnshow1, R.id.imgshow})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btncamera:
                String folder = "fototest";
                File f = new File(Environment.getExternalStorageDirectory(), folder);
                if (!f .exists()){
                    f.mkdir();
                }
                File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                        +"/"+folder+"/PIC"+currentDate()+".jpg");
                Intent i = new Intent(MediaStore.EXTRA_OUTPUT, lokasifile);
                lokasifile = Uri.fromFile(file);
                startActivityForResult(i,CAMERA);
                break;
            case R.id.btnshow1:
                Intent galery = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galery, MyConstants.GALERY);
                break;
            case R.id.imgshow:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==CAMERA){
            if (resultCode==RESULT_OK){
                pesan("berhasil menyimpan gambar \n lokasi"+ lokasifile.toString());
            }else if (resultCode==RESULT_CANCELED){
                pesan("cancel");
            }else{
                pesan("gagal mengambil gambar");
            }
        }else if (requestCode==REQGALERY){
            if (resultCode==RESULT_OK){
                Uri lokasigambar =data.getData();
                InputStream inputStream =null;
                try{
                    inputStream =getContentResolver().openInputStream(lokasigambar);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                bitmap = BitmapFactory.decodeStream(inputStream);
                imgshow.setImageBitmap(bitmap);
                //ctrl alt f
            }else if (resultCode==RESULT_CANCELED){
                pesan("cancel");
            }else{
                pesan("gagal menampilkan gambar");
            }
        }

            }
        }



