package uern.com.br.miapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
//import android.support.v7.app.ActionBarActivity;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import uern.com.br.miapp.connection.bluetooth.Bluetooth;


public class Main extends AppCompatActivity {

    private Bluetooth bluetooth;
    private ListView listDevices = null;
    private String macAddressDevice = null;
    private String user;
    private ProgressDialog progress;
    private Main context;
    private Handler myHandler;
    private SenderFiles senderFiles;
    private Preferences pref;

    public static final String ABSOLUTE_PATH_ROOT_APP = Environment.getExternalStorageDirectory().getPath() + "/mia/";
    public static String ABSOLUTE_PATH_APP_USER;
    public static String ABSOLUTE_PATH_APP_USER_AUDIO;
    public static String ABSOLUTE_PATH_APP_USER_SENSOR;
    public static String ABSOLUTE_PATH_APP_USER_TEMP;
    public static String ADDRESS_SERVER;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        pref = new Preferences(this);
        Main.ADDRESS_SERVER = pref.getAddressServer();
        Bundle extra;
        extra = getIntent().getExtras();
        context = this;
        bluetooth = Bluetooth.getInstance();
        user = extra.getString("user");
        this.createFolderApp(user);

        setContentView(R.layout.activity_main);
        Intent i = bluetooth.activate();

        myHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what==1){
                    boolean isConnected = (Boolean) msg.obj;
                    if(isConnected){
                        Toast.makeText(getApplicationContext(),"Dispositivo Conectado com Sucesso.", Toast.LENGTH_SHORT).show();
                        goReceiverData();
                    }else{
                        Toast.makeText(getApplicationContext(),"Falha ao Conectar.\nTente Novamente.", Toast.LENGTH_SHORT).show();
                    }
                }else if(msg.what == 3){
                    Toast.makeText(context,(String)msg.obj,Toast.LENGTH_LONG).show();
                }
            }
        };

        if(i!=null) {
            startActivityForResult(i, Bluetooth.REQUEST_ENABLE_BT);
        }

        this.refreshBluetoothDevicesList();

        listDevices = (ListView) findViewById(R.id.listView);

        listDevices.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                macAddressDevice = getItemListDevicePaired(i);
                if(!bluetooth.isConnected()) {
                    Processo processo = new Processo(context,bluetooth,myHandler);
                    processo.setMac(macAddressDevice);
                    processo.execute();

                    //bluetooth.connectDevice(macAddressDevice);
                    /*if(bluetooth.isConnected()) {
                        Toast.makeText(getApplicationContext(),"Dispositivo Conectado com Sucesso.", Toast.LENGTH_SHORT).show();
                        goReceiverData();
                    }else{
                        Toast.makeText(getApplicationContext(),"Falha ao Conectar.\nTente Novamente.", Toast.LENGTH_SHORT).show();
                    }*/

                }else {

                    bluetooth.stopReceivingData();

                    bluetooth.desconnectDevice();
                    if(!bluetooth.isConnected()) {
                        Toast.makeText(getApplicationContext(),"Dispositivo Desconectado.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_send_data) {
            senderFiles = new SenderFiles(this, myHandler);
            senderFiles.execute();
            senderFiles = null;
        } else if(id == R.id.action_logout){
            pref.setManterLogado("");
            Intent i = new Intent();
            i.setClass(Main.this,Login.class);
            startActivity(i);
            finish();
        }else if (id == R.id.action_settings){
            Intent intent = new Intent();
            intent.setClass(Main.this,Settings.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == Bluetooth.REQUEST_ENABLE_BT){

            if(resultCode == Activity.RESULT_CANCELED){

                Toast.makeText(getApplicationContext(),"É preciso ativar o Bluetooth.",Toast.LENGTH_LONG).show();

            }else{
                refreshBluetoothDevicesList();
            }

        }

    }

    private void refreshBluetoothDevicesList(){
        ArrayList<String> devices = bluetooth.getPairedDevaices();
        if(devices.size()>0) {

            ListView lv = (ListView) findViewById(R.id.listView);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.device_name, R.id.textViewBluetoothDevice, devices);
            lv.setAdapter(adapter);
        }
    }

    private String getItemListDevicePaired(int position){

        String item = (String)listDevices.getItemAtPosition(position);
        String string[] = item.split("\n");
        Log.i("ItemClicado",string[1]);
        return string[1];
    }

    private boolean createFolderApp(String user){
        ABSOLUTE_PATH_APP_USER = ABSOLUTE_PATH_ROOT_APP + user;
        File file;
        boolean isCriated = false;

        file = new File(ABSOLUTE_PATH_ROOT_APP);

        if(!file.exists()){
            isCriated = file.mkdir();
        }

        file = new File(ABSOLUTE_PATH_APP_USER);

        if(!file.exists()){
            isCriated = file.mkdir();
        }
        ABSOLUTE_PATH_APP_USER_AUDIO = ABSOLUTE_PATH_APP_USER + "/audio";
        file = new File(ABSOLUTE_PATH_APP_USER_AUDIO);
        if(!file.exists()){
            isCriated = file.mkdir();
        }

        ABSOLUTE_PATH_APP_USER_TEMP = ABSOLUTE_PATH_APP_USER + "/temp";
        file = new File(ABSOLUTE_PATH_APP_USER_TEMP);
        if(!file.exists()){
            isCriated = file.mkdir();
        }

        ABSOLUTE_PATH_APP_USER_SENSOR = ABSOLUTE_PATH_APP_USER + "/sensor";
        file = new File(ABSOLUTE_PATH_APP_USER_SENSOR);
        if(!file.exists()){
            isCriated = file.mkdir();
        }

        return isCriated;
    }

    private void goReceiverData(){

        Intent intent = new Intent();
        intent.setClass(Main.this,ReceiverData.class);
        intent.putExtra("macAddress", macAddressDevice);
        intent.putExtra("user",user);
        startActivity(intent);

    }

}
