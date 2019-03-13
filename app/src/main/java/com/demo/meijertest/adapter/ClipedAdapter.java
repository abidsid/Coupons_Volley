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
import com.squareup.picasso.Picasso;

import java.util.List;

public class ClipedAdapter extends RecyclerView.Adapter<ClipedAdapter.RtgViewHolder> {
    private static final String TAG = AvailableAdapter.class.getSimpleName();
    private Activity context;
    List<ListOfCouponsBean> couponsBeanList;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ClipedAdapter(Activity context, List<ListOfCouponsBean> couponsBeanList) {
        this.context = context;
        this.couponsBeanList = couponsBeanList;
        preferences = context.getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        editor = preferences.edit();
    }

    @Override
    public ClipedAdapter.RtgViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.snippet_available_coupons, parent, false);
        return new ClipedAdapter.RtgViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ClipedAdapter.RtgViewHolder holder, final int position) {
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
        }
        holder.tvClip.setText("UnClip");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    HFAUtils.availCouponList.get(position).setIsClipped(false);
                    HFAUtils.showToast(context, "UnClipped");
                    HFAUtils.list.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, HFAUtils.list.size());
                    holder.itemView.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
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
