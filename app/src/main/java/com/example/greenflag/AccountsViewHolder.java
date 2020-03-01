package com.example.greenflag;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AccountsViewHolder
        extends RecyclerView.ViewHolder {
    private static final String TAG = "AccountsViewHolder";

    ImageView ivAccountImage;
    TextView tvAccountName, tvAccountCountry, tvAccountGender, tvAccountBirthdate;

    public AccountsViewHolder(@NonNull View itemView) {
        super(itemView);
        Log.d(TAG, "AccountsViewHolder: Executed");
        ivAccountImage = itemView
                .findViewById(R.id.iv_account_photo);
        tvAccountName = itemView
                .findViewById(R.id.tv_account_name);
        tvAccountCountry = itemView
                .findViewById(R.id.tv_account_country);
        tvAccountGender = itemView
                .findViewById(R.id.tv_account_gender);
        tvAccountBirthdate = itemView
                .findViewById(R.id.tv_account_birthdate);
    }

}
