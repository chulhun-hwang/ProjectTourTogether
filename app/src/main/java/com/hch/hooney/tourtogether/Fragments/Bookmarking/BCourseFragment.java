package com.hch.hooney.tourtogether.Fragments.Bookmarking;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hch.hooney.tourtogether.R;
import com.hch.hooney.tourtogether.Recycler.Bookmark.Route.bRouteAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class BCourseFragment extends Fragment {
    private View view;

    //layout
    private RecyclerView bookmarkRouteList;

    public BCourseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_bcourse, container, false);

        init();
        setUI();

        return view;
    }

    private void init(){
        //layout
        bookmarkRouteList = (RecyclerView) view.findViewById(R.id.bookmark_course_list);
        bookmarkRouteList.setHasFixedSize(false);
        bookmarkRouteList.setLayoutManager(new GridLayoutManager(getContext(), 2));
    }

    private void setUI(){
        bookmarkRouteList.setAdapter(new bRouteAdapter(getContext(), bookmarkRouteList));
    }
}
