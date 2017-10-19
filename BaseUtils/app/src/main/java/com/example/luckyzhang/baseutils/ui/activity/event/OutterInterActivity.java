package com.example.luckyzhang.baseutils.ui.activity.event;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.widget.LinearScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class OutterInterActivity extends AppCompatActivity {

    //    @BindView(R.id.linearScroll)
    LinearScrollView linearScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outter_inter);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        linearScroll = (LinearScrollView) findViewById(R.id.linearScroll);
        LayoutInflater layoutInflater = getLayoutInflater();
        int widthPixels = getScreenMetrics(this).widthPixels;
        int heightPixels = getScreenMetrics(this).heightPixels;
        for (int i = 0; i < 4; i++) {
            View childView = layoutInflater.inflate(R.layout.item_widget_lienar, linearScroll, false);
            TextView textView = (TextView) childView.findViewById(R.id.scroll_text);
            textView.setText("第"+i+"个");
            textView.setBackgroundColor(Color.rgb(255/(i+1),255/(i+1),255/(i+1)));
            ListView listView = (ListView) childView.findViewById(R.id.scroll_list);
            childView.setBackgroundColor(getResources().getColor(R.color.color_333333));
            childView.getLayoutParams().width = widthPixels;
            childView.getLayoutParams().height = heightPixels;
            createList(listView);
            linearScroll.addView(childView);

        }
    }

    private void createList(ListView listView) {
        List datas = new ArrayList();
        for (int i = 0; i < 20; i++) {
            datas.add("数据:" + i + "个");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_type_one, R.id.name, datas);
        listView.setAdapter(adapter);
    }

    public DisplayMetrics getScreenMetrics(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }
}
