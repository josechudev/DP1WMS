package com.dp1wms.util;

import javafx.geometry.Point2D;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PosicionFormatter {

    public static String pointToXYPair(Point2D point){
        return "(" +  String.valueOf(((int) point.getX())) + "," + String.valueOf(((int) point.getY())) + ")";
    }

    public static String pointToXYString(Point2D point){
        return String.valueOf(((int) point.getX())) + "," + String.valueOf(((int) point.getY()));
    }

    public static Point2D stringToPoint(String coordinates){
        Matcher matcher = Pattern.compile("\\d+").matcher(coordinates);
        matcher.find();
        int x = Integer.valueOf(matcher.group());
        matcher.find();
        int y = Integer.valueOf(matcher.group());
        return new Point2D(x, y);
    }
}
