package lsw.guichange.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


import lsw.guichange.Adapter.RecyclerViewAdapter.ExpandableListAdapter;
import lsw.guichange.Adapter.RecyclerViewAdapter.ListAdapter;
import lsw.guichange.Item.addBulletin;
import lsw.guichange.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Expandable.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Expandable#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Expandable extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView recyclerview;
    FloatingActionButton fab;
    FirebaseDatabase database;
    DatabaseReference myRef;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Expandable() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Expandable newInstance() {
        Expandable fragment = new Expandable();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expandable, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        fab = (FloatingActionButton) getView().findViewById(R.id.floating_Btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_dialog();
            }
        });

        recyclerview = (RecyclerView) getView().findViewById(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        List<ExpandableListAdapter.Item> data = new ArrayList<>();

        ExpandableListAdapter.Item shopping = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "쇼핑", R.drawable.ic_bulletinlist_cart);
        shopping.invisibleChildren = new ArrayList<>();
        shopping.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "뽐뿌게시판",R.drawable.ic_bulletinlist_ppomppu));
        shopping.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "중고나라", R.drawable.ic_bulletinlist_jungo));

        ExpandableListAdapter.Item exam = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "시험",R.drawable.ic_bulletinlist_exam);
        exam.invisibleChildren = new ArrayList<>();
        exam.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "오르비", R.drawable.ic_bulletinlist_orbi));
        exam.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "행시사랑", R.drawable.ic_bulletinlist_hangsi));

        ExpandableListAdapter.Item job = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "취업", R.drawable.ic_bulletinlist_bag);
        job.invisibleChildren = new ArrayList<>();
        job.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "스펙업",R.drawable.specup_ic));
        job.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "취업대학교", R.drawable.ic_bulletinlist_chiup));

        ExpandableListAdapter.Item places = new ExpandableListAdapter.Item(ExpandableListAdapter.HEADER, "커뮤니티", R.drawable.ic_bulletinlist_com);
        places.invisibleChildren = new ArrayList<>();
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "뽐뿌자게", R.drawable.ic_bulletinlist_ppomppu));
        places.invisibleChildren.add(new ExpandableListAdapter.Item(ExpandableListAdapter.CHILD, "부스트캠프", R.drawable.ic_bulletinlist_boost));

        data.add(shopping);
        data.add(exam);
        data.add(job);
        data.add(places);


        recyclerview.setAdapter(new ExpandableListAdapter(data));

    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
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
