package com.education.studentregistration;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Resistration extends AppCompatActivity

{

    DataBaseHelper db;
    Button submilt;
    EditText namme,phonno,age,fees;
    Spinner discount;
    RadioGroup rd;
    TextView tv;
    public  static int selected;
    Button camera;

    Uri uri;
String path;

    public static final int PICK_IMAGE = 1;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == PICK_IMAGE) {
            Uri selectedImage = data.getData();
            profile.setImageURI(selectedImage);
            savetocard(selectedImage);
        }
    }

    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistration);
        camera=(Button)findViewById(R.id.image);

        profile=(ImageView)findViewById(R.id.profile);
        namme=(EditText)findViewById(R.id.signup_input_name);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(namme.getText().toString().length()>0){

                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                }else {
                    Toast.makeText(Resistration.this,"Please Provide name first",Toast.LENGTH_LONG).show();
                }


            }
        });

        rd=(RadioGroup)findViewById(R.id.radiogroup);
        tv=(TextView)findViewById(R.id.totlal) ;


        age=(EditText)findViewById(R.id.age);
        fees=(EditText)findViewById(R.id.fees);
        discount=(Spinner) findViewById(R.id.diss);

        fees.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(fees.getText().toString().length()>0){
                    double k=Double.parseDouble(fees.getText().toString());
                    double val=k-((selected/100.0)*k);
                    tv.setText(""+val);
                }

            }
        });

        phonno=(EditText)findViewById(R.id.phone);
        db=new DataBaseHelper(this);

        // Spinner click listener


        // Spinner Drop down elements
        List<Integer> categories = new ArrayList<Integer>();
        categories.add(10);
        categories.add(20);


        // Creating adapter for spinner
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        discount.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();

                // Showing selected spinner item
                Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                selected=Integer.parseInt(item);
                docal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
discount.setAdapter(dataAdapter);
        // attaching data adapter to spinner

       // db.insertData("sus",123,15,"male","file://",10,10,22);

        /*db.add("xx","zzzz");
        Cursor c=db.getAllData();
        //Cursor c = db.rawQuery(sql, null);
        if(c.moveToFirst())
        {
            do{
              ;
                Toast.makeText(Resistration.this,""+ c.getString(0),Toast.LENGTH_LONG).show();
            }while(c.moveToNext());
        }*/



        submilt=(Button)findViewById(R.id.Register);
        submilt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                /*if (namme.getText().toString().length()>0&&age.getText().toString().length()>0&&fees.getText().toString().length()>0&&discount.getText().toString().length()>0&&phonno.getText().toString().length()>0)
                {

                add();
                }*/

                if (namme.getText().toString().length()==0)
                {

                    Toast.makeText(Resistration.this,"please insert name",Toast.LENGTH_LONG).show();

                }
                else if(age.getText().toString().length()==0){
                    Toast.makeText(Resistration.this,"please insert Age",Toast.LENGTH_LONG).show();
                }
                else if (fees.getText().toString().length()==0)
                {
                    Toast.makeText(Resistration.this,"please insert fees",Toast.LENGTH_LONG).show();
                }
                else if (phonno.getText().toString().length()==0)
                {
                    Toast.makeText(Resistration.this,"please insert phone",Toast.LENGTH_LONG).show();
                }
                else{
                    add();
                }



            }
        });





    }

    public void add()
    {

        int selectedId = rd.getCheckedRadioButtonId();
        String gender;
        if(selectedId == R.id.femal)
            gender = "Female";
        else
            gender = "Male";

        db.insertData(namme.getText().toString(),phonno.getText().toString(),age.getText().toString(),gender,path,fees.getText().toString(),""+selected,tv.getText().toString());
        Toast.makeText(Resistration.this,"Added Sucessfully",Toast.LENGTH_LONG).show();

    }

    public void docal()
    {
        double totalval;
        String s=fees.getText().toString();
        if (s.equals(""))
           totalval=0.0;
        else
            totalval=Double.parseDouble(s);

        double actfee=totalval-((selected/100.0)*totalval);
        tv.setText(""+actfee);

    }

    public void savetocard(Uri imageUri){

        try {
        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
        OutputStream output;

    File filepath = Environment.getExternalStorageDirectory();

    // Create a new folder in SD Card
    File dir = new File(filepath.getAbsolutePath()
            + "/Save Image Tutorial/");
    dir.mkdirs();

    // Create a name for the saved image
    File file = new File(dir, namme.getText().toString()+".png");

    // Show a toast message on successful save
    Toast.makeText(Resistration.this, "Image Saved to SD Card",
            Toast.LENGTH_SHORT).show();


        output = new FileOutputStream(file);
       path = file.getAbsolutePath();

        // Compress into png format image from 0% - 100%
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
        output.flush();
        output.close();


    }

    catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}


}
