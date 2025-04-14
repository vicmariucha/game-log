package com.example.gamelog;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class BuscarActivity extends AppCompatActivity {

    private EditText editBusca;
    private ListView listUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        editBusca = findViewById(R.id.editBusca);
        listUsuarios = findViewById(R.id.listUsuarios);

        // Implementação da lógica de busca pode ser feita aqui.
    }
}
