package com.demo.russell.bwsecurity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
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

}

