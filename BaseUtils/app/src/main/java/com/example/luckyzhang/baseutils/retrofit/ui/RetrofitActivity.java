package com.example.luckyzhang.baseutils.retrofit.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.retrofit.bean.Book;
import com.example.luckyzhang.baseutils.retrofit.bean.BookLists;
import com.example.luckyzhang.baseutils.retrofit.utils.FileMutifyUtils;
import com.example.luckyzhang.baseutils.retrofit.utils.InterService;
import com.example.luckyzhang.baseutils.retrofit.utils.RetrofitUtils;
import com.example.luckyzhang.baseutils.tools.LogUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetrofitActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    String[] datas = {"get请求同步", "get异步请求", "map请求", "path请求", "测试的上传,暂时不可用"};
    private InterService api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        listView = (ListView) findViewById(R.id.activity_retrofit);
        ArrayAdapter arrayAdapter = new ArrayAdapter(RetrofitActivity.this, R.layout.item_listview, datas);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
        api = RetrofitUtils.createApi(RetrofitActivity.this, InterService.class);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                execute();
                break;
            case 1:
                enqueue();
                break;
            case 2:
                queryMap();
                break;
            case 3:
                pathQuery();
                break;
            case 4:
                multipart();
                break;

        }

    }

    private void pathQuery() {
        final Call<Book> pathBooks = api.getPathBooks("1003078");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Book body = pathBooks.execute().body();
                    List<Book.TagsBean> books = body.getTags();
                    for (int i = 0; i < books.size(); i++) {
                        LogUtils.d("body:" + books.get(i).toString());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void queryMap() {
        Map<String, String> optionMaps = new HashMap<>();
        optionMaps.put("q", "小王子");
        optionMaps.put("tag", "");
        optionMaps.put("start", "0");
        optionMaps.put("count", "2");
        final Call<BookLists> queryMapBooks = api.getQueryMapBooks(optionMaps);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BookLists body = queryMapBooks.execute().body();
                    List<BookLists.BooksBean> books = body.getBooks();
                    for (int i = 0; i < books.size(); i++) {
                        LogUtils.d("body:" + books.get(i).toString());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    /**
     * 同步请求
     */
    private void execute() {
        final Call<BookLists> call = api.getSeachBooks("小王子", "", 0, 2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    BookLists body = call.execute().body();
                    List<BookLists.BooksBean> books = body.getBooks();
                    for (int i = 0; i < books.size(); i++) {
                        LogUtils.d("body:" + books.get(i).toString());

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    /**
     * 异步请求
     */
    private void enqueue() {
        final Call<BookLists> call2 = api.getSeachBooks("小王子", "", 0, 2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                call2.enqueue(new Callback<BookLists>() {
                    @Override
                    public void onResponse(Call<BookLists> call, Response<BookLists> response) {
                        BookLists body = response.body();
                        List<BookLists.BooksBean> books = body.getBooks();
                        for (int i = 0; i < books.size(); i++) {
                            LogUtils.d("body:" + books.get(i).toString());

                        }
                    }

                    @Override
                    public void onFailure(Call<BookLists> call, Throwable t) {

                    }
                });

            }
        }).start();
    }

    /**
     * 上传的内容
     */
    private void multipart() {
        // // FIXME: 2017/5/8 测试的代码,不能允许的
        Uri file1Uri = Uri.parse("");
        Uri file2Uri = Uri.parse("");
        //上传的文件内容
        MultipartBody.Part body1 = FileMutifyUtils.prepareFilePart(RetrofitActivity.this, "video", file1Uri);
        MultipartBody.Part body2 = FileMutifyUtils.prepareFilePart(RetrofitActivity.this, "thumb", file2Uri);
        //表述信息
        RequestBody partFromString = FileMutifyUtils.createPartFromString("表述信息");
        //测试的上传的请求
//        Call<ResponseBody> responseBodyCall = api.uploadMutipleFiles(partFromString, body1, body2);

    }

}
