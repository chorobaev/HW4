package com.example.nurbol.hw4;

import android.util.Log;
import java.util.ArrayList;

public class Brain {
    private static String strGen = "";
    private static boolean isBracketsOpen = false;
    private static boolean isPointed = false;

    // Adds the given char to the editText.
    public String addSign(char sign) {
        switch (sign) {
            case '+':
            case '-':
            case 'x':
            case '/': // Make operations.
                isPointed = false; // Ensure that not pointed
                if (strGen.length() != 0 && getLast(strGen) != '(') {
                    if (isOperation(getLast(strGen))) {
                        strGen = strGen.substring(0, strGen.length() - 1);
                        strGen += sign;
                    } else {
                        strGen += sign;
                    }
                }
                break;

            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9': // Make digits.
                if (strGen.length() != 0 && getLast(strGen) == ')'){
                    strGen += "x" + sign;
                } else {
                    strGen += sign;
                }
                break;

            case '0': // Makes zero.
                if (strGen.length() != 0 && (Character.isDigit(getLast(strGen)) || getLast(strGen) == '.')) {
                    strGen += sign;
                }

                break;

            case 'B': // Makes brackets.
                isPointed = false; // Ensure that not pointed
                if (isBracketsOpen) {
                    if (strGen.length() != 0 && getLast(strGen) != '(') {
                        isBracketsOpen = false;
                        strGen += ')';
                    }
                } else {
                    isBracketsOpen = true;
                    if (strGen.length() != 0 && (Character.isDigit(getLast(strGen)) || getLast(strGen) == ')')) {
                        strGen += "x(";
                    } else {
                        strGen += '(';
                    }
                }
                break;

            case '?': // Makes negative or positive.
                if (strGen.length() != 0 && Character.isDigit(getLast(strGen))) {

                    if (strGen.length() > 1 && getLastTwoChar(lastNumDeleted(strGen)).equals("(-")) {
                        strGen = lastNumDeleted(strGen).substring(0, lastNumDeleted(strGen).length() - 2) + getLastNum(strGen);
                    } else {
                        isBracketsOpen = true;
                        Log.d("getNum", getLastNum(strGen));
                        strGen = lastNumDeleted(strGen) + "(-" + getLastNum(strGen);
                    }
                }
                break;

            case 'D': // Deletes last char.
                if (strGen.length() != 0) {
                    if (getLast(strGen) == '(') { // Catching that a bracket is deleted.
                        isBracketsOpen = false;
                    } else {
                        if (getLast(strGen) == ')') {
                            isBracketsOpen = true;
                        }
                    }

                    if (getLast(strGen) == '.'){ // Catching that a point is deleted.
                        isPointed = false;
                    }
                    strGen = strGen.substring(0, strGen.length() - 1);
                }
                break;

            case 'A':
                strGen = "";
                break;

            case '.':
                if (!isPointed) {
                    if (strGen.length() != 0 && Character.isDigit(getLast(strGen))) {
                        isPointed = true;
                        strGen += '.';
                    } else {
                        isPointed = true;
                        strGen += "0.";
                    }
                }
                break;

            case '=':
                isPointed = false; // Ensure that not pointed
                if (isBracketsOpen){
                    strGen += ')';
                }
                if (strGen.length() != 0) {
                    if (!Character.isDigit(getLast(strGen)) && getLast(strGen) != ')') {
                        strGen = strGen.substring(0, strGen.length() - 1);
                    }
                    double result = getResult(strGen);
                    if (canBeInteger(result)) {
                        strGen = String.valueOf((int) result);
                    } else {
                        strGen = String.valueOf(result);
                    }
                }
                break;
        }
        return strGen;
    }

    // Returns true if num don't have floating point else false.
    private boolean canBeInteger(double num){
        Log.d("canBe", num - Math.floor(num) + "");
        return (num - Math.floor(num)) == 0.0;
    }

    // Returns last two chars of the given string if its length greater than 1.
    private String getLastTwoChar(String str){
        if (str.length() > 1) {
            Log.d("detlast", str.length() + "");
            return str.substring(str.length() - 2, str.length());
        }
        return "";
    }

    // Returns a string without a last number substring of the given string.
    private String lastNumDeleted(String str){
        int count = 0;
        String strWithoutLasNum = "";
        for (int i = str.length() - 1; i >= 0; i--){
            if (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.'){
                count ++;
            } else {
                strWithoutLasNum = str.substring(0, str.length() - count);
                break;
            }
        }
        return strWithoutLasNum;
    }

