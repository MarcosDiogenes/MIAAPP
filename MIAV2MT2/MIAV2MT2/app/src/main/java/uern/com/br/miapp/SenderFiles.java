package uern.com.br.miapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import java.io.File;


import uern.com.br.miapp.util.Request;

/**
 * Created by Irlan on 29/10/2014.
 */
public class SenderFiles extends AsyncTask <Void, Void, Void> {

    private Request request;
    private Context context;
    private File fileFolderAudio = new File(Main.ABSOLUTE_PATH_APP_USER_AUDIO);
    private File fileFolderSensor = new File(Main.ABSOLUTE_PATH_APP_USER_SENSOR);
    private File[] fileAudios;
    private File[] fileSensors;
    private Handler handler;
    private Message msg;
    private ProgressDialog progress;
    private Preferences pref;
    private String user;

    public SenderFiles(Context context, Handler handler){
        this.handler = handler;
        this.context = context;
        fileAudios = fileFolderAudio.listFiles();
        fileSensors = fileFolderSensor.listFiles();
        pref = new Preferences(context);
        user = pref.getManterLogado();
        msg = new Message();
        msg.what = 3;
    }

    @Override
    protected void onPreExecute(){
        request = new Request(user,Main.ADDRESS_SERVER);
        progress = new ProgressDialog(context);
        progress.setIcon(R.drawable.ic_launcher);
        progress.setTitle("Aguarde");
        progress.setMessage("Enviando Arquivos ...");
        progress.show();
        progress.setCancelable(false);
        progress.setCanceledOnTouchOutside(false);

    }

    @Override
    protected Void doInBackground(Void... params) {

        if(fileSensors.length!=0){
            msg.obj = "Dados enviados com sucesso!";
            for(int i = 0; i < fileAudios.length; i++){
                try {
                    request.uploadFileBytes(fileAudios[i].getAbsolutePath());
                    fileAudios[i].delete();
                    //Toast.makeText(context,request.getMessageReturn(),Toast.LENGTH_LONG).show();
                    request.uploadFileBytes(fileSensors[i].getAbsolutePath());
                    fileSensors[i].delete();
                    //Toast.makeText(context,request.getMessageReturn(),Toast.LENGTH_LONG).show();
                }catch(RuntimeException e){
                    Log.i("Erro: ","Deu certo.");
                    msg.obj = "Falha ao enviar os dados.\nTente novamente mais tarde.";
                }
            }

        }else{
            msg.obj = "Não existe nenhum arquivo para ser enviado!";
        }

        handler.sendMessage(msg);
        return null;
    }

    @Override
    protected void onPostExecute(Void params){
        progress.dismiss();
    }


}
