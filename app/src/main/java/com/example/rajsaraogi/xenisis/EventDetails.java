package com.example.rajsaraogi.xenisis;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajsaraogi.xenisis.database.DatabaseHelper;
import com.example.rajsaraogi.xenisis.holder.Event;

import org.w3c.dom.Text;

public class EventDetails extends AppCompatActivity {
    String event_id;
    Button b1,b2;
    ImageView im;
    TextView event_name,event_description,event_date,event_time,event_prize;
    String mno,ename;
    private Event event;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        event_id=getIntent().getExtras().getString("event_id");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView)findViewById(R.id.title);
        textView.setText("Event Details");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        databaseHelper = new DatabaseHelper(this);

        Toast.makeText(this,event_id,Toast.LENGTH_LONG).show();

        b1=(Button)findViewById(R.id.form);
        b2=(Button)findViewById(R.id.call);
        im=(ImageView)findViewById(R.id.eventImage);
        event_name=(TextView)findViewById(R.id.text_event_name);
        event_description=(TextView)findViewById(R.id.text_event_description);
        event_date=(TextView)findViewById(R.id.text_event_date);
        event_time=(TextView)findViewById(R.id.text_event_time);
        event_prize=(TextView)findViewById(R.id.text_event_prize);
        ename=event_name.getText().toString();
        addView();
    }

    public void addView() {
        event = databaseHelper.events(event_id);
        Log.d("event",event+" "+event.eventName+" "+event.eventDescription);
        event_name.setText(event.eventName);
        event_description.setText(event.eventDescription);
        im.setImageResource(event.imageName);
        mno=event.coordinateMobile;
    }

    public void regForm(View view){
        this.startActivity(new Intent(this, RegistrationForm.class).putExtra("event_name", ename));
    }
    public void call(View view){
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:"+mno));
        startActivity(callIntent);
    }
}
