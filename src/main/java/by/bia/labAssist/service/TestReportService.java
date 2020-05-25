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

/**
 * Provides service logic for{@link TestReport} entity
 */
public interface TestReportService {
    /**
     * Returns List with all TestReport instances
     * @return {@link List<TestReport>} object
     */
    List<TestReport> findAll();

    /**
     * Returns List with all TestReport instances that were passed to it, but in context
     * @param testReports list of TestReport instances that needs to be in context
     * @return {@link List<TestReport>} object
     */
    List<TestReport> findAll(List<TestReport> testReports);

    /**
     * Returns page of all TestReport instances
     * @param pageable pageable object
     * @return {@link Page<TestReport>} object
     */
    Page<TestReport> findAllPages(Pageable pageable);

    /**
     * Returns TestReport by id
     * @param id TestReport id
     * @return {@link TestReport} object
     */
    TestReport findById(Integer id);

    /**
     * Returns all TestReport instances with given {@link Applicant} that were dated after passed date till today
     * @param date TestReport date
     * @param id {@link Applicant} id
     * @return {@link  List<TestReport>} object
     */
    List<TestReport> findAllByDateAfterAndApplicantId(LocalDate date, Integer id);

    /**
     * Creates new TestReport instance and persists it into database
     * @param protocolNumber TestReport protocolNumber
     * @param date TestReport date
     * @param testMethods TestReport {@link List<TestMethod>}
     * @param startDate TestReport startDate
     * @param endDate TestReport endDate
     * @param employee1 TestReport first {@link Employee}
     * @param employee2 TestReport second {@link Employee}
     * @param applicant TestReport {@link Applicant}
     * @return {@link TestReport} instance that was created
     */
    TestReport create(Integer protocolNumber, String date, List<TestMethod> testMethods, String startDate,
                      String endDate, Employee employee1, Employee employee2, Applicant applicant);

    /**
     * Edits passed TestReport instance and persists it into database
     * @param testReportEdit edited instance of TestReport
     * @param protocolNumber TestReport protocolNumber
     * @param date TestReport date
     * @param testMethods TestReport {@link List<TestMethod>}
     * @param startDate TestReport startDate
     * @param endDate TestReport endDate
     * @param employee1 TestReport first {@link Employee}
     * @param employee2 TestReport second {@link Employee}
     * @return {@link TestReport} instance that was edited
     */
    TestReport edit(TestReport testReportEdit, Integer protocolNumber, String date, List<TestMethod> testMethods,
                    String startDate, String endDate, Employee employee1, Employee employee2);

    /**
     * Deletes instance of TestReport from database
     * @param id TestReport id
     */
    void delete(Integer id);

    /**
     * Creates act passages
     * @param testReports - {@link List<TestReport>} filtered by applicant and date
     * @param price - price of one test for one element
     * @return {@link Map<String, Object>} with key "text" for String with formed text passages,
     *          key "textCheck" - same as text, but for html,
     *          key "sum" for int total analise price,
     *          key "protocolAndDate" for String passage with protocols and dates of protocols for given act
     */
    Map<String, Object> createActPassages(List<TestReport> testReports, Integer price);

    /**
     * Creates test protocol passages
     * @param testReport TestReport for which passages are created
     * @return {@link Map<String, Object>} which key "testReport" for TestReport for which passages are created,
     *         key "samplesName" for String passage with names of samples that were tested for this test report,
     *         key "testType" for String passage with types of tests that this samples were tested with,
     *         key "testMethods" for String passage with test methods that were involved,
     *         key "quantity" for String passage with quantity of samples,
     *         key "testTypeInTable" for String passage with types of tests that this samples were tested with, but in table,
     *         key "resultTableRowList" @{@link List<by.bia.labAssist.util.ResultTableRow>} list of objects with data for result table,
     *         key "conclusionTitle" for String passage for conclusion title,
     *         key "conclusion" for String passage containing conclusion on tested samples
     */
    Map<String, Object> createProtocolPassages(TestReport testReport);
}
