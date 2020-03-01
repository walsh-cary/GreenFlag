package com.example.greenflag;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AccountsAdapter
        extends RecyclerView.Adapter<AccountsViewHolder> {
    private static final String TAG = "AccountsAdapter";

    private List<AccountsPojo> dataSet;

    public void setDataSet(List<AccountsPojo> dataSet) {
        this.dataSet = dataSet;
        notifyDataSetChanged();
        Log.d(TAG, "setDataSet: notifyDataSetChanged() executed");
    }

    @NonNull
    @Override
    public AccountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: Executed");
        return new AccountsViewHolder(
                LayoutInflater.from(parent.getContext())
                .inflate(R.layout.accounts_cardview_layout,
                        parent,
                        false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsViewHolder holder, int position) {
        holder.tvAccountName.setText(dataSet.get(position).name);
        holder.tvAccountCountry.setText(dataSet.get(position).country);
        holder.tvAccountGender.setText(dataSet.get(position).gender);
        holder.tvAccountBirthdate.setText(dataSet.get(position).age);
        int positionTag = position + 1;
        Log.d(TAG, "onBindViewHolder: onBindViewHolder iteration " + positionTag);
    }

    @Override
    public int getItemCount() {
        return dataSet != null ? dataSet.size() : 0;
    }
}
