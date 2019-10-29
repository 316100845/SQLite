package com.example.projetoagenda;

import androidx.appcompat.app.AppCompatActivity;
import crud.Insert;
import crud.ListAll;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//Declara os botões
    Button btInsert, btList, btSearch, btExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//Associa o botão de inserção e configura o clique
        btInsert = findViewById(R.id.btMainInsert);
        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent insert = new Intent(getApplicationContext(), Insert.class);
                startActivity(insert);
            }
        });

//Associa o botão de listar e configura o clique
        btList = findViewById(R.id.btMainList);
        btList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent insert = new Intent(getApplicationContext(), ListAll.class);
                startActivity(insert);
            }
        });

//Associa o botão de saída e configura o clique
        btExit = findViewById(R.id.btMainExit);
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//Finaliza o app
                finishAffinity();
            }
            });
    }
}





