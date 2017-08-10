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

import lsw.guichange.Interface.OnListItemClickListener;
import lsw.guichange.Item.Bulletin;
import lsw.guichange.R;

/**
 * Created by lsw38 on 2017-08-10.
 */

public class RecentAdapter  extends RecyclerView.Adapter<RecentAdapter.ViewHolder> implements OnListItemClickListener {

    Context mContext;
    ArrayList<Bulletin> choiced_bulletins;
    public RecentAdapter(Context mContext, ArrayList<Bulletin> bulletins ) {

        this.choiced_bulletins = bulletins;
        this.mContext = mContext;

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i){
        viewHolder.bulletin_name.setText(bulletins.get(i).getBulletin_Name());
        viewHolder.bulletin_img.setImageResource(bulletins.get(i).getBulletin_Img());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.bulletin_list_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        viewHolder.setOnListItemClickListener(this);
        return viewHolder;
    }

    @Override
    public void onListItemClick(int position){
//        Intent intent = new Intent(mContext, CategoryActivity.class);
//        intent.putExtra("category", categories.get(position).getName());
//        mContext.startActivity(intent);

    }
    class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView bulletin_img;
        public TextView bulletin_name;
        public TextView bulletin_content;
        OnListItemClickListener mListner;

        public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener){
            mListner = onListItemClickListener;

        }
        public ViewHolder(View itemView){
            super(itemView);
            bulletin_img  = (ImageView) itemView.findViewById(R.id.bulletin_image);
            bulletin_name = (TextView) itemView.findViewById(R.id.bulletin_name);
            bulletin_content = (TextView) itemView.findViewById(R.Id.);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListner.onListItemClick(getAdapterPosition());
                }
            });


        }

    }

    @Override public int getItemCount(){
        return choiced_bulletins.size();
    }


}
