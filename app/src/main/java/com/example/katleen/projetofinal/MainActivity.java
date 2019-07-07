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

public class MainActivity extends AppCompatActivity {
        /*inserir dados*/
    Button btinserir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btinserir = (Button) findViewById(R.id.btinserir);

        btinserir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                chamaSegundaTela();
            }
        });
    }
        /*retorna a segunda tela*/
    void chamaSegundaTela(){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this, ProjetoFinal.class);
        startActivity(intent);
        finish();
    }
}
