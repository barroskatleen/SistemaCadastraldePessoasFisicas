//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: katleen Lima Barros

//******************************************************
package com.example.katleen.projetofinal;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.List;

class DBFormulario {
    /*Criacao da tabela do banco de dados*/
    private static final String DATABASE_NAME = "bancodedados.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Formulario";

    private Context context;
    private SQLiteDatabase db;

    private SQLiteStatement insertStmt;
    private static final String INSERT = "insert into " + TABLE_NAME + "(nome, cpf, idade, telefone, email) values (?,?,?,?,?)";

    public DBFormulario (Context context){
        this.context = context;
        OpenFormulario openFormulario = new OpenFormulario(this.context);
        this.db = openFormulario.getWritableDatabase();
        this.insertStmt = this.db.compileStatement(INSERT);

    }
            /*Inserir dados ao banco*/
    public long insert (String nome, String cpf, String idade, String telefone, String email){
        this.insertStmt.bindString(1, nome);
        this.insertStmt.bindString(2, cpf);
        this.insertStmt.bindString(3, idade);
        this.insertStmt.bindString(4, telefone);
        this.insertStmt.bindString(5, email);

        return this.insertStmt.executeInsert();
    }

    public  void deleteAll(){
        this.db.delete(TABLE_NAME, null, null);

    }

    public List<Formulario> queryGetAll(){
        List<Formulario> list = new ArrayList<Formulario>();

        try {
            Cursor cursor = this.db.query (TABLE_NAME, new String [] {"nome", "cpf", "idade", "telefone", "email"},
                null, null, null, null, null, null     );
            int nregistros = cursor.getCount();
            if(nregistros!=0){
                cursor.moveToFirst();
                do {
                    Formulario formulario = new Formulario(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
                    list.add(formulario);

                }while (cursor.moveToNext());

                if (cursor != null && ! cursor.isClosed())
                    cursor.close();
                    return list;
            }else
                return null;
        }
        catch (Exception err){
            return null;
        }
    }
                /*para criacao do banco*/
    private static class OpenFormulario extends SQLiteOpenHelper {
        OpenFormulario(Context context){
            super (context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        public void onCreate (SQLiteDatabase db){
            String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, " + "cpf INT, idade INT, telefone INT, email TEXT);";
            db.execSQL(sql);
        }
        public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
            onCreate(db);
        }
    }
}