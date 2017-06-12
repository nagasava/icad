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
        query.append(" ID_PESSOA INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" NOME TEXT(30) NOT NULL,");
        query.append(" EMAIL TEXT(30) NOT NULL,");
        query.append(" CEP TEXT(30) NOT NULL,");
        query.append(" LOGRADOURO TEXT(50)NOT NULL,");
        query.append(" NUMERO TEXT(30) NOT NULL,");
        query.append(" COMPLEMENTO TEXT(30),");
        query.append(" BAIRRO TEXT(30) NOT NULL,");
        query.append(" CIDADE TEXT(30) NOT NULL,");
        query.append(" UF TEXT(30) NOT NULL,");
        query.append(" TELEFONE TEXT(30) NOT NULL)");

        db.execSQL(query.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContato(ContatoValidation contatoValidation) {

        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_CONTATO( ");
        query.append(" ID_PESSOA INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" NOME TEXT(30) NOT NULL,");
        query.append(" EMAIL TEXT(30) NOT NULL,");
        query.append(" CEP TEXT(30) NOT NULL,");
        query.append(" LOGRADOURO TEXT(50)NOT NULL,");
        query.append(" NUMERO TEXT(30) NOT NULL,");
        query.append(" COMPLEMENTO TEXT(30),");
        query.append(" BAIRRO TEXT(30) NOT NULL,");
        query.append(" CIDADE TEXT(30) NOT NULL,");
        query.append(" UF TEXT(30) NOT NULL,");
        query.append(" TELEFONE TEXT(30) NOT NULL)");

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query.toString());

        //SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NOME", contatoValidation.getEdtNome().getText().toString());
        contentValues.put("EMAIL", contatoValidation.getEdtEmail().getText().toString());
        contentValues.put("CEP", contatoValidation.getEdtCep().getText().toString());
        contentValues.put("LOGRADOURO", contatoValidation.getEdtLogradouro().getText().toString());
        contentValues.put("NUMERO", contatoValidation.getEdtNumero().getText().toString());
        contentValues.put("COMPLEMENTO", contatoValidation.getEdtComplemento().getText().toString());
        contentValues.put("BAIRRO", contatoValidation.getEdtBairro().getText().toString());
        contentValues.put("CIDADE", contatoValidation.getEdtCidade().getText().toString());
        contentValues.put("UF", contatoValidation.getEdtUf().getText().toString());
        contentValues.put("TELEFONE", contatoValidation.getEdtTelefone().getText().toString());

        db.insert("TB_CONTATO", null, contentValues);
    }

    public void listarContatos(Activity activity) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_CONTATO", null, "ID_PESSOA = ? ", new String[] {"1"}, null, null, null);
        while(cursor.moveToNext()) {
            String txt = "Id de contato: " + String.valueOf(cursor.getInt(cursor.getColumnIndex("ID_PESSOA")));
            Util.showMsgToast(activity, txt);
        }
    }

}
