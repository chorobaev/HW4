package com.example.nurbol.hw4;

import android.util.Log;
import java.util.ArrayList;

public class Brain {
    private static String strGen = "";
    private static byte openBrackets = 0;
    private static boolean isPointed = false;

    /**
     * Events of buttons
     */
    // Adds operations ("+", "-", "x", "/").
    public String addOperation(char sign){
        isPointed = false; // Ensure that not pointed
        if (strGen.length() != 0 && getLast(strGen) != '(') {
            if (isOperation(getLast(strGen))) {
                strGen = strGen.substring(0, strGen.length() - 1);
                strGen += sign;
            } else {
                strGen += sign;
            }
        }
        return strGen;
    }

    // Adds digits.
    public String addDigit(char sign){
        if (strGen.length() != 0 && getLast(strGen) == ')'){
            strGen += "x" + sign;
        } else {
            strGen += sign;
        }
        checkIfBeginningZero();
        return strGen;
    }

    // Adds zero.
    public String addZero(){
        if (strGen.length() != 0 && (Character.isDigit(getLast(strGen)) || getLast(strGen) == '.')) {
            strGen += '0';
        }
        checkIfBeginningZero();
        return strGen;
    }

    // Adds brackets.
    public String addBracket(){
        isPointed = false; // Ensure that not pointed
        if (openBrackets > 0) {
            if (strGen.length() != 0 && getLast(strGen) != '(' && !isOperation(getLast(strGen))) {
                openBrackets--;
                strGen += ')';
            }
        } else {
            openBrackets++;
            if (strGen.length() != 0 && (Character.isDigit(getLast(strGen)) || getLast(strGen) == ')')) {
                strGen += "x(";
            } else {
                strGen += '(';
            }
        }
        return strGen;
    }

    // Makes a number negative or positive.
    public String makeNOrP(){
        if (strGen.length() != 0 && Character.isDigit(getLast(strGen))) {
            if (strGen.length() > 1 &&
                    getLastTwoChar(lastNumDeleted(strGen)).equals("(-")) {
                Log.d("Nurbol", lastNumDeleted(strGen));
                strGen = lastNumDeleted(strGen).substring(0, lastNumDeleted(strGen).length() - 2)
                        + getLastNum(strGen);
            } else {
                openBrackets++;
                strGen = lastNumDeleted(strGen) + "(-" + getLastNum(strGen);
            }
        }
        return strGen;
    }

    // Deletes the last char.
    public String deleteLast(){
        if (strGen.length() != 0) {
            if (getLast(strGen) == '(') { // Catching that a bracket is deleted.
                openBrackets--;
            } else {
                if (getLast(strGen) == ')') {
                    openBrackets++;
                }
            }

            if (getLast(strGen) == '.'){ // Catching that a point is deleted.
                isPointed = false;
            }
            strGen = strGen.substring(0, strGen.length() - 1);
        }
        return strGen;
    }

    // Deletes all.
    public String deleteAll(){
        strGen = "";
        openBrackets = 0;
        isPointed = false;
        return strGen;
    }

    // Adds point.
    public String addPoint(){
        Log.d("addPoint", isPointed + " - " + strGen);
        if (!isPointed) {
            if (strGen.length() != 0 && Character.isDigit(getLast(strGen))) {
                isPointed = true;
                strGen += '.';
            } else {
                isPointed = true;
                strGen += "0.";
            }
        }
        return strGen;
    }

    // Calculates and gives the result.
    public String calculateAll(){
        isPointed = false; // Ensure that not pointed
        Log.d("Nurbol", "open Br: " + openBrackets);
        strGen += closeAllBrackets();
        Log.d("Nurbol", "After closing brac: " + strGen);
        if (strGen.length() != 0 && getLast(strGen) != '(') {
            if (!Character.isDigit(getLast(strGen)) && getLast(strGen) != ')') {
                strGen = strGen.substring(0, strGen.length() - 1);
            }

            double result = getResult(strGen);
            if (canBeInteger(result)) {
                strGen = String.valueOf((int) result);
            } else {
                strGen = String.valueOf(roundTill4Pl(result));
            }
        }
        return strGen;
    }

