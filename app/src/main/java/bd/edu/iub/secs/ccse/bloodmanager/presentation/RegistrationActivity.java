package bd.edu.iub.secs.ccse.bloodmanager.presentation;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import bd.edu.iub.secs.ccse.bloodmanager.R;
import business.BloodManager;
import business.DonorTO;

/**
 * Created by User on 9/30/2015.
 */
public class RegistrationActivity extends Activity
{
    EditText name;
    EditText age;
    EditText email;
    EditText phone;
    EditText noOfTimesDonated;

    Spinner area;
    Spinner bloodGroup;

    Switch donationStatus;
    Button submit;

    String nameValue;
    String ageValue;
    String emailValue;
    String phoneValue;
    String areaValue;
    String bloodGroupValue;
    Boolean donationStatusValue = true;
    Integer noOfTimesDonatedValue;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initView();
    }

    private void initView()
    {
        name = (EditText) findViewById(R.id.etName);
        email = (EditText) findViewById(R.id.etEmail);
        age = (EditText) findViewById(R.id.etAge);
        phone = (EditText) findViewById(R.id.etPhone);
        noOfTimesDonated = (EditText) findViewById(R.id.etNoOfTimesDonated);

        bloodGroup = (Spinner) findViewById(R.id.spnBloodGroup);
        area = (Spinner) findViewById(R.id.spnArea);
        ArrayAdapter<CharSequence> bloodGroupAdapter = ArrayAdapter.createFromResource(this,
                R.array.blood_group_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        bloodGroupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        bloodGroup.setAdapter(bloodGroupAdapter);
        bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                bloodGroupValue = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> areaAdapter = ArrayAdapter.createFromResource(this,
                R.array.area_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        areaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        area.setAdapter(areaAdapter);
        area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                areaValue = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        donationStatus = (Switch) findViewById(R.id.swDonationStatus);
        donationStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    donationStatusValue = true;
                }
                else
                {
                    donationStatusValue = false;
                }
            }
        });


        submit = (Button) findViewById(R.id.btnSubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nameValue = name.getText().toString();
                ageValue = age.getText().toString();
                emailValue = email.getText().toString();
                phoneValue = phone.getText().toString();
                noOfTimesDonatedValue = Integer.parseInt(noOfTimesDonated.getText().toString());

                DonorTO donorTO = new DonorTO();
                donorTO.setAge(Integer.parseInt(ageValue));
                donorTO.setBloodGroup(bloodGroupValue);
                donorTO.setName(nameValue);
                donorTO.setAddress(areaValue);
                donorTO.setEmail(emailValue);
                donorTO.setDonationStatus(donationStatusValue);
                donorTO.setNumberOfTimesDonated(noOfTimesDonatedValue);
                donorTO.setPhone(phoneValue);

                BloodManager bloodManager = new BloodManager(RegistrationActivity.this);
                try
                {
                    bloodManager.registerDonor(donorTO);
                    Utility.showAlert(RegistrationActivity.this, "Success", "You are registered now!");
                    //finish();
                } catch (Exception e) {
                    e.printStackTrace();
                    Utility.showAlert(RegistrationActivity.this, "Warning", e.getMessage());
                }

            }
        });

    }
}
