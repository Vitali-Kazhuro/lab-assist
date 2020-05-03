package by.bia.labAssist.service;

import by.bia.labAssist.model.Element;
import by.bia.labAssist.model.Norm;
import by.bia.labAssist.model.RegulatoryDocument;

import java.util.List;
import java.util.Map;

public interface NormService {
    List<Norm> findByRegulatoryDocumentId(Integer id);

    List<Norm> getCheckedNorms(Map<String, String> form, List<Norm> allNorms);

    void saveNormForNewRegulatoryDocument(Map<String, String> form,
                                          List<Element> allElements, RegulatoryDocument regulatoryDocument);

    RegulatoryDocument edit(Map<String, String> form, List<Element> allElements, RegulatoryDocument regulatoryDocumentEdit);
}
