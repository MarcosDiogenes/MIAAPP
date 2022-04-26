package uern.com.br.miapp;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import uern.com.br.miapp.connection.bluetooth.Bluetooth;
import uern.com.br.miapp.recorder.Audio;

/**
 * Created by Irlan on 09/10/2014.
 */
public class ReceiverData extends Activity  {

    private boolean startRecording = true;
    private Audio audio = null;
    private String pathAudio = null;
    private Bluetooth bluetooth;
    private String fileName = null;
    private String pathSensor = null;
    private SenderFiles senderFiles;
    private Bundle extra;
    private Handler myHandler ;
    private FileOutputStream out;
    private Context context;
    private Button recordingBt;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        extra = getIntent().getExtras();

        setContentView(R.layout.activity_recorder);
        bluetooth = Bluetooth.getInstance();
        audio = new Audio();

        recordingBt = (Button) findViewById(R.id.recordingBt);


        pathAudio = Main.ABSOLUTE_PATH_APP_USER_AUDIO;
        pathSensor = Main.ABSOLUTE_PATH_APP_USER_SENSOR;

        textView = (TextView) findViewById(R.id.logSensorTxtView);
        myHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                if(msg.what == 1){
                    byte[] data = (byte[]) msg.obj;
                    if(out != null) {
                        try {
                            out.write(data);
                            textView.setText(new String(data));

                            Log.i("", new String(data));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }else if(msg.what == 2){
                   recordingBt.setText("Gravar");
                    String msgAlert = (String) msg.obj;
                    Toast.makeText(context,msgAlert,Toast.LENGTH_LONG).show();
                    stopRecordingData();
                    startRecording = true;
                    new File(pathAudio).delete();
                    new File(pathSensor).delete();
                    finish();
                }else if(msg.what == 3){
                    Toast.makeText(context,(String)msg.obj,Toast.LENGTH_LONG).show();
                }
            }
        };


    }

    private FileOutputStream newFile(String file){
        try {
            return new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void startRecordingData(){
        textView.setText("");
        if(!bluetooth.isConnected()){
            extra = getIntent().getExtras();

            String mac = extra.getString("macAddress");

            bluetooth.connectDevice(mac);
        }

        String user = extra.getString("user");
        fileName = new SimpleDateFormat("yyyy_MM_dd_H_mm_ss").format(new Date());
        pathAudio += "/" + user + "_" + fileName +".wav";
        pathSensor += "/" + user + "_" + fileName + ".csv";
        Log.i("SalvarEm: ", pathAudio);

        audio.setFileName(pathAudio);
        audio.startRecording();

        recordingBt.setText("Parar");

        out = newFile(pathSensor);
        bluetooth.receivingData(myHandler);

    }

    private void stopRecordingData(){
        recordingBt.setText("Gravar");
        audio.stopRecording();
        try {
            out.close();
            out = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        bluetooth.stopReceivingData();
    }

    public void recording(View view){

        pathAudio = Main.ABSOLUTE_PATH_APP_USER_AUDIO;
        pathSensor = Main.ABSOLUTE_PATH_APP_USER_SENSOR;

        if(startRecording){
            startRecordingData();
        } else {
            stopRecordingData();
        }


        startRecording = !startRecording;
    }

    public void sendFiles(View view){
        senderFiles = new SenderFiles(this, myHandler);
        senderFiles.execute();
        senderFiles = null;
    }


}
