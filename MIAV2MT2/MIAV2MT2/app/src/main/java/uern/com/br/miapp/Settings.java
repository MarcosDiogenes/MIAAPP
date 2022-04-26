package uern.com.br.miapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Irlan on 12/11/2014.
 */
public class Settings extends Activity {
    private Preferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pref = new Preferences(this);
        setContentView(R.layout.activity_settings);

        final EditText addressServer = (EditText) findViewById(R.id.addressServerTxt);

        String address = pref.getAddressServer();
        if(!(address.equals(null)||address.equals(""))) {
            addressServer.setHint(address);
        }else{
            addressServer.setHint("Endereço do Servidor.");
        }

        Button okBt = (Button) findViewById(R.id.addressServerBt);
        okBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = "\\d{1,3}(\\.\\d{1,3}){3}";
                String address = addressServer.getText().toString();
                if(address.equals(null)){
                    Toast.makeText(getApplicationContext(),"Insira um endereço de servidor.",Toast.LENGTH_LONG).show();
                }else{
                    if(address.matches(ip)){
                        Main.ADDRESS_SERVER = address;
                        pref.setSaveAddressServer(address);
                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"Insira um endereço válido.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

}
