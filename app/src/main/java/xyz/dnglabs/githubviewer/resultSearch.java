package xyz.dnglabs.githubviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.SharedPreferences;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class resultSearch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_search);
        SharedPreferences pref = getSharedPreferences("MyPref", MODE_PRIVATE);
        String urlSearch = pref.getString("MyPref", "");
        String setName = pref.getString("MyPref2", "");
        TextView result1 = (TextView) findViewById(R.id.userExists);
        result1.setText(setName);
        String JsonURL = urlSearch + "/repos";
        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest arrayreq = new JsonArrayRequest(JsonURL,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject repos = response.getJSONObject(0);
                            JSONArray html_url = repos.getJSONArray("");

                            JSONObject jsonObjectImage = html_url.getJSONObject(0);
                            String repoImage = jsonObjectImage.getString("avatar_url");
                            ImageView profile = (ImageView)findViewById(R.id.profileUser);
                            Picasso.with(getApplicationContext()).load(repoImage).into(profile);

                            JSONObject jsonObject = html_url.getJSONObject(1);
                            String repoA = jsonObject.getString("name");
                            JSONObject jsonObject2 = html_url.getJSONObject(2);
                            String repoB = jsonObject2.getString("name");
                            JSONObject jsonObject3 = html_url.getJSONObject(3);
                            String repoC = jsonObject3.getString("name");
                            JSONObject jsonObject4 = html_url.getJSONObject(4);
                            String repoD = jsonObject4.getString("name");
                            JSONObject jsonObject5 = html_url.getJSONObject(5);
                            String repoE = jsonObject5.getString("name");
                            TextView result2 = (TextView) findViewById(R.id.repo1);
                            result2.setText(repoA);
                            TextView result3 = (TextView) findViewById(R.id.repo2);
                            result3.setText(repoB);
                            TextView result4 = (TextView) findViewById(R.id.repo3);
                            result4.setText(repoC);
                            TextView result5 = (TextView) findViewById(R.id.repo4);
                            result5.setText(repoD);
                            TextView result6 = (TextView) findViewById(R.id.repo5);
                            result6.setText(repoE);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast toast = Toast.makeText(getApplicationContext(), "A network error has occurred. Check your Internet connection and try again later", Toast.LENGTH_LONG);
                        toast.show();
                    }

                }
        );



    }

}

