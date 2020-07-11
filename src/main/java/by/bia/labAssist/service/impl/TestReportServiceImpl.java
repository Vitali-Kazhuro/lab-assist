package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.*;
import by.bia.labAssist.repository.TestReportRepository;
import by.bia.labAssist.service.TestReportService;
import by.bia.labAssist.util.ActPassagesCreator;
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
    public List<TestReport> findAll() {
        return testReportRepository.findAll();
    }

    @Override
    public List<TestReport> findAll(List<TestReport> testReports) {
        return testReportRepository.findAllById(testReports.stream().map(TestReport::getId).collect(Collectors.toList()));
    }

    @Override
    public Page<TestReport> findAllPages(Pageable pageable) {
        return testReportRepository.findAll(pageable);
    }

    @Override
    public TestReport findById(Integer id) {
        return testReportRepository.findById(id).get();
    }

    @Override
    public List<TestReport> findAllByDateAfterAndApplicantId(LocalDate date, Integer id) {
        return testReportRepository.findAllByDateAfterAndApplicantId(date, id);
    }

    @Override
    public TestReport create(Integer protocolNumber, String date, List<TestMethod> testMethods, String startDate,
                             String endDate, Employee employee1, Employee employee2, Applicant applicant){
        LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate formattedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate formattedEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        TestReport testReport = new TestReport(protocolNumber, formattedDate,
                                               formattedStartDate, formattedEndDate, applicant);
        testReport.setTestMethods(testMethods);

        testReport.getPerformers().add(employee1);
        testReport.getPerformers().add(employee2);

        testReportRepository.save(testReport);

        return testReport;
    }

    @Override
    public TestReport edit(TestReport testReportEdit, Integer protocolNumber, String date,
                           List<TestMethod> testMethods, String startDate,
                           String endDate, Employee employee1, Employee employee2){
        LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate formattedStartDate = LocalDate.parse(startDate, DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate formattedEndDate = LocalDate.parse(endDate, DateTimeFormatter.ISO_LOCAL_DATE);

        testReportEdit.setProtocolNumber(protocolNumber);
        testReportEdit.setDate(formattedDate);
        testReportEdit.setStartDate(formattedStartDate);
        testReportEdit.setEndDate(formattedEndDate);
        testReportEdit.setPerformers(new ArrayList<>());
        testReportEdit.getPerformers().add(employee1);
        testReportEdit.getPerformers().add(employee2);
        testReportEdit.setTestMethods(testMethods);

        testReportRepository.save(testReportEdit);

        return testReportEdit;
    }

    @Override
    public void delete(Integer id) {
        testReportRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> createActPassages(List<TestReport> testReports, Integer price) {
        ActPassagesCreator actPassagesCreator = new ActPassagesCreator(testReports, price);
        return actPassagesCreator.createActPassages();
    }

    @Override
    public Map<String, Object> createProtocolPassages(TestReport testReportOffContext) {
        TestReport testReport = testReportRepository.findById(testReportOffContext.getId()).get();
        Map<String, Object> map = new HashMap<>();
        map.put("testReport", testReport);
        //Наименование образцов:
        map.put("samplesName", getSamplesName(testReport));
        //Вид испытаний:
        map.put("testType", getTestType(testReport));
        //Наименование ТНПА, устанавливающей методы испытаний:
        map.put("testMethods", getTestMethods(testReport));
        //Количество образца, поступившего на испытания:
        String quantityTotal = testReport.getSamples().size() + "(";
        map.put("quantity", quantityTotal + getQuantityEach(testReport) + ")");
        //Наименование ОРГАНОВ, производивших отбор проб на испытания
        map.put("objectsOfStudy", getObjectsOfStudy(testReport));
        //Наименование проведенных видов испытаний:
        map.put("testTypeInTable", getTestTypeInTable(testReport));
        //таблица РЕЗУЛЬТАТЫ ИСПЫТАНИЙ:
        map.put("resultTableRowList", getResultTableRows(testReport));
        //Заголовок заключения о результатах испытаний:
        map.put("conclusionTitle", getConclusionTitle(testReport));
        //Заключение о результатах испытания:
        map.put("conclusion", getConclusion(testReport));

        return map;
    }

    private String getConclusion(TestReport testReport) {
        String conclusion = testReport.getSamples().stream()
                .map(sample -> {
                    if (sample.getObjectOfStudy().getRegulatoryDocument().getTitle().contains("фактическое содержание")){
                        return "";
                    }
                    String s = ("Образец Шифр " + sample.getCipher() + " " + sample.getObjectOfStudy().getTitle() +
                            " " + sample.getSeries() + " " + sample.getObjectOfStudy().getProducer() +
                            " соответствует требованиям " + sample.getObjectOfStudy().getRegulatoryDocument().getTitle())
                            .replaceAll("\\s+", " ").trim();
                    if (sample.getSampleNorms().size() == 1){
                        s += " по испытанному показателю содержания элемента " + sample.getSampleNorms().get(0)
                                .getNorm().getElement().getTitle();
                    } else {
                        s += " по испытанным показателям содержания элементов ";
                        s += sample.getSampleNorms().stream()
                                .map(sampleNorm -> sampleNorm.getNorm().getElement().getTitle())
                                .collect(Collectors.joining(", "));
                    }
                    return s;
                })
                .collect(Collectors.joining(";\r\n         "));
        if (!conclusion.equals("")){
            conclusion += ".";
        }
        return conclusion;
    }

    private String getConclusionTitle(TestReport testReport) {
        List<Boolean> factCont = testReport.getSamples().stream()
                .map(sample -> sample.getObjectOfStudy().getRegulatoryDocument().getTitle().contains("фактическое содержание"))
                .collect(Collectors.toList());
        String conclusionTitle;
        if (factCont.contains(false)){
            conclusionTitle = "Заключение о результатах испытаний";
        } else{
            conclusionTitle = "";
        }
        return conclusionTitle;
    }

    private List<ResultTableRow> getResultTableRows(TestReport testReport) {
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
                        } else{
                            s = sampleNorm.getNorm().getValue().replace("не более ", "");
                        }
                        if (testReport.getUnits().equals("")){
                            s += " " + sampleNorm.getNorm().getUnits();
                        }
                        return s;
                    })
                    .collect(Collectors.joining("\n                                     ")));
            resultTableRow.setElementResults(sample.getSampleNorms().stream()
                    .map(sampleNorm -> {
                        if (sampleNorm.getMean() == null){
                            return "";
                        } else if (testReport.getUnits().equals("")){
                            return sampleNorm.getMean() + " " + sampleNorm.getNorm().getUnits();
                        } else{
                            return sampleNorm.getMean();
                        }
                    })
                    .collect(Collectors.joining("\n                                     ")));
            resultTableRows.add(resultTableRow);
        }
        return resultTableRows;
    }

    private String getTestTypeInTable(TestReport testReport) {
        String testTypeInTable = "Определение массовой доли ";
        Stream<String> distinctNormElements = testReport.getSamples().stream()
                .map(Sample::getSampleNorms)
                .flatMap(List::stream)
                .map(sampleNorm -> sampleNorm.getNorm().getElement().getTitle())
                .distinct();
        if(distinctNormElements.count() > 1 &&
                (testReport.getSamples().size() > 1 || testReport.getSamples().get(0).getSampleNorms().size() > 1)) {
            testTypeInTable += "элементов ";
        } else {
            testTypeInTable += "элемента ";
        }
        testTypeInTable += testReport.getSamples().stream()
                .map(Sample::getSampleNorms)
                .flatMap(List::stream)
                .map(sampleNorm -> sampleNorm.getNorm().getElement().getTitle())
                .distinct()
                .collect(Collectors.joining(", "));
        return testTypeInTable;
    }

    private String getObjectsOfStudy(TestReport testReport) {
        String allObjectOfStudyTitles = testReport.getSamples().stream()
                .map(Sample::getObjectOfStudy)
                .map(ObjectOfStudy::getSamplingAuthority)
                .map(SamplingAuthority::getTitle)
                .collect(Collectors.joining("; "));

        int distinctObjectsOfStudy = (int) testReport.getSamples().stream()
                .map(Sample::getObjectOfStudy)
                .map(ObjectOfStudy::getSamplingAuthority)
                .distinct()
                .count();

        boolean allContainOrganizationName = testReport.getSamples().stream()
                .map(Sample::getObjectOfStudy)
                .map(ObjectOfStudy::getSamplingAuthority)
                .allMatch(samplingAuthority ->
                        samplingAuthority.getTitle().contains(testReport.getApplicant().getOrganization()));

        if (distinctObjectsOfStudy == 1){
            return testReport.getSamples().get(0).getObjectOfStudy().getSamplingAuthority().getTitle();
        } else if (allContainOrganizationName){
            String organizationTitle = testReport.getApplicant().getOrganization();
            return organizationTitle + " - " + allObjectOfStudyTitles.replaceAll(organizationTitle, "")
                    .replaceAll(" - ", "");
        } else {
            return allObjectOfStudyTitles;
        }
    }

    private String getQuantityEach(TestReport testReport) {
        String quantityEach = testReport.getSamples().stream()
                .map(Sample::getQuantity)
                .collect(Collectors.joining(", "));
        if (quantityEach.contains(", ")){
            quantityEach += " соответственно";
        }
        return quantityEach;
    }

    private String getTestMethods(TestReport testReport) {
        return testReport.getTestMethods().stream().map(TestMethod::getTitle).collect(Collectors.joining("; "));
    }

    private String getTestType(TestReport testReport) {
        String beginning;
        if(testReport.getSamples().size() > 1 || testReport.getSamples().get(0).getSampleNorms().size() > 1){
            beginning = "определение массовой доли элементов: ";
        } else{
            beginning = "определение массовой доли элемента ";
        }
        return beginning + testReport.getSamples().stream()
                .map(sample -> {
                    String s = sample.getSampleNorms().stream()
                            .map(sampleNorm -> sampleNorm.getNorm().getElement().getTitle())
                            .collect(Collectors.joining(", "));
                    if (testReport.getSamples().size() > 1){
                        s += " в образце " + sample.getObjectOfStudy().getTitle();
                    }
                    if (sample.getObjectOfStudy().getRegulatoryDocument().getTitle().contains("фактическое содержание")){
                        return s + " " + sample.getObjectOfStudy().getRegulatoryDocument().getTitle();
                    } else{
                        return s + " на соответствие требованиям " + sample.getObjectOfStudy().getRegulatoryDocument().getTitle();
                    }
                })
                .collect(Collectors.joining("; "));
    }

    private String getSamplesName(TestReport testReport) {
        return testReport.getSamples().stream()
                    .map(sample -> ("Шифр " + sample.getCipher() + " " + sample.getObjectOfStudy().getTitle() +
                            " " + sample.getSeries() + " " + sample.getObjectOfStudy().getProducer()).trim())
                    .collect(Collectors.joining("; "));
    }
}
