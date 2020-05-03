package by.bia.labAssist.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class TestReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer protocolNumber;
    private LocalDate date;
    private LocalDate startDate;
    private LocalDate endDate;
    /*private Float temperatureMin;
    private Float temperatureMax;
    private Float humidityMin;
    private Float humidityMax;
    private Integer pressureMin;
    private Integer pressureMax;*/

    @Transient
    private String dateS;
    @Transient
    private String startDateS;
    @Transient
    private String endDateS;

    @Transient
    private String units;
    @Transient
    private String notGreater;

    /*@ManyToOne
    @JoinColumn(name = "test_method_id")
    private TestMethod testMethod;*/
    @ManyToMany
    @JoinTable(
            name = "test_report_test_method",
            joinColumns = @JoinColumn(name = "test_report_id"),
            inverseJoinColumns = @JoinColumn(name = "test_method_id"))
    private List<TestMethod> testMethods;

    @ManyToMany//(fetch = FetchType.EAGER)
    @JoinTable(
            name = "test_report_doer",
            joinColumns = @JoinColumn(name = "test_report_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> performers;

    @OneToMany(mappedBy = "testReport", cascade = CascadeType.ALL)
    private List<Sample> samples;
    //new{
    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;
    //new}
    public TestReport() {
    }

    public TestReport(Integer protocolNumber, LocalDate date, LocalDate startDate, LocalDate endDate,
                      /*Float temperatureMin, Float temperatureMax, Float humidityMin,
                      Float humidityMax, Integer pressureMin, Integer pressureMax,*/
                      //TestMethod testMethod,
                      Applicant applicant) {
        this.protocolNumber = protocolNumber;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        /*this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.humidityMin = humidityMin;
        this.humidityMax = humidityMax;
        this.pressureMin = pressureMin;
        this.pressureMax = pressureMax;*/

        //this.testMethod = testMethod;
        this.testMethods = new ArrayList<>();

        this.performers = new ArrayList<>();
        this.applicant = applicant;
        /*this.dateS = date.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        this.startDateS = startDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        this.endDateS = endDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));*/
    }

    public String getTestReportFileName(){
        return "prot isp" + this.getProtocolNumber() +
                "_" + this.getApplicant().getOrganizationForName() +
                "_" + this.getSamples().get(0).getObjectOfStudy().getTitleStripped();
    }

    public String getNotGreater() {
        long notGreaterCount = this.getSamples().stream().map(Sample::getSampleNorms).flatMap(List::stream)
                .map(SampleNorm::getNorm)
                .map(Norm::getValue)
                .filter(s -> s.contains("не более"))
                .count();
        if(this.getSamples().stream().map(Sample::getSampleNorms).flatMap(List::stream).count() == notGreaterCount){
            return ", не более";
        }
        return "";
    }

    public void setNotGreater(String notGreater) {
        this.notGreater = notGreater;
    }

    public String getUnits() {
        List<String> unitsList = this.getSamples().stream().map(Sample::getSampleNorms).flatMap(List::stream)
                .map(SampleNorm::getNorm)
                .map(Norm::getUnits)
                .distinct()
                .collect(Collectors.toList());
        if(unitsList.size() == 1){
            return ", " + unitsList.get(0);
        }
        return "";
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getDateS() {
        /*if(dateS == null){
            this.dateS = this.date.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        }*/
        this.dateS = this.date.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = this.date.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
    }

    public String getStartDateS() {
        /*if (startDateS == null){
            this.startDateS = this.startDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        }*/
        this.startDateS = this.startDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        return startDateS;
    }

    public void setStartDateS(String startDateS) {
        this.startDateS = this.startDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
    }

    public String getEndDateS() {
        /*if(this.endDateS == null){
            this.endDateS = this.endDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        }*/
        this.endDateS = this.endDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        return endDateS;
    }

    public void setEndDateS(String endDateS) {
        this.endDateS = this.endDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProtocolNumber() {
        return protocolNumber;
    }

    public void setProtocolNumber(Integer protocolNumber) {
        this.protocolNumber = protocolNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /*public Float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Float getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(Float humidityMin) {
        this.humidityMin = humidityMin;
    }

    public Float getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(Float humidityMax) {
        this.humidityMax = humidityMax;
    }

    public Integer getPressureMin() {
        return pressureMin;
    }

    public void setPressureMin(Integer pressureMin) {
        this.pressureMin = pressureMin;
    }

    public Integer getPressureMax() {
        return pressureMax;
    }

    public void setPressureMax(Integer pressureMax) {
        this.pressureMax = pressureMax;
    }*/

    /*public TestMethod getTestMethod() {
        return testMethod;
    }

    public void setTestMethod(TestMethod testMethod) {
        this.testMethod = testMethod;
    }*/

    public List<TestMethod> getTestMethods() {
        return testMethods;
    }

    public void setTestMethods(List<TestMethod> testMethods) {
        this.testMethods = testMethods;
    }

    public List<Employee> getPerformers() {
        return performers;
    }

    public void setPerformers(List<Employee> performers) {
        this.performers = performers;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }
}
