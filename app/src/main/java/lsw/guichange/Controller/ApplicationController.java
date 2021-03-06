package lsw.guichange.Controller;

import android.app.Application;
import android.app.admin.NetworkEvent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lsw.guichange.DB.DBHelper;
import lsw.guichange.Item.Bulletin;
import lsw.guichange.Item.Category;
import lsw.guichange.Item.Post;
import lsw.guichange.Item.RecentBulletin;
import lsw.guichange.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lsw38 on 2017-08-10.
 */

public class ApplicationController extends Application {
    public final static String TAG = "LSW";
    private static ApplicationController instance;
    public ArrayList<Bulletin> job_bulletins;
    public ArrayList<Bulletin> exam_bulletins;
    public ArrayList<Bulletin> community_bulletins;
    public ArrayList<Bulletin> shopping_bulletins;
    public ArrayList<Category> categories;
    public ArrayList<Post> Bookmarks;
    ArrayList<RecentBulletin> choiced_bulletins;
    DBHelper dbHelper;

    @Override
    public void onCreate(){
        super.onCreate();
        ApplicationController.instance = this;
        this.exam_bulletins = Makebulletins("시험");
        this.job_bulletins = Makebulletins("취업");
        this.shopping_bulletins = Makebulletins("쇼핑");
        this.community_bulletins = Makebulletins("커뮤니티");
        categories = Makecategories();
        this.choiced_bulletins = new ArrayList<>();
        this.Bookmarks = new ArrayList<>();
        dbHelper = new DBHelper(this);
        choicedBulletin_init();
        BookmarkInit();




    }

    public void BookmarkInit(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Post post;
        Cursor rs = db.rawQuery("select * from Bookmark;", null);
        rs.moveToFirst();
        while(rs.moveToNext()){
            String sitename = rs.getString(0);
            String link = rs.getString(1);
            String comment = rs.getString(2);
//            int postnum = rs.getInt(3);
            String title = rs.getString(3);
            String date = rs.getString(4);
            int bimg = rs.getInt(5);
            String btitle = rs.getString(6);
            post = new Post(link, comment, 0 , title,date, bimg, btitle);
            Bookmarks.add(post);

        }
        db.close();
    }


    public void addBookmark(String bulletinName, int bulletinImg, String link, String comment, int post_num, String date, String posttitle){
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        int bimg = bulletinImg;
        String bName = bulletinName;
        Post bpost = new Post(link , comment ,post_num,date,posttitle,bulletinImg,bulletinName);
        String query = "insert into Bookmark values(null"+", " + "\"" + link+ "\""+", "+"\""+comment+"\"" +", " +"\""+ posttitle+"\"" +", "+"\""+ date+"\""+ " , "+ bulletinImg+", "+ "\""+ bulletinName+"\""+");";
        db.execSQL(query);
        Bookmarks.add(bpost);


    }

    public ArrayList<Post> getPosts(String BulletinName){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Post post;
        ArrayList<Post> posts = new ArrayList<>();
        Cursor rs = db.rawQuery("select * from " +  BulletinName + ";", null);
        rs.moveToFirst();
        while(rs.moveToNext()){
            String sitename = rs.getString(0);
            String link = rs.getString(1);
            String comment = rs.getString(2);
            int postnum = rs.getInt(3);
            String title = rs.getString(4);
            String date = rs.getString(5);
            int bimg = rs.getInt(6);
            String btitle = rs.getString(7);
            post = new Post(link, comment, postnum, date, title, bimg, btitle);
            posts.add(post);

        }
        return posts;
    }

    public void addPosts(String bulletinName, int bulletinImg, Post post){
//        SQLiteDatabase db2 = dbHelper.getReadableDatabase();
//        String findQuery = "select max(postnum) from " + bulletinName+";";
//        Cursor rs = db2.rawQuery(findQuery, null);
//        rs.moveToFirst();
//        int lastPostNum = rs.getInt(0);
//        if (post.getPost_num() > lastPostNum){
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            String link = post.getLink();
            String comment = post.getComment();
            int post_num = post.getPost_num();
            String posttitle = post.getTitle();
            String date = post.getDate();
            int bimg = bulletinImg;
            String bName = bulletinName;
            String query = "insert into "+bulletinName+" values(null"+", " + "\"" + link + "\""+", "+"\""+ comment +"\", " +post_num+" ,"+"\""+ posttitle+"\", "+"\"" +date+ "\" , "+ bimg+", "+ "\""+ bName+"\""+");";
            db.execSQL(query);
//        }


    }

    public void removeBookmark(Post post){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String link = post.getLink();
        String query;
        query = "delete from Bookmark where link = " + "\"" + link + "\""+ ";";
        db.execSQL(query);
        Bookmarks.remove(post);

    };

    public void choicedBulletin_init(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        RecentBulletin recentBulletin;
        Cursor rs = db.rawQuery("select * from RecentBulletin;", null);
        while(rs.moveToNext()){
            int img = rs.getInt(0);
            String category = rs.getString(1);
            String name = rs.getString(2);
            recentBulletin = new RecentBulletin(img, category, name);
            choiced_bulletins.add(recentBulletin);

        }
    }



    public ArrayList<RecentBulletin> getChoiced_bulletins() {
        return choiced_bulletins;
    }

    public void setChoiced_bulletins(RecentBulletin choiced_bulletin) {
        this.choiced_bulletins.add(choiced_bulletin);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into RecentBulletin values("+choiced_bulletin.getBulletin_Img()+", " + "\"" + choiced_bulletin.getCategory()+ "\""+", "+"\""+ choiced_bulletin.getBulletin_Name()+"\"" +");");

    }

