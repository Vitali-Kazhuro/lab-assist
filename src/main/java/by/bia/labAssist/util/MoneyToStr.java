package by.bia.labAssist.util;

import java.util.ArrayList;
import java.util.Collections;

public class MoneyToStr {

    public static String num2str(Double amount) {
        return num2str(amount,true);
    }

    public static String num2str(Integer amount) {
        return num2str(Double.valueOf(amount));
    }

    public static String num2str(Double amount, boolean stripKop) {
        String[][] sex = {
                {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
                {"", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
        };
        String[] str100= {"", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот", "семьсот", "восемьсот", "девятьсот"};
        String[] str11 = {"", "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать", "шестнадцать",
                "семнадцать", "восемнадцать", "девятнадцать", "двадцать"};
        String[] str10 = {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};
        String[][] forms = {
                {"копейка", "копейки", "копеек", "1"},
                {"рубль", "рубля", "рублей", "0"},
                {"тысяча", "тысячи", "тысяч", "1"},
                {"миллион", "миллиона", "миллионов", "0"},
                {"миллиард", "миллиарда", "миллиардов", "0"},
                {"триллион", "триллиона", "триллионов", "0"},
                // можно добавлять дальше секстиллионы и т.д.
        };
        // получаем отдельно рубли и копейки
        long rub = amount.longValue();
        String[] moi = amount.toString().split("\\.");
        long kop = Long.valueOf(moi[1]);
        if (!moi[1].substring(0,1).equals("0")){// начинается не с нуля
            if (kop < 10 )
                kop *= 10;
        }
        String kops = String.valueOf(kop);
        if (kops.length() == 1)
            kops = "0" + kops;
        long rub_tmp = rub;
        // Разбиватель суммы на сегменты по 3 цифры с конца
        ArrayList<Long> segments = new ArrayList<>();
        while(rub_tmp > 999) {
            long seg = rub_tmp / 1000;
            segments.add(rub_tmp - (seg * 1000));
            rub_tmp = seg;
        }
        segments.add(rub_tmp);
        Collections.reverse(segments);
        // Анализируем сегменты
        StringBuilder o = new StringBuilder(amount.toString().replace(".0", ",00") + " (");
        if (rub == 0) {// если Ноль
            o = new StringBuilder("0,00 (ноль) " + morph(0, forms[1][0], forms[1][1], forms[1][2]));
            if (stripKop)
                return o.toString();
            else
                return o + " " + kop + " " + morph(kop, forms[0][0], forms[0][1], forms[0][2]);
        }
        // Больше нуля
        int lev = segments.size();
        for (Object segment : segments) {// перебираем сегменты
            int sexi = Integer.valueOf(forms[lev][3]);//.toString() );// определяем род
            int ri = Integer.valueOf(segment.toString());// текущий сегмент
            if (ri == 0 && lev > 1) {// если сегмент ==0 И не последний уровень(там Units)
                lev--;
                continue;
            }
            String rs = String.valueOf(ri); // число в строку
            // нормализация
            if (rs.length() == 1) rs = "00" + rs;// два нулика в префикс?
            if (rs.length() == 2) rs = "0" + rs; // или лучше один?
            // получаем циферки для анализа
            int r1 = Integer.valueOf(rs.substring(0, 1)); //первая цифра
            int r2 = Integer.valueOf(rs.substring(1, 2)); //вторая
            int r3 = Integer.valueOf(rs.substring(2, 3)); //третья
            int r22 = Integer.valueOf(rs.substring(1, 3)); //вторая и третья
            // Супер-нано-анализатор циферок
            if (ri > 99) o.append(str100[r1]).append(" "); // Сотни
            if (r22 > 20) {// >20
                o.append(str10[r2]).append(" ");
                o.append(sex[sexi][r3]).append(" ");
            } else { // <=20
                if (r22 > 9) o.append(str11[r22 - 9]).append(" "); // 10-20
                else o.append(sex[sexi][r3]).append(" "); // 0-9
            }
            // Единицы измерения (рубли...)
            o.append(morph(ri, forms[lev][0], forms[lev][1], forms[lev][2]));//+" ";
            if (lev == 1) o.append(", ");
            else o.append(" ");
            lev--;
        }
        // Копейки в цифровом виде
        if (stripKop) {
            o = new StringBuilder(o.toString().replaceAll(" {2,}", " "));
        }
        else {
            o.append(kops).append(" ").append(morph(kop, forms[0][0], forms[0][1], forms[0][2]));
            o = new StringBuilder(o.toString().replaceAll(" {2,}", " "));
        }
        return o.toString().replace(" руб", ") руб") + "в том числе НДC (20%) - "
                + getRubleAddition(amount/6);
    }

    private static String morph(long n, String f1, String f2, String f5) {
        n = Math.abs(n) % 100;
        long n1 = n % 10;
        if (n > 10 && n < 20) return f5;
        if (n1 > 1 && n1 < 5) return f2;
        if (n1 == 1) return f1;
        return f5;
    }

    private static String getRubleAddition(Double num) {
        Integer i = num.intValue();
        String numStr = num.toString().replace(".0", ",00");
        return numStr + morph(i, " рубль", " рубля", " рублей");
    }

    public static String getRubleAddition(Integer num) {
        return getRubleAddition(new Double(num));
    }

    /*public static void main(String[] args) {
        for (int i = 0; i < 10000; i++){
            System.out.println(num2str(i*72));
        }
    }*/
}

