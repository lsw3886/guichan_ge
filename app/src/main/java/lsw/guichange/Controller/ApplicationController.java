package lsw.guichange.Controller;

import android.app.Application;
import android.app.admin.NetworkEvent;
import android.util.Log;

import java.util.ArrayList;

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
    public ArrayList<Category> categories;
    ArrayList<RecentBulletin> choiced_bulletins;

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

    @Override
    public void onCreate(){
        super.onCreate();
        ApplicationController.instance = this;
        categories = Makecategories();
        this.choiced_bulletins = new ArrayList<>();
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


}
