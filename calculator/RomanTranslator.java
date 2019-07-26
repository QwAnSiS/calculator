package calculator;

class RomanTranslator {

    private static boolean validationCheck(String romanValue){
        char[] charArr = romanValue.toCharArray();
        for (int i = 0; i < charArr.length ; i++) {
            if((charArr.length-i>=4)&&charArr[i]==charArr[i+1])
                if(charArr[i+1]==charArr[i+2])
                    if(charArr[i+2]==charArr[i+3]) {
                        throw new IllegalStateException("Четыре одинаковые римские цифры не могут идти подряд:\nIIII записывается, как IV");
                    }
        }
        return true;
    }

    public static Integer toInteger(String romanValue){
        if(!validationCheck(romanValue)) return -1;
        Integer resultValue = 0;

        char[] charArr = romanValue.toCharArray();
        int[]  valueArr = new int[charArr.length];

        for (int i = 0; i < charArr.length; i++) {
            switch (charArr[i]){
                case 'M': valueArr[i]=1000; break;
                case 'D': valueArr[i]=500; break;
                case 'C': valueArr[i]=100; break;
                case 'L': valueArr[i]=50; break;
                case 'X': valueArr[i]=10; break;
                case 'V': valueArr[i]=5; break;
                case 'I': valueArr[i]=1; break;
            }
        }

        for (int i = 0; i <valueArr.length-1; i++) {
            if (valueArr[i] < valueArr[i+1]) {
                valueArr[i+1]=valueArr[i+1]-valueArr[i];
                valueArr[i]=0;
            }
        }

        for(int i:valueArr) resultValue+=i;
        return resultValue;
    }

    public static String toRoman(int value){
        String resultString = "";
        int[] brokenNumber = {0,0,0,0,0,0,0,0,0,0};
        char[] romanAnalog = {0,0,0,0,0,0,'M','C','X','I'};

        int tens = 1000000000;
        int j=0;
        for (;;) {
            brokenNumber[j] = value/tens;
            value=value-brokenNumber[j]*tens;
            tens/=10;
            j++;
            if (j==10)break;
        }
        for (int i = 0; i < brokenNumber.length; i++) {
            for (int k = 0; k < brokenNumber[i]; k++) {
                resultString = resultString + romanAnalog[i];
            }
        }

        resultString = levelUp(resultString);
        return resultString;
    }

    private static String levelUp(String str) {

        str = str.replace("IIIII","V");
        str = str.replace("IIII","IV");

        str = str.replace("XXXXX","L");
        str = str.replace("XXXX","XL");

        str = str.replace("CCCCC","D");
        str = str.replace("CCCC","CD");


        str = str.replace("VIV","IX");
        str = str.replace("LXL","XC");
        str = str.replace("DCD","CM");

        return str;
    }
}