package com.mokadev.mokaidea;

import android.content.Context;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.widget.ListView;

public class IdeaSwipeRefresh extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener {
    ListView listView;

    public IdeaSwipeRefresh(Context context) {
        super(context);
        init();
    }

    public IdeaSwipeRefresh(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        this.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );

        setOnRefreshListener(this);
    }

    public void setListView(ListView listView) {
        this.listView = listView;
    }

    @Override
    public boolean canChildScrollUp() {
        return listView != null && listView.getFirstVisiblePosition() != 0;
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                setRefreshing(false);
            }
        }, 5000);
    }


}
