package com.example.a12525.bhplanet;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends Activity {
    private String[] strings = { "一人之下", "火影忍者", "熊猫头", "武林外传" };
    private String[] popularSearch = { "五等分的花嫁", "辉夜大小姐想让我告白", "王思聪热狗", "刃牙", "漫威"};
    private SearchView searchView;
    private ListView listView;
    private GridView gridView;
    private TextView popular_text;
    private String searchText;
    private List<String>dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent intent = getIntent();
        String source = intent.getStringExtra("source");

        if(source.equals("shouye")){
            Log.d("shouye", "shouye");
        }
        searchView = (SearchView)findViewById(R.id.search_view);
        listView = (ListView)findViewById(R.id.search_history_list);
        gridView = (GridView)findViewById(R.id.popular_search_list);
        popular_text = (TextView)findViewById(R.id.popular_search);
        dataList = new ArrayList<>();
        for (String aPopularSearch : popularSearch) {
            dataList.add(aPopularSearch);
        }
        SearchAdapter searchAdapter = new SearchAdapter(dataList, this);
        if(source.equals("shouye")){
            gridView.setAdapter(searchAdapter);
            popular_text.setText("热门搜索");
        } else if (source.equals("shequ")) {
            popular_text.setText("");
        }

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strings));
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchText = listView.getItemAtPosition(position).toString();
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    listView.setFilterText(newText);
                }else{
                    listView.clearTextFilter();
                }
                return false;
            }
        });
    }

    public class SearchAdapter extends BaseAdapter{
        private List<String>data;
        private Context context;

        SearchAdapter(List<String>data, Context context){
            this.data = data;
            this.context = context;
        }
        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;
            if (convertView == null) {
                view = LayoutInflater.from(context).inflate(R.layout.popular_search_view, null);
                holder = new ViewHolder();
                holder.textView = (TextView)view.findViewById(R.id.popular_search_text);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder)view.getTag();
            }
            holder.textView.setText(data.get(position));
            return view;
        }

        class ViewHolder {
            TextView textView;
        }

    }
}
