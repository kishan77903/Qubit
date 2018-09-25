package com.example.rohittamra.indiahackathron;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PowerCalculation extends AppCompatActivity implements View.OnClickListener {
    TextView power,error;
    EditText devices,rating,Mobileno;
    String devicename,ratingentered;
    String customeridGOT,powerGOT;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    Button save,signout,plotGraph,calcerror;
    int count =0;
    DatabaseReference db;
    private ProgressDialog mProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_power_calculation);
        power=(TextView)findViewById(R.id.power);
        devices=(EditText)findViewById(R.id.devices);
        rating=(EditText)findViewById(R.id.rating);
        save=(Button)findViewById(R.id.Save);
        save.setOnClickListener(this);
        signout=(Button)findViewById(R.id.signouuut);
        signout.setOnClickListener(this);
        Mobileno=(EditText)findViewById(R.id.mobileno);
        firebaseAuth=FirebaseAuth.getInstance();
        plotGraph=(Button)findViewById(R.id.plotGraph);
        plotGraph.setOnClickListener(this);
        error=(TextView)findViewById(R.id.error);
        calcerror=(Button)findViewById(R.id.calcError);
        calcerror.setOnClickListener(this);
        fetchData();
    }

    @Override
    public void onClick(View v) {
        if (v == save)
        {
            devicename = devices.getText().toString();
            ratingentered=rating.getText().toString();
            customeridGOT=Mobileno.getText().toString();
            powerGOT=power.getText().toString();
            databaseReference= FirebaseDatabase.getInstance().getReference("Customer").push();
            SupportingClass  supportingclass=new SupportingClass(customeridGOT,devicename,ratingentered,powerGOT);
            databaseReference.child(String.valueOf(count)).child("customeridGOT").setValue(supportingclass.getCustomeridGOT());
            databaseReference.child(String.valueOf(count)).child("devicename").setValue(supportingclass.getDevicename());
            databaseReference.child(String.valueOf(count)).child("ratingentered").setValue(supportingclass.getRatingentered());
            databaseReference.child(String.valueOf(count)).child("powerGOT").setValue(supportingclass.getPowerGOT());
            count++;
            Toast.makeText(getApplicationContext(),"Values Added To Cart",Toast.LENGTH_LONG).show();
        }
        if(v==signout)
        {
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            firebaseAuth.signOut();
        }
        if(v==plotGraph)
        {
        }
        if(v==calcerror)
        {
            float p=Float.parseFloat(power.getText().toString());
            float r=Float.parseFloat(rating.getText().toString());
            float total=(p-r)/100;
            error.setText(""+total+"%");
            if(total>15.0)
                {
                    Toast.makeText(getApplicationContext(),"Need Maintainance(High risk)",Toast.LENGTH_LONG).show();
                }
        }
    }

    private void fetchData() {

        db = FirebaseDatabase.getInstance().getReference();
        db.child("plot").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Toast.makeText(getApplicationContext(),""+dataSnapshot.getValue(),Toast.LENGTH_LONG).show();
                        power.setText(""+dataSnapshot.getValue());
                        }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                }
        );
    }
}
