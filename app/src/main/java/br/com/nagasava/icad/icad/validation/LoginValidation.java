package br.com.nagasava.icad.icad.validation;

import android.app.Activity;
import android.widget.CheckBox;
import android.widget.EditText;

public class LoginValidation {

    private String login;
    private String senha;

    private EditText edtLogin;
    private EditText edtSenha;

    private CheckBox ckbManterLogin;

    private Activity activity;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EditText getEdtLogin() {
        return edtLogin;
    }

    public void setEdtLogin(EditText edtLogin) {
        this.edtLogin = edtLogin;
    }

    public EditText getEdtSenha() {
        return edtSenha;
    }

    public void setEdtSenha(EditText edtSenha) {
        this.edtSenha = edtSenha;
    }

    public CheckBox getCkbManterLogin() {
        return ckbManterLogin;
    }

    public void setCkbManterLogin(CheckBox ckbManterLogin) {
        this.ckbManterLogin = ckbManterLogin;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
