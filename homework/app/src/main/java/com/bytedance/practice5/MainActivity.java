package com.bytedance.practice5;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.bytedance.practice5.model.Message;
import com.bytedance.practice5.model.SocketBean;
import com.bytedance.practice5.socket.SocketActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "chapter5";
    private FeedAdapter adapter = new FeedAdapter();

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        findViewById(R.id.btn_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UploadActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_mine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(Constants.STUDENT_ID);
            }
        });

        findViewById(R.id.btn_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(null);
            }
        });
        findViewById(R.id.btn_socket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SocketActivity.class);
                startActivity(intent);
            }
        });



    }

    //TODO 2
    // 用HttpUrlConnection实现获取留言列表数据，用Gson解析数据，更新UI（调用adapter.setData()方法）
    // 注意网络请求和UI更新分别应该放在哪个线程中

    public List<Message> getRequest(String studentId){
        String urlstring = new String();
        if(studentId!=null){
        urlstring = String.format("https://api-sjtu-camp-2021.bytedance.com/homework/invoke/messages?student_id=%s&extra_value=}",studentId);}
        else {
            urlstring = "https://api-sjtu-camp-2021.bytedance.com/homework/invoke/messages";
        }
        List<Message> result = null;
                try{
                    URL url = new URL(urlstring);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(6000);
                    conn.setReadTimeout(6000);
                    //conn.addRequestProperty("token",Constants.token);
                    conn.connect();

                    if(conn.getResponseCode() == 200) {
                        InputStream in = conn.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                        //reader.toString();
                       String s = reader.readLine();
                     SocketBean socketBean = new Gson().fromJson(s,SocketBean.class);
                     result = socketBean.getFeeds();
                        reader.close();
                        in.close();
                    }
                    conn.disconnect();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return result;
    }

    private void getData(String studentId){
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Message> result = getRequest(studentId);
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(result);
                    }
                });
            }
        }).start();
    }


    public String getString(InputStream is) {

        BufferedReader bufferedReader = null;
        String line = "";
        StringBuilder stringBuilder = new StringBuilder();
        String response = "";
        try {
            bufferedReader = new BufferedReader(new
                    InputStreamReader(is, "utf-8"));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        response = stringBuilder.toString().trim();
        return response;
    }

}