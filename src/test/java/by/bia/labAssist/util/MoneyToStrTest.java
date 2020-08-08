package by.bia.labAssist.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoneyToStrTest {
    @Test
    void num2strFrom72to1080() {
        Assertions.assertEquals("234,00 (двести тридцать четыре) рубля, в том числе НДC (20%) - 39,00 рублей", MoneyToStr.num2str(234));
        Assertions.assertEquals("72,00 (семьдесят два) рубля, в том числе НДC (20%) - 12,00 рублей", MoneyToStr.num2str(72));
        Assertions.assertEquals("144,00 (сто сорок четыре) рубля, в том числе НДC (20%) - 24,00 рубля", MoneyToStr.num2str(144));
        Assertions.assertEquals("216,00 (двести шестнадцать) рублей, в том числе НДC (20%) - 36,00 рублей", MoneyToStr.num2str(216));
        Assertions.assertEquals("288,00 (двести восемьдесят восемь) рублей, в том числе НДC (20%) - 48,00 рублей", MoneyToStr.num2str(288));
        Assertions.assertEquals("360,00 (триста шестьдесят) рублей, в том числе НДC (20%) - 60,00 рублей", MoneyToStr.num2str(360));
        Assertions.assertEquals("432,00 (четыреста тридцать два) рубля, в том числе НДC (20%) - 72,00 рубля", MoneyToStr.num2str(432));
        Assertions.assertEquals("504,00 (пятьсот четыре) рубля, в том числе НДC (20%) - 84,00 рубля", MoneyToStr.num2str(504));
        Assertions.assertEquals("576,00 (пятьсот семьдесят шесть) рублей, в том числе НДC (20%) - 96,00 рублей", MoneyToStr.num2str(576));
        Assertions.assertEquals("648,00 (шестьсот сорок восемь) рублей, в том числе НДC (20%) - 108,00 рублей", MoneyToStr.num2str(648));
        Assertions.assertEquals("720,00 (семьсот двадцать) рублей, в том числе НДC (20%) - 120,00 рублей", MoneyToStr.num2str(720));
        Assertions.assertEquals("792,00 (семьсот девяносто два) рубля, в том числе НДC (20%) - 132,00 рубля", MoneyToStr.num2str(792));
        Assertions.assertEquals("864,00 (восемьсот шестьдесят четыре) рубля, в том числе НДC (20%) - 144,00 рубля", MoneyToStr.num2str(864));
        Assertions.assertEquals("936,00 (девятьсот тридцать шесть) рублей, в том числе НДC (20%) - 156,00 рублей", MoneyToStr.num2str(936));
        Assertions.assertEquals("1008,00 (одна тысяча восемь) рублей, в том числе НДC (20%) - 168,00 рублей", MoneyToStr.num2str(1008));
        Assertions.assertEquals("1080,00 (одна тысяча восемьдесят) рублей, в том числе НДC (20%) - 180,00 рублей", MoneyToStr.num2str(1080));
    }
}