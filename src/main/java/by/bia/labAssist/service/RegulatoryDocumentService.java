package by.bia.labAssist.service;

import by.bia.labAssist.model.RegulatoryDocument;

import java.util.List;

/**
 * Provides service logic for{@link RegulatoryDocument} entity
 */
public interface RegulatoryDocumentService {
    /**
     * Returns List with all RegulatoryDocument instances
     * @return {@link List<RegulatoryDocument>} object
     */
    List<RegulatoryDocument> findAll();

    /**
     * Returns List of RegulatoryDocument instances, which title contains searched phrase
     * @param search searched phrase that RegulatoryDocument's title must contain
     * @return {@link List<RegulatoryDocument>} object
     */
    List<RegulatoryDocument> findAllByTitleContains(String search);

    /**
     * Returns RegulatoryDocument instance by id
     * @param id RegulatoryDocument id
     * @return {@link RegulatoryDocument} object
     */
    RegulatoryDocument findById(Integer id);

    /**
     * Creates new RegulatoryDocument instance and persists it into database
     * @param title RegulatoryDocument title
     * @return {@link RegulatoryDocument} object that was created
     */
    RegulatoryDocument create(String title);

    /**
     * Edits passed RegulatoryDocument instance and persists it into database
     * @param regulatoryDocumentEdit edited instance of RegulatoryDocument
     * @param title RegulatoryDocument title
     */
    void edit(RegulatoryDocument regulatoryDocumentEdit, String title);

    /**
     * Deletes instance of RegulatoryDocument from database
     * @param id RegulatoryDocument id
     */
    void delete(Integer id);
}