    // Returns the last number substring of the given string.
    private String getLastNum(String str){
        String numStr = "";
        for (int i = str.length() - 1; i >= 0; i--){
            if (Character.isDigit(str.charAt(i)) || str.charAt(i) == '.'){
                numStr = str.charAt(i) + numStr;
            } else {
                return numStr;
            }
        }
        return numStr;
    }

    // Returns true if the given char is operation else false.
    private boolean isOperation(char ch){
        return (ch == '+' || ch == '-' || ch == 'x' || ch == '/');
    }

    // Returns the last char of the given string. If the length of the string zero then returns '@'.
    private char getLast(String string){
        if (string != "") {
            return string.charAt(string.length() - 1);
        } else {
            return '@';
        }
    }

    /**
     * Calculation
     * @param str
     * @return
     */
    private double getResult(String str){
        ArrayList<Double> nums = new ArrayList<>();
        ArrayList<Character> opers = new ArrayList<>();
        boolean isNextNumNegative = false;
        String strVrt = "";
        char sign;

        for (int index = 0; index < str.length(); index++){
            sign = str.charAt(index);
            switch (sign){
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                case '0':
                case '.':
                    strVrt += sign;
                    break;

                case '+':
                case 'x':
                case '/':
                    if (isNextNumNegative) {
                        nums.add(Double.valueOf(strVrt) * (-1));
                    } else {
                        nums.add(Double.valueOf(strVrt));
                    }
                    opers.add(sign);
                    strVrt = "";
                    isNextNumNegative = false;
                    Log.d("minus", "" + nums.get(nums.size() - 1));
                    break;

                case '-':
                    if (index == 0){
                        isNextNumNegative = true;
                    } else {
                        if (isNextNumNegative) {
                            nums.add(Double.valueOf(strVrt) * (-1));
                        } else {
                            nums.add(Double.valueOf(strVrt));
                        }
                        opers.add(sign);
                        strVrt = "";
                        isNextNumNegative = false;
                    }
                    break;

                case '(':
                    Log.d("NurbolBag", "" + str.substring(index +1, str.indexOf(")", index)));
                    nums.add(getResult(str.substring(index +1, str.indexOf(")", index))));
                    index = str.indexOf(")", index + 1) + 1;
                    if (str.length() > index){
                        opers.add(str.charAt(index));
                    }
                    Log.d("NurbolBag", index + "");
                    break;

                case ')':
                    break;
            }
        }
        if (strVrt.length() != 0){
            if (isNextNumNegative){
                nums.add(Double.valueOf(strVrt) * (-1));
            } else {
                nums.add(Double.valueOf(strVrt));
            }
        }
        return calculate(nums, opers);
    }

    // Returns a result of calculation, getting two arrays (List of numbers, list of operations).
    private double calculate(ArrayList<Double> numbers, ArrayList<Character> operations){
        ArrayList<Double> nums = new ArrayList<>();
        ArrayList<Character> opers = new ArrayList<>();
        double result = 0;
        Log.d("opoeratin", numbers.toString() + "\n" + operations.toString());

        // Doing multiplications and divisions
        for (int i = 0; i < operations.size(); i++){
            if (operations.get(i) == 'x' || operations.get(i) == '/'){
                nums.add(doOperation(numbers.get(i), numbers.get(i + 1), operations.get(i)));
                if (i != operations.size() - 1){
                    opers.add(operations.get(i + 1));
                }
            } else {
                nums.add(numbers.get(i));
                opers.add(operations.get(i));
                if (i == operations.size() - 1){
                    nums.add(numbers.get(i + 1));
                }
            }
        }
        if (opers.size() == 0){
            nums.add(numbers.get(0));
        }
        Log.d("opoeratin", nums.toString() + "\n" + opers.toString());

        for (int i = 0; i < opers.size(); i++){
            result += doOperation(nums.get(i), nums.get(i + 1), opers.get(i));
        }
        if (opers.size() == 0){
            result = nums.get(0);
        }
        return result;
    }

    // Returns float result of operation, getting two float numbers and the operation (+, -, x, /).
    private double doOperation(double num1, double num2, char operation){
        switch (operation){
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case 'x':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return 0;
        }
    }
}