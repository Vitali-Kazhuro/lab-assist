package by.bia.labAssist.service;

import by.bia.labAssist.model.Applicant;
import by.bia.labAssist.model.SamplingAuthority;

import java.util.List;

/**
 * Provides service logic for{@link SamplingAuthority} entity
 */
public interface SamplingAuthorityService {
    /**
     * Returns SamplingAuthority instance by id
     * @param id SamplingAuthority id
     * @return {@link SamplingAuthority} object
     */
    SamplingAuthority findById(Integer id);

    /**
     * Returns List of SamplingAuthority instances for particular {@link Applicant}, which title contains searched phrase
     * @param id {@link Applicant} id
     * @param search searched phrase that SamplingAuthority's title must contain
     * @return {@link List<SamplingAuthority>} object
     */
    List<SamplingAuthority> findAllByApplicantIdAndTitleContains(Integer id, String search);

    /**
     * Returns List of SamplingAuthority instances for particular {@link Applicant}
     * @param id {@link Applicant} id
     * @return {@link List<SamplingAuthority>} object
     */
    List<SamplingAuthority> findByApplicantId(Integer id);

    /**
     * Creates new SamplingAuthority instance and persists it into database
     * @param title SamplingAuthority title
     * @param applicant {@link Applicant} for whom we are creating this SamplingAuthority
     */
    void create(String title, Applicant applicant);

    /**
     * Edits passed SamplingAuthority instance and persists it into database
     * @param samplingAuthorityEdit edited instance of SamplingAuthority
     * @param title SamplingAuthority title
     */
    void edit(SamplingAuthority samplingAuthorityEdit, String title);

    /**
     * Deletes instance of SamplingAuthority from database
     * @param id SamplingAuthority id
     */
    void delete(Integer id);
}
