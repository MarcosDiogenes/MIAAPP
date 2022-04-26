package uern.com.br.miapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

public class EntradaDegluticao extends AppCompatActivity {

    private String textura;
    private Button botaoAlimentoSolido;
    private Button botaoAlimentoPastoso;
    private Button botaoAlimentoLiquido;
    private Button botaoConfirmaTextura;
    private Button botaoInfoPaciente;
    private TextView textoBemVindoPaciente;
    private TextView textoSelecioneTextura;
    private TextView textoConfirmacaoTextura;
    private ImageView imagemAlimento;
    public static String fk_teste_tbl_classificados;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrada_degluticao);

        botaoAlimentoLiquido = (Button) findViewById(R.id.botaoAlimentoLiquido);
        botaoAlimentoPastoso = (Button) findViewById(R.id.botaoAlimentoPastoso);
        botaoAlimentoSolido = (Button) findViewById(R.id.botaoAlimentoSolido);
        botaoConfirmaTextura = (Button) findViewById(R.id.botaoConfirmaTextura);
        botaoInfoPaciente = (Button) findViewById(R.id.botaoInfoPaciente);
        textoSelecioneTextura = (TextView) findViewById(R.id.textoSelecioneTextura);
        textoBemVindoPaciente = (TextView) findViewById(R.id.textoBemVindoPaciente);
        textoConfirmacaoTextura = (TextView) findViewById(R.id.textoConfirmacaoTextura);
        fk_teste_tbl_classificados = BackgroundNovoLogin.IDBACKGROUND;
        System.out.println(fk_teste_tbl_classificados);

        imagemAlimento = (ImageView) findViewById(R.id.imagemAlimento);
        textura= "NULL";

        View.OnClickListener onClickListenerBotaoLiquido = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTextura("Liquido");
                textoConfirmacaoTextura.setText("You selected liquid. To continue, touch Confirm.");
                imagemAlimento.setImageResource(R.drawable.imagemalimentoliquido);
            }
        };
        botaoAlimentoLiquido.setOnClickListener(onClickListenerBotaoLiquido);

        View.OnClickListener onClickListenerBotaoPastoso = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTextura("pastoso");
                textoConfirmacaoTextura.setText("You selected pasty. To continue, touch Confirm.");
                imagemAlimento.setImageResource(R.drawable.imagemalimentopastoso);
            }
        };
        botaoAlimentoPastoso.setOnClickListener(onClickListenerBotaoPastoso);

        View.OnClickListener onClickListenerBotaoSolido = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                setTextura("Solido");
                textoConfirmacaoTextura.setText("You selected solid. To continue, touch Confirm.");
                imagemAlimento.setImageResource(R.drawable.imagemalimentosolido);
            }
        };
        botaoAlimentoSolido.setOnClickListener(onClickListenerBotaoSolido);

        View.OnClickListener onClickListenerBotaoConfirmaTextura = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method = "register";
                String textura_alimento = textura;
                String reg_1 = "1";
                BackgroundDegluticao backgroundDegluticao = new BackgroundDegluticao(getBaseContext());
                backgroundDegluticao.execute(method,textura_alimento,reg_1, fk_teste_tbl_classificados);
                //finish();

            }
        };
        botaoConfirmaTextura.setOnClickListener(onClickListenerBotaoConfirmaTextura);

        View.OnClickListener onClickListenerBotaoInfoPaciente = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EntradaDegluticao.this, ListaInfoPaciente.class));

            }
        };
        botaoInfoPaciente.setOnClickListener(onClickListenerBotaoInfoPaciente);

    }

    public String getTextura() {
        return textura;
    }

    public void setTextura(String textura) {
        this.textura = textura;
    }
}
