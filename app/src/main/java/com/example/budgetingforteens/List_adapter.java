package com.example.budgetingforteens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class List_adapter extends ArrayAdapter {
    private Activity context;
    List<User> userList;
    public List_adapter(Activity context, List<User> userList) {
        super(context, R.layout.list_item, userList);
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listItemView = inflater.inflate(R.layout.list_item, null, true);
        TextView tvActivity = listItemView.findViewById(R.id.Activity);
        TextView tvExpenditure = listItemView.findViewById(R.id.Expenditure);
        TextView tvDate = listItemView.findViewById(R.id.Date);
        User users = userList.get(position);
        tvActivity.setText(users.getActivity());
        tvExpenditure.setText(users.getExpenditure());
        tvDate.setText(users.getDate());
        return listItemView;
    }
}