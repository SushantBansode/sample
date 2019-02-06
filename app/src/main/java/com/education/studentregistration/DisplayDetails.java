package com.education.studentregistration;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DisplayDetails extends AppCompatActivity {


    TextView name,age,number,gender,fees,disscout,total;
    ImageButton imageButton;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_details);

        name=(TextView)findViewById(R.id.user_profile_name);
        imageButton=(ImageButton)findViewById(R.id.user_profile_photo);

        gender=(TextView)findViewById(R.id.gender);
        age=(TextView)findViewById(R.id.age);
        number=(TextView)findViewById(R.id.phone);
        disscout=(TextView)findViewById(R.id.feesafterdisscount);
        fees=(TextView)findViewById(R.id.fees);





        Bundle b=getIntent().getExtras();
      String name1=  b.getString("name");
        String age11=  b.getString("age");
        String numb1=  b.getString("numb");
        String gender1=  b.getString("gender");
        String fees1=  b.getString("fees");
        String disscount1=  b.getString("disscount");
        String total1=  b.getString("total");
        String image=  b.getString("image");
        name.setText(name1);
        gender.setText(gender1);
        age.setText(age11);
        number.setText(numb1);
        fees.setText(fees1);
        disscout.setText(total1);
        imageButton.setImageURI(Uri.parse(image));




    }
}
