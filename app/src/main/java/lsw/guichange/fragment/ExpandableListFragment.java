package lsw.guichange.fragment;


import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Item.Bulletin;
import lsw.guichange.Item.Category;
import lsw.guichange.Item.Post;
import lsw.guichange.Item.RecentBulletin;
import lsw.guichange.Item.addBulletin;
import lsw.guichange.R;
import java.util.ArrayList;
import java.util.List;

import lsw.guichange.lib.AnimatedExpandableListView;
import lsw.guichange.lib.AnimatedExpandableListView.AnimatedExpandableListAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpandableListFragment extends Fragment {
    ArrayList<Bulletin> bulletins;
    private AnimatedExpandableListView listView;
    private ExampleAdapter adapter;
    ApplicationController application;
    List<Category> items;
    FirebaseDatabase database;
    DatabaseReference myRef ;
    FloatingActionButton fab;
    PagerAdapter pagerAdapter;



    public ExpandableListFragment() {
        // Required empty public constructor
    }


    public static ExpandableListFragment newInstance(lsw.guichange.Adapter.PagerAdapter pagerAdapter) {
        ExpandableListFragment fragment = new ExpandableListFragment();
        fragment.application = ApplicationController.getInstance();
        fragment.items = fragment.application.Makecategories();
        fragment.pagerAdapter = pagerAdapter;
//        fragment.categories = fragment.Makecategories();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expandable_list, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab = (FloatingActionButton) getView().findViewById(R.id.floating_Btn2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                show_dialog();


            }
        });
       ;

        // Populate our list with groups and it's children
        items.get(0).setbItems(application.setBulletins("쇼핑"));
        items.get(1).setbItems(application.setBulletins("시험"));
        items.get(2).setbItems(application.setBulletins("취업"));
        items.get(3).setbItems(application.setBulletins("커뮤니티"));

        adapter = new ExampleAdapter(getActivity());
        adapter.setData(items);

        listView = (AnimatedExpandableListView)getView().findViewById(R.id.listView);
        listView.setAdapter(adapter);

        // In order to show animations, we need to use a custom click handler
        // for our ExpandableListView.
        listView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                // We call collapseGroupWithAnimation(int) and
                // expandGroupWithAnimation(int) to animate group
                // expansion/collapse.
                if (listView.isGroupExpanded(groupPosition)) {
                    listView.collapseGroupWithAnimation(groupPosition);
                } else {
                    listView.expandGroupWithAnimation(groupPosition);
                }
                return true;
            }

        });


    }




    private static class ChildHolder {
        public ImageView bulletin_img;
        public TextView bulletin_name;
        public ImageView isBulletin_selected;
    }

    private static class GroupHolder {
        public ImageView category_img;
        public TextView category_title;
        public ImageView categrory_indicator;

    }

    private class ExampleAdapter extends AnimatedExpandableListAdapter {
        private LayoutInflater inflater;

        private List<Category> items;

        public ExampleAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void setData(List<Category> items) {
            this.items = items;
        }

        @Override
        public Bulletin getChild(int groupPosition, int childPosition) {
            return items.get(groupPosition).bItems.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getRealChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildHolder holder;
            Bulletin item = getChild(groupPosition, childPosition);
            if (convertView == null) {
                holder = new ChildHolder();
                convertView = inflater.inflate(R.layout.bulletin_list_item, parent, false);
                holder.bulletin_img = (ImageView) convertView.findViewById(R.id.bulletin_image);
                holder.bulletin_name = (TextView) convertView.findViewById(R.id.bulletin_name);
                holder.isBulletin_selected = (ImageView) convertView.findViewById(R.id.is_bulletin_selected);
                convertView.setTag(holder);
            } else {
                holder = (ChildHolder) convertView.getTag();
            }

            holder.bulletin_img.setImageResource(item.getBulletin_Img());
            holder.bulletin_name.setText(item.getBulletin_Name());
            holder.isBulletin_selected.setImageResource(R.drawable.ic_bulletinlist_select);
            holder.isBulletin_selected.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(application.isBulletinInChoicedBulletins(items.get(groupPosition).getbItems().get(childPosition).getBulletin_Name()) == false){
                        RecentBulletin recentBulletin = new RecentBulletin(items.get(groupPosition).getbItems().get(childPosition).getBulletin_Img(), items.get(groupPosition).getbItems().get(childPosition).getCategory(), items.get(groupPosition).getbItems().get(childPosition).getBulletin_Name());
                        application.setChoiced_bulletins(recentBulletin);
                        application.makePostDB(items.get(groupPosition).getbItems().get(childPosition).getBulletin_Name());

                        Toast.makeText(getActivity(), "나의 게시판에 추가되었습니다.", Toast.LENGTH_SHORT).show();
                        pagerAdapter.notifyDataSetChanged();


                    }else{
                        Toast.makeText(getActivity(),"이미 추가 되었습니다.", Toast.LENGTH_SHORT).show();
                    }

                }
            });
//            holder.isBulletin_selected.setImageResource(item.getIschecked());


            return convertView;
        }

        @Override
        public int getRealChildrenCount(int groupPosition) {
            return items.get(groupPosition).bItems.size();
        }

        @Override
        public Category getGroup(int groupPosition) {
            return items.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
            return items.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            final GroupHolder holder;
            final Category item = getGroup(groupPosition);
            if (convertView == null) {
                holder = new GroupHolder();
                convertView = inflater.inflate(R.layout.category, parent, false);
                holder.category_img = (ImageView)convertView.findViewById(R.id.category_image);
                holder.category_title = (TextView) convertView.findViewById(R.id.category_name);
//                holder.categrory_indicator = (ImageView) convertView.findViewById(R.id.group_indicator);
                convertView.setTag(holder);
            } else {
                holder = (GroupHolder) convertView.getTag();
            }

            holder.category_title.setText(item.name);
            holder.category_img.setImageResource(item.image);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }

    }
    private void show_dialog() {
        LayoutInflater _inflater = (LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View _layout = _inflater.inflate(R.layout.add_bulletin_popup, null);
        final EditText inputBulletinName = (EditText)_layout.findViewById(R.id.add_bulletin_name);
        final EditText inputBulletinAddress = (EditText)_layout.findViewById(R.id.add_bulletin_address);

        AlertDialog alert = new AlertDialog.Builder(getActivity())
                .setTitle("알림")
                .setPositiveButton("전송", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if(inputBulletinAddress.getText().toString().equals("") || inputBulletinName.getText().toString().equals("")){
                            Toast.makeText(getActivity(), "전송실패!",Toast.LENGTH_SHORT);

                        }else {
                            database = FirebaseDatabase.getInstance();
                            myRef = database.getReference("Bulletins");
                            addBulletin addBulletin = new addBulletin(inputBulletinName.getText().toString(), inputBulletinAddress.getText().toString());
                            myRef.child(inputBulletinName.getText().toString()).setValue(addBulletin);
                            Toast.makeText(getActivity(), "전송성공!",Toast.LENGTH_SHORT);
                        }




                    }
                })
                .setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getActivity(), "취소되었습니다.",Toast.LENGTH_SHORT);

                        //CAncel  버튼 눌렀을때

                    }
                }).create();
        alert.setView(_layout);
        alert.show();
    }



}



