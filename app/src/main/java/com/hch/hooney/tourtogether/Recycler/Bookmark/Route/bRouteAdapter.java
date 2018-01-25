package com.hch.hooney.tourtogether.Recycler.Bookmark.Route;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.hch.hooney.tourtogether.DAO.DAO;
import com.hch.hooney.tourtogether.DAO.TourApiItem;
import com.hch.hooney.tourtogether.R;
import com.squareup.picasso.Picasso;

/**
 * Created by qewqs on 2018-01-25.
 */

public class bRouteAdapter  extends RecyclerView.Adapter {
    private Context mContext;

    public bRouteAdapter(Context mContext) {
        this.mContext = mContext;
    }

    // Allows to remember the last item shown on screen
    private int lastPosition = -1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark_route,parent,false);
        bRouteHolder holder = new bRouteHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        bRouteHolder hold = (bRouteHolder)holder;
        TourApiItem item = DAO.bookmarkRouteList.get(position);

        String dateTime = item.getModifyDateTIme();
        String year = dateTime.substring(0, 4);
        String month = dateTime.substring(4, 6);
        String day = dateTime.substring(6, 8);
        String hour = dateTime.substring(8, 10);
        String min = dateTime.substring(10, 12);
        hold.br_DateAndTime.setText(year+"-"+month+"-"+day+" "+hour+":"+min);

        hold.br_title.setText(item.getTitle());

        Picasso.with(mContext).load(item.getFirstImage()).into(hold.br_imageview);

        hold.br_viewCount.setText("view : " + item.getReadCount());

        hold.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Show Route...", Toast.LENGTH_SHORT).show();
            }
        });

        setAnimation(hold.itemView, position);
    }

    @Override
    public int getItemCount() {
        return DAO.bookmarkRouteList.size();
    }

    private void setAnimation(View viewToAnimate, int position) {
        // 새로 보여지는 뷰라면 애니메이션을 해줍니다
        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }
}
