package com.example.gomedii.mynotes;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListOfNotesActivity extends AppCompatActivity implements View.OnClickListener, AdapterListView.OnItemClickListner {

    private RecyclerView rvNotes;
    private LinearLayoutManager linearLayoutManager;
    private ImageView addIcon;
    private int REQUEST_CODE = 101;
    public static AppDatabase appDatabase;
    private AdapterListView adapterListView;
    private List<User> users;
    private int pos;
    private TextView noList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_notes);
        findViews();
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "userdb").allowMainThreadQueries().build();
        setData();
    }

    private void setData() {
        users = appDatabase.userDao().getUsers();

        if ( users.size()==0) {
            noList.setVisibility(View.VISIBLE);
        }
        rvNotes.setVisibility(View.VISIBLE);
        adapterListView = new AdapterListView(this, this, users);
        rvNotes.setAdapter(adapterListView);


    }

    private void findViews() {
        rvNotes = (RecyclerView) findViewById(R.id.rvNotes);
        addIcon = (ImageView) findViewById(R.id.addIcon);
        noList = (TextView) findViewById(R.id.noList);
        linearLayoutManager = new LinearLayoutManager(ListOfNotesActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNotes.setLayoutManager(linearLayoutManager);
        addIcon.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addIcon:
                navigateEdit(false, 0);
                break;
        }
    }

    private void navigateEdit(boolean isEmpty, int id) {
        Intent intent = new Intent(ListOfNotesActivity.this, EditAddNotesActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, REQUEST_CODE);

    }

    @Override
    public void onItemClickListner(int position, int flag) {
        pos = position;
        int id = users.get(position).getId();
        navigateEdit(true, id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            noList.setVisibility(View.GONE);
            users.clear();
            users.addAll(appDatabase.userDao().getUsers());
            adapterListView.notifyDataSetChanged();


        }
    }
}
