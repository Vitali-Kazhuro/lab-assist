package by.bia.labAssist.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Norm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String value;
    private String units;

    @ManyToOne
    @JoinColumn(name = "element_id")
    private Element element;

    @ManyToOne
    @JoinColumn(name = "regulatory_document_id")
    private RegulatoryDocument regulatoryDocument;

    @OneToMany(mappedBy = "norm", cascade = CascadeType.ALL)
    private List<SampleNorm> sampleNorms;

    public Norm() {
    }

    public Norm(String value, String units, Element element, RegulatoryDocument regulatoryDocument) {
        this.value = value.replaceAll("\\s+", " ").trim();
        this.units = units.replaceAll("\\s+", " ").trim();
        this.element = element;
        this.regulatoryDocument = regulatoryDocument;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value.replaceAll("\\s+", " ").trim();
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units.replaceAll("\\s+", " ").trim();
    }

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }

    public RegulatoryDocument getRegulatoryDocument() {
        return regulatoryDocument;
    }

    public void setRegulatoryDocument(RegulatoryDocument regulatoryDocument) {
        this.regulatoryDocument = regulatoryDocument;
    }

    public List<SampleNorm> getSampleNorms() {
        return sampleNorms;
    }

    public void setSampleNorms(List<SampleNorm> sampleNorms) {
        this.sampleNorms = sampleNorms;
    }
}
