package crud;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetoagenda.R;

import androidx.appcompat.app.AppCompatActivity;
import pojo.Agenda;
import utils.Message;

public class Insert extends AppCompatActivity {
        EditText nome, telefone;
        Button btInserir;
        SQLiteDatabase db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_cadastrar);

//Cria o banco de dados
            db = openOrCreateDatabase("db_agenda", Context.MODE_PRIVATE, null);

//Cria a tabela se não existir previamente
            db.execSQL("CREATE TABLE IF NOT EXISTS agenda(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nome VARCHAR NOT NULL, " +
                    "telefone VARCHAR NOT NULL);");

//Botão para voltar
            getSupportActionBar().setTitle("Agenda - Cadastrar");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);


            nome = findViewById(R.id.editNome);
            telefone = findViewById(R.id.editTelefone);
            btInserir = findViewById(R.id.btCadastrar);

            btInserir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//Cria um objeto agenda para receber os dados
                    Agenda agenda = new Agenda();
                    agenda.setNome(nome.getText().toString());
                    agenda.setTelefone(telefone.getText().toString());

//Coleta os dados digitados
                    ContentValues values = new ContentValues();
                    values.put("nome", agenda.getNome());
                    values.put("telefone", agenda.getTelefone());
//Insere os dados na tabela
                    db.insert("agenda", null, values);
//Mostra que os dados foram incluídos
                    Message message = new Message(Insert.this);
                    message.show(
                            "Dados incluídos com sucesso!",
                            agenda.getDados(),
                            R.drawable.ic_add);
//Limpa os campos de entrada



                    clearText();
                }
            });
        }

//Configura a seta na barra superior
        public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()) {
                case android.R.id.home:
                    this.finish();
                    return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

//Limpa os campos e fecha o teclado
        public void clearText() {
            nome.setText("");
            telefone.setText("");
//Fecha o teclado virtual
                   ((InputMethodManager) Insert.this.getSystemService(
                    Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                    getCurrentFocus().getWindowToken(), 0);
        }
    }


