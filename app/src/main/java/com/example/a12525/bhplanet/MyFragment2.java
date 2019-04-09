package com.example.a12525.bhplanet;



import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyFragment2 extends Fragment implements View.OnClickListener{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_shequ, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = (Button)getActivity().findViewById(R.id.button);
        Button button2 = (Button)getActivity().findViewById(R.id.button2);
        Button button3 = (Button)getActivity().findViewById(R.id.button3);
        Button button4 = (Button)getActivity().findViewById(R.id.button4);
        Button button5 = (Button)getActivity().findViewById(R.id.button5);
        Button button6 = (Button)getActivity().findViewById(R.id.button6);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        replaceFragment(new WodeBankuaiFragment());
    }
    @Override
    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.button:
                replaceFragment(new WodeBankuaiFragment());
                break;
            case R.id.button2:
                replaceFragment(new ManhuaFragment());
                break;
            case R.id.button3:
                replaceFragment(new DoutuFragment());
                break;
            case R.id.button4:
                replaceFragment(new YuanchuangFragment());
                break;
            case R.id.button5:
                replaceFragment(new YingshiFragment());
                break;
            case R.id.button6:
                replaceFragment(new ZizhiFragment());
                break;
            default:
                break;
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.community_board_content, fragment);
        transaction.commit();
    }

    /*private NavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new NavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_board_comic:

                    return true;
                case R.id.navigation_board_doutu:
            }
            return false;
        }
    };*/
}

