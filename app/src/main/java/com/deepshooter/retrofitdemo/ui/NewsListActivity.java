package com.deepshooter.retrofitdemo.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.deepshooter.retrofitdemo.R;
import com.deepshooter.retrofitdemo.customs.ConnectionDetector;
import com.deepshooter.retrofitdemo.customs.SimpleDividerItemDecoration;
import com.deepshooter.retrofitdemo.customs.SnackBar;
import com.deepshooter.retrofitdemo.ui.getNewsDetails.GetNewsDetails;
import com.deepshooter.retrofitdemo.ui.getNewsList.Datum;
import com.deepshooter.retrofitdemo.ui.getNewsList.GetNewsList;
import com.deepshooter.retrofitdemo.utils.OnResponseListner;
import com.deepshooter.retrofitdemo.utils.WebServices;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsListActivity extends AppCompatActivity implements OnResponseListner {


    RecyclerView vL_anl_newsListView;
    NewsListAdapter newsListAdapter;

    List<Datum> newsList ;
    String preImageURL ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        initializeView();

        ConnectionDetector mDetector = new ConnectionDetector(NewsListActivity.this.getApplicationContext());
        if (mDetector.isConnectingToInternet()) {
            callNewsListService();
        }else {
            SnackBar.makeText(NewsListActivity.this, getString(R.string.please_check_internet), Toast.LENGTH_SHORT).show();
        }
    }


    private void initializeView()
    {
        vL_anl_newsListView = (RecyclerView) findViewById(R.id.vL_anl_newsListView);
    }


    private void setValues()
    {
        newsListAdapter = new NewsListAdapter(newsList,NewsListActivity.this);
        vL_anl_newsListView.setLayoutManager(new LinearLayoutManager(NewsListActivity.this));
        vL_anl_newsListView.setItemAnimator(new DefaultItemAnimator());
       // vL_anl_newsListView.addItemDecoration(new SimpleDividerItemDecoration(NewsListActivity.this));
        vL_anl_newsListView.setHasFixedSize(false);

        vL_anl_newsListView.setAdapter(newsListAdapter);
        newsListAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResponse(Object response, WebServices.ApiType URL, boolean isSucces) {

        if (URL == WebServices.ApiType.getNewsList) {
            if (isSucces) {

                GetNewsList getNewsList = (GetNewsList) response;
                Log.e("NewsListResponse",getNewsList.getResponse()+"");
                if(getNewsList.getResponse()!=null)
                {
                    if(getNewsList.getResponse().getData() ==null || getNewsList.getResponse().getData().size() == 0)
                    {
                        SnackBar.makeText(NewsListActivity.this, "No News Available", Toast.LENGTH_SHORT).show();
                    }else {

                        preImageURL = getNewsList.getFileRelativeUrl();
                        newsList = new ArrayList<>();
                        newsList.addAll(getNewsList.getResponse().getData());



                        setValues();

                    }
                }else {

                    SnackBar.makeText(NewsListActivity.this, getString(R.string.no_data_available), Toast.LENGTH_SHORT).show();
                }

            }else {


                SnackBar.makeText(NewsListActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }

        }else if (URL == WebServices.ApiType.getNewsDetails) {

            if (isSucces) {

                GetNewsDetails getNewsDetails = (GetNewsDetails) response;
                Log.e("NewsDetails",getNewsDetails.getResponse()+"");

            }else {

                SnackBar.makeText(NewsListActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }

        }
    }


    public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder>
    {
        List<Datum> resultList ;
        Context context;

        public NewsListAdapter(List<Datum> resultList, Context context) {
            this.resultList = resultList;
            this.context = context;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_list, parent, false);
            return new NewsListAdapter.MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {


            Picasso.with(NewsListActivity.this)
                    .load(preImageURL+"/"+resultList.get(position).getPoster())
                    .placeholder(R.drawable.bg)
                    .into(holder.vI_adnl_newsImage);

            String publishDate = resultList.get(position).getPublishDate();
            publishDate = publishDate.substring(0,10);

            holder.vT_adnl_newsTitle.setText(fromHtml(resultList.get(position).getCaption()));
            holder.vT_adnl_newsDate.setText(publishDate);



            holder.vL_adnl_newsLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(NewsListActivity.this,NewsDetailsActivity.class);
                    intent.putExtra("id",resultList.get(position).getId()+"");
                    intent.putExtra("preImageURL",preImageURL);
                    startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            if (resultList == null || resultList.size() == 0)
                return 0;
            return resultList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView vI_adnl_newsImage;
            TextView vT_adnl_newsTitle,vT_adnl_newsDate,vT_adnl_newsLocation;
            LinearLayout vL_adnl_newsLayout;


            public MyViewHolder(View itemView) {
                super(itemView);

                vI_adnl_newsImage = (ImageView) itemView.findViewById(R.id.vI_adnl_newsImage);
                vT_adnl_newsTitle = (TextView) itemView.findViewById(R.id.vT_adnl_newsTitle);
                vT_adnl_newsDate = (TextView) itemView.findViewById(R.id.vT_adnl_newsDate);
                vT_adnl_newsLocation = (TextView) itemView.findViewById(R.id.vT_adnl_newsLocation);
                vL_adnl_newsLayout = (LinearLayout) itemView.findViewById(R.id.vL_adnl_newsLayout);

            }
        }

    }


    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

            if (isNotEmpty((html))){
                result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
            }
            else{
                result = Html.fromHtml("",Html.FROM_HTML_MODE_LEGACY);
            }
        } else {
            if (isNotEmpty(html)){
                html = html.replaceAll("\\r", "").replaceAll("\\t", "");
                result = Html.fromHtml(html);
            }
            else{
                result = Html.fromHtml("");
            }

        }
        return result;
    }


    public static Boolean isNotEmpty(String string) {
        if (string != null && !string.isEmpty() && string.trim().length() != 0)
            return true;
        return false;
    }

    private void callNewsListService()
    {
        WebServices<GetNewsList> newsListWebServices = new WebServices<>(NewsListActivity.this);
        newsListWebServices.getNewsList(WebServices.PublicDevService, WebServices.ApiType.getNewsList,"en");
    }


}
