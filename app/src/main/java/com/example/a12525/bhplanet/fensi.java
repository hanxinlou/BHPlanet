package com.example.a12525.bhplanet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
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
                         Fruit orange=new Fruit("大老鼠",R.drawable.head);
                         fruitList.add(orange);
                         Fruit banana=new Fruit("小精灵",R.drawable.head);
                         fruitList.add(banana);
                         Fruit waterlenmo=new Fruit("大精灵",R.drawable.head);
                         fruitList.add(waterlenmo);
                         Fruit pear=new Fruit("Pear",R.drawable.head);
                         fruitList.add(pear);
                         Fruit grape=new Fruit("Grape",R.drawable.head);
                       fruitList.add(grape);
                        Fruit pineapple=new Fruit("Pineapple",R.drawable.head);
                        fruitList.add(pineapple);
                         Fruit strawberry=new Fruit("Strawberry",R.drawable.head);
                         fruitList.add(strawberry);
                         Fruit cherry=new Fruit("Cherry",R.drawable.head);
                         fruitList.add(cherry);
                         Fruit mango=new Fruit("mango",R.drawable.head);
                         fruitList.add(mango);
                     }
             }

}
