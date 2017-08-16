package lsw.guichange.Item;

/**
 * Created by lsw38 on 2017-08-09.
 */

public class Post {
    public String site_name;
    public String link;
    public String comment;
    public int post_num = 0;
    public String title;
    public String date;
    public int BulletinImg;
    public String BulletinTitle;

    public Post() {
    }


    public Post(String link, String comment, int post_num, String date, String title, int bulletinImg, String bulletinTitle) {
        this.link = link;
        this.comment = comment;
        this.post_num = post_num;
        this.title = title;
        BulletinImg = bulletinImg;
        BulletinTitle = bulletinTitle;
        this.date = date;

    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBulletinImg() {
        return BulletinImg;
    }

    public void setBulletinImg(int bulletinImg) {
        BulletinImg = bulletinImg;
    }

    public String getBulletinTitle() {
        return BulletinTitle;
    }

    public void setBulletinTitle(String bulletinTitle) {
        BulletinTitle = bulletinTitle;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPost_num() {
        return post_num;
    }

    public void setPost_num(int post_num) {
        this.post_num = post_num;
    }
}
