package crud;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projetoagenda.R;

import androidx.appcompat.app.AppCompatActivity;
import pojo.Agenda;

public class Details extends AppCompatActivity {
    Button btEditar, btDelete;
    TextView id,  nome, telefone;
    String db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhar);
//Mostra um botão para voltar
        getSupportActionBar().setTitle("Agenda - Detalhes do Contato");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        id = findViewById(R.id.iddt);
        nome = findViewById(R.id.nomedt);
        telefone = findViewById(R.id.telefonedt);
        btEditar = findViewById(R.id.btEditar);
        btDelete = findViewById(R.id.btDelete);

        Intent itAgenda = getIntent();
        final Agenda agenda = (Agenda) itAgenda.getExtras().getSerializable("objAgenda");
        id.setText(String.valueOf(agenda.getId()));
        nome.setText(agenda.getNome());
        telefone.setText(agenda.getTelefone());
//Configura para editar os dados
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editar = new Intent(getApplicationContext(), EditRecord.class);
                editar.putExtra("objAgenda", agenda);
                startActivity(editar);
            }
        });
        //Configura para deletar os dados
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deletar = new Intent(getApplicationContext(), Details.class);
            }
        });    }
//Configura uma seta na barra superior
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