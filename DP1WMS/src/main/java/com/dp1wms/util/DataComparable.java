package com.dp1wms.util;

import javafx.beans.property.SimpleStringProperty;

public class DataComparable {
    private final SimpleStringProperty data1;
    private final SimpleStringProperty data2;

    public DataComparable(String data1, String data2){
        this.data1 = new SimpleStringProperty(data1);
        this.data2 = new SimpleStringProperty(data2);
    }

    public void setData1(String d){
        data1.setValue(d);
    }

    public String getData1() {
        return data1.get();
    }

    public void setData2(String d){
        data2.setValue(d);
    }

    public String getData2() {
        return data2.get();
    }
}
