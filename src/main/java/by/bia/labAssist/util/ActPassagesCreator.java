package by.bia.labAssist.util;

import by.bia.labAssist.model.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ActPassagesCreator {
    private static final  String[] NUMBER = {"", "одном", "двух", "трёх", "четырёх", "пяти", "шести", "семи", "восьми",
            "девяти", "десяти", "одиннадцати", "двенадцати", "тринадцати", "четырнадцати", "пятнадцати",
            "шестнадцати", "семнадцати", "восемнадцати", "девятнадцати", "двадцати"};
    private int totalCost;
    private StringBuilder stringBuilder;
    private List<TestReport> testReports;
    private Integer price;

    public ActPassagesCreator(List<TestReport> testReports, Integer price) {
        this.totalCost = 0;
        this.stringBuilder = new StringBuilder();
        this.testReports = testReports;
        this.price = price;
    }

    public Map<String, Object> createActPassages(){
        //собираем в лист все образцы из отобранных отчётов
        List<Sample> samples = testReports.stream()
                .map(TestReport::getSamples)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        //группируем образцы по объекту исследования
        Map<ObjectOfStudy, List<Sample>> samplesByObjectOfStudy = samples.stream()
                .collect(Collectors.groupingBy(Sample::getObjectOfStudy));//TODO сделать по стрингу, как с нормами чтобы убрать equals у объекта исследования
        //пройдёмся по всем образцам из каждой группы объектов исследования
        Iterator<Map.Entry<ObjectOfStudy, List<Sample>>> entriesByObjectOfStudy = samplesByObjectOfStudy.entrySet().iterator();
        iterateAllSamples(entriesByObjectOfStudy);
        //формируем предложение с перечислением номеров протоколов и датой протоколов, за которые выставляем счёт
        String protocolAndDate = createProtocolsAndDatePassage();
        //положим созданные данные в мапу для дальнейшего вывода и отдадим её
        Map<String, Object> map = new HashMap<>();
        map.put("sum", MoneyToStr.num2str(totalCost));
        map.put("text", stringBuilder.toString());
        map.put("textCheck", stringBuilder.toString().replace(";", ";<br>"));
        map.put("protocolAndDate", protocolAndDate);

        return map;
    }

    private String createProtocolsAndDatePassage() {
        return testReports.stream()
                    .map(x -> "№" + x.getProtocolNumber() + " от " + x.getDateS())
                    .collect(Collectors.joining("; "));
    }

    private void iterateAllSamples(Iterator<Map.Entry<ObjectOfStudy, List<Sample>>> entriesByObjectOfStudy) {
        while(entriesByObjectOfStudy.hasNext()){
            Map.Entry<ObjectOfStudy, List<Sample>> entryByObjectOfStudy = entriesByObjectOfStudy.next();
            //достаём список образцов для данного объекта исследования
            List<Sample> samplesOfOneObjectOfStudy = entryByObjectOfStudy.getValue();
            //группируем по кастомному методу, возвращающему строку для сравнения листов норм разных образцов (т.к. просто по листу норм не группируется)
            Map<String, List<Sample>> samplesByNorms = samplesOfOneObjectOfStudy.stream()
                    .collect(Collectors.groupingBy(Sample::forGrouping));
            //пройдёмся по всем образцам из каждой группы норм
            Iterator<Map.Entry<String, List<Sample>>> entriesByNorms = samplesByNorms.entrySet().iterator();
            iterateSamplesByNorms(entriesByObjectOfStudy, entryByObjectOfStudy, entriesByNorms);
        }
    }

    private void iterateSamplesByNorms(Iterator<Map.Entry<ObjectOfStudy, List<Sample>>> entriesByObjectOfStudy,
                                       Map.Entry<ObjectOfStudy, List<Sample>> entryByObjectOfStudy,
                                       Iterator<Map.Entry<String, List<Sample>>> entriesByNorms) {
        while (entriesByNorms.hasNext()){
            Map.Entry<String, List<Sample>> entryByNorms = entriesByNorms.next();
            //начинаем формировать один абзац
            stringBuilder.append("– по определению содержания массовой доли ");
            //достаём нормы нулевого образца группы(а они у всех в этой группе одинаковые)
            List<Norm> norms = entryByNorms.getValue().get(0).getSampleNorms().stream()
                    .map(SampleNorm::getNorm)
                    .collect(Collectors.toList());
            int numberOfNorms = norms.size(); //количество норм(читай - элементов); нужно чтобы посчитать цену
            if(numberOfNorms > 1){
                stringBuilder.append("элементов ");
            } else{
                stringBuilder.append("элемента ");
            }
            listElementsTitles(norms, numberOfNorms); //перечисляем элементы
            int numberOfSamples = entryByNorms.getValue().size(); //количество образцов с одинаковыми нормами
            stringBuilder.append("в ")
                    .append(NUMBER[numberOfSamples]);
            if(numberOfSamples > 1){
                stringBuilder.append(" образцах ");
            } else{
                stringBuilder.append(" образце ");
            }
            String objectOfStudyTitle = entryByObjectOfStudy.getKey().getTitle(); //название объекта исследования образца
            stringBuilder.append(objectOfStudyTitle)
                    .append(" в размере ")
                    .append(MoneyToStr.num2str(numberOfNorms*numberOfSamples*price)); //цена за эти анализы
            if (entriesByObjectOfStudy.hasNext() || entriesByNorms.hasNext()){
                stringBuilder.append(";\r\n          "); //если есть ещё образцы по объекту исследования - делаем новый абзац
            } else{
                stringBuilder.append(".");
            }
            totalCost += numberOfNorms*numberOfSamples*price; //накапливаем сумму для общей суммы за все анализы
        }
    }

    private void listElementsTitles(List<Norm> norms, int numberOfNorms) {
        for(int i = 0; i < numberOfNorms; i++){
            String elementTitle = norms.get(i).getElement().getTitle(); //достаём название элемента
            stringBuilder.append(elementTitle);
            if(i != norms.size()-1){
                //если элементов больше одного, ставим запятую
                stringBuilder.append(", ");
            } else{
                stringBuilder.append(" ");
            }
        }
    }
}
