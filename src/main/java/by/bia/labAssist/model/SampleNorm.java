package by.bia.labAssist.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class SampleNorm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double result1;
    private Double result2;
    private String detectionLimit;

    @ManyToOne
    @JoinColumn(name = "sample_id")
    private Sample sample;

    @ManyToOne
    @JoinColumn(name = "norm_id")
    private Norm norm;

    @Transient
    private String mean;

    public SampleNorm() {
    }

    public SampleNorm(Sample sample, Norm norm) {
        this.sample = sample;
        this.norm = norm;
    }

    public String getMean() {
        if (this.result1 != null && this.result2 != null && this.getDetectionLimit() == null)
            this.mean = ((BigDecimal.valueOf(result1).add(BigDecimal.valueOf(result2)))
                    .divide(BigDecimal.valueOf(2))).toPlainString().replace(".",",");
        else {
            this.mean = this.detectionLimit;
        }
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getResult1() {
        return result1;
    }

    public void setResult1(Double result1) {
        this.result1 = result1;
    }

    public Double getResult2() {
        return result2;
    }

    public void setResult2(Double result2) {
        this.result2 = result2;
    }

    public String getDetectionLimit() {
        return detectionLimit;
    }

    public void setDetectionLimit(String detectionLimit) {
        if (detectionLimit.equals("")) this.detectionLimit = null;
        else this.detectionLimit = detectionLimit.replaceAll("\\s+", " ").trim();
    }

    public Sample getSample() {
        return sample;
    }

    public void setSample(Sample sample) {
        this.sample = sample;
    }

    public Norm getNorm() {
        return norm;
    }

    public void setNorm(Norm norm) {
        this.norm = norm;
    }
}
