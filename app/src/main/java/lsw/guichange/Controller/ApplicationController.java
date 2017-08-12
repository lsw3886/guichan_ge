package lsw.guichange.Controller;

import android.app.Application;
import android.app.admin.NetworkEvent;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

import lsw.guichange.Item.Bulletin;
import lsw.guichange.Item.Category;
import lsw.guichange.Item.RecentBulletin;
import lsw.guichange.R;
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
    ArrayList<RecentBulletin> choiced_bulletins;

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

    }



    public ArrayList<RecentBulletin> getChoiced_bulletins() {
        return choiced_bulletins;
    }

    public void setChoiced_bulletins(RecentBulletin choiced_bulletin) {
        this.choiced_bulletins.add(choiced_bulletin);
    }
    public void deleteChoiced_bulletins(RecentBulletin choiced_bulletin){
        this.choiced_bulletins.remove(choiced_bulletins);
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


}
