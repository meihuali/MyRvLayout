package com.example.a77299.myrvlayout;

import android.os.Bundle;
import android.provider.FontsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.text.style.TtsSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.chad.library.adapter.base.BaseQuickAdapter;

import org.greenrobot.greendao.query.QueryBuilder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedSet;

import static com.example.a77299.myrvlayout.EntytyTypeDao.Properties.Sorts;

/**
 * 多功能编辑 界面
 * */
public class RvLayout extends  BaseActivity implements View.OnClickListener {
    private RecyclerView mRecyclerView;
    private TextView tv_edit;
    private boolean isItemState = false;
    private MyMultipleItemRvAdapter multipleItemAdapter;
    private Boolean isFirstState = false;
    private List<EntytyType> list;
    private List<EntytyType> MyList = new ArrayList<>();
    private List<EntytyType> OtherList = new ArrayList<>();

    private DaoSession daoSession;
    private EntytyTypeDao entytyTypeDao;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxlayout);

        daoSession = mApplication.getDaoSession();
        entytyTypeDao = daoSession.getEntytyTypeDao();

        initView();
    }




    private void showToast(String text) {
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
    }
    private void showToast(int cuont) {
        Toast.makeText(getApplicationContext(), String.valueOf(cuont), Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mRecyclerView = findViewById(R.id.mRecyclerView);
        tv_edit = findViewById(R.id.tv_edit);
        tv_edit.setOnClickListener(this);



        if (ShareUtils.getBoolean(mContext, "isItemState", false)) {
            //   查询数据库，升序排列
            list = entytyTypeDao.queryBuilder().orderAsc(EntytyTypeDao.Properties.Sorts).list();
            OtherList = entytyTypeDao.queryBuilder().list();
        } else {
            initData(0);
        }


        multipleItemAdapter = new MyMultipleItemRvAdapter(this, list);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);


        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                if (position >= multipleItemAdapter.getItemCount()) {
                    return 1;
                }
                int viewType = multipleItemAdapter.getItemViewType(position);
                return viewType == EntytyType.Mys || viewType ==  EntytyType.Oths ? 1 : 4;
            }
        });
        mRecyclerView.setAdapter(multipleItemAdapter);



        initListener();
    }

    private List<EntytyType> TestList = new ArrayList<>();
    private void initListener() {
        /**
         * 子Item 点击事件
         * */
        multipleItemAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                DaoSession daoSession = mContext.getDaoSession();
                switch (view.getId()) {
                    //加号点击事件
                    case R.id.img_add:
                        EntytyType Type1 = (EntytyType) adapter.getData().get(position);
                        Type1.setIsState(2);
                        Type1.setIsSaveMinus(1);
                        MyList.clear();
                        MyList.add(Type1);
                        list.addAll(1, MyList);
                        multipleItemAdapter.notifyDataSetChanged();

                        break;
                    //减号点击事件
                    case R.id.img_minus:
                        EntytyType type2 = (EntytyType) adapter.getData().get(position);
                        type2.setIsState(1);
                        list.remove(type2);
                        multipleItemAdapter.notifyDataSetChanged();
                        break;
                }
            }
        });


        /**
         * 总Item 点击事件
         * */
        multipleItemAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast(position);

            }
        });
    }

    private void initData(int isItemState) {

        list = new ArrayList<>();
        MyList.clear();
        OtherList.clear();

        //这个for 循环 表示常用功能
        // for (int i = 0; i < 5 ; i++) {
        // if (i == 0) {
        EntytyType entytyType0 = new EntytyType(0,"常用功能",0,0,0);
        MyList.add(entytyType0);

        // 这个for 表示 基本功能
        for (int j = 0; j < 9; j++) {
            if (j == 0) {
                continue;
            }
            if (j == 1) {
                EntytyType entytyTypes1 = new EntytyType(1,"基本功能",1,0,1);
                OtherList.add(entytyTypes1);
            }
            EntytyType entytyTypes1 = new EntytyType(4,"其他应用"+j,1,j,1);
            entytyTypes1.setIsState(isItemState);
            OtherList.add(entytyTypes1);
        }

        //这个for 循环表示 扩展功能
        for (int k = 0; k < 8; k++) {
            if (k == 0 || k == 1) {
                continue;
            }
            if (k == 2) {
                EntytyType entytyTypes2 = new EntytyType(2,"扩展功能",2,0,2);
                OtherList.add(entytyTypes2);
            }
            EntytyType entytyTypes2 = new EntytyType(4,"已有应用"+k,2,k,2);
            entytyTypes2.setIsState(isItemState);
            OtherList.add(entytyTypes2);
        }




        list.clear();
        list.addAll(MyList);
        list.addAll(OtherList);

        multipleItemAdapter = new MyMultipleItemRvAdapter(this, list);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        mRecyclerView.setLayoutManager(manager);


        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                if (position >= multipleItemAdapter.getItemCount()) {
                    return 1;
                }
                int viewType = multipleItemAdapter.getItemViewType(position);
                return viewType == EntytyType.Mys || viewType ==  EntytyType.Oths ? 1 : 4;
            }
        });
        mRecyclerView.setAdapter(multipleItemAdapter);



    }


    private boolean isFisrtEditState = false;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //遍历是否要显示 加减号
            case R.id.tv_edit:
                //点击完成走这个if
                if (isFirstState) {
                    tv_edit.setText("编辑");
                   // initData(0);
                    isFirstState = false;

                    //点击完成按钮后，将所有的item中IsState属性设置为0，具体标识位请查看实体类
                    for (int i = 0; i < list.size(); i++) {
                        list.get(i).setIsState(0);
                    }
                    multipleItemAdapter.notifyDataSetChanged();
                } else {
                    tv_edit.setText("完成");
                    isFirstState = true;

                    if (isFisrtEditState) {
                        for (int i = 0; i < list.size(); i++) {
                            int IsSaveMinus = list.get(i).getIsSaveMinus();
                            if (IsSaveMinus == 1) {
                              list.get(i).setIsState(2);
                            }
                            if (IsSaveMinus != 1) {
                                list.get(i).setIsState(1);
                            }
                        }
                    } else {
                        initData(1);
                        isFisrtEditState = true; //等于true 标识已经点击过一次编辑

                    }



                }
                multipleItemAdapter.notifyDataSetChanged();
                //这里很坑爹，就是你调用了·NotifyDataSetChanged后 要在掉一次 item 监听事件也就是 initListener(); 方法
                initListener();
                break;
        }
    }
}
