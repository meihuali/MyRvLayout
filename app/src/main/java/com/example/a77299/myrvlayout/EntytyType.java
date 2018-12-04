package com.example.a77299.myrvlayout;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

@Entity
public class EntytyType implements MultiItemEntity {
    @Id(autoincrement = true)
    Long testId;
    public static final int Title1 = 0;
    public static final int Title2 = 1;
    public static final int Title3 = 2;
    public static final int Mys = 3;
    public static final int Oths = 4;


    public  int channelType;//类型，0位header类型，，1为我的应用，2为其他应用
    public String content;

    private int area;
    private int pid;
    private int curArea;
    public int isState = 0; //0 表示不显示加减号，1表示显示加号，2 表示显示减号

    public int sorts = 0;

    public int getSorts() {
        return sorts;
    }

    public void setSorts(int sorts) {
        this.sorts = sorts;
    }

    public int getIsState() {
        return isState;
    }

    public void setIsState(int isState) {
        this.isState = isState;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public EntytyType() {
    }

    public EntytyType(int channelType, String content,int area ,int pid,int curArea) {
        this.channelType = channelType;
        this.content = content;
        this.area = area;
        this.pid = pid;
        this.curArea = curArea;
    }

    @Generated(hash = 1390424319)
    public EntytyType(Long testId, int channelType, String content, int area, int pid,
            int curArea, int isState, int sorts) {
        this.testId = testId;
        this.channelType = channelType;
        this.content = content;
        this.area = area;
        this.pid = pid;
        this.curArea = curArea;
        this.isState = isState;
        this.sorts = sorts;
    }

    public int getChannelType() {
        return channelType;
    }

    public void setChannelType(int channelType) {
        this.channelType = channelType;
    }

    @Override
    public int getItemType() {
        return channelType;
    }



    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }



    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public Long getTestId() {
        return this.testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public int getCurArea() {
        return curArea;
    }

    public void setCurArea(int curArea) {
        this.curArea = curArea;
    }
}
