package by.bia.labAssist.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class SamplingAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @OneToMany(mappedBy = "samplingAuthority", cascade = CascadeType.ALL)
    private List<ObjectOfStudy> objectsOfStudy;

    public SamplingAuthority() {
    }

    public SamplingAuthority(String title, Applicant applicant) {
        this.title = title.replaceAll("\\s+", " ").trim();
        this.applicant = applicant;
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

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public List<ObjectOfStudy> getObjectsOfStudy() {
        return objectsOfStudy;
    }

    public void setObjectsOfStudy(List<ObjectOfStudy> objectsOfStudy) {
        this.objectsOfStudy = objectsOfStudy;
    }

}
