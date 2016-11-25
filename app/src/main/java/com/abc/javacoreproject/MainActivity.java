package com.abc.javacoreproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // khai bao cac thanh phan
    private RecyclerView mRecyclerView;
    private DemoAdapter mDemoAdapter;
    private List<String> mStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRecyclerView();
    }

    /**
     * init view, using find by Id de get view tu file xml
     */
    private void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_demo);
    }

    /**
     * method init recycler View, 1 recycler view can it nhat 2 setup:
     * 1. mot layout manager dung de set layout of list
     * 2. mot Adapter de manage list
     */
    private void initRecyclerView() {
        // tao ra mot cai layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        // set layout manager nay cho recycler view
        mRecyclerView.setLayoutManager(layoutManager);
        // tao ra mot cai Adapter
        mDemoAdapter = new DemoAdapter(getData());
        //set adapter cho recycler view
        mRecyclerView.setAdapter(mDemoAdapter);
    }

    private List<String> getData() {
        mStrings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mStrings.add("Label number " + i);
        }

        return mStrings;
    }

    // create adapter bat buoc phai ke thua tu RecyclerView.Adapter<RecyclerView.ViewHolder>
    private class DemoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<String> mStrings;

        public DemoAdapter(List<String> strings) {
            mStrings = strings;
        }

        // ham nay dung de tao ra mot ViewHolder : manager 1 item
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_demo, parent, false);
            return new DemoHolder(view);
        }

        // ham nay dung de do du lieu phai holder
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((DemoHolder) holder).setData(mStrings.get(position));
        }

        // ham set so luong item of list
        @Override
        public int getItemCount() {
            return mStrings.size();
        }

        // create view holder (ke thua tu RecyclerView.ViewHolder) dung de control item of list
        private class DemoHolder extends RecyclerView.ViewHolder {

            TextView mTextView;

            // ham contructor cua class
            public DemoHolder(View itemView) {
                super(itemView);
                mTextView = (TextView) itemView.findViewById(R.id.text_view_demo);
            }

            // set data cho item of list
            public void setData(String data) {
                mTextView.setText(data);
            }
        }
    }
}
