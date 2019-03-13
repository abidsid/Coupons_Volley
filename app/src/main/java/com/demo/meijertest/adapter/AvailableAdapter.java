package com.demo.meijertest.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.demo.meijertest.R;
import com.demo.meijertest.models.ListOfCouponsBean;
import com.demo.meijertest.utils.HFAUtils;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AvailableAdapter extends RecyclerView.Adapter<AvailableAdapter.RtgViewHolder> {
    private static final String TAG = AvailableAdapter.class.getSimpleName();
    private Activity context;
    List<ListOfCouponsBean> couponsBeanList;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public AvailableAdapter(Activity context, List<ListOfCouponsBean> couponsBeanList) {
        this.context = context;
        this.couponsBeanList = couponsBeanList;
           }

    @Override
    public AvailableAdapter.RtgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.snippet_available_coupons, parent, false);
        return new AvailableAdapter.RtgViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final AvailableAdapter.RtgViewHolder holder, final int position) {
        final ListOfCouponsBean item = couponsBeanList.get(position);
        if (item.getTitle() != null)
            holder.tvProduct.setText(item.getTitle().trim());
        if (item.getDescription() != null)
            holder.tvDesc.setText(item.getDescription().trim());
        if (item.getRedemptionEndDate() != null)
            holder.tvDate.setText("Valid Thru" + item.getRedemptionEndDate().trim());
        if (item.getImageURL() != null && !item.getImageURL().isEmpty()) {
            Log.d(TAG, "IMAGE PATH--><" + item.getImageURL());
            Picasso.with(context).load(item.getImageURL()).into(holder.imgProduct);
            // Glide.with(context).load(item.getASSET_IMAGE_PATH()).apply(RequestOptions.centerCropTransform()).into(holder.imgRecent);
        }
        if (item.getIsClipped()) {
            holder.tvClip.setVisibility(View.GONE);
        } else {
            holder.tvClip.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HFAUtils.showToast(context, "Clipped");
                holder.tvClip.setVisibility(View.GONE);
                HFAUtils.list.add(couponsBeanList.get(position));
                Log.d(TAG, "List size-->" + HFAUtils.list.size());
                HFAUtils.availCouponList.get(position).setIsClipped(true);
                /*List<ListOfCouponsBean> list = new ArrayList<>();
                String clippedList = preferences.getString("clip_item", "");
                editor.putString("clip_item", new Gson().toJson(list));
                editor.commit();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return couponsBeanList.size();
    }

    public class RtgViewHolder extends RecyclerView.ViewHolder {
        TextView tvProduct, tvDesc, tvDate;
        ImageView imgProduct;
        RelativeLayout rlClip;
        TextView tvClip;

        public RtgViewHolder(View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvProduct = itemView.findViewById(R.id.tv_product_name);
            tvDesc = itemView.findViewById(R.id.tv_product_desc);
            tvDate = itemView.findViewById(R.id.tv_product_valid);
            tvClip = itemView.findViewById(R.id.tv_clip);
            rlClip = itemView.findViewById(R.id.rl_clip);
        }
    }
}