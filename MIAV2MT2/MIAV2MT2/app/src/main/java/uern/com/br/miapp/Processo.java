package uern.com.br.miapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import uern.com.br.miapp.connection.bluetooth.Bluetooth;

/**
 * Created by Irlan on 18/09/2014.
 */
public class Processo extends AsyncTask<Void, Void, Void> {
    private ProgressDialog progress;
    private Context context;
    private Bluetooth bluetooth = null;
    private String mac = null;
    private Handler handler;
    private Message msg;

    Processo(Context ctx, Bluetooth b, Handler handler){
        this.context = ctx;
        this.bluetooth = b;
        this.handler = handler;
        msg = new Message();
        msg.what = 1;
    }

    public void setMac(String mac){
        this.mac = mac;
    }
    @Override
    protected void onPreExecute() {
        //Cria novo um ProgressDialogo e exibe um ProgressDialog
        progress = new ProgressDialog(context);
        progress.setIcon(R.drawable.ic_launcher);
        progress.setTitle("Aguarde");
        progress.setMessage("Conectando ao Dispositivo ...");
        progress.show();
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);

    }

    public Bluetooth getBluetooth(){
        return bluetooth;
    }

    @Override
    protected Void doInBackground(Void... v) {

        bluetooth.connectDevice(mac);


        /*
        if(bluetooth.isConnected()){
            progress.setMessage("Dispositivo Conectado com Sucesso!");
        }else{
            progress.setMessage("Falha ao Conectar. Tente Novamente!");
        }*/

        return null;

    }


    @Override
    protected void onProgressUpdate(Void... values) {
        //Atualiza mensagem
        //progress.setMessage(msg);
    }

    @Override
    protected void onPostExecute(Void result) {
        if(bluetooth.isConnected()){


            msg.obj = true;
            progress.setMessage("Dispositivo Conectado com Sucesso!");
        }else{
            msg.obj = false;
            progress.setMessage("Falha ao conectar.");
        }
        handler.sendMessage(msg);
        //Cancela progressDialogo
        progress.dismiss();
    }
}
