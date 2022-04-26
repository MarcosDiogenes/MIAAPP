package uern.com.br.miapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NovoLogin extends AppCompatActivity {

    private EditText boxNomePaciente;
    private EditText boxCPF;
    private Button btnConfirmaLogin;
    public static String nomePaciente;
    public static String cpf;
    public static String idPaciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_novo_login);

        boxNomePaciente = (EditText) findViewById(R.id.boxNomePaciente);
        boxCPF = (EditText) findViewById(R.id.boxCPF);
        btnConfirmaLogin = (Button) findViewById(R.id.btnConfirmaLogin);

        View.OnClickListener onClickBtnConfirmaLogin = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                BackgroundNovoLogin backgroundNovoLogin = new BackgroundNovoLogin(getBaseContext());
                String method = "loginfuncoes";
                nomePaciente = boxNomePaciente.getText().toString();
                cpf = boxCPF.getText().toString();
                backgroundNovoLogin.execute(method,nomePaciente,cpf);
                idPaciente = BackgroundNovoLogin.IDBACKGROUND;
                //System.out.println(idPaciente);
                if (idPaciente != null){
                    startActivity(new Intent(NovoLogin.this, EntradaDegluticao.class));
                }

            }
        };
        btnConfirmaLogin.setOnClickListener(onClickBtnConfirmaLogin);

    }
}