package by.bia.labAssist.service;

import by.bia.labAssist.model.ObjectOfStudy;
import by.bia.labAssist.model.Sample;
import by.bia.labAssist.model.TestReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Provides service logic for{@link Sample} entity
 */
public interface SampleService {
    /**
     * Returns List with all Sample instances
     * @return {@link List<Sample>} object
     */
    List<Sample> findAll();

    /**
     * Returns page of all Sample instances
     * @param pageable pageable object
     * @return {@link Page<Sample>} object
     */
    Page<Sample> findAllPages(Pageable pageable);

    /**
     * Returns Sample instance by id
     * @param id Sample id
     * @return {@link Sample} object
     */
    Sample findById(Integer id);

    /**
     * Creates new Sample instance and persists it into database
     * @param cipher Sample cipher
     * @param series Sample series
     * @param samplingReport Sample samplingReport
     * @param quantity Sample quantity
     * @param objectOfStudy Sample {@link ObjectOfStudy}
     * @param TestReport Sample {@link TestReport}
     * @return {@link Sample} object that was created
     */
    Sample create(String cipher, String series, String samplingReport, String quantity,
                  ObjectOfStudy objectOfStudy, TestReport TestReport);

    /**
     * Edits passed Sample instance and persists it into database
     * @param sampleEdit edited instance of Sample
     * @param cipher Sample cipher
     * @param series Sample series
     * @param samplingReport Sample samplingReport
     * @param quantity Sample quantity
     * @return {@link Sample} instance that was edited
     */
    Sample edit(Sample sampleEdit, String cipher, String series, String samplingReport,
                String quantity);

    /**
     * Deletes instance of Sample from database
     * @param id Sample id
     */
    void delete(Integer id);
}
