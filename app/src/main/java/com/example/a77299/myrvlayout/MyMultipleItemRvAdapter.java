package com.example.a77299.myrvlayout;


import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


public class MyMultipleItemRvAdapter extends BaseMultiItemQuickAdapter<EntytyType, BaseViewHolder> {

    private List<EntytyType> data;
    private LinearLayout ll;
    private Context mContext;

    public MyMultipleItemRvAdapter(Context context, List<EntytyType> data) {
        super(data);
        this. data = data;
        this.mContext = context;
        addItemType(EntytyType.Title1, R.layout.item_title1_view);
        addItemType(EntytyType.Title2, R.layout.item_title2_view);
        addItemType(EntytyType.Title3, R.layout.item_title3_view);

        addItemType(EntytyType.Mys,R.layout.item_mys_view);
        addItemType(EntytyType.Oths, R.layout.item_oths_view);



    }
    @Override
    protected void convert(BaseViewHolder helper, EntytyType item) {
        switch (helper.getItemViewType()) {
            case EntytyType.Title1:
                helper.setText(R.id.tv1, item.getContent());
                break;
            case EntytyType.Title2:
                helper.setText(R.id.tv1, item.getContent());
                break;
            case EntytyType.Title3:
                helper.setText(R.id.tv1, item.getContent());
                break;
            case EntytyType.Mys:
                helper.setText(R.id.tv2, item.getContent());
                break;
            case EntytyType.Oths:
                helper.setText(R.id.tv3, item.getContent());
                //遍历所有Item 是否显示 加减号
                if (item.getIsState() == 1) {
                    helper.getView(R.id.img_add).setVisibility(View.VISIBLE); //显示加号
                    helper.getView(R.id.img_minus).setVisibility(View.GONE);  //隐藏减号
                } else if (item.getIsState() == 0) {
                    helper.getView(R.id.img_add).setVisibility(View.GONE); //隐藏加号
                    helper.getView(R.id.img_minus).setVisibility(View.GONE); //隐藏减号

                } else if (item.getIsState() == 2) {
                    helper.getView(R.id.img_add).setVisibility(View.GONE); //隐藏加号
                    helper.getView(R.id.img_minus).setVisibility(View.VISIBLE); //显示减号
                } else {
                    return;
                }
                helper.addOnClickListener(R.id.img_add);
                helper.addOnClickListener(R.id.img_minus);
                break;
        }






    }


    @Override
    public int getItemViewType(int position) {
        //super.getItemViewType(position);
        if (position >= data.size()){
            return -1;
        }
        EntytyType entity = data.get(position);
        switch (entity.channelType){
            case 0:
                //标题1
                return EntytyType.Title1;
            case 1:
                //标题2
                return EntytyType.Title2;
            case 2:
                //标题3
                return EntytyType.Title3;

            case 3:
                //已有1
                return EntytyType.Mys;
            case 4:
                //其他1
                return EntytyType.Oths;
           default:
               return -1;
        }



    }
}
