package com.example.a12525.bhplanet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.a12525.bhplanet.R;

import java.util.ArrayList;
import java.util.List;


public class fensi extends AppCompatActivity {
    private List<Fruit> fruitList=new ArrayList<>();

     @Override
     protected void onCreate(Bundle savedInstanceState) {
                 super.onCreate(savedInstanceState);
                 setContentView(R.layout.activity_fensi);
                 ImageButton fanhui=(ImageButton)findViewById(R.id.fanhui);
                 fanhui.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 finish();
             }
         });
                 initFruits();                 //初始化水果数据
                 FruitAdapter adapter=new FruitAdapter(fensi.this,R.layout.fensi_item,fruitList);

         //      ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);
                ListView listview = (ListView) findViewById(R.id.list_view);
                 listview.setAdapter(adapter);
            }

             private void initFruits(){
                 for(int i=0;i<2;i++){
                         Fruit apple=new Fruit("小老鼠",R.drawable.head);
                         fruitList.add(apple);
                         Fruit orange=new Fruit("大老鼠",R.drawable.head1);
                         fruitList.add(orange);
                         Fruit banana=new Fruit("小精灵",R.drawable.head2);
                         fruitList.add(banana);
                         Fruit waterlenmo=new Fruit("大精灵",R.drawable.head3);
                         fruitList.add(waterlenmo);
                         Fruit pear=new Fruit("Pear",R.drawable.head4);
                         fruitList.add(pear);
                         Fruit grape=new Fruit("Grape",R.drawable.head5);
                       fruitList.add(grape);
                        Fruit pineapple=new Fruit("Pineapple",R.drawable.head6);
                        fruitList.add(pineapple);
                         Fruit strawberry=new Fruit("Strawberry",R.drawable.head7);
                         fruitList.add(strawberry);
                         Fruit cherry=new Fruit("Cherry",R.drawable.head8);
                         fruitList.add(cherry);
                         Fruit mango=new Fruit("mango",R.drawable.head9);
                         fruitList.add(mango);
                     }
             }

}
