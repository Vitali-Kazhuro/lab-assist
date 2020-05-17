package by.bia.labAssist.service;

import by.bia.labAssist.model.Element;
import by.bia.labAssist.model.Norm;
import by.bia.labAssist.model.RegulatoryDocument;

import java.util.List;
import java.util.Map;

/**
 * Provides service logic for{@link Norm} entity
 */
public interface NormService {
    /**
     * Returns List of Norm instances for particular {@link RegulatoryDocument}
     * @param id {@link RegulatoryDocument} id
     * @return {@link List<Norm>} object
     */
    List<Norm> findByRegulatoryDocumentId(Integer id);

    /**
     * Get Norm instances that were checked at frontend form from all Norm instances
     * @param form html form with checked Norms
     * @param allNorms {@link List<Norm>} List with all Norm instances
     * @return {@link List<Norm>} List with checked Norm instances
     */
    List<Norm> getCheckedNorms(Map<String, String> form, List<Norm> allNorms);

    /**
     * Creates and persists Norm instance for new {@link RegulatoryDocument} instance into database
     * @param form html form with chosen Norm instances
     * @param allElements List of all {@link Element} instances
     * @param regulatoryDocument {@link RegulatoryDocument} for which Norm instances are created
     */
    void saveNormForNewRegulatoryDocument(Map<String, String> form,
                                          List<Element> allElements, RegulatoryDocument regulatoryDocument);

    /**
     * Edits Norm instances for passed {@link RegulatoryDocument}
     * @param form html form with chosen Norm instances
     * @param allElements List of all {@link Element} instances
     * @param regulatoryDocumentEdit {@link RegulatoryDocument} for which Norm instances are edited
     * @return {@link RegulatoryDocument} with edited Norm instances
     */
    RegulatoryDocument edit(Map<String, String> form, List<Element> allElements, RegulatoryDocument regulatoryDocumentEdit);
}
