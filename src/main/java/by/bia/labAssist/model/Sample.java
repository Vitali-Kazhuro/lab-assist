package by.bia.labAssist.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Sample {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cipher;
    private String series;
    private String samplingReport;
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "object_of_study_id")
    private ObjectOfStudy objectOfStudy;

    @ManyToOne
    @JoinColumn(name = "test_report_id")
    private TestReport testReport;

    @OneToMany(mappedBy = "sample", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SampleNorm> sampleNorms;

    @Transient
    private String units;
    @Transient
    private String notGreater;

    public Sample() {
    }

    public Sample(String cipher, String series, String samplingReport, String quantity,
                  ObjectOfStudy objectOfStudy, TestReport testReport) {
        this.cipher = cipher.replaceAll("\\s+", " ").trim();
        this.series = series.replaceAll("\\s+", " ").trim();
        this.samplingReport = samplingReport.replaceAll("\\s+", " ").trim();
        this.quantity = quantity.replaceAll("\\s+", " ").trim();
        this.objectOfStudy = objectOfStudy;
        this.testReport = testReport;
        this.sampleNorms = new ArrayList<>();
    }

    public String getNotGreater() {
        long notGreaterCount = this.getSampleNorms().stream()
                .map(SampleNorm::getNorm)
                .map(Norm::getValue)
                .filter(s -> s.contains("не более"))
                .count();
        if(this.getSampleNorms().size() == notGreaterCount){
            return ", не более";
        }
        return "";
    }

    public void setNotGreater(String notGreater) {
        this.notGreater = notGreater;
    }

    public String getUnits() {
        List<String> unitsList = this.getSampleNorms().stream()
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series.replaceAll("\\s+", " ").trim();
    }

    public String getSamplingReport() {
        return samplingReport;
    }

    public void setSamplingReport(String samplingReport) {
        this.samplingReport = samplingReport.replaceAll("\\s+", " ").trim();
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity.replaceAll("\\s+", " ").trim();
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher.replaceAll("\\s+", " ").trim();
    }

    public ObjectOfStudy getObjectOfStudy() {
        return objectOfStudy;
    }

    public void setObjectOfStudy(ObjectOfStudy objectOfStudy) {
        this.objectOfStudy = objectOfStudy;
    }

    public TestReport getTestReport() {
        return testReport;
    }

    public void setTestReport(TestReport testReport) {
        this.testReport = testReport;
    }

    public List<SampleNorm> getSampleNorms() {
        return sampleNorms;
    }

    public void setSampleNorms(List<SampleNorm> sampleNorms) {
        this.sampleNorms = sampleNorms;
    }

    public String forGrouping(){
        return this.getSampleNorms().stream().map(SampleNorm::getNorm).collect(Collectors.toList()).toString();
    }
}
