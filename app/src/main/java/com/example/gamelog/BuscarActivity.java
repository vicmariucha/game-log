package com.example.gamelog;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class BuscarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        EditText editBusca = findViewById(R.id.editBusca);
        ListView listUsuarios = findViewById(R.id.listUsuarios);

        // Adapter para a lista de usuários
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        listUsuarios.setAdapter(adapter);

        // Busca em tempo real
        editBusca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<String> resultados = DataHelper.buscarUsuarios(s.toString());
                adapter.clear();
                adapter.addAll(resultados);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Clique em um usuário da lista
        listUsuarios.setOnItemClickListener((parent, view, position, id) -> {
            String usuarioSelecionado = (String) parent.getItemAtPosition(position);
            // Aqui você pode implementar a navegação para o perfil do usuário
            Toast.makeText(BuscarActivity.this,
                    "Perfil de " + usuarioSelecionado, Toast.LENGTH_SHORT).show();
        });
    }
}