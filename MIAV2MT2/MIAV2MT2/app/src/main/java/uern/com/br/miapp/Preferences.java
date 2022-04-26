package uern.com.br.miapp;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Irlan on 04/11/2014.
 */
public class Preferences {
    private Context context;
    private SharedPreferences appPrefs;
    private SharedPreferences.Editor prefsEd;

    public Preferences(Context context){
        this.context = context;
        appPrefs = context.getSharedPreferences("MiaPrefs",0);
        prefsEd = appPrefs.edit();
    }

    public void setManterLogado (final String idUser){
        prefsEd.putString("idUser",idUser);
        prefsEd.apply();
    }

    public String getManterLogado(){
        SharedPreferences appPrefs = context.getSharedPreferences("MiaPrefs",0);
        return appPrefs.getString("idUser","");
    }

    public void setSaveAddressServer(String addressServer){
        prefsEd.putString("addressServer",addressServer);
        prefsEd.apply();
    }

    public String getAddressServer(){
        SharedPreferences appPrefs = context.getSharedPreferences("MiaPrefs",0);
        return appPrefs.getString("addressServer","");
    }
}
