package com.hch.hooney.tourtogether.DAO.TourAPI;

import com.hch.hooney.tourtogether.DAO.TourApiItem;

import java.util.ArrayList;

/**
 * Created by hooney on 2018. 2. 3..
 */

public class Course extends TourApiItem {
    private String totalDistance;
    private String time;
    private ArrayList<TourApiItem> spotlist;

    public Course(String totalDistance, String time, ArrayList<TourApiItem> spotlist) {
        this.totalDistance = totalDistance;
        this.time = time;
        this.spotlist = spotlist;
    }

    public Course(TourApiItem item) {
        super(item);
        this.totalDistance = "";
        this.time = "";
        this.spotlist = new ArrayList<TourApiItem>();
    }

    public Course(TourApiItem item, String totalDistance, String time, ArrayList<TourApiItem> spotlist) {
        super(item);
        this.totalDistance = totalDistance;
        this.time = time;
        this.spotlist = spotlist;
    }

    public TourApiItem getSuper(){
        return new TourApiItem(
        super.isPost, super.addr1, super.addr2, super.areaCode, super.cat1,
                super.cat2, super.cat3, super.contentID, super.contentTypeID, super.firstImage,
                super.mapx, super.mapy, super.modifyDateTIme, super.readCount, super.sigunguCode,
                super.title, super.tel, super.directions, super.basic_overView
        );
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public ArrayList<TourApiItem> getSpotlist() {
        return spotlist;
    }

    public void setSpotlist(ArrayList<TourApiItem> spotlist) {
        this.spotlist = spotlist;
    }
}
