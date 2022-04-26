package uern.com.br.miapp;


import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
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

public class ListaInfoPaciente extends AppCompatActivity {

    TextView listaInfoPaciente;
    TextView alimentoIndicado;
    TextView consistenciaAlimento;
    String tbl_paciente_id_paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_info_paciente);

        listaInfoPaciente = (TextView) findViewById(R.id.listaInfoPaciente);
        alimentoIndicado = (TextView) findViewById(R.id.alimentoIndicado);
        consistenciaAlimento = (TextView) findViewById(R.id.consistenciaAlimento);
        tbl_paciente_id_paciente = BackgroundNovoLogin.IDBACKGROUND;
        BackgroundListaInfoPaciente backgroundListaInfoPaciente = new BackgroundListaInfoPaciente(getBaseContext());
        String method = "listainfopaciente";
        backgroundListaInfoPaciente.execute(method,tbl_paciente_id_paciente);
        loadIntoListView();
    }

    private void loadIntoListView(){

        String niveldisfagia = BackgroundListaInfoPaciente.NIVELDISFAGIA;
        listaInfoPaciente.setText(niveldisfagia);
        String alimentosindicados = BackgroundListaInfoPaciente.ALIMENTOSINDICADOS;
        alimentoIndicado.setText(alimentosindicados);
        String consistencyfood = BackgroundListaInfoPaciente.CONSISTENCYFOOD;
        consistenciaAlimento.setText(consistencyfood);
    }
}