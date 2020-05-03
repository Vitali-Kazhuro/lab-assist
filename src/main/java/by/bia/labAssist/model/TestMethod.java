package by.bia.labAssist.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class TestMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    //@OneToMany(mappedBy = "testMethod")
    @ManyToMany(mappedBy = "testMethods")
    private List<TestReport> testReports;

    public TestMethod() {
    }

    public TestMethod(String title) {
        this.title = title;
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

    public List<TestReport> getTestReports() {
        return testReports;
    }

    public void setTestReports(List<TestReport> testReports) {
        this.testReports = testReports;
    }
}
