package uern.com.br.miapp;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundDegluticao extends AsyncTask<String,Void,String> {

    Context ctx;
    BackgroundDegluticao(Context ctx){

        this.ctx=ctx;
}

    //Inicia após "onPreExecute", caracteriza a tarefa de plano de fundo que a classe irá executar.
    @Override
    protected String doInBackground(String... params) {

        String reg_url="http://192.168.1.8/miacon/register.php";
        String method= params [0];
        if(method.equals("register")){

            String textura_alimento= params[1];
            String reg_1= params[2];
            String fk_teste_tbl_classificados=params[3];

            try {
                URL url= new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os= httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("textura_alimento","UTF-8")+"="+URLEncoder.encode(textura_alimento,"UTF-8")+ "&"
                        +URLEncoder.encode("reg_1","UTF-8")+"="+URLEncoder.encode(reg_1,"UTF-8")+ "&"
                        +URLEncoder.encode("fk_teste_tbl_classificados","UTF-8")+"="+URLEncoder.encode(fk_teste_tbl_classificados,"UTF-8");

                bufferedWriter.write(data);

                bufferedWriter.flush();

                bufferedWriter.close();

                //String data= "textura_alimento"+"="+textura_alimento;
                //os.write(data.getBytes());
                os.flush();



                //os.close();

                return "Registrado com sucesso: " + httpURLConnection.getResponseMessage();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    //Caracteriza a etapa de preparação da tarefa de fundo.
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    //Caracteriza o fechamento da tarefa de fundo.
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


}

