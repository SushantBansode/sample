package com.education.studentregistration;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowStudentList extends AppCompatActivity {


    ArrayList<String> name,gender,number,fees,disscount,total,age,Images;
    Cursor c;
    DataBaseHelper db;
    ListView lv;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_student_list);

        name=new ArrayList<>();
        gender=new ArrayList<>();
        number=new ArrayList<>();
        fees=new ArrayList<>();
        disscount=new ArrayList<>();
        total=new ArrayList<>();
        age=new ArrayList<>();
        Images=new ArrayList<>();


        lv=(ListView)findViewById(R.id.listview);
        db=new DataBaseHelper(this);
        c=db.getAllData();


        if(c.moveToFirst())
        {
            do{
                name.add(c.getString(0));

                number.add(c.getString(1));
                age.add(c.getString(2));


                gender.add(c.getString(3));
                Images.add(c.getString(4));
                fees.add(c.getString(5));
                disscount.add(c.getString(6));
                total.add(c.getString(7));
            }while(c.moveToNext());
        }

        adapter=new CustomAdapter(this,name,Images,gender);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Intent in=new Intent(ShowStudentList.this,DisplayDetails.class);
                in.putExtra("name",name.get(i));
                in.putExtra("age",age.get(i));
                in.putExtra("numb",number.get(i));
                in.putExtra("gender",gender.get(i));
                in.putExtra("fees",fees.get(i));
                in.putExtra("disscount",disscount.get(i));
                in.putExtra("total",total.get(i));
                in.putExtra("image",Images.get(i));
                startActivity(in);

            }
        });


    }
}
