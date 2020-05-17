package com.duallens.fairandsquare;

import android.content.Context;
import android.content.res.Resources;

import java.util.ArrayList;
import java.util.List;

//Handles all the arrays used in the program
public class MethodsData {
    private static MethodsData sMethodD;
    private List<Methods> mMethod;

    public static MethodsData get(Context context) {
        if (sMethodD == null) {
            sMethodD = new MethodsData(context);
        }
        return sMethodD;
    }

    //Used in DetailsActivity to get the choosing method requested
    private MethodsData(Context context) {
        mMethod = new ArrayList<>();
        Resources res = context.getResources();
        String[] methods = res.getStringArray(R.array.method);
        String[] descriptions = res.getStringArray(R.array.howTo);
        String[] gameDescriptions = res.getStringArray(R.array.game);
        for (int i = 0; i < methods.length; i++) {
            mMethod.add(new Methods(i + 1, methods[i], descriptions[i], gameDescriptions[i]));
        }
    }

    //Returns the array list
    public List<Methods> getMethods() {
        return mMethod;
    }

    //Get the method in the array
    public Methods getMethod(int methodId) {
        for (Methods method : mMethod) {
            if (method.getId() == methodId) {
                return method;
            }
        }
        return null;
    }
}

