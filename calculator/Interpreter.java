package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Interpreter{

    private String inStr;

    private NumberModel firstNumber;
    private NumberModel secondNumber;
    private Character sign;


    Interpreter(String inStr){
        this.inStr = inStr;
        firstNumber = new NumberModel();
        secondNumber = new NumberModel();
    }

    public void run(){
        interpretation();
        toFormatNumber(firstNumber);
        toFormatNumber(secondNumber);
    }

    private void interpretation(){

        if (!Pattern.matches(".+ [+[-]/*] .+",inStr)){
            throw new IllegalStateException("Не совпадение с шаблоном:\nчисло(пробел)знак_действия(пробел)число");
           }

        String template = " [+[-]/*] ";
        Pattern pattern = Pattern.compile(template);
        Matcher matcher = pattern.matcher(inStr);

        int regexStart;
        if(matcher.find())
            regexStart = matcher.start();
        else
            return;

        firstNumber.numberUnFormat = inStr.substring(0,regexStart);
        secondNumber.numberUnFormat = inStr.substring(regexStart+3);

        Matcher matcherSign = Pattern.compile("[+[-]*/]").matcher(inStr);
        matcherSign.find();
        sign = inStr.charAt(matcherSign.start());
    }

    private void  toFormatNumber(NumberModel number) {
        if(Pattern.matches("[0-9]*",number.numberUnFormat)) {
            number.numberArabic = true;
            number.numberCount = Integer.parseInt(number.numberUnFormat);
            return;
        }
        if(Pattern.matches("[IVXLCDM]*",number.numberUnFormat)) {
            number.numberRoman = true;
            number.numberCount = RomanTranslator.toInteger(number.numberUnFormat);
            return;
        }

    }

    public NumberModel getFirstNumber() {
        return firstNumber;
    }

    public NumberModel getSecondNumber() {
        return secondNumber;
    }

    public Character getSign() {
        return sign;
    }
}
