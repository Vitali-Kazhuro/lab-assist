package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.*;
import by.bia.labAssist.repository.TestReportRepository;
import by.bia.labAssist.service.TestReportService;
import by.bia.labAssist.util.MoneyToStr;
import by.bia.labAssist.util.ResultTableRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TestReportServiceImpl implements TestReportService {
    @Autowired
    private TestReportRepository testReportRepository;

    @Override
    public TestReport save(Integer protocolNumber, String date, TestMethod testMethod1, TestMethod testMethod2,
                           String startDate, String endDate,
                           /*Float temperatureMin, Float temperatureMax, Float humidityMin,
                           Float humidityMax, Integer pressureMin, Integer pressureMax, */
                           Employee employee1, Employee employee2, Applicant applicant){
        LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate formattedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate formattedEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        TestReport testReport = new TestReport(protocolNumber, formattedDate, formattedStartDate, formattedEndDate,
                //temperatureMin, temperatureMax, humidityMin, humidityMax, pressureMin, pressureMax,
                //testMethod,
                applicant);
        testReport.getTestMethods().add(testMethod1);
        testReport.getTestMethods().add(testMethod2);

        testReport.getPerformers().add(employee1);
        testReport.getPerformers().add(employee2);

        testReportRepository.save(testReport);

        return testReport;
    }

    @Override
    public TestReport edit(TestReport testReportEdit, Integer protocolNumber, String date,
                           TestMethod testMethod1, TestMethod testMethod2, String startDate, String endDate,
                           /*Float temperatureMin, Float temperatureMax, Float humidityMin, Float humidityMax,
                           Integer pressureMin, Integer pressureMax,*/
                           Employee employee1, Employee employee2){
        LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate formattedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate formattedEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        testReportEdit.setProtocolNumber(protocolNumber);
        testReportEdit.setDate(formattedDate);
        //testReportEdit.setTestMethod(testMethod);
        testReportEdit.setStartDate(formattedStartDate);
        testReportEdit.setEndDate(formattedEndDate);
        /*testReportEdit.setTemperatureMin(temperatureMin);
        testReportEdit.setTemperatureMax(temperatureMax);
        testReportEdit.setHumidityMin(humidityMin);
        testReportEdit.setHumidityMax(humidityMax);
        testReportEdit.setPressureMin(pressureMin);
        testReportEdit.setPressureMax(pressureMax);*/
        testReportEdit.setPerformers(new ArrayList<>());
        testReportEdit.getPerformers().add(employee1);
        testReportEdit.getPerformers().add(employee2);

        testReportEdit.setTestMethods(new ArrayList<>());
        testReportEdit.getTestMethods().add(testMethod1);
        testReportEdit.getTestMethods().add(testMethod2);

        testReportRepository.save(testReportEdit);

        return testReportEdit;
    }

    @Override
    public List<TestReport> findAllByDateAfterAndApplicantId(LocalDate date, Integer id) {
        return testReportRepository.findAllByDateAfterAndApplicantId(date, id);
    }

    @Override
    public List<TestReport> findAll() {
        return testReportRepository.findAll();
    }

    @Override
    public List<TestReport> findAll(List<TestReport> testReports) {
        return testReportRepository.findAllById(testReports.stream().map(TestReport::getId).collect(Collectors.toList()));
    }

    @Override
    public TestReport findById(Integer id) {
        return testReportRepository.findById(id).get();
    }

    @Override
    public Page<TestReport> findAllPages(Pageable pageable) {
        return testReportRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        testReportRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> createActPassages(List<TestReport> testReports, Integer price) {
        String[] number = {"", "одном", "двух", "трёх", "четырёх", "пяти", "шести", "семи", "восьми", "девяти",
                "десяти", "одиннадцати", "двенадцати", "тринадцати", "четырнадцати", "пятнадцати",
                "шестнадцати", "семнадцати", "восемнадцати", "девятнадцати", "двадцати"};
        //собираем в лист все образцы из отобранных отчётов
        List<Sample> samples = testReports.stream()
                .map(TestReport::getSamples)
                .flatMap(List::stream)
                .collect(Collectors.toList());
        //группируем образцы по объекту исследования
        Map<ObjectOfStudy, List<Sample>> testReportsByObjectOfStudy = samples.stream()
                .collect(Collectors.groupingBy(Sample::getObjectOfStudy));//TODO сделать по стрингу, как с нормами чтобы убрать equals у объекта исследования?
        //собирать абзацы будем в стрингБилдер
        StringBuilder sb = new StringBuilder();
        int sum = 0;//здесь будет сумма за все элементы анализов (= кол-во элементов * цена)
        //пройдёмся по всем образцам из каждой группы объектов исследования
        Iterator<Map.Entry<ObjectOfStudy, List<Sample>>> entriesByObjectOfStudy = testReportsByObjectOfStudy.entrySet().iterator();
        while(entriesByObjectOfStudy.hasNext()){
            Map.Entry<ObjectOfStudy, List<Sample>> entryByObjectOfStudy = entriesByObjectOfStudy.next();
            //достаём список образцов для данного объекта исследования
            List<Sample> samplesOfOneObjectOfStudy = entryByObjectOfStudy.getValue();
            //группируем эти образцы по списку норм, чтобы можно было посчитать вместе образцы, исследовавшиеся на одинаковые элементы
            ////Map<List<Norm>, List<Sample>> samplesByNorms = samplesOfOneObjectOfStudy.stream().collect(Collectors.groupingBy(Sample::getNorms));
            //группируем по кастомному методу, возвращающему строку для сравнения листов норм разных образцов (т.к. просто по листу норм не группируется)
            Map<String, List<Sample>> samplesByNorms = samplesOfOneObjectOfStudy.stream()
                    .collect(Collectors.groupingBy(Sample::forGrouping));
            //пройдёмся по всем образцам из каждой группы норм
            ////Iterator<Map.Entry<List<Norm>, List<Sample>>> entries1 = samplesByNorms.entrySet().iterator();
            Iterator<Map.Entry<String, List<Sample>>> entriesByNorms = samplesByNorms.entrySet().iterator();
            while (entriesByNorms.hasNext()){
                ////Map.Entry<List<Norm>, List<Sample>> item1 = entries1.next();
                Map.Entry<String, List<Sample>> entryByNorms = entriesByNorms.next();
                //начинаем формировать один абзац
                sb.append("– по определению содержания массовой доли ");
                ////List<Norm> norms = item1.getKey();
                //достаём нормы нулевого образца группы(а они у всех в этой группе одинаковые)
                //List<Norm> norms = item.getValue().get(0).getNorms();

                //////List<Norm> norms = entryByNorms.getValue().get(0).getNorms();
                List<Norm> norms = entryByNorms.getValue().get(0).getSampleNorms().stream()
                        .map(SampleNorm::getNorm)
                        .collect(Collectors.toList());

                int numberOfNorms = norms.size();//количество норм(читай - элементов), нужно чтобы посчитать цену
                if(numberOfNorms > 1)
                    sb.append("элементов ");
                else sb.append("элемента ");
                for(int i = 0; i < numberOfNorms; i++){
                    String elementTitle = norms.get(i).getElement().getTitle();//достаём название элемента
                    sb.append(elementTitle);
                    if(i != norms.size()-1)//если элементов больше одного, ставим запятую
                        sb.append(", ");
                    else sb.append(" ");
                }
                int numberOfSamples = entryByNorms.getValue().size(); //количество образцов с одинаковыми нормами
                sb.append("в ")
                    .append(number[numberOfSamples]);
                if(numberOfSamples > 1)
                    sb.append(" образцах ");
                else sb.append(" образце ");
                String objectOfStudyTitle = entryByObjectOfStudy.getKey().getTitle();//название объекта исследования образца
                sb.append(objectOfStudyTitle)
                        .append(" в размере ")
                        .append(MoneyToStr.num2str(numberOfNorms*numberOfSamples*price));//цена за эти анализы
                if (entriesByObjectOfStudy.hasNext() || entriesByNorms.hasNext())
                    sb.append(";\r\n          ");//если есть ещё образцы по объекту исследования - делаем новый абзац
                else sb.append(".");

                sum += numberOfNorms*numberOfSamples*price;//накапливаем сумму для общей суммы за все анализы
            }
        }
        //формируем предложение с перечислением номеров протоколов и датой протоколов, за которые выставляем счёт
        String protocolAndDate = testReports.stream().map(x -> "№" + x.getProtocolNumber() + " от " + x.getDateS())// + " г.")
                .collect(Collectors.joining("; "));
        //положим созданные данные в мапу для дальнейшего вывода и отдадим её
        Map<String, Object> map = new HashMap<>();
        map.put("sum", MoneyToStr.num2str(sum));
        map.put("text", sb.toString());
        map.put("textCheck", sb.toString().replace(";", ";<br>"));
        map.put("protocolAndDate", protocolAndDate);

        return map;
    }

    @Override
    public Map<String, Object> createProtocolPassages(TestReport testReportOffContext) {
        TestReport testReport = testReportRepository.findById(testReportOffContext.getId()).get();
        Map<String, Object> map = new HashMap<>();
        map.put("testReport", testReport);
        //TODO сделать каждый абзац отдельной функцией?

        //Наименование образцов:
        String samplesName = testReport.getSamples().stream()
                .map(sample -> ("Шифр " + sample.getCipher() + " " + sample.getObjectOfStudy().getTitle() +
                        " " + sample.getSeries() + " " + sample.getObjectOfStudy().getProducer()).trim())
                .collect(Collectors.joining("; "));
        map.put("samplesName", samplesName);

        //Вид испытаний:
        String beginning;
        if(testReport.getSamples().size() > 1 || testReport.getSamples().get(0).getSampleNorms().size() > 1)
            beginning = "определение массовой доли элементов: ";
        else
            beginning = "определение массовой доли элемента ";
        String testType = beginning + testReport.getSamples().stream()
                .map(sample -> {
                    String s = sample.getSampleNorms().stream()
                            .map(sampleNorm -> sampleNorm.getNorm().getElement().getTitle())
                            .collect(Collectors.joining(", "));
                    if (testReport.getSamples().size() > 1)
                        s += " в образце " + sample.getObjectOfStudy().getTitle();
                    if (sample.getObjectOfStudy().getRegulatoryDocument().getTitle().contains("фактическое содержание"))
                        return s + " " + sample.getObjectOfStudy().getRegulatoryDocument().getTitle();
                    else
                        return s + " на соответствие требованиям " + sample.getObjectOfStudy().getRegulatoryDocument().getTitle();
                })
                .collect(Collectors.joining("; "));
        map.put("testType", testType);

        //Наименование ТНПА, устанавливающей методы испытаний:
        String testMethods = testReport.getTestMethods().stream().map(TestMethod::getTitle).collect(Collectors.joining("; "));
        if(testMethods.lastIndexOf(";") == testMethods.length()-2)
            testMethods = testMethods.substring(0, testMethods.length() - 2);
        map.put("testMethods", testMethods);

        //Количество образца, поступившего на испытания:
        //TODO сделать объединение одинаковых объемов в скобках и числетельное количества?
        String quantity = testReport.getSamples().size() + "(";
        String quantityEach = testReport.getSamples().stream()
                .map(Sample::getQuantity)
                .collect(Collectors.joining(", "));
        if (quantityEach.contains(", "))
            quantityEach += " соответственно";
        map.put("quantity", quantity + quantityEach + ")");

        //Наименование проведенных видов испытаний:
        String testTypeInTable = "Определение массовой доли ";
        Stream<String> distinctNormElements = testReport.getSamples().stream()
                .map(Sample::getSampleNorms)
                .flatMap(List::stream)
                .map(sampleNorm -> sampleNorm.getNorm().getElement().getTitle())
                .distinct();
        if(distinctNormElements.count() > 1 &&
                (testReport.getSamples().size() > 1 || testReport.getSamples().get(0).getSampleNorms().size() > 1)) {
            testTypeInTable += "элементов ";
        }
        else {
            testTypeInTable += "элемента ";
        }
        testTypeInTable += testReport.getSamples().stream()
                .map(Sample::getSampleNorms)
                .flatMap(List::stream)
                .map(sampleNorm -> sampleNorm.getNorm().getElement().getTitle())
                .distinct()
                .collect(Collectors.joining(", "));
        map.put("testTypeInTable", testTypeInTable);

        //таблица РЕЗУЛЬТАТЫ ИСПЫТАНИЙ:
        List<ResultTableRow> resultTableRows = new ArrayList<>();
        for (Sample sample: testReport.getSamples()) {
            ResultTableRow resultTableRow = new ResultTableRow();
            resultTableRow.setCipher(sample.getCipher());
            resultTableRow.setSampleName(sample.getObjectOfStudy().getTitle() + " " + sample.getSeries() + " "
                    + sample.getObjectOfStudy().getProducer());
            resultTableRow.setElementNames(sample.getSampleNorms().stream()
                    .map(sampleNorm -> sampleNorm.getNorm().getElement().getTitle())
                    .collect(Collectors.joining("\n          ")));
            resultTableRow.setElementNorms(sample.getSampleNorms().stream()
                    .map(sampleNorm -> {
                        String s;
                        if (testReport.getNotGreater().equals("")) {
                            s = sampleNorm.getNorm().getValue();
                        } else
                            s = sampleNorm.getNorm().getValue().replace("не более ", "");
                        if (testReport.getUnits().equals(""))
                            s += " " + sampleNorm.getNorm().getUnits();
                        return s;
                    })
                    .collect(Collectors.joining("\n                                     ")));
            resultTableRow.setElementResults(sample.getSampleNorms().stream()
                    .map(sampleNorm -> {
                        if (sampleNorm.getMean() == null)
                            return "";
                        else if (testReport.getUnits().equals(""))
                            return sampleNorm.getMean() + " " + sampleNorm.getNorm().getUnits();
                        else
                            return sampleNorm.getMean();
                    })
                    .collect(Collectors.joining("\n                                     ")));
            resultTableRows.add(resultTableRow);
        }
        map.put("resultTableRowList", resultTableRows);

        //Заключение о результатах испытания:
        List<Boolean> factCont = testReport.getSamples().stream()
                .map(sample -> sample.getObjectOfStudy().getRegulatoryDocument().getTitle().contains("фактическое содержание"))
                .collect(Collectors.toList());
        String conclusionTitle;
        if (factCont.contains(false))
            conclusionTitle = "Заключение о результатах испытаний";
        else
            conclusionTitle = "";
        map.put("conclusionTitle", conclusionTitle);
        String conclusion = testReport.getSamples().stream()
                .map(sample -> {
                    if (sample.getObjectOfStudy().getRegulatoryDocument().getTitle().contains("фактическое содержание"))
                        return "";
                    String s = ("Образец Шифр " + sample.getCipher() + " " + sample.getObjectOfStudy().getTitle() +
                            " " + sample.getSeries() + " " + sample.getObjectOfStudy().getProducer() +
                            " соответствует требованиям " + sample.getObjectOfStudy().getRegulatoryDocument().getTitle())
                            .replaceAll("\\s+", " ").trim();
                    if (sample.getSampleNorms().size() == 1)
                        s += " по испытанному показателю содержания элемента " + sample.getSampleNorms().get(0)
                                .getNorm().getElement().getTitle();
                    else {
                        s += " по испытанным показателям содержания элементов ";
                        s += sample.getSampleNorms().stream()
                                .map(sampleNorm -> sampleNorm.getNorm().getElement().getTitle())
                                .collect(Collectors.joining(", "));
                    }
                    return s;
                })
                .collect(Collectors.joining(";\r\n         "));
        if (!conclusion.equals(""))
            conclusion += ".";
        map.put("conclusion", conclusion);

        return map;
    }
}
