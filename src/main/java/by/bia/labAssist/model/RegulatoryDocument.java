package by.bia.labAssist.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class RegulatoryDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    @OneToMany(mappedBy = "regulatoryDocument", cascade = CascadeType.ALL)
    private List<ObjectOfStudy> objectsOfStudy;

    @OneToMany(mappedBy = "regulatoryDocument", cascade = CascadeType.ALL, orphanRemoval = true)// последнее чтобы можно было нормы удалять при изменении
    private List<Norm> norms;

    public RegulatoryDocument() {
    }

    public RegulatoryDocument(String title) {
        this.title = title.replaceAll("\\s+", " ").trim();
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

    public List<ObjectOfStudy> getObjectsOfStudy() {
        return objectsOfStudy;
    }

    public void setObjectsOfStudy(List<ObjectOfStudy> objectsOfStudy) {
        this.objectsOfStudy = objectsOfStudy;
    }

    public List<Norm> getNorms() {
        return norms;
    }

    public void setNorms(List<Norm> norms) {
        this.norms = norms;
    }
}
