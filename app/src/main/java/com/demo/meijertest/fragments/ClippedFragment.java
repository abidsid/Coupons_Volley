package com.demo.meijertest.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.meijertest.R;
import com.demo.meijertest.adapter.AvailableAdapter;
import com.demo.meijertest.adapter.ClipedAdapter;
import com.demo.meijertest.models.ListOfCouponsBean;
import com.demo.meijertest.utils.HFAUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link ClippedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClippedFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String TAG = ClippedFragment.class.getSimpleName();
    Activity mActivity;
    RecyclerView rcvClippedeCoupons;
    TextView tvAvaCount;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public ClippedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ClippedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClippedFragment newInstance(String param1, String param2) {
        ClippedFragment fragment = new ClippedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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
        View view = inflater.inflate(R.layout.fragment_clipped, container, false);
        mActivity = getActivity();
        preferences = mActivity.getSharedPreferences("my_pref", Context.MODE_PRIVATE);
        editor = preferences.edit();
        rcvClippedeCoupons = view.findViewById(R.id.rcv_clipped_count);
        tvAvaCount = view.findViewById(R.id.tv_clipped_count);
        tvAvaCount.setVisibility(View.GONE);
        setClipData();
        return view;
    }

    void setClipData() {
        Log.d(TAG, "Clipped List Size-->" + HFAUtils.list.size());
        if (HFAUtils.list != null && HFAUtils.list.size() > 0) {
            Log.d(TAG, "Clipped List Size-->" + HFAUtils.list.size());
            tvAvaCount.setText(HFAUtils.list.size() + " TOTAL");
            setAdapter();
        } else
            tvAvaCount.setText(HFAUtils.list.size() + " TOTAL");
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            setClipData();
        }
    }


    private void setAdapter() {
      /*  List<ListOfCouponsBean> listOfCouponsBeans = new ArrayList<>();
        for (int i = 0; i < HFAUtils.availCouponList.size(); i++) {
            if (HFAUtils.availCouponList.get(i).getIsClipped()) {
                listOfCouponsBeans.add(HFAUtils.availCouponList.get(i));
            }
        }*/
        if (HFAUtils.list.size() > 0) {
            ClipedAdapter availableAdapter = new ClipedAdapter(mActivity, HFAUtils.list);
            rcvClippedeCoupons.setLayoutManager(new LinearLayoutManager(mActivity));
            rcvClippedeCoupons.setAdapter(availableAdapter);
        }
    }

}
