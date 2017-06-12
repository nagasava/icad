package br.com.nagasava.icad.icad.bo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import br.com.nagasava.icad.icad.R;
import br.com.nagasava.icad.icad.Util.Util;
import br.com.nagasava.icad.icad.repository.ContatoRepository;
import br.com.nagasava.icad.icad.validation.ContatoValidation;
import br.com.nagasava.icad.icad.validation.LoginValidation;

public class ContatoBO {

    private ContatoRepository contatoRepository;

    public ContatoBO(Activity activity) {
        contatoRepository = new ContatoRepository(activity);
        //contatoRepository.listarContatos(activity);
    }

    public boolean validarCamposContato(ContatoValidation validation) {
        boolean resultado = true;
        if (validation.getEdtNome().getText().toString() == null || "".equals(validation.getEdtNome().getText().toString())) {
            validation.getEdtNome().setError( validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        }

        if (validation.getEdtEmail().getText().toString() == null || "".equals(validation.getEdtEmail().getText().toString())) {
            validation.getEdtEmail().setError( validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        }else if(!validaEmail(validation.getEdtEmail().getText().toString())) {
            validation.getEdtEmail().setError( validation.getActivity().getString(R.string.msg_email_invalido));
            resultado = false;
        }

        if (validation.getEdtCep().getText().toString() == null || "".equals(validation.getEdtCep().getText().toString())) {
            validation.getEdtCep().setError( validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        }

        if (validation.getEdtLogradouro().getText().toString() == null || "".equals(validation.getEdtLogradouro().getText().toString())) {
            validation.getEdtLogradouro().setError( validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        }

        if (validation.getEdtNumero().getText().toString() == null || "".equals(validation.getEdtNumero().getText().toString())) {
            validation.getEdtNumero().setError( validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        }

        if (validation.getEdtBairro().getText().toString() == null || "".equals(validation.getEdtBairro().getText().toString())) {
            validation.getEdtBairro().setError( validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        }

        if (validation.getEdtCidade().getText().toString() == null || "".equals(validation.getEdtCidade().getText().toString())) {
            validation.getEdtCidade().setError( validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        }

        if (validation.getEdtUf().getText().toString() == null || "".equals(validation.getEdtUf().getText().toString())) {
            validation.getEdtUf().setError( validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        }

        if (validation.getEdtTelefone().getText().toString() == null || "".equals(validation.getEdtTelefone().getText().toString())) {
            validation.getEdtTelefone().setError( validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        }

        if(resultado) {
            contatoRepository.addContato(validation);
        }

        return resultado;
    }

    private boolean validaEmail(String email) {
        String regex = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
}
