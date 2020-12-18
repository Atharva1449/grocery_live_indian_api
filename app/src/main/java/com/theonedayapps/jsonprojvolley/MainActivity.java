package com.theonedayapps.jsonprojvolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    MyDatabase myDatabase;
   TextView text1;
    private final static String URL="https://api.data.gov.in/resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&offset=0&limit=10";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
text1=findViewById(R.id.text);
        myDatabase= Room.databaseBuilder(this,MyDatabase.class,"userDb").allowMainThreadQueries().build();

        final RequestQueue requestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,URL, null,new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("records");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject obj=jsonArray.getJSONObject(i);

                        String state=obj.getString("state");
                        String district=obj.getString("district");
                        String market=obj.getString("market");
                        String commodity=obj.getString("commodity");
                        String variety=obj.getString("variety");
                        String arrivaldate=obj.getString("arrival_date");
                        String minprice=obj.getString("min_price");
                        String maxprice=obj.getString("max_price");
                        String modalprice=obj.getString("modal_price");
fun(state,district,market,commodity,variety,arrivaldate,minprice,maxprice,modalprice);

        //                        text1.append("State"+state+"\n District: "+district+"\n Market: "+market+"\n\n");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
VolleyLog.d("#############################################Error",error.getMessage());
            }
        });
requestQueue.add(jsonObjectRequest);
fun2();
    }

    void fun(String state1, String district1, String market1,
             String commodity1, String variety1, String arrivaldate1,
             String minprice1, String maxprice1, String modalprice1){
        User user=new User(state1,district1,market1,commodity1,variety1,arrivaldate1,minprice1,maxprice1,modalprice1);
      //  myDatabase= Room.databaseBuilder(this,MyDatabase.class,"userDb").allowMainThreadQueries().build();
        myDatabase.mydao().addUser(user);
    }
void fun2()
{
    List<User> userdata=myDatabase.mydao().getuser();
    for(int i=0;i<userdata.size();i++){
        text1.append("State"+userdata.get(i).getState()+"\n District: "+userdata.get(i).getDistrict()+"\n Market: "+userdata.get(i).getMarket()+"\n\n");
    }
}

}