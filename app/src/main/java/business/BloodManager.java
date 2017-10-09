package business;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import persistence.Service;

/**
 * Created by User on 9/30/2015.
 */
public class BloodManager {
    Service service;

    public BloodManager(Context context) {
        service = new Service(context);
    }

    public void registerDonor(DonorTO donorTO) throws Exception{
        Validation validator = new Validation();
        validator.validateDonor(donorTO);
        service.persistDonor(donorTO);
    }

    public List<DonorTO> fetchDonorsByBloodGroup(String bloodGroup) throws Exception{
        List<DonorTO> donorsList = service.fetchDonorsByBloodGroup(bloodGroup);
        if(donorsList.size() == 0) throw new Exception("List Empty: No Donor found for this blood group");
        Log.d("getAllDonors()", donorsList.toString());
        return donorsList;
    }

    public List<DonorTO> fetchDonorsByBloodGroupAndAddress(String bloodGroup, String address) throws Exception{
        //filter out by location
        List<DonorTO> donorsList = fetchDonorsByBloodGroup(bloodGroup);
        List<DonorTO> newDonorsList = new ArrayList<DonorTO>();
        for (DonorTO donorTO : donorsList) {
            if(donorTO.getAddress().equals(address)){
                newDonorsList.add(donorTO);
            }
        }
        if(donorsList.size() == 0) throw new Exception("List Empty: No donor found in this area");
        Log.d("getAllDonorsBy()", newDonorsList.toString());
        return newDonorsList;
    }
}
