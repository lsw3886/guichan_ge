package lsw.guichange.Activity;

import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import lsw.guichange.Adapter.RecyclerViewAdapter.PostAdapter;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Controller.NetworkService;
import lsw.guichange.DB.DBHelper;
import lsw.guichange.Item.Post;
import lsw.guichange.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    ApplicationController application;
    NetworkService networkService;
    ArrayList<Post> Bulletin_posts;
    RecyclerView recyclerView;
    PostAdapter adapter;
    String bulletinName;
    int bulletinImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        application = ApplicationController.getInstance();
        application.buildNetworkService("c3d6c7b2.ngrok.io");
        networkService = application.getNetworkService();
        Bulletin_posts = new ArrayList<>();
        receivePosts();
        Bundle extra = getIntent().getExtras();
        bulletinName = extra.getString("BulletinName");
        bulletinImage = extra.getInt("BulletinImage");
        TextView title_view = (TextView) findViewById(R.id.post_activity_title);
        ImageView title_imageview = (ImageView) findViewById(R.id.post_activity_title_img);
        title_view.setText(bulletinName);
        title_imageview.setImageResource(bulletinImage);

        recyclerView = (RecyclerView) findViewById(R.id.post_recyclerview);
        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        this.adapter = new PostAdapter(this, bulletinImage, bulletinName , Bulletin_posts);

        recyclerView.setAdapter(adapter);
    }

//    public void receivePosts(){
//
//        Call<List<Post>> versionCall = networkService.get_post();
//        versionCall.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if(response.isSuccessful()) {
//                    List<Post> posts = response.body();
//
//                    for(Post x : posts){
//                        Bulletin_posts.add(x);
//                    }
//                    adapter.notifyDataSetChanged();
//                    //adapter.setPosts(Bulletin_posts);
//                } else {
//                    int StatusCode = response.code();
//                    Log.i(ApplicationController.TAG, "Status Code : " + StatusCode);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Post>> call, Throwable t) {
//                Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
//            }
//        });
//    }
    public void receivePosts(){

        Call<List<Post>> versionCall = networkService.get_post();
        versionCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(response.isSuccessful()) {
                    List<Post> posts = response.body();

                    for(Post x : posts){
                        application.addPosts(bulletinName, bulletinImage, x);
                    }
                    adapter.setPosts(application.getPosts(bulletinName));
                    adapter.notifyDataSetChanged();
                } else {
                    int StatusCode = response.code();
                    adapter.setPosts(application.getPosts(bulletinName));
                    adapter.notifyDataSetChanged();
                    Log.i(ApplicationController.TAG, "Status Code : " + StatusCode);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                Log.i(ApplicationController.TAG, "Fail Message : " + t.getMessage());
            }
        });
    }



}
