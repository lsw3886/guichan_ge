package lsw.guichange.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

import lsw.guichange.Adapter.RecyclerViewAdapter.ListAdapter;
import lsw.guichange.Item.Category;
import lsw.guichange.Item.addBulletin;
import lsw.guichange.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;


public class ListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
//    ArrayList<Category> categories;
    FirebaseDatabase database;
    DatabaseReference myRef ;

    RecyclerView recyclerView;
    ListAdapter adapter;
    FloatingActionButton fab;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ListFragment() {
        // Required empty public constructor
    }


    public static ListFragment newInstance() {
        ListFragment fragment = new ListFragment();
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
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

        recyclerView = (RecyclerView) getView().findViewById(R.id.ListRecyclerview);

        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);


        this.adapter = new ListAdapter(getActivity(), 0);

        recyclerView.setAdapter(adapter);

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
