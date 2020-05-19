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
        List<Sample> samples = testReports.stream()
                .map(TestReport::getSamples)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        Map<ObjectOfStudy, List<Sample>> samplesByObjectOfStudy = samples.stream()
                .collect(Collectors.groupingBy(Sample::getObjectOfStudy));
        Iterator<Map.Entry<ObjectOfStudy, List<Sample>>> entriesByObjectOfStudy = samplesByObjectOfStudy.entrySet().iterator();
        //пройдёмся по всем образцам из каждой группы объектов исследования и создадим абзацы
        iterateAllSamples(entriesByObjectOfStudy);
        //формируем предложение с перечислением номеров протоколов и датой протоколов, за которые выставляем счёт
        String protocolAndDate = createProtocolsAndDatePassage();

        Map<String, Object> map = new HashMap<>();
        map.put("sum", MoneyToStr.num2str(totalCost));
        map.put("text", stringBuilder.toString());
        map.put("textCheck", stringBuilder.toString().replace(";", ";<br>"));
        map.put("protocolAndDate", protocolAndDate);

        return map;
    }

    private String createProtocolsAndDatePassage() {
        return testReports.stream()
                    .map(testReport -> "№" + testReport.getProtocolNumber() + " от " + testReport.getDateS())
                    .collect(Collectors.joining("; "));
    }

    private void iterateAllSamples(Iterator<Map.Entry<ObjectOfStudy, List<Sample>>> entriesByObjectOfStudy) {
        while(entriesByObjectOfStudy.hasNext()){
            Map.Entry<ObjectOfStudy, List<Sample>> entryByObjectOfStudy = entriesByObjectOfStudy.next();

            List<Sample> samplesOfOneObjectOfStudy = entryByObjectOfStudy.getValue();
            //группируем по кастомному методу, возвращающему строку для сравнения листов норм разных образцов (т.к. просто по листу норм не группируется)
            Map<String, List<Sample>> samplesByNorms = samplesOfOneObjectOfStudy.stream()
                    .collect(Collectors.groupingBy(Sample::forGrouping));
            Iterator<Map.Entry<String, List<Sample>>> entriesByNorms = samplesByNorms.entrySet().iterator();
            //пройдёмся по всем образцам из каждой группы норм и создадим абзацы
            iterateSamplesByNormsAndCreatePassages(entriesByObjectOfStudy, entryByObjectOfStudy, entriesByNorms);
        }
    }

    private void iterateSamplesByNormsAndCreatePassages(Iterator<Map.Entry<ObjectOfStudy, List<Sample>>> entriesByObjectOfStudy,
                                                        Map.Entry<ObjectOfStudy, List<Sample>> entryByObjectOfStudy,
                                                        Iterator<Map.Entry<String, List<Sample>>> entriesByNorms) {
        while (entriesByNorms.hasNext()){
            Map.Entry<String, List<Sample>> entryByNorms = entriesByNorms.next();
            stringBuilder.append("– по определению содержания массовой доли "); //начинаем формировать один абзац
            //достаём нормы нулевого образца группы, т.к. они у всех в этой группе одинаковые
            List<Norm> norms = entryByNorms.getValue().get(0).getSampleNorms().stream()
                    .map(SampleNorm::getNorm)
                    .collect(Collectors.toList());
            int numberOfNorms = norms.size();
            if(numberOfNorms > 1){
                stringBuilder.append("элементов ");
            } else{
                stringBuilder.append("элемента ");
            }
            listElementsTitles(norms, numberOfNorms);
            int numberOfSamples = entryByNorms.getValue().size();
            stringBuilder.append("в ")
                    .append(NUMBER[numberOfSamples]);
            if(numberOfSamples > 1){
                stringBuilder.append(" образцах ");
            } else{
                stringBuilder.append(" образце ");
            }
            String objectOfStudyTitle = entryByObjectOfStudy.getKey().getTitle();
            stringBuilder.append(objectOfStudyTitle)
                    .append(" в размере ")
                    .append(MoneyToStr.num2str(numberOfNorms*numberOfSamples*price));
            if (entriesByObjectOfStudy.hasNext() || entriesByNorms.hasNext()){
                stringBuilder.append(";\r\n          "); //если есть ещё образцы по объекту исследования - делаем новый абзац
            } else{
                stringBuilder.append(".");
            }
            totalCost += numberOfNorms*numberOfSamples*price;
        }
    }

    private void listElementsTitles(List<Norm> norms, int numberOfNorms) {
        for(int i = 0; i < numberOfNorms; i++){
            String elementTitle = norms.get(i).getElement().getTitle();
            stringBuilder.append(elementTitle);
            if(i != norms.size()-1){
                stringBuilder.append(", ");
            } else{
                stringBuilder.append(" ");
            }
        }
    }
}
