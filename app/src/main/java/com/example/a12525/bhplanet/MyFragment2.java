package com.example.a12525.bhplanet;



import android.app.Activity;
import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment2 extends Fragment {

    public MyFragment2() {
    }
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fg_content,container,false);
        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText("第二个Fragment");
        Log.e("HEHE", "2日狗");
        return view;
    }
}
