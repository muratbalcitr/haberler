
package com.murat.haberler.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.murat.haberler.R;
import com.murat.haberler.adapter.NewsAdapter;
import com.murat.haberler.model.News;
import com.murat.haberler.util.ItemClickListener;
import com.murat.haberler.util.VolleyData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Business_Fragment extends Fragment {

    VolleyData volleyData;
    private ArrayList<News> haberList;
    private RecyclerView rvBoss;
    private NewsAdapter haberAdapter;
     private String type_s = "business";
    String title,
            desc, imageUrl,
            name_yayinci,
            author,
            url,
            publishedAt,
            content;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Fragment fragment;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            News news = haberList.get(position);

            Bundle args = new Bundle();
            fragment = new NewsDetails_Fragment();
            args.putString("title", news.getTitle());
            args.putString("imageUrl", news.getUrlToImage());
            args.putString("name_yayinci", news.getName_url());
            args.putString("author", news.getAuthor());
            args.putString("url", news.getUrl());
            args.putString("publishedAt", news.getYayin_tarihi());
            args.putString("content", news.getHaber_content());
            fragment.setArguments(args);
            loadFragment(fragment);

        }
    };
    LinearLayoutManager manager;

    //hiçbir şeye tıklayamıyoz :D
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        haberList = new ArrayList<>();
        getNews(type_s);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_business, container, false);

        rvBoss = view.findViewById(R.id.rvBoss);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeBoss);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
                getNews(type_s);
            }
        });
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }


    public void getNews(final String type) {
        haberList.clear();
        final String news_link = "https://newsapi.org/v2/top-headlines?country=tr&category=" + type + "&apiKey=a9db625ef9904a14ab5b38836b3f7d67";

        StringRequest request = new StringRequest(news_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String strings) {

                try {
                    JSONObject jsonObject = new JSONObject(strings);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        title = jo.getString("title");
                        desc = jo.getString("description");
                        imageUrl = jo.getString("urlToImage");
                        name_yayinci = jo.getJSONObject("source").getString("name");
                        author = jo.getString("author");
                        url = jo.getString("url");
                        publishedAt = jo.getString("publishedAt");
                        content = jo.getString("content");
                        haberList.add(new News(desc, name_yayinci, author, title, url, imageUrl, publishedAt, content));

                        //haberAdapter.notifyDataSetChanged();
                    }
                    if (haberList.size() > 0)
                        recList(getView());
                    else
                        getNews(type);

                } catch (
                        JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(), "Lütfen daha sonra deneyiniz", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        rQueue.add(request);
    }

    public void recList(View view) {
        try {
            haberAdapter = new NewsAdapter(haberList, view.getContext());
            haberAdapter.setOnItemClickListener(onItemClickListener);
            rvBoss.setLayoutManager(new LinearLayoutManager(view.getContext()));
            rvBoss.setAdapter(haberAdapter);
            haberAdapter.notifyDataSetChanged();
            //}
            //haberAdapter.setOnItemClickListener(onItemClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
