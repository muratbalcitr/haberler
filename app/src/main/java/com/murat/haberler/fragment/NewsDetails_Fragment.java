package com.murat.haberler.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.murat.haberler.R;
import com.murat.haberler.model.News;
import com.murat.haberler.util.ItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetails_Fragment extends Fragment {

    private TextView tvTitle, tvContent;
    private ImageView imgDetail;
    String title,
            desc, imageUrl,
            name_yayinci,
            author,
            url,
            content,
            publishedAt;

    public NewsDetails_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = "";
        imageUrl = "";
        name_yayinci = "";
        author = "";
        url = "";
        publishedAt = "";
        content="";
        title = getArguments().getString("title");
        imageUrl = getArguments().getString("imageUrl");
        name_yayinci = getArguments().getString("name_yayinci");
        author = getArguments().getString("author");
        url = getArguments().getString("url");
        content = getArguments().getString("content");
        publishedAt = getArguments().getString("publishedAt");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news_details, container, false);
        tvTitle = view.findViewById(R.id.tvDetailTitle);
        tvContent = view.findViewById(R.id.tvContent);
        imgDetail = view.findViewById(R.id.imgDetailView);
        tvTitle.setText(title);
        tvContent.setText(content);
        Glide.with(getContext()).load(imageUrl).into(imgDetail);
        return view;
    }

}
