package com.example.a12525.bhplanet;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class TieziAdapter extends ArrayAdapter {
    private int resourceId;
    public TieziAdapter(Context context, int resource, List<Posts> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Posts posts = (Posts)getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.boardName = (TextView)view.findViewById(R.id.community_posts_board_name);
            viewHolder.boardTitle = (TextView)view.findViewById(R.id.community_posts_title);
            viewHolder.userName = (TextView)view.findViewById(R.id.community_posts_user_name);
            viewHolder.zanNum = (TextView)view.findViewById(R.id.community_posts_zan_num);
            viewHolder.shareNum = (TextView)view.findViewById(R.id.community_posts_share_num);
            viewHolder.commentNum = (TextView)view.findViewById(R.id.community_posts_comment_num);
            viewHolder.boardImg = (ImageView)view.findViewById(R.id.community_posts_board_img);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.boardName.setText(posts.getBoardName());
        viewHolder.boardTitle.setText(posts.getTitle());
        viewHolder.userName.setText(posts.getUserName());
        viewHolder.zanNum.setText(String.valueOf(posts.getZanNum()));
        viewHolder.shareNum.setText(String.valueOf(posts.getShareNum()));
        viewHolder.commentNum.setText(String.valueOf(posts.getCommentNum()));
        viewHolder.boardImg.setImageResource(posts.getBoardImgId());
        return view;
    }
    class ViewHolder{
        TextView boardName;
        TextView boardTitle;
        TextView userName;
        TextView zanNum;
        TextView shareNum;
        TextView commentNum;
        ImageView boardImg;
    }
}
