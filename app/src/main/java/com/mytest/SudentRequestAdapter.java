package com.mytest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SudentRequestAdapter extends  RecyclerView.Adapter<SudentRequestAdapter.StudentViewHolder>{
    private Context context;
    private ArrayList<StudentListModelClass> list;


    public SudentRequestAdapter(Context context, ArrayList<StudentListModelClass> list){

        this.context= context;
        this.list=list;

    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.request_student_users_singleitem,parent,false);
        return  new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {

        final  StudentListModelClass modelClass= list.get(position);

        holder.reg_name.setText(modelClass.getName());
        holder.reg_status.setText(modelClass.getStatus());
        holder.reg_date.setText(modelClass.getDate());
        holder.clickOnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j= new Intent(context,Show_Profile.class);
                j.putExtra("Name",modelClass.getName());
                j.putExtra("Date",modelClass.getDate());
                j.putExtra("Status",modelClass.getStatus());
                j.putExtra("Email",modelClass.getEmail());
                j.putExtra("About",modelClass.getAbout());
                j.putExtra("DOB",modelClass.getDate_of_birth());
                j.putExtra("Gender",modelClass.getGender());
                j.putExtra("Istitute",modelClass.getInstitute());
                j.putExtra("Mobile",modelClass.getMobile());
                j.putExtra("Type_User",modelClass.getType_user());
                v.getContext().startActivity(j);


            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public  class StudentViewHolder extends RecyclerView.ViewHolder{
private TextView reg_name,reg_date,reg_status;
private LinearLayout clickOnStudent;
        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            reg_name= itemView.findViewById(R.id.reg_name);
            reg_date= itemView.findViewById(R.id.reg_date);
            reg_status= itemView.findViewById(R.id.reg_status);
            clickOnStudent=itemView.findViewById(R.id.item_single);
        }
    }
}
