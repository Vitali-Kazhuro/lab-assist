package by.bia.labAssist.repository;

import by.bia.labAssist.model.TestReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TestReportRepository extends JpaRepository<TestReport, Integer> {

    List<TestReport> findAllByDateAfterAndApplicantId(LocalDate date, Integer id);
}
