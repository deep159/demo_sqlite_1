package com.sts.sqlitedemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button mInsertButton,mViewButton,mDeleteButton,mUpdateButton,mFindButton;
    EditText mIdEditText,mNameEditText,mDeleteEdittext,mFindEdittext;
    DB_Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler=new DB_Handler(this);
        handler.getWritableDatabase();

        mIdEditText= (EditText) findViewById(R.id.id_edittext);
        mNameEditText= (EditText) findViewById(R.id.name_edittext);
        mInsertButton= (Button) findViewById(R.id.insert_button);
        mInsertButton.setOnClickListener(this);
        mViewButton= (Button) findViewById(R.id.view_button);
        mViewButton.setOnClickListener(this);
        mDeleteButton= (Button) findViewById(R.id.delete_button);
        mDeleteButton.setOnClickListener(this);
        mDeleteEdittext= (EditText) findViewById(R.id.delete_editext);

        mUpdateButton= (Button) findViewById(R.id.update_button);
        mUpdateButton.setOnClickListener(this);

        mFindEdittext= (EditText) findViewById(R.id.view_particular_editext);
        mFindButton= (Button) findViewById(R.id.find_button);
        mFindButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(view.getId()==R.id.insert_button)
        {
            String id=mIdEditText.getText().toString().trim();
            String name=mNameEditText.getText().toString().trim();
            handler.insertData(id,name);
            Toast.makeText(this, "data inserted", Toast.LENGTH_SHORT).show();
        }
        if(view.getId()==R.id.view_button)
        {
            handler.viewDate();
        }
        if(view.getId()==R.id.delete_button)
        {
            String id=mDeleteEdittext.getText().toString().trim();
            handler.deleteData(id);
        }
        if(view.getId()==R.id.update_button)
        {
            String oldname=mIdEditText.getText().toString().trim();
            String newname=mNameEditText.getText().toString().trim();
            handler.updateData(oldname,newname);
        }
        if(view.getId()==R.id.find_button)
        {
            String id=mFindEdittext.getText().toString().trim();
            handler.findData(id);

        }
    }
}
