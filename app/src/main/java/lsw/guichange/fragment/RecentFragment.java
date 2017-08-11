package lsw.guichange.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import junit.framework.Assert;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import lsw.guichange.Adapter.RecyclerViewAdapter.ListAdapter;
import lsw.guichange.Adapter.RecyclerViewAdapter.RecentAdapter;
import lsw.guichange.Controller.ApplicationController;
import lsw.guichange.Controller.NetworkService;
import lsw.guichange.Item.Post;
import lsw.guichange.Item.RecentBulletin;
import lsw.guichange.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecentFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    NetworkService networkService;
    RecyclerView recyclerView;
    TextView textview;
    RecentAdapter adapter;
    ArrayList<RecentBulletin> bulletins;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public RecentFragment() {
        // Required empty public constructor
    }

    public static RecentFragment newInstance() {
        RecentFragment fragment = new RecentFragment();
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
        networkService = ApplicationController.getInstance().getNetworkService();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recent, container, false);
    }
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getView().findViewById(R.id.recent_recycler);
        bulletins = new ArrayList<>();
        LinearLayoutManager lm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(lm);
        this.adapter = new RecentAdapter(getActivity(), bulletins);

        recyclerView.setAdapter(adapter);








//        textview = (TextView) getView().findViewById(R.id.test);
//        Button button = (Button) getView().findViewById(R.id.testbtn);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                retrofitTest();
//
//            }
//        });



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


//    public void retrofitTest(){
//
//        Call<List<Post>> versionCall = networkService.get_post();
//        versionCall.enqueue(new Callback<List<Post>>() {
//            @Override
//            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
//                if(response.isSuccessful()) {
//                    List<Post> posts = response.body();
//
//                    String version_txt = "";
//                    for(Post version : posts){
//                        version_txt += version.getTitle() + "\n";
//                    }
//                    textview.setText(version_txt);
//
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


}
