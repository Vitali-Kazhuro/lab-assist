package by.bia.labAssist.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class ObjectOfStudy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String producer;

    @OneToMany(mappedBy = "objectOfStudy", cascade = CascadeType.ALL)
    private List<Sample> samples;

    @ManyToOne
    @JoinColumn(name = "sampling_authority_id")
    private SamplingAuthority samplingAuthority;

    @ManyToOne
    @JoinColumn(name = "regulatory_document_id")
    private RegulatoryDocument regulatoryDocument;

    public ObjectOfStudy() {
    }

    public ObjectOfStudy(String title, String producer, SamplingAuthority samplingAuthority, RegulatoryDocument regulatoryDocument) {
        this.title = title.replaceAll("\\s+", " ").trim();
        this.producer = producer.replaceAll("\\s+", " ").trim();
        this.samplingAuthority = samplingAuthority;
        this.regulatoryDocument = regulatoryDocument;
    }

    public String getTitleStripped() {
        if(title.length() >= 40) {
            return title.substring(0, 40);// + "...";
        }
        return title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title.replaceAll("\\s+", " ").trim();
    }

    public String getProducer() {
        if (producer.equals("")) return producer;
        return "- производитель " + producer;
    }

    public void setProducer(String producer) {
        this.producer = producer.replaceAll("\\s+", " ").trim();
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public void setSamples(List<Sample> samples) {
        this.samples = samples;
    }

    public SamplingAuthority getSamplingAuthority() {
        return samplingAuthority;
    }

    public void setSamplingAuthority(SamplingAuthority samplingAuthority) {
        this.samplingAuthority = samplingAuthority;
    }

    public RegulatoryDocument getRegulatoryDocument() {
        return regulatoryDocument;
    }

    public void setRegulatoryDocument(RegulatoryDocument regulatoryDocument) {
        this.regulatoryDocument = regulatoryDocument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectOfStudy that = (ObjectOfStudy) o;
        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
