package com.example.rajsaraogi.xenisis;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rajsaraogi.xenisis.database.DatabaseHelper;
import com.example.rajsaraogi.xenisis.database.DatabaseSchema;
import com.example.rajsaraogi.xenisis.holder.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComputerDept extends AppCompatActivity {
    String id;
    private List<Event> events = new ArrayList<>();
    private static DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.Adapter adapter;
    //  private RequestQueue requestQueue;
    String deptName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_computer_dept);
        id=getIntent().getExtras().getString("id");
        deptName=getIntent().getExtras().getString("name");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
  //      TextView textView = (TextView)findViewById(R.id.title);
//        textView.setText(deptName);
        setSupportActionBar(toolbar);
     //   getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    //    Toast.makeText(this,id,Toast.LENGTH_LONG).show();
        databaseHelper = new DatabaseHelper(this);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view2);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        events = databaseHelper.events(Arrays.asList(DatabaseSchema.Events.DEPARTMENT_ID),new String[]{id});
        adapter = new EventCardAdapter(events, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }
}
