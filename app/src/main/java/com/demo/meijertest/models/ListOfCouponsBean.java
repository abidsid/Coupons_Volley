package com.demo.meijertest.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ListOfCouponsBean {
    @Expose
    @SerializedName("rewardProgramId")
    private int rewardProgramId;
    @Expose
    @SerializedName("offerClassId")
    private int offerClassId;
    @Expose
    @SerializedName("manufacturerCoupon")
    private boolean manufacturerCoupon;
    @Expose
    @SerializedName("redemptionEndDate")
    private String redemptionEndDate;
    @Expose
    @SerializedName("redemptionStartDate")
    private String redemptionStartDate;
    @Expose
    @SerializedName("termsAndConditions")
    private String termsAndConditions;
    @Expose
    @SerializedName("largeImageURL")
    private String largeImageURL;
    @Expose
    @SerializedName("imageURL")
    private String imageURL;
    @Expose
    @SerializedName("showLargeImage")
    private boolean showLargeImage;
    @Expose
    @SerializedName("isMeijerBuck")
    private boolean isMeijerBuck;
    @Expose
    @SerializedName("borderColor")
    private int borderColor;
    @Expose
    @SerializedName("hatColor")
    private int hatColor;
    @Expose
    @SerializedName("hatText")
    private String hatText;
    @Expose
    @SerializedName("tags")
    private List<String> tags;
    @Expose
    @SerializedName("category")
    private CategoryBean category;
    @Expose
    @SerializedName("description")
    private String description;
    @Expose
    @SerializedName("title")
    private String title;
    @Expose
    @SerializedName("meijerOfferId")
    private int meijerOfferId;
    @Expose
    @SerializedName("redemptionDate")
    private String redemptionDate;
    @Expose
    @SerializedName("isSpecialOffer")
    private boolean isSpecialOffer;
    @Expose
    @SerializedName("couponExpirationGroupTag")
    private String couponExpirationGroupTag;
    @Expose
    @SerializedName("couponInclusionGroupTag")
    private String couponInclusionGroupTag;
    @Expose
    @SerializedName("isHidden")
    private boolean isHidden;
    @Expose
    @SerializedName("isAutoClipped")
    private boolean isAutoClipped;
    @Expose
    @SerializedName("isClipped")
    private boolean isClipped;
    @Expose
    @SerializedName("isSuggested")
    private boolean isSuggested;

    public int getRewardProgramId() {
        return rewardProgramId;
    }

    public void setRewardProgramId(int rewardProgramId) {
        this.rewardProgramId = rewardProgramId;
    }

    public int getOfferClassId() {
        return offerClassId;
    }

    public void setOfferClassId(int offerClassId) {
        this.offerClassId = offerClassId;
    }

    public boolean getManufacturerCoupon() {
        return manufacturerCoupon;
    }

    public void setManufacturerCoupon(boolean manufacturerCoupon) {
        this.manufacturerCoupon = manufacturerCoupon;
    }

    public String getRedemptionEndDate() {
        return redemptionEndDate;
    }

    public void setRedemptionEndDate(String redemptionEndDate) {
        this.redemptionEndDate = redemptionEndDate;
    }

    public String getRedemptionStartDate() {
        return redemptionStartDate;
    }

    public void setRedemptionStartDate(String redemptionStartDate) {
        this.redemptionStartDate = redemptionStartDate;
    }

    public String getTermsAndConditions() {
        return termsAndConditions;
    }

    public void setTermsAndConditions(String termsAndConditions) {
        this.termsAndConditions = termsAndConditions;
    }

    public String getLargeImageURL() {
        return largeImageURL;
    }

    public void setLargeImageURL(String largeImageURL) {
        this.largeImageURL = largeImageURL;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public boolean getShowLargeImage() {
        return showLargeImage;
    }

    public void setShowLargeImage(boolean showLargeImage) {
        this.showLargeImage = showLargeImage;
    }

    public boolean getIsMeijerBuck() {
        return isMeijerBuck;
    }

    public void setIsMeijerBuck(boolean isMeijerBuck) {
        this.isMeijerBuck = isMeijerBuck;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getHatColor() {
        return hatColor;
    }

    public void setHatColor(int hatColor) {
        this.hatColor = hatColor;
    }

    public String getHatText() {
        return hatText;
    }

    public void setHatText(String hatText) {
        this.hatText = hatText;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public CategoryBean getCategory() {
        return category;
    }

    public void setCategory(CategoryBean category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMeijerOfferId() {
        return meijerOfferId;
    }

    public void setMeijerOfferId(int meijerOfferId) {
        this.meijerOfferId = meijerOfferId;
    }

    public String getRedemptionDate() {
        return redemptionDate;
    }

    public void setRedemptionDate(String redemptionDate) {
        this.redemptionDate = redemptionDate;
    }

    public boolean getIsSpecialOffer() {
        return isSpecialOffer;
    }

    public void setIsSpecialOffer(boolean isSpecialOffer) {
        this.isSpecialOffer = isSpecialOffer;
    }

    public String getCouponExpirationGroupTag() {
        return couponExpirationGroupTag;
    }

    public void setCouponExpirationGroupTag(String couponExpirationGroupTag) {
        this.couponExpirationGroupTag = couponExpirationGroupTag;
    }

    public String getCouponInclusionGroupTag() {
        return couponInclusionGroupTag;
    }

    public void setCouponInclusionGroupTag(String couponInclusionGroupTag) {
        this.couponInclusionGroupTag = couponInclusionGroupTag;
    }

    public boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean getIsAutoClipped() {
        return isAutoClipped;
    }

    public void setIsAutoClipped(boolean isAutoClipped) {
        this.isAutoClipped = isAutoClipped;
    }

    public boolean getIsClipped() {
        return isClipped;
    }

    public void setIsClipped(boolean isClipped) {
        this.isClipped = isClipped;
    }

    public boolean getIsSuggested() {
        return isSuggested;
    }

    public void setIsSuggested(boolean isSuggested) {
        this.isSuggested = isSuggested;
    }
}
