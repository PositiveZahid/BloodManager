package bd.edu.iub.secs.ccse.bloodmanager.presentation;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import bd.edu.iub.secs.ccse.bloodmanager.R;
import business.BloodManager;
import business.DonorTO;

public class DonorListActivity extends ActionBarActivity {

    ListView donorList;
    String bloodGroup;
    String area;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_list);
        Bundle bundle = getIntent().getExtras();
        bloodGroup = (String) bundle.get("bloodGroup");
        area = (String) bundle.get("area");
        initView();
    }

    private void initView()
    {
        donorList = (ListView) findViewById(R.id.lvDonorList);
        BloodManager bloodManager = new BloodManager(this);

        List<DonorTO> list = null;
        try {
            list = bloodManager.fetchDonorsByBloodGroupAndAddress(bloodGroup, area);
            if(list!= null){
                DonorListAdapter adapter = new DonorListAdapter(this, (ArrayList<DonorTO>) list);
                donorList.setAdapter(adapter);
            }
            else{
                ArrayList<DonorTO> emptyList = new ArrayList<DonorTO>();
                DonorTO emptyDonor = new DonorTO();
                emptyDonor.setEmail("No Donor Found");
                emptyDonor.setPhone("No Donor Found");
                emptyList.add(emptyDonor);

                DonorListAdapter adapter = new DonorListAdapter(this, emptyList);
                donorList.setAdapter(adapter);
            }

        } catch (Exception e) {
            Utility.showAlert(DonorListActivity.this, "Exception", e.getMessage());
            e.printStackTrace();

            //finish();
        }

        donorList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                DonorTO d = (DonorTO) parent.getItemAtPosition(position);
                Toast.makeText(DonorListActivity.this, d.getPhone(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_donor_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
