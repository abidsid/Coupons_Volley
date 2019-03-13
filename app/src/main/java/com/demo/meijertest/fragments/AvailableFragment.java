package com.demo.meijertest.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.demo.meijertest.R;
import com.demo.meijertest.adapter.AvailableAdapter;
import com.demo.meijertest.models.ListOfCouponsBean;
import com.demo.meijertest.utils.HFAUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AvailableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AvailableFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String TAG = AvailableFragment.class.getSimpleName();
    Activity mActivity;
    RecyclerView rcvAvailableCoupons;
    TextView tvAvaCount;
  //  String BASE_URL = "https://meijerkraig.azurewebsites.net/api/Products?code=34lgBae%2FxIEnqksQpkn3w9F0JTKCafuiCr0ejLNLvBzlOlOZBa1CMA%3D%3D";
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public AvailableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AvailableFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AvailableFragment newInstance(String param1, String param2) {
        AvailableFragment fragment = new AvailableFragment();
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
        View view = inflater.inflate(R.layout.fragment_available, container, false);
        mActivity = getActivity();
        rcvAvailableCoupons = view.findViewById(R.id.rcv_available_count);
        tvAvaCount = view.findViewById(R.id.tv_available_count);
        if (HFAUtils.isOnline(mActivity))
            loadAvailableCouponsList();
        return view;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Refresh your fragment here
            if (HFAUtils.availCouponList.size() > 0)
                setAdapter(HFAUtils.availCouponList);
            else
                Log.d(TAG, "setUserVisibleHint List is zero");
        }
    }

    private void setAdapter(List<ListOfCouponsBean> listOfCoupons) {
        try {
            AvailableAdapter availableAdapter = new AvailableAdapter(mActivity, listOfCoupons);
            rcvAvailableCoupons.setLayoutManager(new LinearLayoutManager(mActivity));
            rcvAvailableCoupons.setAdapter(availableAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAvailableCouponsList() {
        //creating a string request to send request to the url
        final ProgressDialog progressDialog = HFAUtils.showProgressDialog(mActivity, "Loading Please Wait...");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, HFAUtils.BASE_URL,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            if (response != null && !response.isEmpty()) {
                                Log.d(TAG, "Response-->" + response);
                                JSONObject obj = new JSONObject(response);
                                String status = obj.getString("responseMessage");
                                if (status != null && !status.isEmpty() && status.equalsIgnoreCase("SUCCESS")) {
                                    String avaCount = obj.getString("availableCouponCount");
                                    tvAvaCount.setText(avaCount + " TOTAL");
                                    JSONArray jsonArray = obj.getJSONArray("listOfCoupons");
                                    Log.d(TAG, "Array length-->" + jsonArray.length());
                                    List<ListOfCouponsBean> listOfCouponsBeans = new ArrayList<>();
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject jObject = jsonArray.getJSONObject(i);
                                        ListOfCouponsBean listOfCouponsBean = new ListOfCouponsBean();
                                        listOfCouponsBean.setTitle(jObject.getString("title"));
                                        listOfCouponsBean.setDescription(jObject.getString("description"));
                                        listOfCouponsBean.setImageURL(jObject.getString("imageURL"));
                                        listOfCouponsBean.setRedemptionEndDate(jObject.getString("redemptionEndDate"));
                                        listOfCouponsBean.setIsClipped(jObject.getBoolean("isClipped"));
                                        listOfCouponsBeans.add(listOfCouponsBean);
                                    }
                                    HFAUtils.list.clear();
                                    if (progressDialog != null && progressDialog.isShowing())
                                        progressDialog.dismiss();
                                    if (listOfCouponsBeans.size() > 0) {
                                        HFAUtils.availCouponList.addAll(listOfCouponsBeans);
                                        setAdapter(listOfCouponsBeans);
                                    }
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (progressDialog != null && progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(mActivity, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(mActivity);
        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
