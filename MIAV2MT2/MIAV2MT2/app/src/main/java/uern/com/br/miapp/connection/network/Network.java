package uern.com.br.miapp.connection.network;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by Irlan on 03/11/2014.
 */
public class Network {
    public static boolean isConnected(Context context){
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

            if(cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()||cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static int ConnectedType(Context context){
        try{
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);

            if(cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                return 1;
            }else if(cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()){
                return 2;
            }else{
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }

    }
}
