package com.example.gomedii.mynotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class EditAddNotesActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtText;
    private TextView txtOk;
    private String value;
    private EditText edtHeader;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_add_notes);
        findViews();
    }

    private void findViews() {
        edtText = (EditText) findViewById(R.id.edtText);
        txtOk = (TextView) findViewById(R.id.txtOk);
        edtHeader = (EditText) findViewById(R.id.edtHeader);
        txtOk.setOnClickListener(this);
        id = getIntent().getIntExtra("id", 0);
        if (id != 0) {
            edtText.setText(ListOfNotesActivity.appDatabase.userDao().getUser(id).getName());
            edtHeader.setText(ListOfNotesActivity.appDatabase.userDao().getUser(id).getTitle());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtOk:
                setData();
                break;
        }
    }

    private void setData() {
        if (edtText.getText() != null && !edtText.getText().toString().isEmpty()) {
            User user = new User();
            user.setName(edtText.getText().toString());
            user.setTitle(edtHeader.getText().toString());
            if (id != 0) {
                user.setId(id);
                ListOfNotesActivity.appDatabase.userDao().update(user);
            } else {
                ListOfNotesActivity.appDatabase.userDao().addUser(user);
            }
            Intent returnIntent = new Intent();
            setResult(RESULT_OK, returnIntent);
            finish();
        }
    }
}

