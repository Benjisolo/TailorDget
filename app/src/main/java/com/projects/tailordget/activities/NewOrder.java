package com.projects.tailordget.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projects.tailordget.datas.AppDatabase;
import com.projects.tailordget.datas.Order;
import com.projects.tailordget.datas.Profile;
import com.projects.tailordget.utilities.AppExecutors;
import com.projects.test.tailordget.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewOrder extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public static final String EDIT_MODE = "editMode";

    private static final String LOG_TAG = NewOrder.class.getSimpleName();
    private Toolbar newOrderToolbar;
    private static AppDatabase mDB;
    private ArrayAdapter<String> arrayAdapter;
    private List<Profile> profileList;
    private List<String> profileNameList;
    private EditText nameEditText, priceEditText, detailsEditText;
    private Spinner typeSpinner, profileSpinner, statusSpinner;

    private List<Order> orderList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        newOrderToolbar = (Toolbar) findViewById(R.id.newOrderToolbar);
        profileList = new ArrayList<>();
        profileNameList = new ArrayList<>();
        nameEditText = (EditText) findViewById(R.id.orderNameEditText);
        priceEditText = (EditText) findViewById(R.id.priceEditText);
        detailsEditText = (EditText) findViewById(R.id.orderDetailsEditText);
        typeSpinner = (Spinner) findViewById(R.id.orderTypeSpinner);
        statusSpinner = (Spinner) findViewById(R.id.orderStatusSpinner);

        mDB = AppDatabase.getInstance(getApplicationContext());

        getProfilesData();

        Bundle extraData = getIntent().getExtras();
//        final boolean editMode = (boolean)extraData.get(EDIT_MODE);
        newOrderToolbar.inflateMenu(R.menu.save_menu);
        newOrderToolbar.setOnMenuItemClickListener(new android.support.v7.widget.Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem == newOrderToolbar.getMenu().getItem(0)) {
//                    if(editMode) {
//                        updateOrder();
//                        Toast.makeText(getApplicationContext(), "Order updated successfully!", Toast.LENGTH_LONG).show();
//                    }
//                    else {
                        saveOrder();
                        Toast.makeText(getApplicationContext(), "Order saved successfully!", Toast.LENGTH_LONG).show();
//                    }
//                    finish();
                }
                return true;
            }
        });
        newOrderToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        for(Profile p : profileList) {
            profileNameList.add(p.getName());
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, profileNameList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        profileSpinner = (Spinner) findViewById(R.id.profileOrderingSpinner);
        profileSpinner.setAdapter(arrayAdapter);
    }

    public void saveOrder() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final Order newOrder = new Order();
                final Date currentDate = new Date();
                initOrderInformations(newOrder, currentDate);
                newOrder.setDateRecord(currentDate);
                mDB.orderDao().insertOrder(newOrder);
            }
        });
    }

    private void initOrderInformations(Order newOrder, Date dateUpdate) {
        newOrder.setTitle(nameEditText.getText().toString());
        newOrder.setType(Integer.valueOf(typeSpinner.getSelectedItem().toString()));
        for(Profile p : profileList) {
            if(p.getName().equals(profileSpinner.getSelectedItem().toString())) {
                newOrder.setProfile(p.getId());
            }
        }
        newOrder.setStatus(Integer.valueOf(statusSpinner.getSelectedItem().toString()));
        newOrder.setPrice(Float.valueOf(priceEditText.getText().toString()));
        newOrder.setDetails(detailsEditText.getText().toString());
        newOrder.setDateUpdate(dateUpdate);
    }

    public void getProfilesData() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                profileList = mDB.profileDao().loadAllProfiles();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public List<String> getProfileNameList() {
        return profileNameList;
    }

    public void setProfileNameList(List<String> profileNameList) {
        this.profileNameList = profileNameList;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        System.out.println("=======================");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
