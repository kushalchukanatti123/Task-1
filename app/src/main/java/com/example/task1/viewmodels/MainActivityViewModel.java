package com.example.task1.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.task1.Models.ExclusionsModel;
import com.example.task1.Models.FacilitiesMotherModel;
import com.example.task1.repositories.FacilitiesAndExclusionsRepository;

import java.util.ArrayList;

public class MainActivityViewModel extends AndroidViewModel {

    //The data we fetch from asynchronously
    private MutableLiveData<ArrayList<FacilitiesMotherModel>> facilitiesList = new MutableLiveData<ArrayList<FacilitiesMotherModel>>();
    private MutableLiveData<ArrayList<ArrayList<ExclusionsModel>>> exclusionsList = new MutableLiveData<ArrayList<ArrayList<ExclusionsModel>>>();
    private FacilitiesAndExclusionsRepository facilitiesAndExclusionsRepository;

    public MainActivityViewModel(Application application) {
        super(application);
    }

    public void init(){
        if(facilitiesList != null){
            facilitiesList= new MutableLiveData<>();
        }
        if (exclusionsList!=null){
            exclusionsList = new MutableLiveData<>();
        }
        facilitiesAndExclusionsRepository = FacilitiesAndExclusionsRepository.getInstance(); //Initialize the repository
        facilitiesList = facilitiesAndExclusionsRepository.getFacilities(this.getApplication());
        exclusionsList = facilitiesAndExclusionsRepository.getExclusions();
    }

    public LiveData<ArrayList<FacilitiesMotherModel>> getFacilities() {
        if(facilitiesList == null){
            facilitiesList = new MutableLiveData<>();
        }
        return facilitiesList;
    }
    public LiveData<ArrayList<ArrayList<ExclusionsModel>>> getExclusions() {
        if(exclusionsList == null){
            exclusionsList = new MutableLiveData<>();
        }
        return exclusionsList;
    }
}
