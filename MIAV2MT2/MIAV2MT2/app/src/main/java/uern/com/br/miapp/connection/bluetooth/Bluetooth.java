package uern.com.br.miapp.connection.bluetooth;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Irlan on 15/09/2014.
 */
public class Bluetooth{
    private BluetoothAdapter mBluetoothAdapter = null;
    private Thread threadReceiver;
    private boolean isReceiving = false;
    public static int REQUEST_ENABLE_BT = 1;
    private BluetoothSocket bluetoothSocket = null;
    private static Bluetooth bluetooth = new Bluetooth();

    private Bluetooth(){

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    }

    public static Bluetooth getInstance(){
        return bluetooth;
    }

    public Intent activate(){

        Intent enableBtIntent = null;

        if(!mBluetoothAdapter.isEnabled()){
            enableBtIntent = new Intent (BluetoothAdapter.ACTION_REQUEST_ENABLE);
        }

        return enableBtIntent;

    }

    public ArrayList<String> getPairedDevaices(){

        ArrayList<String> mArrayPairedDevices = new ArrayList<String>();

        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        Log.i("Bluethooth","Número de dispositivos: "+ pairedDevices.size());
        if(pairedDevices.size() > 0){
            for(BluetoothDevice device : pairedDevices){
                mArrayPairedDevices.add(device.getName() + "\n" + device.getAddress());
            }
        }

        return mArrayPairedDevices;
    }

    public void connectDevice(String mac){
        if(mac!=null) {
            BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(mac);
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
            try {
                bluetoothSocket = device.createRfcommSocketToServiceRecord(uuid);
                bluetoothSocket.connect();
            } catch (IOException e) {
                Log.d("Bluetooth: ","Erro na conexão do dispositivo: connectDevice()");
            }
        }
    }

    public void desconnectDevice(){
        if(bluetoothSocket != null){
            try{
                bluetoothSocket.close();
                bluetoothSocket = null;
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public boolean isConnected(){
        boolean isConnected = false;
        if(this.bluetoothSocket != null) {
            isConnected = bluetoothSocket.isConnected();
        }

        return isConnected;
    }


    public void receivingData(final Handler handler){

        isReceiving = true;
        threadReceiver = new Thread(new Runnable() {
        @Override
        public void run() {
            InputStream in;

            byte[] buffer; // buffer store for the stream

            int availableBytes;

            // Keep listening to the InputStream until an exception occurs

            try {
                bluetooth.sendData('1');
            } catch (IOException e) {

                Log.d("Bluetooth: ","Erro na conexão do dispositivo: receivingData()");
                isReceiving = false;
                Message m = new Message();
                m.what = 2;
                m.obj = e.getMessage();
                handler.sendMessage(m);
            }

            while (isReceiving) {

                try {

                    in = bluetoothSocket.getInputStream();

                    availableBytes = in.available();

                    if (availableBytes > 44) {

                        // Read from the InputStream
                        buffer = new byte[availableBytes];
                        in.read(buffer);



                        Message message = new Message();
                        message.obj = buffer;
                        message.what = 1;
                        handler.sendMessage(message);


                    } else {

                        SystemClock.sleep(1);

                    }
                } catch (IOException e) {
                    Log.e("Leitura", "Error reading from btInputStream");
                    break;
                }
            }
            if(!isReceiving){
                threadReceiver = null;
            }
        }
        }, "ReceiverDataBluetooth Thread");

        threadReceiver.start();

    }

    public void stopReceivingData() {
        try {
            sendData('0');
        } catch (IOException e) {
            Log.d("Bluetooth: ", "Erro na conexão do dispositivo: stopReceivingData()");
        }
        isReceiving = false;
        //bluetoothSocket.close();
        threadReceiver = null;


    }

    public void sendData(char data) throws IOException {

        if(bluetooth.isConnected()) {

            OutputStream outputStream;
            try {
                outputStream = bluetoothSocket.getOutputStream();
                outputStream.write(data);
            } catch (IOException e) {
                Log.d("Bluetooth: ", "Erro na conexão do dispositivo: sendData(char data)");
                throw new IOException("Erro na conexão");
            }
        }
    }
}