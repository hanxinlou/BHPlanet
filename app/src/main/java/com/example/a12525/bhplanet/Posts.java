package com.example.a12525.bhplanet;

public class Posts {
    private String boardName;
    private String title;
    private String userName;
    private int zanNum;
    private int shareNum;
    private int commentNum;
    private int boardImgId;

    public Posts(String boardName, String title, String userName,
                 int zanNum, int shareNum, int commentNum, int boardImgId){
        this.boardName = boardName;
        this.title = title;
        this.userName = userName;
        this.zanNum = zanNum;
        this.shareNum = shareNum;
        this.commentNum = commentNum;
        this.boardImgId = boardImgId;
    }

    public String getBoardName(){
        return boardName;
    }
    public String getTitle(){
        return title;
    }
    public String getUserName(){
        return userName;
    }
    public int getZanNum(){
        return zanNum;
    }
    public int getShareNum(){
        return shareNum;
    }
    public int getCommentNum(){
        return commentNum;
    }
    public int getBoardImgId(){
        return boardImgId;
    }
}
