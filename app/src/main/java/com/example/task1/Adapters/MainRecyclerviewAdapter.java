package com.example.task1.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.InnerRecyclerviewClickInterface;
import com.example.task1.MainRecyclerviewClickInterface;
import com.example.task1.Models.FacilitiesModel;
import com.example.task1.Models.FacilitiesMotherModel;
import com.example.task1.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MainRecyclerviewAdapter extends RecyclerView.Adapter<MainRecyclerviewAdapter.ViewHolder> implements InnerRecyclerviewClickInterface{

    private ArrayList<FacilitiesMotherModel> mModels = new ArrayList<>();
    private Context mContext;
    MainRecyclerviewClickInterface mainRecyclerviewClickInterface;
    HashMap<Integer,Integer> selectionMap ;
    private int currentSelectedPosition = RecyclerView.NO_POSITION;

    Map<Integer,InnerRecyclerViewAdapter> adapterMap = new HashMap<>();


    public MainRecyclerviewAdapter(Context context, ArrayList<FacilitiesMotherModel> models,HashMap<Integer,Integer> selectionMap, MainRecyclerviewClickInterface mainRecyclerviewClickInterface) {
        mContext = context;
        mModels=models;
        this.mainRecyclerviewClickInterface = mainRecyclerviewClickInterface;
        this.selectionMap = selectionMap;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.headerTxt.setText(mModels.get(position).getName());
        //attach recyclerview

        ArrayList<FacilitiesModel> facilitiesModelArrayList = new ArrayList<FacilitiesModel>();
        JSONArray options = (JSONArray) mModels.get(position).getOptions();
        for(int i=0;i<options.length();i++) {
            JSONObject opt  = null;
            try {
                opt = options.getJSONObject(i);

            String name = opt.getString("name");
            String icon = opt.getString("icon");
            String id = opt.getString("id");
            facilitiesModelArrayList.add(new FacilitiesModel(name,icon,id));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        InnerRecyclerViewAdapter innerAdapter = new InnerRecyclerViewAdapter(mContext,facilitiesModelArrayList,position,selectionMap,this);
        holder.recyclerView.setAdapter(innerAdapter);
        adapterMap.put(position,innerAdapter);

    }

    @Override
    public int getItemCount() {
        return mModels.size();
    }

    @Override
    public void onItemClick(int facility_id,int option_id,String name) {
        mainRecyclerviewClickInterface.onItemClick(facility_id,option_id,name);
        adapterMap.get(facility_id).notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView headerTxt;
        RecyclerView recyclerView;
        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.item_recyclerview_id);
            headerTxt = itemView.findViewById(R.id.tv_header);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));


        }
    }
}
