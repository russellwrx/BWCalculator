package com.demo.russell.bwsecurity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class EditTextPreference extends android.preference.EditTextPreference {
    private TextView textValue;

    public EditTextPreference(Context context){
        super(context);
        setLayoutResource(R.layout.preference_with_value);
    }


    public EditTextPreference(Context context, AttributeSet attrs){
        super(context, attrs);
        setLayoutResource(R.layout.preference_with_value);
    }

    public EditTextPreference(Context context, AttributeSet attrs, int defStyle){
        super(context,attrs,defStyle);
        setLayoutResource(R.layout.preference_with_value);
    }

    @Override
    protected void onBindView(View view){
        super.onBindView(view);

        textValue = (TextView) view.findViewById(R.id.preference_value);
        if (textValue!=null)
            textValue.setText(getText());
    }

    @Override
    public void setText(String text){
        super.setText(text);
        if (textValue!=null)
            textValue.setText(getText());
    }

    @Override
    protected void showDialog(Bundle state) {
        super.showDialog(state);
        final Resources res = getContext().getResources();
        final Window window = getDialog().getWindow();

        final int bkColor = res.getColor(android.R.color.black);
        final int bkColor2 = res.getColor(android.R.color.darker_gray);

        // Title
        final int titleId = res.getIdentifier("alertTitle", "id", "android");
        final View title = window.findViewById(titleId);
        if (title != null) {
            ((TextView) title).setTextColor(bkColor);
        }

        // Title divider
        final int titleDividerId = res.getIdentifier("titleDivider", "id", "android");
        final View titleDivider = window.findViewById(titleDividerId);
        if (titleDivider != null) {
            titleDivider.setBackgroundColor(bkColor2);
        }
//
//        // EditText
//        final View editText = window.findViewById(android.R.id.edit);
//        if (editText != null) {
//            editText.setBackgroundColor(Color.BLACK);
//        }
    }

}

