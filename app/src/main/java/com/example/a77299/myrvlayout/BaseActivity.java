package com.example.a77299.myrvlayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends AppCompatActivity {
    MyAppliction mContext;
    public MyAppliction mApplication;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplication = ((MyAppliction) getApplication());
        mContext = (MyAppliction) mApplication.getApplicationContext();

    }



}
