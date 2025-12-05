package com.daw.weatherinfoapp.shared.utils;

public class StringUtils {
    public static String replaceAll(String aString, String[] toBeReplaced, String[] newValues) {
        if (toBeReplaced.length != newValues.length) {
            return aString;
        }
        int numToBeReplaced = toBeReplaced.length;
        for (int i = 0; i < numToBeReplaced; i++) {
            String stringToBeReplaced = toBeReplaced[i];
            String stringNewValue = newValues[i];
            aString = aString.replace(stringToBeReplaced, stringNewValue);
        }
        return aString;
    }

    public static String replaceAll(String aString, String[] toBeReplaced, double[] newValues) {
        if (toBeReplaced.length != newValues.length) {
            return aString;
        }
        int numToBeReplaced = toBeReplaced.length;
        for (int i = 0; i < numToBeReplaced; i++) {
            String stringToBeReplaced = toBeReplaced[i];
            double stringNewValue = newValues[i];
            aString = aString.replace(stringToBeReplaced, String.valueOf(stringNewValue));
        }
        return aString;
    }
}
