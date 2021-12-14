package com.example.task1.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.task1.InnerRecyclerviewClickInterface;
import com.example.task1.MainActivity;
import com.example.task1.Models.FacilitiesModel;
import com.example.task1.Models.FacilitiesMotherModel;
import com.example.task1.R;

import java.util.ArrayList;
import java.util.HashMap;

public class InnerRecyclerViewAdapter extends RecyclerView.Adapter<InnerRecyclerViewAdapter.InnerViewHolder>{
    ArrayList<FacilitiesModel> facilitiesModelArrayList;
    Context mContext;
    int facId;
    private InnerRecyclerviewClickInterface innerRecyclerviewClickInterface;
    HashMap<Integer,Integer> selectionMap;


    public InnerRecyclerViewAdapter(Context context, ArrayList<FacilitiesModel> modelList,int facId,HashMap<Integer,Integer> selectionMap, InnerRecyclerviewClickInterface innerRecyclerviewClickInterface) {
        facilitiesModelArrayList = modelList;
        mContext = context;
        this.selectionMap = selectionMap;
        this.facId = facId;
        this.innerRecyclerviewClickInterface = innerRecyclerviewClickInterface;
    }

    @NonNull
    @Override
    public InnerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new InnerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InnerViewHolder holder, int position) {
        holder.iconTxt.setText(facilitiesModelArrayList.get(position).getIcon());
        holder.nameTxt.setText(facilitiesModelArrayList.get(position).getName());

        if (selectionMap.containsKey((MainActivity.facilityClickedId +1))){
            if (selectionMap.get((MainActivity.facilityClickedId+1))==(Integer.parseInt(facilitiesModelArrayList.get(position).getId()))) {
                holder.checked.setVisibility(View.VISIBLE);
            }else {
                holder.checked.setVisibility(View.GONE);
            }
        }else {
            holder.checked.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return facilitiesModelArrayList.size();
    }

    public class InnerViewHolder extends RecyclerView.ViewHolder {
        TextView nameTxt,iconTxt;
        RelativeLayout parentLyt;
        ImageView checked;
        public InnerViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.list_item_name_id);
            iconTxt = itemView.findViewById(R.id.list_item_icon_id);
            parentLyt = itemView.findViewById(R.id.item_parent_layout);
            checked = itemView.findViewById(R.id.list_item_check_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    innerRecyclerviewClickInterface.onItemClick(facId,Integer.parseInt(facilitiesModelArrayList.get(getAdapterPosition()).getId()),facilitiesModelArrayList.get(getAdapterPosition()).getName());
                }
            });
        }
    }
}
