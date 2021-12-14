package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.task1.Adapters.MainRecyclerviewAdapter;
import com.example.task1.Models.ExclusionsModel;
import com.example.task1.Models.FacilitiesModel;
import com.example.task1.Models.FacilitiesMotherModel;
import com.example.task1.viewmodels.MainActivityViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity  implements MainRecyclerviewClickInterface{

    RecyclerView recyclerView;
    MainRecyclerviewAdapter adapter;
    ArrayList<FacilitiesMotherModel> facilitiesMotherModelArrayList = new ArrayList<>();
    HashMap<Integer,Integer> selectionMap = new HashMap<>();
    public static int facilityClickedId;
    public static ArrayList<ArrayList<ExclusionsModel>> exclusionsList;
    Map<String,Map<ExclusionsModel,ExclusionsModel>> exclusionMap = new HashMap<>();




    //View model objects
    private MainActivityViewModel mainActivityViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerv_view);

        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mainActivityViewModel.init();
        mainActivityViewModel.getFacilities().observe(this, new Observer<List<FacilitiesMotherModel>>() {
            @Override
            public void onChanged(List<FacilitiesMotherModel> decodeFacilityDetails) {
                if (adapter == null) {
                    initRecyclerView();
                }
                adapter.notifyDataSetChanged();
            }
        });
    }


    private void initRecyclerView() {
        adapter = new MainRecyclerviewAdapter(this,mainActivityViewModel.getFacilities().getValue(),selectionMap,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onItemClick(int facility_id,int option_id,String name) {

        selectionMap.put((facility_id+1),(option_id));
        facilityClickedId = facility_id;
    }



}