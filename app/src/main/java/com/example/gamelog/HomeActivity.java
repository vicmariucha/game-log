package com.example.gamelog;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DataHelper.init(this);
        DataHelper.User user = DataHelper.getCurrentUser();

        TextView txtWelcome = findViewById(R.id.txtWelcome);
        ListView listReviews = findViewById(R.id.listReviews);

        if (user != null) {
            txtWelcome.setText("Bem-vindo, " + user.name);

            // Carrega avaliações do usuário
            List<DataHelper.Review> reviews = DataHelper.getUserReviews(user.id);
            List<String> reviewItems = new ArrayList<>();

            for (DataHelper.Review review : reviews) {
                DataHelper.Game game = DataHelper.getGameById(review.gameId);
                if (game != null) {
                    reviewItems.add(game.name + " - " + review.rating + "★: " + review.comment);
                }
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    reviewItems
            );
            listReviews.setAdapter(adapter);
        }
    }
}