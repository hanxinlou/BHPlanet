package com.example.a12525.bhplanet;

public class Posts {
    private String boardName;
    private String title;
    private String userName;
    private int zanNum;
    private int commentNum;
    private String boardImgId;

    public Posts(String boardName, String title, String userName,
                 int zanNum, int commentNum, String boardImgId){
        this.boardName = boardName;
        this.title = title;
        this.userName = userName;
        this.zanNum = zanNum;
        this.commentNum = commentNum;
        this.boardImgId = boardImgId;
    }

    public String getBoardName(){ return boardName; }
    public String getTitle(){
        return title;
    }
    public String getUserName(){
        return userName;
    }
    public int getZanNum(){
        return zanNum;
    }
    public int getCommentNum(){
        return commentNum;
    }
    public String getBoardImgId(){
        return boardImgId;
    }
}
