package by.bia.labAssist.service;

import by.bia.labAssist.model.ObjectOfStudy;
import by.bia.labAssist.model.Sample;
import by.bia.labAssist.model.TestReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SampleService {
    List<Sample> findAll();

    Page<Sample> findAllPages(Pageable pageable);

    Sample findById(Integer id);

    Sample create(String cipher, String series, String samplingReport, String quantity,
                  ObjectOfStudy objectOfStudy, TestReport TestReport);

    Sample edit(Sample sampleEdit, String cipher, String series, String samplingReport,
                String quantity);

    void delete(Integer id);
}
