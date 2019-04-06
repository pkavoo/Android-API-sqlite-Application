package com.sendy.assignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sendy.assignment.R;
import com.sendy.assignment.database.Worker;

import java.util.List;


public class WorkerAdapter extends RecyclerView.Adapter<WorkerAdapter.MyViewHolder> {

    private Context context;
    private List<Worker> workerList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView employee_name;
        public TextView employee_age;
        public TextView employee_salary;

        public MyViewHolder(View view) {
            super(view);
            employee_name = view.findViewById(R.id.employee_name);
            employee_age = view.findViewById(R.id.employee_age);
            employee_salary = view.findViewById(R.id.employee_salary);
        }
    }


    public WorkerAdapter(Context context, List<Worker> workersList) {
        this.context = context;
        this.workerList = workersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_worker_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Worker worker = workerList.get(position);

        holder.employee_name.setText(worker.getName());
        holder.employee_age.setText(worker.getAge());
        holder.employee_salary.setText(worker.getSalary());


        // Formatting and displaying timestamp
//        holder.time_in.setText(worker.getTime_in());
    }

    @Override
    public int getItemCount() {
        return workerList.size();
    }

}