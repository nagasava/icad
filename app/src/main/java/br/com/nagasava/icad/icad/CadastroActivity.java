package br.com.nagasava.icad.icad;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.nagasava.icad.icad.Util.Util;
import br.com.nagasava.icad.icad.bo.ContatoBO;
import br.com.nagasava.icad.icad.repository.ContatoRepository;
import br.com.nagasava.icad.icad.validation.ContatoValidation;
import br.com.nagasava.icad.icad.validation.LoginValidation;

public class CadastroActivity extends AppCompatActivity {

    private ContatoBO contatoBO;

    private EditText edtNome;
    private EditText edtEmail;
    private EditText edtCep;
    private EditText edtLogradouro;
    private EditText edtNumero;
    private EditText edtComplemento;
    private EditText edtBairro;
    private EditText edtCidade;
    private EditText edtUf;
    private EditText edtTelefone;

    private Button btnSalvar;

    private ProgressDialog load;

    private ContatoRepository contato;

    protected boolean hasConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        contatoBO = new ContatoBO(this);

        edtNome         = (EditText) findViewById(R.id.edtNome);
        edtEmail        = (EditText) findViewById(R.id.edtEmail);
        edtCep          = (EditText) findViewById(R.id.edtCep);
        edtLogradouro   = (EditText) findViewById(R.id.edtLogradouro);
        edtNumero       = (EditText) findViewById(R.id.edtNumero);
        edtComplemento  = (EditText) findViewById(R.id.edtComplemento);
        edtBairro       = (EditText) findViewById(R.id.edtBairro);
        edtCidade       = (EditText) findViewById(R.id.edtCidade);
        edtUf           = (EditText) findViewById(R.id.edtUf);
        edtTelefone     = (EditText) findViewById(R.id.edtTelefone);

        btnSalvar       = (Button) findViewById(R.id.btnSalvar);

        consultaCep();

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContatoValidation validation = montarContato();

                boolean isValido = contatoBO.validarCamposContato(validation);

                if(isValido){
                    Util.showMsgToast(CadastroActivity.this, "isValido");
                    contatoBO.salvarContato(validation);
                    Intent i = new Intent(CadastroActivity.this, SucessoActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    private ContatoValidation montarContato() {
        ContatoValidation validation = new ContatoValidation();
        validation.setEdtNome(edtNome);
        validation.setEdtEmail(edtEmail);
        validation.setEdtCep(edtCep);
        validation.setEdtLogradouro(edtLogradouro);
        validation.setEdtNumero(edtNumero);
        validation.setEdtComplemento(edtComplemento);
        validation.setEdtBairro(edtBairro);
        validation.setEdtCidade(edtCidade);
        validation.setEdtUf(edtUf);
        validation.setEdtTelefone(edtTelefone);
        validation.setActivity(CadastroActivity.this);

        Util.showMsgToast(this, validation.toString());

        return validation;
    }

    private void consultaCep() {
        final EditText edtCep = (EditText) findViewById(R.id.edtCep);

        edtCep.setOnFocusChangeListener(new EditText.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (hasConnectivity()) {
                        CepService task = new CepService();
                        task.execute(edtCep.getText().toString());
                    }
                }
            }
        });
    }

    public class CepService extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {
            load = ProgressDialog.show(CadastroActivity.this, CadastroActivity.this.getString(R.string.msg_aguarde), CadastroActivity.this.getString(R.string.msg_buscando));
        }

        @Override
        protected void onPostExecute(String jsonText) {
            if (jsonText != null) {
                try {
                    JSONObject jsonResult = new JSONObject(jsonText);
                    completarEndereco(jsonResult);
                } catch (JSONException e) {
                    Util.showMsgToast(CadastroActivity.this, CadastroActivity.this.getString(R.string.msg_endereco_erro));

                    edtLogradouro.setEnabled(true);
                    edtBairro.setEnabled(true);
                    edtCidade.setEnabled(true);
                    edtUf.setEnabled(true);

                    edtLogradouro.setText("");
                    edtBairro.setText("");
                    edtCidade.setText("");
                    edtUf.setText("");

                    e.printStackTrace();
                }
            }
            load.dismiss();
        }

        private void completarEndereco(JSONObject jsonResult) throws JSONException {
            edtLogradouro.setText(jsonResult.getString("logradouro"));
            edtBairro.setText(jsonResult.getString("bairro"));
            edtCidade.setText(jsonResult.getString("localidade"));
            edtUf.setText(jsonResult.getString("uf"));

            edtLogradouro.setEnabled(false);
            edtBairro.setEnabled(false);
            edtCidade.setEnabled(false);
            edtUf.setEnabled(false);
        }

        @Override
        protected String doInBackground(String... params) {
            String json = "UNDEFINED";

            try {
                String urlSpec = "https://viacep.com.br/ws/" + params[0] + "/json";
                URL url = new URL(urlSpec);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                StringBuilder builder = new StringBuilder();

                String inputString;
                while ((inputString = bufferedReader.readLine()) != null) {
                    builder.append(inputString);
                }

                json = builder.toString();

                urlConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return json;
        }
    }
}
