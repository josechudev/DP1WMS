package com.dp1wms.view;

public interface FxmlView {
    String getTitle();
    String getFxmlFile();
    boolean isResizable();
    String getStringFromResourceBundle(String key);

}