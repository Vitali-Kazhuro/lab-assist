package by.bia.labAssist.service;

import by.bia.labAssist.model.Norm;
import by.bia.labAssist.model.Sample;

import java.util.List;
import java.util.Map;

/**
 * Provides service logic for{@link by.bia.labAssist.model.SampleNorm} entity
 */
public interface SampleNormService {
    /**
     * Creates SampleNorm for {@link Sample}
     * @param sample {@link Sample} instance
     * @param checkedNorms {@link List<Norm>} List of {@link Norm} that were checked for testing for this Sample
     * @return {@link Sample} instance with added checked norms
     */
    Sample createSampleNormForSample(Sample sample, List<Norm> checkedNorms);

    /**
     * Edits SampleNorm for {@link Sample}
     * @param sample {@link Sample} instance
     * @param checkedNormsEdit {@link List<Norm>} List of {@link Norm} that were checked for testing for this Sample
     * @return instance with edited checked norms
     */
    Sample editSampleNormForSample(Sample sample, List<Norm> checkedNormsEdit);

    /**
     * Adds result1 and result2 or detectionLimit to {@link by.bia.labAssist.model.SampleNorm} for this {@link Sample}
     * depending on what was checked at form
     * @param sample {@link Sample} instance
     * @param form html form from frontend with checked detectionLimit or results and ints values
     */
    void processResults(Sample sample, Map<String, String> form);
}
