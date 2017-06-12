package br.com.nagasava.icad.icad.repository;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.nagasava.icad.icad.Util.Constantes;
import br.com.nagasava.icad.icad.Util.Util;
import br.com.nagasava.icad.icad.validation.ContatoValidation;

public class ContatoRepository extends SQLiteOpenHelper {

    public ContatoRepository(Context context) {
        super(context, Constantes.BD_NOME, null, Constantes.BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_CONTATO( ");
        query.append(" ID_CONTATO INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" NOME TEXT(30) NOT NULL,");
        query.append(" EMAIL TEXT(30) NOT NULL,");
        query.append(" CEP TEXT(30) NOT NULL,");
        query.append(" LOGRADOURO TEXT(50),");
        query.append(" COMPLEMENTO TEXT(30),");
        query.append(" BAIRRO TEXT(30) NOT NULL,");
        query.append(" CIDADE TEXT(30) NOT NULL,");
        query.append(" UF TEXT(30) NOT NULL,");
        query.append(" TELEFONE TEXT(30) NOT NULL)");

        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public void salvarContato(ContatoValidation contato) {

        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();

            contentValues.put("NOME", contato.getEdtNome().getText().toString());
            contentValues.put("EMAIL", contato.getEdtEmail().getText().toString());
            contentValues.put("CEP", contato.getEdtCep().getText().toString());
            contentValues.put("LOGRADOURO", contato.getEdtLogradouro().getText().toString());
            contentValues.put("NUMERO", contato.getEdtNumero().getText().toString());
            contentValues.put("COMPLEMENTO", contato.getEdtComplemento().getText().toString());
            contentValues.put("BAIRRO", contato.getEdtBairro().getText().toString());
            contentValues.put("CIDADE", contato.getEdtCidade().getText().toString());
            contentValues.put("UF", contato.getEdtUf().getText().toString());
            contentValues.put("TELEFONE", contato.getEdtTelefone().getText().toString());

            db.insert("TB_CONTATO", null, contentValues);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
