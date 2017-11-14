package com.dp1wms.util;

import javafx.beans.property.SimpleStringProperty;

public class DataSimple {
    private final SimpleStringProperty data;

    public DataSimple(String data){
        this.data = new SimpleStringProperty(data);
    }

    public void setData(String d){
        data.setValue(d);
    }

    public String getData() {
        return data.get();
    }
}
