package by.bia.labAssist.util;

import java.util.ArrayList;
import java.util.Collections;

public class MoneyToStr {
    private static final String[][] SEX = {
            {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
            {"", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
    };
    private static final String[] STR_100 = {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
    private static final String[] STR_11 = {"", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать",
            "семнадцать", "восемнадцать", "девятнадцать", "двадцать"};
    private static final String[] STR_10 = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
    private static final String[][] FORMS = {
            {"копейка", "копейки", "копеек", "1"},
            {"рубль", "рубля", "рублей", "0"},
            {"тысяча", "тысячи", "тысяч", "1"},
            {"миллион", "миллиона", "миллионов", "0"},
            {"миллиард", "миллиарда", "миллиардов", "0"},
            {"триллион", "триллиона", "триллионов", "0"},
            //можно добавлять дальше секстиллионы и т.д.
    };
    private static long rub;
    private static long kop;
    private static StringBuilder stringBuilder;

    public static String num2str(Double amount) {
        return num2str(amount,true);
    }

    public static String num2str(Integer amount) {
        return num2str(Double.valueOf(amount));
    }

    public static String num2str(Double amount, boolean stripKop) {
        //Получаем отдельно рубли и копейки
        setRubs(amount);
        String kopString = setKopsAndGetProperKopsString(amount);
        //Разбиватель суммы на сегменты по 3 цифры с конца
        ArrayList<Long> segments = getSegments();
        stringBuilder = new StringBuilder(amount.toString().replace(".0", ",00") + " (");
        //Анализируем сегменты
        if (rub == 0) { //если ноль
            stringBuilder = new StringBuilder("0,00 (ноль) " + morph(0, FORMS[1][0], FORMS[1][1], FORMS[1][2]));
            if (stripKop){
                return stringBuilder.toString();
            } else{
                return stringBuilder + " " + kop + " " + morph(kop, FORMS[0][0], FORMS[0][1], FORMS[0][2]);
            }
        }
        //Если больше нуля
        processSegments(segments); //тут основная работа
        //Добавляем копейки, если надо
        addKopsIfNeeded(stripKop, kopString);
        //Возвращаем получившееся
        return stringBuilder.toString().replace(" руб", ") руб") + "в том числе НДC (20%) - "
                + getRubleAddition(amount/6);
    }

    private static void addKopsIfNeeded(boolean stripKop, String kops) {
        if (stripKop) {
            stringBuilder = new StringBuilder(stringBuilder.toString().replaceAll(" {2,}", " "));
        } else {
            stringBuilder.append(kops).append(" ").append(morph(kop, FORMS[0][0], FORMS[0][1], FORMS[0][2]));
            stringBuilder = new StringBuilder(stringBuilder.toString().replaceAll(" {2,}", " "));
        }
    }

    private static void processSegments(ArrayList<Long> segments) {
        int level = segments.size();
        for (Object segment : segments) { //перебираем сегменты
            int currentSex = Integer.valueOf(FORMS[level][3]); //определяем род
            int currentSegment = Integer.valueOf(segment.toString()); //текущий сегмент
            if (currentSegment == 0 && level > 1) { //если сегмент == 0 и не последний уровень(там Units)
                level--;
                continue;
            }
            String currentSegmentAsString = String.valueOf(currentSegment); //число в строку
            //нормализация к трехзначному виду (***)
            if (currentSegmentAsString.length() == 1) {
                currentSegmentAsString = "00" + currentSegmentAsString; //два нуля в префикс
            }
            if (currentSegmentAsString.length() == 2) {
                currentSegmentAsString = "0" + currentSegmentAsString; //или один?
            }
            //получаем цифры для анализа
            int firstDigit = Integer.valueOf(currentSegmentAsString.substring(0, 1)); //первая цифра
            int secondDigit = Integer.valueOf(currentSegmentAsString.substring(1, 2)); //вторая
            int thirdDigit = Integer.valueOf(currentSegmentAsString.substring(2, 3)); //третья
            int secondAndThirdDigits = Integer.valueOf(currentSegmentAsString.substring(1, 3)); //вторая и третья
            //анализируем и записываем цифры
            if (currentSegment > 99) {
                stringBuilder.append(STR_100[firstDigit]).append(" "); //сотни
            }
            if (secondAndThirdDigits > 20) {
                stringBuilder.append(STR_10[secondDigit]).append(" ")
                            .append(SEX[currentSex][thirdDigit]).append(" ");
            } else {
                if (secondAndThirdDigits > 9) {
                    stringBuilder.append(STR_11[secondAndThirdDigits - 9]).append(" "); //10-20
                } else {
                    stringBuilder.append(SEX[currentSex][thirdDigit]).append(" "); //0-9
                }
            }
            //Единицы измерения (рубли...)
            stringBuilder.append(morph(currentSegment, FORMS[level][0], FORMS[level][1], FORMS[level][2]));
            if (level == 1) {
                stringBuilder.append(", ");
            } else {
                stringBuilder.append(" ");
            }
            level--;
        }
    }

    private static ArrayList<Long> getSegments() {
        long rub_tmp = rub;
        ArrayList<Long> segments = new ArrayList<>();
        while(rub_tmp > 999) {
            long seg = rub_tmp / 1000;
            segments.add(rub_tmp - (seg * 1000));
            rub_tmp = seg;
        }
        segments.add(rub_tmp);
        Collections.reverse(segments);
        return segments;
    }

    private static String setKopsAndGetProperKopsString(Double amount) {
        String[] rubsAndKopsSep = amount.toString().split("\\.");
        kop = Long.valueOf(rubsAndKopsSep[1]);
        if (!rubsAndKopsSep[1].substring(0,1).equals("0")){ //начинается не с нуля
            if (kop < 10 ){
                kop *= 10;
            }
        }
        String kopString = String.valueOf(kop);
        if (kopString.length() == 1){
            kopString = "0" + kopString;
        }
        return kopString;
    }

    private static void setRubs(Double amount) {
        rub = amount.longValue();
    }

    private static String morph(long number, String wordForm1, String wordForm2, String wordForm3) {
        number = Math.abs(number) % 100;
        long n1 = number % 10;
        if (number > 10 && number < 20) {
            return wordForm3;
        }
        if (n1 > 1 && n1 < 5) {
            return wordForm2;
        }
        if (n1 == 1) {
            return wordForm1;
        }
        return wordForm3;
    }

    private static String getRubleAddition(Double num) {
        int numAsInt = num.intValue();
        String numStr = num.toString().replace(".0", ",00");
        return numStr + morph(numAsInt, " рубль", " рубля", " рублей");
    }

    public static String getRubleAddition(Integer num) {
        return getRubleAddition(new Double(num));
    }
}

