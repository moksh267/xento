package com.xento.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xento.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CountriesListHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_country_name)
    public TextView tvCountryName;

    @BindView(R.id.tv_favourite)
    public TextView tvFavorite;

    @BindView(R.id.iv_country)
    public ImageView ivCountry;

    public CountriesListHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
