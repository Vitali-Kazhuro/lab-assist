package by.bia.labAssist.service;

import by.bia.labAssist.model.Applicant;
import by.bia.labAssist.model.Employee;
import by.bia.labAssist.model.TestMethod;
import by.bia.labAssist.model.TestReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface TestReportService {
    TestReport save(Integer protocolNumber, String date, TestMethod testMethod1, TestMethod testMethod2,
                    String startDate, String endDate, Employee employee1, Employee employee2, Applicant applicant);

    TestReport edit(TestReport testReportEdit, Integer protocolNumber, String date, TestMethod testMethod1,
                    TestMethod testMethod2,String startDate, String endDate, Employee employee1, Employee employee2);

    List<TestReport> findAll();

    List<TestReport> findAll(List<TestReport> testReports);

    Page<TestReport> findAllPages(Pageable pageable);

    TestReport findById(Integer id);

    void delete(Integer id);

    List<TestReport> findAllByDateAfterAndApplicantId(LocalDate date, Integer id);

    /**
     * *
     * @param testReports - test reports filtered by applicant and date
     * @param price - price of one test for one element
     * @return Map<String, Object> with key "text" for formed text passages, key "sum" for total analise price,
     * "protocolAndDate" for protocols and dates of protocols for given act
     */
    Map<String, Object> createActPassages(List<TestReport> testReports, Integer price);

    Map<String, Object> createProtocolPassages(TestReport testReport);
}
