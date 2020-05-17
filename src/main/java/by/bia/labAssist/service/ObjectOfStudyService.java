package by.bia.labAssist.service;

import by.bia.labAssist.model.ObjectOfStudy;
import by.bia.labAssist.model.RegulatoryDocument;
import by.bia.labAssist.model.SamplingAuthority;

import java.util.List;

/**
 * Provides service logic for{@link ObjectOfStudy} entity
 */
public interface ObjectOfStudyService {
    /**
     * Returns ObjectOfStudy instance by id
     * @param id ObjectOfStudy id
     * @return {@link ObjectOfStudy} object
     */
    ObjectOfStudy findById(Integer id);

    /**
     * Returns List of ObjectOfStudy instances for particular {@link SamplingAuthority}
     * @param id {@link SamplingAuthority} id
     * @return {@link List<ObjectOfStudy>} object
     */
    List<ObjectOfStudy> findBySamplingAuthorityId(Integer id);

    /**
     * Returns List of ObjectOfStudy instances for particular {@link SamplingAuthority}, which title contains searched phrase
     * @param id {@link SamplingAuthority} id
     * @param search searched phrase that ObjectOfStudy's title must contain
     * @return {@link List<ObjectOfStudy>} object
     */
    List<ObjectOfStudy> findBySamplingAuthorityIdAndTitleContains(Integer id, String search);

    /**
     * Creates new ObjectOfStudy instance and persists it into database
     * @param title ObjectOfStudy title
     * @param producer ObjectOfStudy producer
     * @param samplingAuthority ObjectOfStudy {@link SamplingAuthority}
     * @param regulatoryDocument ObjectOfStudy {@link RegulatoryDocument}
     */
    void create(String title, String producer, SamplingAuthority samplingAuthority, RegulatoryDocument regulatoryDocument);

    /**
     * Edits passed ObjectOfStudy instance and persists it into database
     * @param objectOfStudy edited instance of ObjectOfStudy
     * @param title ObjectOfStudy title
     * @param producer ObjectOfStudy producer
     * @param samplingAuthority ObjectOfStudy {@link SamplingAuthority}
     * @param regulatoryDocument ObjectOfStudy {@link RegulatoryDocument}
     */
    void edit(ObjectOfStudy objectOfStudy, String title, String producer,
              SamplingAuthority samplingAuthority, RegulatoryDocument regulatoryDocument);

    /**
     * Deletes instance of ObjectOfStudy from database
     * @param id ObjectOfStudy id
     */
    void delete(Integer id);
}