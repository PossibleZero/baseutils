package com.example.luckyzhang.baseutils.frame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luckyzhang.baseutils.R;

/**
 * Created by luckyzhang on 2017/5/17.
 */
public class PublishFragment extends Fragment{

    View inflate;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.fragment_tab, container, false);
        return inflate;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        TextView textView = (TextView) inflate.findViewById(R.id.tab_class);
        textView.setText("PublishFragment");
    }
}