    public ArrayList<Post> getBookmarks() {
        return Bookmarks;
    }

    public void setBookmarks(ArrayList<Post> bookmarks) {
        Bookmarks = bookmarks;
    }


    public void deleteChoiced_bulletins(String s){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query;
        String postquery;
        for(int x = 0; x < choiced_bulletins.size(); x++){
            if(choiced_bulletins.get(x).getBulletin_Name().equals(s)){
                query = "delete from RecentBulletin where name = " + "\"" + s + "\""+ ";";
                db.execSQL(query);
                postquery = "drop table "+ s + ";";
                db.execSQL(postquery);
                choiced_bulletins.remove(x);
            }
        }
    }
    public boolean isBulletinInBookmark(String s){
        for(int x = 0; x < Bookmarks.size(); x++){
            if(Bookmarks.get(x).getTitle().equals(s)){
                return true;
            }

        }
        return false;
    }
    public boolean isBulletinInChoicedBulletins(String s){
        for(int x = 0; x < choiced_bulletins.size(); x++){
            if(choiced_bulletins.get(x).getBulletin_Name().equals(s)){
                return true;
            }

        }
        return false;
    }

    public static ApplicationController getInstance(){
        return instance;

    }

    private NetworkService networkService;
    public NetworkService getNetworkService(){
        return networkService;
    }
    private String baseUrl;
    public void buildNetworkService(String ip, int port){
        synchronized (ApplicationController.class){
            if (networkService == null){
                baseUrl = String.format("http://%s:%d/", ip, port);
                Log.i(TAG, baseUrl);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                networkService = retrofit.create(NetworkService.class);
            }
        }
    }

    public void buildNetworkService(String ip){
        synchronized (ApplicationController.class){
            if (networkService == null){
                baseUrl = String.format("http://%s/", ip);
                Log.i(TAG, baseUrl);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                networkService = retrofit.create(NetworkService.class);
            }
        }
    }


    public ArrayList<Category> Makecategories(){

        Category trade = new Category("쇼핑", R.drawable.ic_bulletinlist_cart);
        Category exam = new Category("시험", R.drawable.ic_bulletinlist_exam);
        Category job = new Category("취업", R.drawable.ic_bulletinlist_bag);
        Category community = new Category("커뮤니티", R.drawable.ic_bulletinlist_com);

        ArrayList<Category> categories = new ArrayList<>();
        categories.add(trade);
        categories.add(exam);
        categories.add(job);
        categories.add(community);

        return categories;


    }
    public void removeRecentBulletins(Bulletin bulletin){

        for(int x = 0; x < choiced_bulletins.size(); x++){
            if(choiced_bulletins.get(x).getBulletin_Name().equals(bulletin.getBulletin_Name())){
                choiced_bulletins.remove(x);
            }
        }

    }
    public ArrayList<Bulletin> setBulletins(String category) {
        Bulletin default_bulletin = new Bulletin(R.drawable.ic_splash_guichan, category, "귀찮게");
        ArrayList<Bulletin> default_list = new ArrayList<>();

        switch (category) {
            case "쇼핑":

                return this.shopping_bulletins;

            case "시험":


                return this.exam_bulletins;

            case "취업":

                return this.job_bulletins;

            case "커뮤니티":

                return this.community_bulletins;

            default:
                default_list.add(default_bulletin);
                return default_list;
        }
    }

    public ArrayList<Bulletin> Makebulletins(String category){
        ArrayList<Bulletin> bulletins = new ArrayList<>();
        Bulletin default_bulletins = new Bulletin(R.drawable.ic_splash_guichan, category , "귀찮게");

        switch (category){
            case "쇼핑":
                Bulletin ppompu = new Bulletin(R.drawable.ic_bulletinlist_ppomppu, category, "뽐게");
                Bulletin joong_go = new Bulletin(R.drawable.ic_bulletinlist_boost, category, "중고나라");


                bulletins.add(ppompu);
                bulletins.add(joong_go);

                return bulletins;

            case "시험":
                Bulletin hangsi = new Bulletin(R.drawable.ic_bulletinlist_ppomppu, category, "행시사랑");
                Bulletin orbi = new Bulletin(R.drawable.ic_bulletinlist_boost, category, "오르비");


                bulletins.add(hangsi);
                bulletins.add(orbi);

                return bulletins;


            case "취업":
                Bulletin specup = new Bulletin(R.drawable.ic_bulletinlist_ppomppu, category, "스펙업");
                Bulletin chuicollege = new Bulletin(R.drawable.ic_bulletinlist_boost, category, "취업대학교");


                bulletins.add(specup);
                bulletins.add(chuicollege);
                return bulletins;

            case "커뮤니티":
                Bulletin ppompu_free = new Bulletin(R.drawable.ic_bulletinlist_ppomppu,"커뮤니티", "뽐뿌자게");
                Bulletin boost = new Bulletin(R.drawable.ic_bulletinlist_boost, "커뮤니티", "부스트캠프");


                bulletins.add(ppompu_free);
                bulletins.add(boost);

                return bulletins;

            default:
                bulletins.add(default_bulletins);
                return bulletins;



        }




    }

    public void makePostDB(String s){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String query = "CREATE TABLE "+ s + "(sitename TEXT, link TEXT, comment TEXT, postnum INTEGER, title TEXT, bdate TEXT, bimg INTEGER, "+ s + " TEXT);";
        db.execSQL(query);
    }
}
