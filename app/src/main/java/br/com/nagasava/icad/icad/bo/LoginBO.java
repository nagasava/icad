package br.com.nagasava.icad.icad.bo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import br.com.nagasava.icad.icad.R;
import br.com.nagasava.icad.icad.Util.Util;
import br.com.nagasava.icad.icad.repository.LoginRepository;
import br.com.nagasava.icad.icad.validation.LoginValidation;

public class LoginBO {

    private LoginRepository loginRepository;

    public LoginBO(Activity activity) {
        loginRepository = new LoginRepository(activity);
        loginRepository.listarLogins(activity);
    }

    public boolean validarCamposLogin(LoginValidation validation) {
        boolean resultado = true;
        if (validation.getLogin() == null || "".equals(validation.getLogin())) {
            validation.getEdtLogin().setError( validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        } else if (validation.getLogin().length() < 5) {
            validation.getEdtLogin().setError(validation.getActivity().getString(R.string.msg_campo_tamanho_minimo));
        }

        if (validation.getSenha() == null || "".equals(validation.getSenha())) {
            validation.getEdtSenha().setError(validation.getActivity().getString(R.string.msg_campo_obrigatorio));
            resultado = false;
        }else if (validation.getSenha().length() < 5) {
            validation.getEdtSenha().setError(validation.getActivity().getString(R.string.msg_campo_tamanho_minimo));
        }

        if (resultado){
            if (!validation.getLogin().equals("admin") || !validation.getSenha().equals("admin")) {
                Util.showMsgToast(validation.getActivity(), validation.getActivity().getString(R.string.msg_login_invalido));
                resultado = false;
            }else if (validation.getCkbManterLogin().isChecked()) {
                SharedPreferences.Editor editor = validation.getActivity().getSharedPreferences("pref", Context.MODE_PRIVATE).edit();
                editor.putString("login", validation.getLogin());
                editor.putString("senha", validation.getSenha());
                editor.commit();
            }
        }
        return resultado;
    }

    public void deslogar() {

    }
}
