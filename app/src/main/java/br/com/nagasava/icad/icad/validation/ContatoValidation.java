package br.com.nagasava.icad.icad.validation;

import android.app.Activity;
import android.widget.EditText;

public class ContatoValidation {
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
    private Activity activity;

    public EditText getEdtNome() {
        return edtNome;
    }

    public void setEdtNome(EditText edtNome) {
        this.edtNome = edtNome;
    }

    public EditText getEdtEmail() {
        return edtEmail;
    }

    public void setEdtEmail(EditText edtEmail) {
        this.edtEmail = edtEmail;
    }

    public EditText getEdtCep() {
        return edtCep;
    }

    public void setEdtCep(EditText edtCep) {
        this.edtCep = edtCep;
    }

    public EditText getEdtLogradouro() {
        return edtLogradouro;
    }

    public void setEdtLogradouro(EditText edtLogradouro) {
        this.edtLogradouro = edtLogradouro;
    }

    public EditText getEdtNumero() {
        return edtNumero;
    }

    public void setEdtNumero(EditText edtNumero) {
        this.edtNumero = edtNumero;
    }

    public EditText getEdtComplemento() {
        return edtComplemento;
    }

    public void setEdtComplemento(EditText edtComplemento) {
        this.edtComplemento = edtComplemento;
    }

    public EditText getEdtBairro() {
        return edtBairro;
    }

    public void setEdtBairro(EditText edtBairro) {
        this.edtBairro = edtBairro;
    }

    public EditText getEdtCidade() {
        return edtCidade;
    }

    public void setEdtCidade(EditText edtCidade) {
        this.edtCidade = edtCidade;
    }

    public EditText getEdtUf() {
        return edtUf;
    }

    public void setEdtUf(EditText edtUf) {
        this.edtUf = edtUf;
    }

    public EditText getEdtTelefone() {
        return edtTelefone;
    }

    public void setEdtTelefone(EditText edtTelefone) {
        this.edtTelefone = edtTelefone;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "nome='" + edtNome.getText().toString() + '\'' +
                ", email='" + edtEmail.getText().toString() + '\'' +
                ", cep='" + edtCep.getText().toString() + '\'' +
                ", logradouro='" + edtLogradouro.getText().toString() + '\'' +
                ", numero='" + edtNumero.getText().toString() + '\'' +
                ", complemento=" + edtComplemento.getText().toString() +
                ", bairro=" + edtBairro.getText().toString() +
                ", cidade=" + edtCidade.getText().toString() +
                ", uf=" + edtUf.getText().toString() +
                ", telefone=" +  edtTelefone.getText().toString() +
                '}';
    }
}
