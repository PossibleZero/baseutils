package com.example.luckyzhang.baseutils.frame.tabactivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.luckyzhang.baseutils.R;
import com.example.luckyzhang.baseutils.frame.fragment.HomeFragment;
import com.example.luckyzhang.baseutils.frame.fragment.MessageFragment;
import com.example.luckyzhang.baseutils.frame.fragment.MyFragment;
import com.example.luckyzhang.baseutils.frame.fragment.PublishFragment;
import com.example.luckyzhang.baseutils.frame.fragment.WorkFragment;
import com.example.utils.recylerview.recyler.frame.LibFragmentTabHost;
import com.example.utils.recylerview.recyler.widget.BadgeView;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity implements View.OnClickListener {

    // 标签
    private String[] TabTag = {"tab1", "tab2", "tab3", "tab4", "tab5"};
    // 自定义tab布局显示文本和顶部的图片
    private Integer[] ImgTab = {R.layout.tab_main_home, R.layout.tab_main_work,
            R.layout.tab_main_pub, R.layout.tab_main_message,
            R.layout.tab_main_my};

    public LibFragmentTabHost mTabHost;
    private ImageView mPubView;


    // tab 选中的activity
    private Class[] ClassTab = {HomeFragment.class, WorkFragment.class,
            PublishFragment.class, MessageFragment.class,
            MyFragment.class};

    // tab选中背景 drawable 样式图片 背景都是同一个"白色"
    private Integer[] StyleTab = {R.color.white, R.color.white, R.color.white, R.color.white, R.color.white};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        setupView();
        initTabView();
    }

    private void setupView() {
        mPubView = (ImageView) findViewById(R.id.pub_tab);
        mPubView.setOnClickListener(this);
        mTabHost = (LibFragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
    }

    private BadgeView badgeView;
    private List<BadgeView> badgeViewList = new ArrayList<>();

    private void initTabView() {
        Bundle bundle = new Bundle();
        for (int i = 0; i < TabTag.length; i++) {
            // 封装的自定义的tab的样式
            View indicator = getIndicatorView(i);
            // 添加红点状态
            View mes = indicator.findViewById(R.id.tab_mes);
            badgeView = new BadgeView(this, mes);
            badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
            badgeView.setBadgeBackgroundColor(getResources().getColor(R.color.color_ee524d));
//            badgeView.setBackgroundResource(R.drawable.notification_bg);
            badgeView.setGravity(Gravity.CENTER);
            badgeView.hide();
            badgeViewList.add(badgeView);

            if (i == 2) {
                indicator.setVisibility(View.INVISIBLE);
                mTabHost.addTab(
                        mTabHost.newTabSpec(TabTag[i]).setIndicator(indicator),
                        null, bundle
                );
            } else {
                mTabHost.addTab(
                        mTabHost.newTabSpec(TabTag[i]).setIndicator(indicator),
                        ClassTab[i], bundle
                );
            }
        }
        mTabHost.getTabWidget().setDividerDrawable(R.color.white);
    }

    // 设置tab自定义样式:注意 每一个tab xml子布局的linearlayout 的id必须一样
    private View getIndicatorView(int i) {
        // 找到tabhost的子tab的布局视图
        View v = getLayoutInflater().inflate(ImgTab[i], null);
        View tv_lay = v.findViewById(R.id.layout_back);
        tv_lay.setBackgroundResource(StyleTab[i]);

        return v;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pub_tab:
                Toast.makeText(this, "弹窗", 0).show();
                break;
        }
    }
}
