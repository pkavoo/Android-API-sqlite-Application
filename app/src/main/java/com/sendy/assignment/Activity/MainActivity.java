package com.sendy.assignment.Activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.sendy.assignment.Models.ModelDisplayEmployees;
import com.sendy.assignment.R;
import com.sendy.assignment.adapter.Displayemployees;
import com.sendy.assignment.database.DatabaseHelper;
import com.sendy.assignment.database.Worker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog;
    String employee_url,employee_name, employee_salary, employee_age;
    RecyclerView recycler_view;
    Displayemployees displayemployees;
    DatabaseHelper db;
    private List<ModelDisplayEmployees> employees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        employee_url="http://dummy.restapiexample.com/api/v1/employees";

        dialog = new ProgressDialog(this, R.style.AppTheme_Dark_Dialog);

        db = new DatabaseHelper(this);

        recycler_view = findViewById(R.id.recyclerView);
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        employees = new ArrayList<>();


        //initializing adapter
        displayemployees = new Displayemployees(employees, this);
        recycler_view.setAdapter(displayemployees);

        getEmployees();

    }
    private void createEmployee(String name, String age, String salary)

    {
        long id = db.insertWorker(new Worker(name, age, salary));
    }
    public void getEmployees()
    {
        dialog.show();

        StringRequest commonRequest = new StringRequest(Request.Method.GET, employee_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //HANDLE RESPONSE
                dialog.dismiss();

                try{

                //JSONObject jsonObject = new JSONObject(response);
                JSONArray jsonArray = new JSONArray(response);
                for (int p = 0; p < jsonArray.length(); p++) {


                    ModelDisplayEmployees mOdelDisplayEmployees = new ModelDisplayEmployees();
                    JSONObject employeedetails = jsonArray.getJSONObject(p);


//

                    String employeename = employeedetails.getString("employee_name");
                    String employeeage = employeedetails.getString("employee_salary");
                    String employeesalary = employeedetails.getString("employee_age");


                    //add data to local db
                    createEmployee(employeename,
                            employeeage,
                            employeesalary
                            );
                 //   Toast.makeText(getApplicationContext(),"Saved to local database succesfully",Toast.LENGTH_LONG).show();

                    mOdelDisplayEmployees.setEmployee_name(employeename);
                    mOdelDisplayEmployees.setEmployee_age(employeeage);
                    mOdelDisplayEmployees.setEmployee_salary(employeesalary);



                    if (employees.contains(employeename)) {
                        /*do nothing*/
                    } else {
                        employees.add(mOdelDisplayEmployees);
                    }

                }
                } catch (JSONException e) {
                    e.printStackTrace();
                    e.getMessage();
                }
                displayemployees.notifyDataSetChanged();
                }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();



            }
        }) ;

        commonRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 1, 2));
        MySingleton.getInstance(this).addToRequestQueue(commonRequest);


    }
}
