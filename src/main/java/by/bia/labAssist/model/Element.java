package by.bia.labAssist.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Element {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String symbol;

    @OneToMany(mappedBy = "element")
    private List<Norm> norms;

    public Element() {
    }

    public Element(String title, String symbol) {
        this.title = title;
        this.symbol = symbol;
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

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol.replaceAll("\\s+", " ").trim();;
    }

    public List<Norm> getNorms() {
        return norms;
    }

    public void setNorms(List<Norm> norms) {
        this.norms = norms;
    }
}