    /**
     * Helpful methods for spelling
     */

    // Rounds the given number until 4 digits after the point.
    private double roundTill4Pl(double num){
        num *= 10000;
        return (double) Math.round(num) / 10000;
    }

    // Deletes all zeros at the beginning of the number.
    private void checkIfBeginningZero(){
        String stack = strGen.length() != 0 ? getLastNum(strGen) : "";
        Log.d("deleteZero", strGen + "; Stack: " + stack);
        int i;
        if (!(stack.length() > 2 && stack.charAt(1) == '.')) {
            for (i = 0; i < stack.length() && stack.charAt(i) == '0'; i++) ;
            if (strGen.length() != 0) {
                strGen = lastNumDeleted(strGen) + stack.substring(i, stack.length());
            }
        }
        Log.d("deleteZero", "the end");
    }

    // Closes all brackets increasing the general value "openBrackets".
    private String closeAllBrackets(){
        StringBuffer brack = new StringBuffer();
        while (openBrackets > 0){
            brack.append(')');
            openBrackets--;
        }
        return String.valueOf(brack);
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
        if (!string.equals("")) {
            return string.charAt(string.length() - 1);
        } else {
            return '@';
        }
    }

    /**
     * Calculating engine
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
                    try {
                        Log.d("NurbolBag", "" + str.substring(index + 1, str.indexOf(")", index)));
                        nums.add(getResult(str.substring(index + 1, str.indexOf(")", index))));
                        index = str.indexOf(")", index + 1) + 1;
                        if (str.length() > index) {
                            opers.add(str.charAt(index));
                        }
                        Log.d("NurbolBag", index + "");
                    }catch (Exception e) {
                        Log.d("NurbolBag", "promoh");
                    }
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
        ArrayList[] arrays = multiplyAll(numbers, operations);
        ArrayList<Double> nums = arrays[0];
        ArrayList<Character> opers = arrays[1];
        double result = 0;
        Log.d("additionNU", "Beginning: " + nums.toString() + "\n" + opers.toString());

        for (int i = 0; i < opers.size(); i++){
            result = doOperation(nums.get(i), nums.get(i + 1), opers.get(i));
            nums.set(i + 1, result);
        }
        if (opers.size() == 0 && nums.size() != 0){
            result = nums.get(nums.size() - 1);
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

    private ArrayList[] multiplyAll(ArrayList<Double> numbers, ArrayList<Character> operations){
        ArrayList<Double> nums = new ArrayList<>();
        ArrayList<Character> opers = new ArrayList<>();
        double stackDouble = 0;
        Log.d("multiplyNU", "Beginning: " +numbers.toString() + "\n" + operations.toString());
        int i;
        for (i = 0; i < operations.size(); i++){
            if (operations.get(i) == 'x' || operations.get(i) == '/'){

                nums.add(doOperation(numbers.get(i), numbers.get(i + 1), operations.get(i)));
                Log.d("multiplyNU", "Loop i = " + i + ": " + nums.toString() + "\n" + opers.toString());
                if (i < operations.size() -1){
                    opers.add(operations.get(i + 1));
                }
                i++;
            } else {
                nums.add(numbers.get(i));
                opers.add(operations.get(i));
                if (i == operations.size() - 1){
                    nums.add(numbers.get(i + 1));
                }
            }
        }
        if (numbers.size() - 1 == i){
            nums.add(numbers.get(numbers.size() - 1));
        }
        Log.d("multiplyNU", "After the loop: " + nums.toString() + "\n" + opers.toString());

        if (!hasMD(opers)){
            return new ArrayList[]{nums, opers};
        }

        return multiplyAll(nums, opers);
    }

    // Returns false, if the given array contains multiplications or divisions, else true;
    private boolean hasMD(ArrayList<Character> opers){
        for (Character ch:opers){
            if (ch == 'x' || ch == '/'){
                return true;
            }
        }
        return false;
    }
}