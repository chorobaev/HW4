package com.example.nurbol.hw4;


import android.view.View;
import android.widget.EditText;

public class MyOnClickListener implements View.OnClickListener, View.OnLongClickListener {
    private Brain mBrain;
    private EditText etDisplay;

    public MyOnClickListener(EditText et) {
        this.etDisplay = et;
        mBrain = new Brain();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btPlusMines:
                etDisplay.setText(mBrain.makeNOrP());
                break;

            case R.id.btBrackets:
                etDisplay.setText(mBrain.addBracket());
                break;

            case R.id.btDelete:
                etDisplay.setText(mBrain.deleteLast());
                break;

            case R.id.btPoint:
                etDisplay.setText(mBrain.addPoint());
                break;

            // Numbers
            case R.id.btOne:
                etDisplay.setText(mBrain.addDigit('1'));
                break;

            case R.id.btTwo:
                etDisplay.setText(mBrain.addDigit('2'));
                break;

            case R.id.btThree:
                etDisplay.setText(mBrain.addDigit('3'));
                break;
            case R.id.btFour:
                etDisplay.setText(mBrain.addDigit('4'));
                break;
            case R.id.btFive:
                etDisplay.setText(mBrain.addDigit('5'));
                break;
            case R.id.btSix:
                etDisplay.setText(mBrain.addDigit('6'));
                break;
            case R.id.btSeven:
                etDisplay.setText(mBrain.addDigit('7'));
                break;
            case R.id.btEight:
                etDisplay.setText(mBrain.addDigit('8'));
                break;
            case R.id.btNine:
                etDisplay.setText(mBrain.addDigit('9'));
                break;

            case R.id.btZero:
                mBrain.addZero();
                break;

            // Operations
            case R.id.btDivide:
                etDisplay.setText(mBrain.addOperation('/'));
                break;
            case R.id.btMultiply:
                etDisplay.setText(mBrain.addOperation('x'));
                break;
            case R.id.btSubtract:
                etDisplay.setText(mBrain.addOperation('-'));
                break;
            case R.id.btAdd:
                etDisplay.setText(mBrain.addOperation('+'));
                break;

            case R.id.btEqual:
                etDisplay.setText(mBrain.calculateAll());
                break;
        }
    }

    @Override
    public boolean onLongClick(View v) {
        etDisplay.setText(mBrain.deleteAll());
        return false;
    }
}