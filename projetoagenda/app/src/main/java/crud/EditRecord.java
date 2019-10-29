package crud;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Message;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.projetoagenda.MainActivity;
import com.example.projetoagenda.R;

import androidx.appcompat.app.AppCompatActivity;
import pojo.Agenda;

public class EditRecord extends AppCompatActivity {

    TextView id;
    EditText nome, telefone;
    Button btSalvar, btDelete;

    SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        // Mostra um botão para voltar
        getSupportActionBar().setTitle("Agenda - Editar Contato");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id = findViewById(R.id.ided);
        nome = findViewById(R.id.nomeed);
        telefone = findViewById(R.id.telefoneed);

        final Intent itAgenda = getIntent();
        final Agenda agenda = (Agenda) itAgenda.getExtras().getSerializable("objAgenda");
        id.setText(String.valueOf(agenda.getId()));
        nome.setText(agenda.getNome());
        telefone.setText(agenda.getTelefone());


        btSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Coleta os dados digitados nos campos
                ContentValues values = new ContentValues();
                values.put("nome", nome.getText().toString());
                values.put("telefone", telefone.getText().toString());

                Agenda novosDados = new Agenda();
                novosDados.setNome(nome.getText().toString());
                novosDados.setTelefone(telefone.getText().toString());

                // Atualiza os dados na tabela
                db = openOrCreateDatabase("db_agenda", Context.MODE_PRIVATE, null);
                db.execSQL("UPDATE agenda SET " +
                        "nome='" + novosDados.getNome() + "'," +
                        "telefone='" + novosDados.getTelefone() + "'," +
                        "WHERE id=" + agenda.getId()
                );

                ;
                Intent main = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(main);
            }
        });
    }

    // Configura a seta na barra superior
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}