package com.zafintermedit.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BluetoothActivity extends MyFunction {
    // ganti jaid my function
    @BindView(R.id.btnturnon)
    Button btnturnon;
    @BindView(R.id.btnlist)
    Button btnlist;
    @BindView(R.id.btnfind)
    Button btnfind;
    @BindView(R.id.btnvisible)
    Button btnvisible;
    @BindView(R.id.btnturnoff)
    Button btnturnoff;
    @BindView(R.id.linear1)
    LinearLayout linear1;
    @BindView(R.id.listdevice)
    ListView listdevice;
    @BindView(R.id.listfind)
    ListView listfind;
    @BindView(R.id.activity_bluetooth)
    RelativeLayout activityBluetooth;
    private BluetoothAdapter ba;
    private Set<BluetoothDevice> bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        ButterKnife.bind(this);
        ba = BluetoothAdapter.getDefaultAdapter();
    }

    @OnClick({R.id.btnturnon, R.id.btnlist, R.id.btnfind, R.id.btnvisible, R.id.btnturnoff})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnturnon:
                if (!ba.enable()){
                    Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(i,MyConstants.BLUET);
                    pesan("bluetooth sudah aktif");
                }else {
                    pesan(" bluetooth aktif");
                }

                break;
            case R.id.btnlist:
                bd = ba.getBondedDevices();
                ArrayList arrayList = new ArrayList();
                if (bd.size() > 0) {
                    for (BluetoothDevice device : bd) {
                        arrayList.add(device.getName() + "\n" + device.getAddress());
                        Toast.makeText(this, "menampilakan perangkat yang telah terhubung", Toast.LENGTH_SHORT).show();
                        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);
                        listdevice.setAdapter(adapter);
                    }}
                else if
                        (!ba.isEnabled()){
                    Toast.makeText(this,"Bluetooh belum aktif",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "belum ada perangkat yang terhubung", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btnfind:
                //mencari perangkat bluetooth yang ada di sekitar
                BluetoothAdapter bluetoothAdapter = (BluetoothAdapter.getDefaultAdapter());
                bluetoothAdapter.startDiscovery();
                final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1);
                BroadcastReceiver receiver = new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String aksi = intent.getAction();
                        //aksi mencari device
                        if(BluetoothDevice.ACTION_FOUND.equals(aksi)){
                            BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                            adapter.add(device.getName()+"\n"+device.getAddress());
                            listfind.setAdapter(adapter);
                        }else {
                            Toast.makeText(BluetoothActivity.this, "tidak ada perangkat bluetooth yang aktif di sekitar", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
                if(!ba.isEnabled()){
                    Toast.makeText(this,"Bluetooh belum aktif",Toast.LENGTH_SHORT).show();
                }
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(receiver,filter);
                break;
            case R.id.btnvisible:
                Intent intent = new Intent(ba.ACTION_REQUEST_DISCOVERABLE);
                startActivityForResult(intent,3);
                break;
            case R.id.btnturnoff:
                ba.disable();
                listdevice.setAdapter(null);
                listfind.setAdapter(null);
                pesan("bluetooth tidak aktif");
                break;
        }
    }
}
