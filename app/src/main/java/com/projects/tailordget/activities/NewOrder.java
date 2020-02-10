package com.projects.tailordget.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.projects.tailordget.datas.AppDatabase;
import com.projects.tailordget.datas.Order;
import com.projects.tailordget.datas.Profile;
import com.projects.tailordget.datas.Status;
import com.projects.tailordget.datas.Type;
import com.projects.tailordget.utilities.AppExecutors;
import com.projects.test.tailordget.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewOrder extends AppCompatActivity {

    public static final String EDIT_MODE = "editMode";

    private static final String LOG_TAG = NewOrder.class.getSimpleName();
    private Toolbar newOrderToolbar;
    private static AppDatabase mDB;
    private ArrayAdapter<String> profileArrayAdapter;
    private ArrayAdapter<String> typeArrayAdapter;
    private ArrayAdapter<String> statusArrayAdapter;
    private List<Profile> profileList;
    private List<Order> orderList;
    private List<Type> typeList;
    private List<Status> statusList;
    private List<String> profileNameList;
    private List<String> typeNameList;
    private List<String> statusNameList;
    private EditText nameEditText, priceEditText, detailsEditText;
    private Spinner typeSpinner, profileSpinner, statusSpinner;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        newOrderToolbar = (Toolbar) findViewById(R.id.newOrderToolbar);
        profileList = new ArrayList<>();
        typeList = new ArrayList<>();
        statusList = new ArrayList<>();
        profileNameList = new ArrayList<>();
        typeNameList = new ArrayList<>();
        statusNameList = new ArrayList<>();
        nameEditText = (EditText) findViewById(R.id.orderNameEditText);
        priceEditText = (EditText) findViewById(R.id.priceEditText);
        detailsEditText = (EditText) findViewById(R.id.orderDetailsEditText);
        typeSpinner = (Spinner) findViewById(R.id.orderTypeSpinner);
        statusSpinner = (Spinner) findViewById(R.id.orderStatusSpinner);

        mDB = AppDatabase.getInstance(getApplicationContext());

        getProfilesData();
        getTypeOrderData();
        getStatusOrderData();

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
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
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
        // Saving the Defaults spinners values
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                saveDefaultTypes();
                saveDefaultStatus();
            }
        });
        populateProfileSpinner();
        populateTypeSpinner();
        populateStatusSpinner();
    }

    private void saveDefaultStatus() {
//        mDB.statusDao().deleteAllStatus();
        mDB.statusDao().insertStatus(new Status("Pending"));
        mDB.statusDao().insertStatus(new Status("Complete"));
        mDB.statusDao().insertStatus(new Status("Delivered"));
    }

    private void saveDefaultTypes() {
//        mDB.typeDao().deleteAllType();
        mDB.typeDao().insertType(new Type("Shirt"));
        mDB.typeDao().insertType(new Type("T-Shirt"));
        mDB.typeDao().insertType(new Type("Tunic"));
        mDB.typeDao().insertType(new Type("Pants"));
    }

    private void populateProfileSpinner() {
        for(Profile p : profileList) {
            profileNameList.add(p.getName());
        }
        profileArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, profileNameList);
        profileArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        profileSpinner = (Spinner) findViewById(R.id.profileOrderingSpinner);
        profileSpinner.setAdapter(profileArrayAdapter);
    }

    private void populateTypeSpinner() {
        for (Type t : typeList) {
            typeNameList.add(t.getName());
        }
        typeArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeNameList);
        typeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner = (Spinner) findViewById(R.id.orderTypeSpinner);
        typeSpinner.setAdapter(typeArrayAdapter);
    }

    private void populateStatusSpinner() {
        for(Status s : statusList) {
            statusNameList.add(s.getName());
        }

        statusArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statusNameList);
        statusArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        statusSpinner = (Spinner) findViewById(R.id.orderStatusSpinner);
        statusSpinner.setAdapter(statusArrayAdapter);
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
        for(Type t : typeList) {
            if(t.getName().equals(typeSpinner.getSelectedItem().toString())) {
                newOrder.setType(t.getId());
            }
        }
        for(Profile p : profileList) {
            if(p.getName().equals(profileSpinner.getSelectedItem().toString())) {
                newOrder.setProfile(p.getId());
            }
        }
        for(Status s : statusList) {
            if(s.getName().equals(statusSpinner.getSelectedItem().toString())) {
                newOrder.setType(s.getId());
            }
        }
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

    public void getTypeOrderData() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                typeList = mDB.typeDao().loadAllTypes();
            }
        });
    }

    public void getStatusOrderData() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                statusList = mDB.statusDao().loadAllStatus();
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

//Verifying the OnItemSelectedListener
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
//        System.out.println("=======================");
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
