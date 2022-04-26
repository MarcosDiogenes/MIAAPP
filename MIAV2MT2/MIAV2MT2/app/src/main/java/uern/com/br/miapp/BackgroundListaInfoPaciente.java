package uern.com.br.miapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class BackgroundListaInfoPaciente extends AsyncTask<String, Void, String> {

    Context ctx;
    BackgroundListaInfoPaciente(Context ctx){
        this.ctx=ctx;
    }

    public static String NIVELDISFAGIA;
    public static String ALIMENTOSINDICADOS;
    public static String CONSISTENCYFOOD;

    @Override
    protected String doInBackground(String... params) {

        String reg_url="http://192.168.1.8/miacon/getjson.php";
        String method= params [0];
        if(method.equals("listainfopaciente")){

            String tbl_paciente_id_paciente= params[1];


            try {
                URL url= new URL(reg_url);
                HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os= httpURLConnection.getOutputStream();

                BufferedWriter bufferedWriter= new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));

                String data= URLEncoder.encode("tbl_paciente_id_paciente","UTF-8")+"="+ URLEncoder.encode(tbl_paciente_id_paciente,"UTF-8");

                bufferedWriter.write(data);

                bufferedWriter.flush();

                bufferedWriter.close();

                //os.flush();


                //GETJSON
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader brLogin = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String jsonIdUsuario;

                while((jsonIdUsuario = brLogin.readLine()) != null){
                    stringBuilder.append(jsonIdUsuario + "\n");
                }

                os.flush();

                return stringBuilder.toString().trim();
                //FIN_GETJSON

                //os.close();


                //return "Login Efetuado com sucesso: " + httpURLConnection.getResponseMessage();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String result) {
        //GETJSON
        super.onPostExecute(result);
        Toast.makeText(ctx, result, Toast.LENGTH_SHORT).show();

        //MANTERID
        try{
            manterInfo(result);
            //System.out.println(IDBACKGROUND);
        }catch(JSONException e){
            e.printStackTrace();
        }
        //FIN_MANTERID
        //FIN_GETJSON
        //Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    //MANTERID
    private void manterInfo(String jsonIdUsuario) throws JSONException{
        JSONObject object = new JSONObject(jsonIdUsuario);
        JSONArray Jarray  = object.getJSONArray("server_response");

        String[] niveldisfagia = new String[Jarray.length()];
        String[] alimentosindicados = new String[Jarray.length()];
        String[] consistencyfood = new String[Jarray.length()];

        //System.out.println(id_paciente);

        for (int i = 0; i < Jarray.length(); i++){
            JSONObject jsonObject = Jarray.getJSONObject(i);
            niveldisfagia[i] = jsonObject.getString("niveldisfagia");
            alimentosindicados[i] = jsonObject.getString("alimentosindicados");
            consistencyfood[i] = jsonObject.getString("consistencyfood");
        }
        NIVELDISFAGIA = niveldisfagia[0];
        ALIMENTOSINDICADOS = alimentosindicados[0];
        CONSISTENCYFOOD = consistencyfood[0];
    }
    //FIN_MANTERID
}
