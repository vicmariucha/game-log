package com.example.gamelog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class ReviewAdapter extends BaseAdapter {
    private Context context;
    private List<DataHelper.Review> reviews;
    private LayoutInflater inflater;

    public ReviewAdapter(Context context, List<DataHelper.Review> reviews) {
        this.context = context;
        this.reviews = reviews;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return reviews.size();
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        ImageView gameCover;
        TextView gameTitle;
        RatingBar ratingBar;
        TextView comment;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_review, parent, false);

            holder = new ViewHolder();
            holder.gameCover = convertView.findViewById(R.id.imgCapaJogo);
            holder.gameTitle = convertView.findViewById(R.id.txtTituloJogo);
            holder.ratingBar = convertView.findViewById(R.id.ratingEstrelas);
            holder.comment = convertView.findViewById(R.id.txtComentario);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DataHelper.Review review = reviews.get(position);

        // Imagem default (pesquisar sobre Glide, Picasso para carregamento real de fotos)
        holder.gameCover.setImageResource(R.drawable.exemplo_jogo);

        // Mostra o nome real do jogo, se disponível
        DataHelper.Game game = DataHelper.getGameById(review.gameId);
        if (game != null) {
            holder.gameTitle.setText(game.name);
        } else {
            holder.gameTitle.setText("Jogo " + review.gameId);
        }

        holder.ratingBar.setRating(review.rating);
        holder.comment.setText(review.comment);

        return convertView;
    }
}
