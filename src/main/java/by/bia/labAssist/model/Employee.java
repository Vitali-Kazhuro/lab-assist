package by.bia.labAssist.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String position;

    @ManyToMany(mappedBy = "performers")
    private List<TestReport> testReports;

    public Employee() {
    }

    public Employee(String name, String position) {
        this.name = name;
        this.position = position;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name.replaceAll("\\s+", " ").trim();
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position.replaceAll("\\s+", " ").trim();
    }

    public List<TestReport> getTestReports() {
        return testReports;
    }

    public void setTestReports(List<TestReport> testReports) {
        this.testReports = testReports;
    }
}
