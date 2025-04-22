package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class BuscarActivity extends AppCompatActivity {

    private ListView listUsuarios;
    private EditText editBusca;
    private List<String> allUsers;      // Lista completa de usuários simulados
    private List<String> filteredUsers; // Lista filtrada com base na busca

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        // Inicialização dos componentes de interface
        listUsuarios = findViewById(R.id.listUsuarios);
        editBusca = findViewById(R.id.editBusca);

        // Dados simulados - substitua por dados reais se necessário
        allUsers = new ArrayList<>();
        allUsers.add("João");
        allUsers.add("Maria");
        allUsers.add("Carlos");
        allUsers.add("Ana");
        allUsers.add("Lucas");

        // Inicialmente a lista filtrada contém todos os usuários
        filteredUsers = new ArrayList<>(allUsers);

        // Adaptador customizado para a ListView
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_usuario, filteredUsers) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.list_item_usuario, parent, false);
                }

                // Define o nome do usuário no item da lista
                TextView nomeUsuario = convertView.findViewById(R.id.txtNomeUsuario);
                nomeUsuario.setText(filteredUsers.get(position));

                // Configura o botão "Ver lista"
                Button btnVerLista = convertView.findViewById(R.id.btnVerLista);
                btnVerLista.setOnClickListener(v -> {
                    String nome = filteredUsers.get(position);
                    Intent intent = new Intent(BuscarActivity.this, PerfilActivity.class);
                    intent.putExtra("nomeUsuario", nome);
                    startActivity(intent);
                });

                return convertView;
            }
        };

        listUsuarios.setAdapter(adapter);

        // Listener para filtrar conforme o usuário digita
        editBusca.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase().trim();
                filteredUsers.clear();
                for (String user : allUsers) {
                    if (user.toLowerCase().contains(query)) {
                        filteredUsers.add(user);
                    }
                }
                adapter.notifyDataSetChanged(); // Atualiza a ListView com os resultados filtrados
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
}
