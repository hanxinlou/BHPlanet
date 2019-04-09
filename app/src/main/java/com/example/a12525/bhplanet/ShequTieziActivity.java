package com.example.a12525.bhplanet;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class ShequTieziActivity extends AppCompatActivity{
    private List<Posts>postsList = new ArrayList<>();
    private Toolbar toolbar;
    private Button button;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shequ_bankuai_neirong);

        //toolbar = (Toolbar)findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        initPosts();
        TieziAdapter adapter = new TieziAdapter(ShequTieziActivity.this, R.layout.shequ_tiezi, postsList);
        ListView listView = (ListView)findViewById(R.id.posts_list_view);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Posts posts = postsList.get(position);
                Toast.makeText(ShequTieziActivity.this, posts.getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ShequTieziActivity.this, TieziNeirongActivity.class);
                startActivity(intent);
            }
        });

        button = (Button)findViewById(R.id.new_post_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ShequTieziActivity.this, FatieActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initPosts(){
        Posts post1 = new Posts("一个版块", "这是标题", "这是用户名",
                10, 3, 7, R.drawable.img1);
        Posts post2 = new Posts("另一个版块", "这是另一个标题", "这也是用户名",
                11, 4, 8, R.drawable.img2);
        postsList.add(post1);
        postsList.add(post2);
    }


//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//
//        menu.findItem(R.id.new_posts).setVisible(true);
//        menu.findItem(R.id.search).setVisible(false);
//        return super.onPrepareOptionsMenu(menu);
//    }
}
