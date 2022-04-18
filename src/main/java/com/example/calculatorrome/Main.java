package com.example.calculatorrome;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static String calc(String input) {
        String[] splitter = input.split("\\b");
        String firstValue = splitter[0];
        String[] romanNumbers = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        String result = input;
        for (String romanNumber : romanNumbers) {
            if (firstValue.equals(romanNumber)) {
                result = calcRoman(input);
                return result;
            }
        }
        return calcArab(result);
    }


    public static String calcArab(String input) {
        String[] splitter = input.split("\\b");
        int firstValue = Integer.parseInt(splitter[0]);
        int secondValue = Integer.parseInt(splitter[2]);
        String operator = splitter[1];
        String result;
        if (splitter.length > 4) {
            throw new IllegalArgumentException(input + " too much operators");
        }

        switch (operator) {
            case "+":
                result = String.valueOf(firstValue + secondValue);
                return result;
            case "-":
                result = String.valueOf(firstValue - secondValue);
                return result;
            case "/":
                result = String.valueOf(firstValue / secondValue);
                return result;
            case "*":
                result = String.valueOf(firstValue * secondValue);
                return result;
            default:
                System.out.println("Wrong operator");
                break;
        }
        return null;

    }

    public static String calcRoman(String input) {
        String[] splitter = input.split("\\b");
        int firstValue = convertRomanToArab(splitter[0]);
        int secondValue = convertRomanToArab(splitter[2]);
        String operator = splitter[1];
        String result;
        if (splitter.length > 4) {
            throw new IllegalArgumentException(input + " too much operators");
        }

        switch (operator) {
            case "+":
                result = String.valueOf(firstValue + secondValue);
                return convertArabToRoman(Integer.parseInt(result));
            case "-":
                result = String.valueOf(firstValue - secondValue);
                return convertArabToRoman(Integer.parseInt(result));
            case "/":
                result = String.valueOf(firstValue / secondValue);
                return convertArabToRoman(Integer.parseInt(result));
            case "*":
                result = String.valueOf(firstValue * secondValue);
                return convertArabToRoman(Integer.parseInt(result));
            default:
                System.out.println("Wrong operator");
                break;
        }
        return null;

    }


    public static int convertRomanToArab(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
        }

        return result;
    }


    public static String convertArabToRoman(int number) {
        if ((number <= 0) || (number > 100)) {
            throw new IllegalArgumentException(number + " is not in range (0,100)");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println("Enter an operand from 0-9 with operator[+,-,/,*] or from I-X with operator[+,-,/,*]");
        String scanner = new Scanner(System.in).nextLine();
        System.out.println(calc(scanner));

    }

}
