//******************************************************

//Instituto Federal de São Paulo - Campus Sertãozinho

//Disciplina......: M4DADM

//Programação de Computadores e Dispositivos Móveis

//Aluno...........: katleen Lima Barros

//******************************************************
package com.example.katleen.projetofinal;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class ProjetoFinal extends AppCompatActivity {
        /*campos para inserir dados na segunda tela*/
    Button btinserir;

    private DBFormulario db;
    EditText etNome, etCpf, etIdade, etTelefone, etEmail;
    Button btVoltar, btLr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projeto_final);

        this.db = new DBFormulario(this);
        etNome = (EditText) findViewById(R.id.etnome);
        etCpf = (EditText) findViewById(R.id.etcpf);
        etIdade = (EditText) findViewById(R.id.etidade);
        etTelefone = (EditText) findViewById(R.id.ettelefone);
        etEmail = (EditText) findViewById(R.id.etemail);
            /*botoes declarados*/
        btLr = (Button) findViewById(R.id.btlr);
        btVoltar = (Button) findViewById(R.id.btvoltar);
        btinserir = (Button) findViewById(R.id.btinserir);

        btVoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                chamaPrimeiraTela();
            }
        });

        btinserir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (etNome.getText() .length()>0 && etCpf.getText() .length()>0 &&etIdade.getText() .length() >0 &&etTelefone.getText() .length()>0 &&etEmail.getText() .length()>0){
                    db.insert(etNome.getText() .toString(), etCpf.getText().toString(), etIdade.getText() .toString(), etTelefone.getText().toString(), etEmail.getText() .toString());
                    AlertDialog.Builder adb = new AlertDialog.Builder(ProjetoFinal.this);
                    adb.setTitle("Sucesso");
                    adb.setMessage("Cadastro Realizado!");
                    adb.show();

                    etNome.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");

                }
                else{
                    AlertDialog.Builder adb = new AlertDialog.Builder(ProjetoFinal.this);
                    adb.setTitle("Erro");
                    adb.setMessage("Todos os campos devem ser preenchidos!");
                    adb.show();

                    etNome.setText("");
                    etCpf.setText("");
                    etIdade.setText("");
                    etTelefone.setText("");
                    etEmail.setText("");
                }
            }
        });

        btLr.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                List<Formulario> formularios = db.queryGetAll();
                if (formularios == null){
                    AlertDialog.Builder adb = new AlertDialog.Builder(ProjetoFinal.this);
                    adb.setTitle("Mensagem");
                    adb.setMessage("Não há registros cadastrados!");
                    adb.show();
                    return;
                }
                for (int i=formularios.size()-1; i>=0; i--){
                    Formulario formulario = (Formulario) formularios.get(i);
                    AlertDialog.Builder adb = new AlertDialog.Builder(ProjetoFinal.this);
                    adb.setTitle("Registro " + (i+1));
                    adb.setMessage("Nome: "+ formulario.getNome() + "\nCpf: "+ formulario.getCpf() +"\nIdade: "+ formulario.getIdade() +"\nTelefone: "+ formulario.getTelefone() +"\nEmail: "+ formulario.getEmail());
                    adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    adb.show();
                }
            }
        });

    }
        /*retonar a primeira tela*/
    void chamaPrimeiraTela(){
        Intent intent = new Intent();
        intent.setClass(ProjetoFinal.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
