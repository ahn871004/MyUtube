package kr.co.ajsoft.myutube.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import kr.co.ajsoft.myutube.Adapter.ItemAdapter;
import kr.co.ajsoft.myutube.ItemActivity;
import kr.co.ajsoft.myutube.Model.Item;
import kr.co.ajsoft.myutube.R;


public class KpopFragment extends Fragment {

    private static final String TAG_PARSE = "TAG_PARSE";

    RecyclerView recyclerView;

    ItemAdapter adapter;
    ArrayList<Item> items=new ArrayList<>();

    SwipeRefreshLayout refreshLayout;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_kpop, container, false);

        recyclerView=view.findViewById(R.id.recycler_kpop);

        adapter=new ItemAdapter(items,getContext());

        recyclerView.setAdapter(adapter);


        //리사이클러의 배치관리자 설정
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        //대량의 데이터 추가 작업



        items.clear();
        readRss();
        readRss1();
        readRss2();

        refreshLayout=view.findViewById(R.id.layout_swipe);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                items.clear();
                adapter.notifyDataSetChanged();
                readRss();
                readRss1();
                readRss2();

            }
        });


        return view;
    }
    private void readRss2() {
        try {
            URL url=new URL("https://www.youtube.com/feeds/videos.xml?channel_id=UChY4PXxJI0aJYsGT1X6INWA");

            //스트림 연결하여 데이터 읽어오기 : 인터넷 작업은 반드시 퍼미션 작성해야함
            //Network작업은 반드시 별도의 Thread만 할 수 있음.
            //별도의 Thread객체 생성
            RssFeedTask task=new RssFeedTask();
            task.execute(url); //doInBackground() 메소드 발동(Thread의 start()와 같은 역할)
            //배열 0 보내는 위치

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    private void readRss1() {
        try {
            URL url=new URL("https://www.youtube.com/feeds/videos.xml?channel_id=UCMyB-XGM7dcovuPoKeunAMQ");

            //스트림 연결하여 데이터 읽어오기 : 인터넷 작업은 반드시 퍼미션 작성해야함
            //Network작업은 반드시 별도의 Thread만 할 수 있음.
            //별도의 Thread객체 생성
            RssFeedTask task=new RssFeedTask();
            task.execute(url); //doInBackground() 메소드 발동(Thread의 start()와 같은 역할)
            //배열 0 보내는 위치

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

    private void readRss() {

        try {
            URL url=new URL("https://www.youtube.com/feeds/videos.xml?channel_id=UCtm_QoN2SIxwCE-59shX7Qg");

            //스트림 연결하여 데이터 읽어오기 : 인터넷 작업은 반드시 퍼미션 작성해야함
            //Network작업은 반드시 별도의 Thread만 할 수 있음.
            //별도의 Thread객체 생성
            RssFeedTask task=new RssFeedTask();
            task.execute(url); //doInBackground() 메소드 발동(Thread의 start()와 같은 역할)
            //배열 0 보내는 위치

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }


    class RssFeedTask extends AsyncTask<URL,Void,String> {


        //Thread의 run()메소드 같은 역할
        @Override
        protected String doInBackground(URL... urls) {

            URL url=urls[0];

            try {
                InputStream is=url.openStream();

                XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                XmlPullParser xpp=factory.newPullParser();

                //utf-8은 한글도 읽어오기 위한 인코딩 방식
                xpp.setInput(is,"utf-8");

                int eventType=xpp.getEventType();
                Item item=null;
                String tagName=null;
                String tagText=null;

                while (eventType!=XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:

                            break;

                        case XmlPullParser.START_TAG:

                        tagName=xpp.getName();
                            Log.i(TAG_PARSE, "START_TAG : " + xpp.getName());


//                            int count = xpp.getAttributeCount();
//                            Log.i(TAG_PARSE, "getAttributeCount() : " + count);
//
//                            for(int i = 0; i < count; i++){
//                                Log.i(TAG_PARSE, i + " getAttributeName() : " + xpp.getAttributeName(i));
//                                Log.i(TAG_PARSE, i + " getAttributeValue() : " + xpp.getAttributeValue(i));
//                            }
//                            break;


                        if(tagName.equals("entry")){
                            item=new Item();
                        }else if(tagName.equals("yt:videoId")){
                            xpp.next();
                            if(item!=null) item.setId(xpp.getText());
                        }else if(tagName.equals("title")) {
                            xpp.next();
                            if (item != null) item.setTitle(xpp.getText());
                        }else if(tagName.equals("name")){
                                xpp.next();
                                if (item != null) item.setPublisher(xpp.getText());
                        }else if(tagName.equals("updated")){
                            xpp.next();
                            if(item!=null) item.setDate(xpp.getText());
                        }else if(tagName.equals("media:thumbnail")){
                            xpp.next();
                            if(item!=null) item.setImgUrl(xpp.getAttributeValue(0));
                           // Log.i(TAG_PARSE,  "getAttributeValue() : " + xpp.getAttributeValue(0));
                        }else if(tagName.equals("media:statistics")){
                            xpp.next();
                            if(item!=null) item.setViews(xpp.getAttributeValue(0));
                        }

                        break;



                        case XmlPullParser.TEXT:
                            //Log.i(TAG_PARSE, "TEXT : " + xpp.getText());

                            break;

                        case XmlPullParser.END_TAG:
                            tagName=xpp.getName();
                            if(tagName.equals("entry")){
                                //읽어온 기사 한개를 대량의 데이터에 추가
                                items.add(item);
                                item=null;
                                //리사이클러의 어댑터에게 데이터가
                                //변경되었다는 것을 통지(화면갱신)
                                //UI변경 작업을 하고 싶다면..
                                publishProgress();


                            }
                            break;
                    }

                    eventType=xpp.next();


                }




            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }


            return "파싱종료";
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            adapter.notifyItemInserted(items.size());
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);




            //adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);



            //Toast.makeText(getContext(), s+""+items.size(), Toast.LENGTH_SHORT).show();

        }
    }
}
