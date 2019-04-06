package com.sendy.assignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sendy.assignment.Models.ModelDisplayEmployees;
import com.sendy.assignment.R;

import java.util.List;

public class Displayemployees extends RecyclerView.Adapter<Displayemployees.MyViewHolder>  {

    private List<ModelDisplayEmployees> dataSet;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView employee_name, employee_age,employee_salary;



        public MyViewHolder(View itemView) {
            super(itemView);
            this.employee_name = itemView.findViewById(R.id.employee_name);
            this.employee_age = itemView.findViewById(R.id.employee_age);
            this.employee_salary = itemView.findViewById(R.id.employee_salary);

        }


    }

    public Displayemployees(List<ModelDisplayEmployees> data, Context context) {
        this.dataSet = data;
        this.context = context;
    }




    @Override
    public Displayemployees.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_worker_item, parent, false);
        Displayemployees.MyViewHolder myViewHolder = new Displayemployees.MyViewHolder(view);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(final Displayemployees.MyViewHolder holder, final int listPosition) {
        final ModelDisplayEmployees indivindual_sites= dataSet.get(listPosition);
        TextView employee_name = holder.employee_name;
        TextView employee_age = holder.employee_age;
        TextView employee_salary = holder.employee_salary;


        employee_name.setText(indivindual_sites.getEmployee_name());
        employee_age.setText(indivindual_sites.getEmployee_age());
        employee_salary.setText(indivindual_sites.getEmployee_salary());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
