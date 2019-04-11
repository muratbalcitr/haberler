package com.murat.haberler.util;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.murat.haberler.adapter.NewsAdapter;
import com.murat.haberler.model.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VolleyData {

    private Context context;
    private List<News> haberList = new ArrayList<>();
   // private NewsAdapter haberAdapter = new NewsAdapter(haberList, context);

    public List<News> getNews(String type, Context activity) {

        String news_link = "https://newsapi.org/v2/top-headlines?country=tr&category=" + type + "&apiKey=a9db625ef9904a14ab5b38836b3f7d67";
        haberList.clear();
        StringRequest request = new StringRequest(news_link, new Response.Listener<String>() {
            @Override
            public void onResponse(String strings) {

                try {
                    JSONObject jsonObject = new JSONObject(strings);
                    JSONArray jsonArray = jsonObject.getJSONArray("articles");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jo = jsonArray.getJSONObject(i);
                        String title = jo.getString("title");
                        String desc = jo.getString("description");
                        String imageUrl = jo.getString("urlToImage");
                        News haber = new News(title, desc, imageUrl);
                        haberList.add(haber);
                        //haberAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //Toast.makeText(context, "Lütfen daha sonra deneyiniz", Toast.LENGTH_SHORT).show();
                System.out.print(volleyError);
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(activity);
        rQueue.add(request);
        return haberList;
    }
}
