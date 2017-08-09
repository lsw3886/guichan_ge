package lsw.guichange.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import lsw.guichange.Adapter.RecyclerViewAdapter.ListAdapter;
import lsw.guichange.Adapter.RecyclerViewAdapter.ListInListAdapter;
import lsw.guichange.Item.Bulletin;
import lsw.guichange.R;

public class CategoryActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ListInListAdapter adapter;
    ArrayList<Bulletin> bulletins;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        Bundle extra = getIntent().getExtras();
        String s = extra.getString("category");
        TextView title_view = (TextView) findViewById(R.id.category_activity_title);
        title_view.setText(s);

        recyclerView = (RecyclerView) findViewById(R.id.category_listinlist_recyclerview);

        LinearLayoutManager lm = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);

        bulletins = Makebulletins(s);
        this.adapter = new ListInListAdapter(this, bulletins);

        recyclerView.setAdapter(adapter);



    }

    public ArrayList<Bulletin> Makebulletins(String category){
        ArrayList<Bulletin> bulletins = new ArrayList<>();
        Bulletin default_bulletin = new Bulletin(R.drawable.ic_splash_guichan, category , "귀찮게");

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
                bulletins.add(default_bulletin);
                return bulletins;



        }




    }
}
