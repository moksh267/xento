package com.xento.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.xento.R;
import com.xento.adapters.CountriesListAdapter;
import com.xento.interfaces.GetResultListener;
import com.xento.model.Country;
import com.xento.model.RootObject;
import com.xento.retrofitservices.ApiClient;
import com.xento.retrofitservices.ApiInterface;
import com.xento.utils.RetrofitCallback;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity implements GetResultListener {

    private List<Country> countriesList = new ArrayList<>();

    @BindView(R.id.countries_recyclerview)
    RecyclerView countriesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        initData();

    }

    private void initRecyclerView() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        countriesRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(
                countriesRecyclerView.getContext(),
                mLayoutManager.getOrientation()
        );
        countriesRecyclerView.addItemDecoration(mDividerItemDecoration);
        CountriesListAdapter adapter = new CountriesListAdapter(this, countriesList);
        countriesRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<RootObject> call = apiService.getCountriesCall();
        call.enqueue(new RetrofitCallback<RootObject>(this, this));
    }

    @Override
    public void getResult(Object result) {
        if (result != null && result instanceof Response) {
            Response res = (Response) result;
            if (((RootObject) res.body()).getStatusCode() == 200) {
                try {
                    JsonObject gson = new JsonParser().parse(((RootObject) res.body()).getResults().toString()).getAsJsonObject();
                    JSONObject jo2 = new JSONObject(gson.toString());
                    Iterator<String> keys = jo2.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        JSONObject innerJObject = jo2.getJSONObject(key);
                        Gson gsonObject = new Gson();
                        Country country = gsonObject.fromJson(innerJObject.toString(), Country.class);
                        System.out.println(country.toString());
                        countriesList.add(country);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(this, getResources().getString(R.string.error_message), Toast.LENGTH_SHORT).show();
            }
        }
        Collections.sort(countriesList, new Comparator<Country>() {
            @Override
            public int compare(Country country1, Country country2) {
                return country1.getName().compareToIgnoreCase(country2.getName());
            }
        });
        initRecyclerView();
    }


}
