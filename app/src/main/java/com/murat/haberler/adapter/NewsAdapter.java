package com.murat.haberler.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.murat.haberler.R;
import com.murat.haberler.model.News;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.HaberHolder> {

    private Context context;
    private ArrayList<News> haberList;
    private View.OnClickListener onItemClickListener;

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        onItemClickListener = itemClickListener;
    }

    public NewsAdapter(ArrayList<News> haberList, Context context) {
        this.context = context;
        this.haberList = haberList;
    }

    @NonNull
    @Override
    public HaberHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.row_news, viewGroup, false);

        return new HaberHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HaberHolder haberHolder, int i) {
        News haber = haberList.get(i);
        haberHolder.tvTitle.setText(haber.getTitle());
        haberHolder.tvDesc.setText(haber.getDescription());
        Glide.with(context).load(haber.getUrlToImage()).into(haberHolder.imgHaber);
    }

    @Override
    public int getItemCount() {
        return haberList.size();
    }

    class HaberHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDesc;
        ImageView imgHaber;

        HaberHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            imgHaber = itemView.findViewById(R.id.imgHaberUrl);
            tvDesc = itemView.findViewById(R.id.tvDescription);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }
    }
}
