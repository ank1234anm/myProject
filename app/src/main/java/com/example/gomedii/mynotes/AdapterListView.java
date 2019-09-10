package com.example.gomedii.mynotes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterListView extends RecyclerView.Adapter<AdapterListView.CustomViewHolder> {

    private OnItemClickListner onItemClickListner;
    Context context;
    List<User>users;

    public AdapterListView(Context mcontext,OnItemClickListner onItemClickListner,List<User> userList) {
        this.onItemClickListner = onItemClickListner;
        this.context = mcontext;
        this.users=userList;

    }

    public interface OnItemClickListner
    {
        void onItemClickListner(int position,int flag);
    }

    @NonNull
    @Override
    public AdapterListView.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row_layout, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListView.CustomViewHolder customViewHolder, int i) {
       customViewHolder.txtViewList.setText(users.get(i).getTitle());
        customViewHolder.txtViewDescrip.setText(users.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class
    CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtViewList;
        private TextView txtViewDescrip;
        private LinearLayout llMain;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewList =(TextView)itemView.findViewById(R.id.txtViewList);
            txtViewDescrip =(TextView)itemView.findViewById(R.id.txtViewDescrip);
            txtViewList.setOnClickListener(this);
            txtViewDescrip.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            switch (v.getId())
            {
                case R.id.txtViewList:
                    onItemClickListner.onItemClickListner(pos,1);
                    break;
                case R.id.txtViewDescrip:
                    onItemClickListner.onItemClickListner(pos,1);
                    break;
            }
        }
    }
}
