package lsw.guichange.Adapter.RecyclerViewAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import lsw.guichange.Item.Category;
import lsw.guichange.R;

/**
 * Created by lsw38 on 2017-08-08.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{

    Context mContext;
    ArrayList<Category> categories;
    public ListAdapter(Context mContext, ArrayList<Category> categories ) {

        this.categories = categories;
        this.mContext = mContext;

    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int i){
        viewHolder.category_title.setText(categories.get(i).getName());
        viewHolder.category_img.setImageResource(categories.get(i).getImage());




    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView category_img;
        public TextView category_title;

        public ViewHolder(View itemView){
            super(itemView);
            category_img  = (ImageView) itemView.findViewById(R.id.category_image);
            category_title = (TextView) itemView.findViewById(R.id.category_name);


        }
    }

    @Override public int getItemCount(){
        return 4;
    }


}