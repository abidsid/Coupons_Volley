package com.demo.meijertest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class AvailableCouponsPojo {
    @Expose
    @SerializedName("hasSpecialOffers")
    private boolean hasSpecialOffers;
    @Expose
    @SerializedName("responseMessage")
    private String responseMessage;
    @Expose
    @SerializedName("responseCode")
    private int responseCode;
    @Expose
    @SerializedName("listOfCoupons")
    private List<ListOfCouponsBean> listOfCoupons;
    @Expose
    @SerializedName("availableCouponCount")
    private int availableCouponCount;
    @Expose
    @SerializedName("couponCount")
    private int couponCount;

    public boolean getHasSpecialOffers() {
        return hasSpecialOffers;
    }

    public void setHasSpecialOffers(boolean hasSpecialOffers) {
        this.hasSpecialOffers = hasSpecialOffers;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public List<ListOfCouponsBean> getListOfCoupons() {
        return listOfCoupons;
    }

    public void setListOfCoupons(List<ListOfCouponsBean> listOfCoupons) {
        this.listOfCoupons = listOfCoupons;
    }

    public int getAvailableCouponCount() {
        return availableCouponCount;
    }

    public void setAvailableCouponCount(int availableCouponCount) {
        this.availableCouponCount = availableCouponCount;
    }

    public int getCouponCount() {
        return couponCount;
    }

    public void setCouponCount(int couponCount) {
        this.couponCount = couponCount;
    }
}
