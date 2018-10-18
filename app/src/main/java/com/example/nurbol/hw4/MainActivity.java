package com.example.nurbol.hw4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    // Others
    @BindView(R.id.editTextDisplay)
    EditText etDisplay;
    @BindView(R.id.btPlusMines)
    Button btPlusMines;
    @BindView(R.id.btBrackets)
    Button btBrackets;
    @BindView(R.id.btDelete)
    Button btDelete;
    @BindView(R.id.btPoint)
    Button btPoint;

    // Numbers
    @BindView(R.id.btOne)
    Button btOne;
    @BindView(R.id.btTwo)
    Button btTwo;
    @BindView(R.id.btThree)
    Button btThree;
    @BindView(R.id.btFour)
    Button btFour;
    @BindView(R.id.btFive)
    Button btFive;
    @BindView(R.id.btSix)
    Button btSix;
    @BindView(R.id.btSeven)
    Button btSeven;
    @BindView(R.id.btEight)
    Button btEight;
    @BindView(R.id.btNine)
    Button btNine;
    @BindView(R.id.btZero)
    Button btZero;

    // Operations
    @BindView(R.id.btDivide)
    Button btDivide;
    @BindView(R.id.btMultiply)
    Button btMultiply;
    @BindView(R.id.btSubtract)
    Button btSubtract;
    @BindView(R.id.btAdd)
    Button btAdd;
    @BindView(R.id.btEqual)
    Button btEqual;

    private void initOnClicks(){
        MyOnClickListener listener = new MyOnClickListener(etDisplay);
        // Numbers
        btOne.setOnClickListener(listener);
        btTwo.setOnClickListener(listener);
        btThree.setOnClickListener(listener);
        btFour.setOnClickListener(listener);
        btFive.setOnClickListener(listener);
        btSix.setOnClickListener(listener);
        btSeven.setOnClickListener(listener);
        btEight.setOnClickListener(listener);
        btNine.setOnClickListener(listener);
        btZero.setOnClickListener(listener);

        // Operations
        btDivide.setOnClickListener(listener);
        btMultiply.setOnClickListener(listener);
        btSubtract.setOnClickListener(listener);
        btAdd.setOnClickListener(listener);
        btEqual.setOnClickListener(listener);

        //Others
        btPlusMines.setOnClickListener(listener);
        btBrackets.setOnClickListener(listener);
        btDelete.setOnClickListener(listener);
        btDelete.setOnLongClickListener(listener);
        btPoint.setOnClickListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initOnClicks();
    }
}