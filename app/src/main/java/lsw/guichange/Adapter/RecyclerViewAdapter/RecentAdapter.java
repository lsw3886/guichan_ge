package lsw.guichange.Adapter.RecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Interface.OnListItemClickListener;
import lsw.guichange.Interface.OnRecentItemClickListener;
import lsw.guichange.Item.Bulletin;
import lsw.guichange.Item.RecentBulletin;
import lsw.guichange.R;

/**
 * Created by lsw38 on 2017-08-10.
 */

public class RecentAdapter  extends RecyclerView.Adapter<RecentAdapter.ViewHolder> implements OnRecentItemClickListener {

    Context mContext;
    ArrayList<RecentBulletin> choiced_bulletins;
    ApplicationController application;
    public RecentAdapter(Context mContext) {
        application = ApplicationController.getInstance();
        this.choiced_bulletins = application.getChoiced_bulletins();
        this.mContext = mContext;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        viewHolder.bulletin_name.setText(choiced_bulletins.get(i).getBulletin_Name());
        viewHolder.bulletin_img.setImageResource(choiced_bulletins.get(i).getBulletin_Img());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recent_bulletin_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.setOnListItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onRecentItemClick(int position){



    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView bulletin_img;
        public TextView bulletin_name;
        public TextView bulletin_content;
        OnRecentItemClickListener mListner;

        public void setOnListItemClickListener(OnRecentItemClickListener onRecentItemClickListener){
            mListner = onRecentItemClickListener;

        }
        public ViewHolder(View itemView){
            super(itemView);
            bulletin_img  = (ImageView) itemView.findViewById(R.id.recent_bulletin_image);
            bulletin_name = (TextView) itemView.findViewById(R.id.recent_bulletin_name);
            bulletin_content = (TextView) itemView.findViewById(R.id.recent_bulletin_content);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  mListner.onRecentItemClick(getAdapterPosition());
                }
            });


        }

    }

    @Override public int getItemCount(){
        return choiced_bulletins.size();
    }


}
