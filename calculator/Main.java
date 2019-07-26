package calculator;

import java.util.Scanner;

/*a + b, a - b, a * b, a / b.
 1234567890 (Integer)
 I II III IV V ...
 Или c арабскими или с римскими, при 3 + II - должен выдавать ошибку

 Разбить на логические классы
*/

public class Main {
    private static String inStr;

    public static void main(String[] args) {
        inputString();
        Interpreter interpreter = new Interpreter(inStr);
        interpreter.run();

        NumberModel firstNumber = interpreter.getFirstNumber();
        NumberModel secondNumber = interpreter.getSecondNumber();
        Character sign = interpreter.getSign();

        String outStr = countUp(firstNumber,secondNumber,sign);
        System.out.print(outStr);
    }

    private static void inputString(){
        Scanner sc = new Scanner(System.in);
        inStr = sc.nextLine();
    }

    private static String countUp(NumberModel fN, NumberModel sN, Character sign) {
        if ((fN.numberArabic == sN.numberArabic) && (fN.numberRoman == sN.numberRoman)){
            switch (sign){
                case '+':
                    if(fN.numberArabic) return Integer.toString(fN.numberCount + sN.numberCount);
                    else return RomanTranslator.toRoman(fN.numberCount + sN.numberCount);
                case '-':
                    if(fN.numberArabic) return Integer.toString(fN.numberCount - sN.numberCount);
                    else return RomanTranslator.toRoman(fN.numberCount - sN.numberCount);
                case '*':
                    if(fN.numberArabic) return Integer.toString(fN.numberCount * sN.numberCount);
                    else return RomanTranslator.toRoman(fN.numberCount * sN.numberCount);
                case '/':
                    if(fN.numberArabic) return Integer.toString(fN.numberCount / sN.numberCount);
                    else return RomanTranslator.toRoman(fN.numberCount / sN.numberCount);
            }
        } 
            throw new IllegalStateException("Выражение необходимо писать только арабскими или только римскими цифрами");
    }


}









