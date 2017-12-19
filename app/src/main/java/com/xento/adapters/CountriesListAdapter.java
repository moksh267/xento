package com.xento.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;
import com.xento.R;
import com.xento.model.Country;
import com.xento.utils.Constants;

import java.util.List;


public class CountriesListAdapter extends RecyclerView.Adapter<CountriesListHolder> {

    private final Context context;
    private List<Country> listCountries;


    public CountriesListAdapter(Context context, List<Country> countries) {
        this.context = context;
        listCountries = countries;
    }

    @Override
    public CountriesListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.countries_list_item, parent, false);

        return new CountriesListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountriesListHolder holder, int position) {
        holder.tvCountryName.setText(listCountries.get(position).getName());
        StringBuilder builder = new StringBuilder(Constants.BASE_URL);
        builder.append("flag/");
        builder.append(listCountries.get(position).getCountryCodes().getIso2());
        builder.append(".png");
        Picasso.with(context).load(builder.toString()).error(R.drawable.ic_launcher_background).into(holder.ivCountry);
    }


    @Override
    public int getItemCount() {
        return listCountries.size();
    }
}
