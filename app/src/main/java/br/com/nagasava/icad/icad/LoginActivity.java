package br.com.nagasava.icad.icad;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import br.com.nagasava.icad.icad.bo.LoginBO;
import br.com.nagasava.icad.icad.validation.LoginValidation;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLogin;
    private EditText edtSenha;

    private CheckBox ckbManterLogin;

    private Button btnLogar;

    private SharedPreferences preferences;

    private LoginBO loginBO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBO = new LoginBO(this);

        getSupportActionBar().hide();

        preferences = getSharedPreferences("pref", Context.MODE_PRIVATE);
        String login = preferences.getString("login", null);
        String senha = preferences.getString("senha", null);

        if (login != null && senha != null) {
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        }

        edtLogin  = (EditText) findViewById(R.id.edtLogin);
        edtSenha  = (EditText) findViewById(R.id.edtSenha);

        ckbManterLogin = (CheckBox) findViewById(R.id.ckbManterLogin);

        btnLogar = (Button) findViewById(R.id.btnLogar);

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = edtLogin.getText().toString();
                String senha = edtSenha.getText().toString();

                LoginValidation validation = new LoginValidation();
                validation.setActivity(LoginActivity.this);
                validation.setEdtLogin(edtLogin);
                validation.setEdtSenha(edtSenha);
                validation.setCkbManterLogin(ckbManterLogin);
                validation.setLogin(login);
                validation.setSenha(senha);
                boolean isValido = loginBO.validarCamposLogin(validation);
                if (isValido) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
