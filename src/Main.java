

import java.util.Scanner;
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int operator1, operator2;
    static char operation=' ';

    public static void main (String[] args) throws IllegalArgumentException {
        System.out.println("Введите выражение из двух арабских [2+2]\n" +
                "или  римских [V+V] чисел от 1 до 10: для расчета нажмите Enter\n" +
                "для выхода из калькулятора нажмите Q ");
        String userInput="";
        while (true ){
//          В цикле  считываем строку userInput которую ввёл пользователь
            userInput = scanner.nextLine();
            if(userInput.equals("Q") ) {
//              выходим мз цикла
                break;
            } else if (userInput.equals("")) {
                System.out.println ("пустая строка введите требуемый формат");
            }else {
//          Печатаем результат
                System.out.println(calc(userInput));
            }
        }
    }
    public static String calc(String input){
        String result="" ;
//      Создаём пустой символьный массив длиной 10 символов:  under_char
        char[] under_char = new char[10];
        int j=0;
        operation =' ';
//      Заполняем символьный массив символами строки которую ввел пользователь и
//      по ходу ловим знак операции и чистим строку от пробелов
        for (int i = 0; i < input.length(); i++) {
            char symbol=input.charAt(i);

            if (symbol!=' '){
                under_char[j] = symbol;
                switch (under_char[j]) {
                    case '+':
                        operation = '+';
                        break;
                    case '-':
                        operation = '-';
                        break;
                    case '*':
                        operation = '*';
                        break;
                    case '/':
                        operation = '/';
                        break;
                }
                j++;
            }
        }

        if (operation == ' ')   {
            throw new IllegalArgumentException("отсутсвует оператор (+, -, /, *)");
        }

//      преобразуем масив символов в строку
        String under_charString = String.valueOf(under_char);
//      формируем масив операторов
        String[] operators = under_charString.split("[+-/*]");
        if (operators.length!=2){
            throw new IllegalArgumentException("формат математической операции не удовлетворяет заданию - два целых числа и один оператор (+, -, /, *)");
        }
//      нормализуем операторы
        String operatorStr1 = operators[0].trim();
        String operatorStr2 = operators[1].trim();
//      переводим операторы к римскому формату
        operator1 = convertRimToNum(operatorStr1);
        operator2 = convertRimToNum(operatorStr2);
//      по результату перевода к римскому формату делаем расчеты
        if (operator1 < 0 && operator2 < 0) {
//          тут арабское исчисление преобразуем строку в число с отработкой исключения
            try {
                operator1 = Integer.parseInt(operatorStr1);
                operator2 = Integer.parseInt(operatorStr2);
            } catch (Exception e) {
                throw new IllegalArgumentException("Неверный формат числа");
            }
//          проверка на вхождение числа в диапазон от 1 до 10
            if((operator1>10 || operator1==0)|| (operator2>10 || operator2==0)){
                throw new IllegalArgumentException("число не входит в диапазон от 1 до 10");
            }
            result="Результат для арабских  цифр:"
                    + calculate(operator1, operator2, operation);
        } else {
            if (operator1 > 0 && operator2 > 0){
//              тут римское исчисление, конвертируем результат в римское исчисление
                result="Результат для римских цифр: "
                        + convertNumToRim(calculate(operator1, operator2, operation));
            }else {
//              тут введены разные форматы исчисления
                throw new IllegalArgumentException("используются одновременно разные системы счисления или привышен диапазон для римского формата");
            }
        }
        return result;
    }
    private static String convertNumToRim(int atribut) {
        if (atribut<0){
            throw new IllegalArgumentException("в римской системе нет отрицательных чисел");
        }
        String[] rim = {"0","I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        final String s = rim[atribut];
        return s;
    }
    private static int convertRimToNum(String roman) {
        try {
            if (roman.equals("I")) {
                return 1;
            } else if (roman.equals("II")) {
                return 2;
            } else if (roman.equals("III")) {
                return 3;
            } else if (roman.equals("IV")) {
                return 4;
            } else if (roman.equals("V")) {
                return 5;
            } else if (roman.equals("VI")) {
                return 6;
            } else if (roman.equals("VII")) {
                return 7;
            } else if (roman.equals("VIII")) {
                return 8;
            } else if (roman.equals("IX")) {
                return 9;
            } else if (roman.equals("X")) {
                return 10;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Неверный формат данных");
        }
        return -1;
    }

    public static int calculate(int arg1, int arg2, char op) {
        int result = 0;
        switch (op) {
            case '+':
                result = arg1 + arg2;
                break;
            case '-':
                result = arg1 - arg2;
                break;
            case '*':
                result = arg1 * arg2;
                break;
            case '/':
                result = arg1 / arg2;
                break;
            default:
                throw new IllegalArgumentException("Не верный знак арифмитической операции");
        }
        return result;
    }
}