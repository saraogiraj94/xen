package com.example.rajsaraogi.xenisis;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RegistrationForm extends AppCompatActivity {
    private String TAG_URL="http:\\xenesis.ldrp.ac.in/api/api_mb_reg.php";
    //private String TAG="http://myandroiddevelopment.esy.es/reg.php";
    private ProgressDialog pDialog;
    private RequestQueue requestQueue;
    EditText edit_student_name,edit_student_college,edit_student_email,edit_student_mobile;
    String sname,cname,smobile,semail,ip,ename;
    String event_name;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_form);
        event_name=getIntent().getExtras().getString("event_name");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView)findViewById(R.id.title);
        textView.setText("Registration Form");
        setSupportActionBar(toolbar);
        try { getSupportActionBar().setDisplayShowTitleEnabled(false); } catch (NullPointerException e) { e.printStackTrace(); }
        pDialog=new ProgressDialog(this);
        pDialog.setCancelable(false);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        edit_student_name=(EditText)findViewById(R.id.edit_student_name);
        edit_student_college=(EditText)findViewById(R.id.edit_student_college);
        edit_student_email=(EditText)findViewById(R.id.edit_student_email);
        edit_student_mobile=(EditText)findViewById(R.id.edit_student_phone_no);
        reg=(Button)findViewById(R.id.submit);
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        sname=edit_student_name.getText().toString();
        cname=edit_student_college.getText().toString();
        smobile=edit_student_name.getText().toString();
        semail=edit_student_email.getText().toString();
        ip=getipAddress();
        try { Log.d("ip",ip); } catch (Exception e){ Toast.makeText(getApplicationContext(), "Please Check Your Internet", Toast.LENGTH_SHORT).show();}
    }

    public void register(View view){

        if (!sname.isEmpty() && !semail.isEmpty() && !cname.isEmpty()&&!smobile.isEmpty()) {
            sendRegDetails(sname, semail, smobile, cname, ip, event_name);
        } else {
            Toast.makeText(getApplicationContext(),
                    "Please enter your details!", Toast.LENGTH_LONG).show();
        }
    }

    public void sendRegDetails(String name, String email, String phone, String college, String ip, String eventName){
        pDialog.setMessage("Registering ...");
        showDialog();

        final HashMap<String, String> bodyParams = new HashMap<>();
        bodyParams.put("name", name);
        bodyParams.put("email", email);
        bodyParams.put("phone", phone);
        bodyParams.put("college", college);
        bodyParams.put("id", ip);
        bodyParams.put("event_name", eventName);
        StringRequest request = new StringRequest(Request.Method.POST, TAG_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject object = new JSONObject(response);
                    boolean success = object.getBoolean("success");
                    if(success) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Check Your Internet", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    Log.e("registration post", "error in sending data");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                hideDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return bodyParams;
            }
        };
        requestQueue.add(request);

    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
    public static String getipAddress() {
        try {
            String ipv4;
            for (Enumeration<NetworkInterface> en = NetworkInterface
                    .getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf
                        .getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();

                    // for getting IPV4 format
                    if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(ipv4 = inetAddress.getHostAddress())) {

                        String ip = inetAddress.getHostAddress().toString();
                        return ipv4;
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("IP Address", ex.toString());
        }
        return null;
    }
}
