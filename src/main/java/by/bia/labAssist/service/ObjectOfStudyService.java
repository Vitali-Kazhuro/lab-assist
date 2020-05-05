package by.bia.labAssist.service;

import by.bia.labAssist.model.ObjectOfStudy;
import by.bia.labAssist.model.RegulatoryDocument;
import by.bia.labAssist.model.SamplingAuthority;

import java.util.List;

public interface ObjectOfStudyService {
    ObjectOfStudy findById(Integer id);

    List<ObjectOfStudy> findBySamplingAuthorityId(Integer id);

    List<ObjectOfStudy> findBySamplingAuthorityIdAndTitleContains(Integer id, String search);

    void save(String title, String producer, SamplingAuthority samplingAuthority, RegulatoryDocument regulatoryDocument);

    void edit(ObjectOfStudy objectOfStudy, String title, String producer,
              SamplingAuthority samplingAuthority, RegulatoryDocument regulatoryDocument);

    void delete(Integer id);
}
