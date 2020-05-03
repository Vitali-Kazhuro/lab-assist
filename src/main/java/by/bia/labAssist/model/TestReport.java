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

    @ManyToMany
    @JoinTable(
            name = "test_report_test_method",
            joinColumns = @JoinColumn(name = "test_report_id"),
            inverseJoinColumns = @JoinColumn(name = "test_method_id"))
    private List<TestMethod> testMethods;

    @ManyToMany
    @JoinTable(
            name = "test_report_doer",
            joinColumns = @JoinColumn(name = "test_report_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> performers;

    @OneToMany(mappedBy = "testReport", cascade = CascadeType.ALL)
    private List<Sample> samples;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    public TestReport() {
    }

    public TestReport(Integer protocolNumber, LocalDate date, LocalDate startDate, LocalDate endDate, Applicant applicant) {
        this.protocolNumber = protocolNumber;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.testMethods = new ArrayList<>();
        this.performers = new ArrayList<>();
        this.applicant = applicant;
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
        this.dateS = this.date.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        return dateS;
    }

    public void setDateS(String dateS) {
        this.dateS = this.date.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
    }

    public String getStartDateS() {
        this.startDateS = this.startDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        return startDateS;
    }

    public void setStartDateS(String startDateS) {
        this.startDateS = this.startDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
    }

    public String getEndDateS() {
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
