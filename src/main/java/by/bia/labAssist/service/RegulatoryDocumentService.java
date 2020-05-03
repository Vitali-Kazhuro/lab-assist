package by.bia.labAssist.service;

import by.bia.labAssist.model.RegulatoryDocument;

import java.util.List;

public interface RegulatoryDocumentService {
    List<RegulatoryDocument> findAll();

    RegulatoryDocument findById(Integer id);

    RegulatoryDocument save(String title);

    void edit(RegulatoryDocument regulatoryDocumentEdit, String title);

    void delete(Integer id);
}
