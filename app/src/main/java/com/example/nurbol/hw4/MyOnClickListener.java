package com.example.nurbol.hw4;


import android.view.View;
import android.widget.EditText;

public class MyOnClickListener implements View.OnClickListener, View.OnLongClickListener {
    private Brain mBrain;
    private String strUi = "";
    private EditText etDisplay;

    public MyOnClickListener(EditText et) {
        this.etDisplay = et;
        mBrain = new Brain();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btPlusMines:
                strUi = mBrain.addSign('?');
                etDisplay.setText(strUi);
                break;

            case R.id.btBrackets:
                strUi = mBrain.addSign('B');
                etDisplay.setText(strUi);
                break;

            case R.id.btDelete:
                strUi = mBrain.addSign('D');
                etDisplay.setText(strUi);
                break;

            case R.id.btPoint:
                strUi = mBrain.addSign('.');
                etDisplay.setText(strUi);
                break;

            // Numbers
            case R.id.btOne:
                strUi = mBrain.addSign('1');
                etDisplay.setText(strUi);
                break;

            case R.id.btTwo:
                strUi = mBrain.addSign('2');
                etDisplay.setText(strUi);
                break;

            case R.id.btThree:
                strUi = mBrain.addSign('3');
                etDisplay.setText(strUi);
                break;

            case R.id.btFour:
                strUi = mBrain.addSign('4');
                etDisplay.setText(strUi);
                break;

            case R.id.btFive:
                strUi = mBrain.addSign('5');
                etDisplay.setText(strUi);
                break;

            case R.id.btSix:
                strUi = mBrain.addSign('6');
                etDisplay.setText(strUi);
                break;

            case R.id.btSeven:
                strUi = mBrain.addSign('7');
                etDisplay.setText(strUi);
                break;

            case R.id.btEight:
                strUi = mBrain.addSign('8');
                etDisplay.setText(strUi);
                break;

            case R.id.btNine:
                strUi = mBrain.addSign('9');
                etDisplay.setText(strUi);
                break;

            case R.id.btZero:
                strUi = mBrain.addSign('0');
                etDisplay.setText(strUi);
                break;

            // Operations
            case R.id.btDivide:
                strUi = mBrain.addSign('/');
                etDisplay.setText(strUi);
                break;

            case R.id.btMultiply:
                strUi = mBrain.addSign('x');
                etDisplay.setText(strUi);
                break;

            case R.id.btSubtract:
                strUi = mBrain.addSign('-');
                etDisplay.setText(strUi);
                break;

            case R.id.btAdd:
                strUi = mBrain.addSign('+');
                etDisplay.setText(strUi);
                break;

            case R.id.btEqual:
                strUi = mBrain.addSign('=');
                etDisplay.setText(strUi);
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        strUi = mBrain.addSign('A');
        etDisplay.setText(strUi);
        return false;
    }
}