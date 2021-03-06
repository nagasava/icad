package br.com.nagasava.icad.icad.repository;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.nagasava.icad.icad.Util.Constantes;
import br.com.nagasava.icad.icad.Util.Util;

public class LoginRepository extends SQLiteOpenHelper {

    public LoginRepository(Context context) {
        super(context, Constantes.BD_NOME, null, Constantes.BD_VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("CREATE TABLE TB_LOGIN( ");
        query.append(" ID_LOGIN INTEGER PRIMARY KEY AUTOINCREMENT,");
        query.append(" USUARIO TEXT(15) NOT NULL,");
        query.append(" SENHA TEXT(15) NOT NULL)");

        db.execSQL(query.toString());

        popularBD(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    private void popularBD(SQLiteDatabase db) {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO TB_LOGIN(USUARIO, SENHA) VALUES(?, ?)");

        db.execSQL(query.toString(), new String[] {"admin", "admin"});
    }

    public void listarLogins(Activity activity) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query("TB_LOGIN", null, "id_login = ? and usuario = ?", new String[] {"1", "admin"}, null, null, "USUARIO");
        while(cursor.moveToNext()) {
            String txt = "Id de usuário: " + String.valueOf(cursor.getInt(cursor.getColumnIndex("ID_LOGIN")));
            Util.showMsgToast(activity, txt);
        }
    }

    public void addLogin(String login, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USUARIO", login);
        contentValues.put("SENHA", senha);

        db.insert("TB_LOGIN", null, contentValues);
    }

    public void updateLogin(String login, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("USUARIO", login);
        contentValues.put("SENHA", senha);

        db.update("TB_LOGIN", contentValues, "id_login > 1", null);
    }

    public void deleteLogin(String login, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("TB_LOGIN", "usuario = ? or senha = ?", new String[] {login, senha});
    }

}
