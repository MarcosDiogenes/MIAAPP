package uern.com.br.miapp.util;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.SyncHttpClient;

import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import uern.com.br.miapp.Preferences;


/**
 * Created by Irlan on 20/10/2014.
 */
public class Request {

    private SyncHttpClient httpClient;
    //private AsyncHttpClient httpClient;

    private String user;

    private String message = "Erro Desconhecido.";
    private String addressServer;

    public Request(String user, String addressServer){
        httpClient = new SyncHttpClient();
        this.addressServer = addressServer;
        this.user = user;

        //httpClient = new AsyncHttpClient();

    }

    public void uploadFileBytes(final String filePath){
        message = filePath + ":" + message;
        final File myFile = new File(filePath);
        FileInputStream inputStream = null;
        byte [] fileBytes = null;

        try {

            inputStream = new FileInputStream(myFile);
            fileBytes = new byte[inputStream.available()];
            inputStream.read(fileBytes);
            inputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams();
        params.put("sound", new ByteArrayInputStream(fileBytes), filePath);
        params.put("user",user);




        httpClient.post("http://" + addressServer + "/mia/upload.php",params, new AsyncHttpResponseHandler() {
        //httpClient.post("http://10.0.0.101/mia/teste.php",params, new AsyncHttpResponseHandler() {

            @Override
            public void onStart(){

            }

            @Override
            public void onProgress(int bytesWritten, int totalSize){

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                String [] tmp = filePath.split("/");
                setMessage("Arquivo " +tmp[tmp.length-1] +  new String(responseBody));

                Log.i("Status Upload: ", "Arquivo" + filePath + " enviado com sucesso!");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                String [] tmp = filePath.split("/");
                setMessage("Arquivo " +tmp[tmp.length-1] +  new String(responseBody));
                Log.i("Status Upload: ","Envio do arquivo " + filePath + " falhou!");
            }
        });

    }

    private void setMessage(String m){
        this.message = m;
    }

}
