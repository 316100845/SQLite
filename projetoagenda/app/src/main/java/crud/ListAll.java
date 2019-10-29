package crud;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.projetoagenda.R;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import pojo.Agenda;

public class ListAll extends AppCompatActivity {
    ListView listViewAgenda;
    ArrayList<Agenda> agenda = new ArrayList<>();
    ArrayAdapter<Agenda> adaptador;
    SQLiteDatabase db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar);
        // Mostra um botão para voltar
        getSupportActionBar().setTitle("Agenda - Lista de Contatos");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        // Abre o banco de dados existente
        db = openOrCreateDatabase("db_agenda", Context.MODE_PRIVATE, null);

        listViewAgenda = findViewById(R.id.listagem);

        // Carrega os registros em ordem alfabética no ArrayList para anexar ao adaptador
        agenda.clear();
        Cursor c = db.rawQuery("SELECT * FROM agenda ORDER BY nome ASC", null);
        while (c.moveToNext()) {
            agenda.add(new Agenda(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2)));
        }
        // Configura o adaptador
        adaptador = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                agenda);


        // Anexa o adaptador à ListView
        listViewAgenda.setAdapter(adaptador);

        listViewAgenda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Agenda agenda = (Agenda) listViewAgenda.getItemAtPosition(position);
                Intent itAgenda = new Intent(getApplicationContext(), Details.class);
                itAgenda.putExtra("objAgenda", agenda);
                startActivity(itAgenda);
            }
        });
    }

    // Configura a seta na narra superior
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
