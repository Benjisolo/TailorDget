package com.projects.test.tailordget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class MensurationCardView extends View {

    private TextView label, value, untit;

    public MensurationCardView(Context context) {
        super(context);

        label = (TextView) findViewById(R.id.mensurationLabel);
        value = (TextView) findViewById(R.id.mensurationValue);
        untit = (TextView) findViewById(R.id.mensurationUnit);
    }

    public TextView getLabel() {
        return label;
    }

    public void setLabel(TextView label) {
        this.label = label;
    }

    public TextView getValue() {
        return value;
    }

    public void setValue(TextView value) {
        this.value = value;
    }

    public TextView getUntit() {
        return untit;
    }

    public void setUntit(TextView untit) {
        this.untit = untit;
    }
}
