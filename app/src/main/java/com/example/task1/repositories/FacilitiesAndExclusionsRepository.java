package com.example.task1.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.task1.Models.ExclusionsModel;
import com.example.task1.Models.FacilitiesMotherModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FacilitiesAndExclusionsRepository {
    private static FacilitiesAndExclusionsRepository instance;
    private ArrayList<FacilitiesMotherModel> dataset = new ArrayList<>();
    private ArrayList<ArrayList<ExclusionsModel>> exclusionDataset = new ArrayList<>();
    private RequestQueue mQueue;
    Context context;
    String url = "https://my-json-server.typicode.com/ricky1550/pariksha/db";
    MutableLiveData<ArrayList<FacilitiesMotherModel>> data = new MutableLiveData<>();
    MutableLiveData<ArrayList<ArrayList<ExclusionsModel>>> exclusionData = new MutableLiveData<>();


    public static FacilitiesAndExclusionsRepository getInstance(){
        if(instance==null){
            instance = new FacilitiesAndExclusionsRepository();
        }

        return  instance;
    }

    public MutableLiveData<ArrayList<FacilitiesMotherModel>> getFacilities(Context c){
        context = c;
        setFacilitiesAndExclusions();
        return data;
    }
    public MutableLiveData<ArrayList<ArrayList<ExclusionsModel>>> getExclusions(){
        return exclusionData;
    }



    private void setFacilitiesAndExclusions(){
        //retrieve data from server
        mQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //Extracting facilities
                    JSONArray facilities  = response.getJSONArray("facilities");
                    for(int i=0;i<facilities.length();i++){
                        JSONObject facility = facilities.getJSONObject(i);
                        String name = facility.getString("name");
                        String facility_id = facility.getString("facility_id");
                        JSONArray options = facility.getJSONArray("options");
                        FacilitiesMotherModel motherModel = new FacilitiesMotherModel(facility_id,name,options);
                        dataset.add(motherModel);
                        Log.d("MODEL_LIST",dataset.toString());
                    }

                    data.setValue(dataset);


                    JSONArray exclusions  = response.getJSONArray("exclusions");
                    for(int i=0;i<exclusions.length();i++){
                        JSONArray exclusionArr = exclusions.getJSONArray(i);
                        ArrayList<ExclusionsModel> tempList = new ArrayList<>();
                        for(int j=0;i<exclusionArr.length();i++){

                            JSONObject exclusion = exclusions.getJSONObject(i);
                            String facility_id = exclusion.getString("facility_id");
                            String options_id = exclusion.getString("options_id");
                            ExclusionsModel exclusionsModel = new ExclusionsModel(facility_id,options_id);
                            tempList.add(exclusionsModel);
                        }
                        exclusionDataset.add(tempList);
                        tempList.clear();

                    }
                    exclusionData.setValue(exclusionDataset);
                    Log.d("MODEL_LIST",dataset.toString());
                    Log.d("EXCLUSION_LIST",exclusionDataset.toString());




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(jsonObjectRequest);
    }


}
