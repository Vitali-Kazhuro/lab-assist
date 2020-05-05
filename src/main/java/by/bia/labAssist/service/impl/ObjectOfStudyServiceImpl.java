package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.ObjectOfStudy;
import by.bia.labAssist.model.RegulatoryDocument;
import by.bia.labAssist.model.SamplingAuthority;
import by.bia.labAssist.repository.ObjectOfStudyRepository;
import by.bia.labAssist.service.ObjectOfStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObjectOfStudyServiceImpl implements ObjectOfStudyService {
    @Autowired
    private ObjectOfStudyRepository objectOfStudyRepository;

    @Override
    public ObjectOfStudy findById(Integer id) {
        return objectOfStudyRepository.findById(id).get();
    }

    @Override
    public List<ObjectOfStudy> findBySamplingAuthorityId(Integer id) {
        return objectOfStudyRepository.findBySamplingAuthorityId(id);
    }

    @Override
    public List<ObjectOfStudy> findBySamplingAuthorityIdAndTitleContains(Integer id, String search) {
        return objectOfStudyRepository.findBySamplingAuthorityIdAndTitleContains(id, search);
    }

    @Override
    public void save(String title, String producer, SamplingAuthority samplingAuthority,
                     RegulatoryDocument regulatoryDocument) {
        ObjectOfStudy objectOfStudy = new ObjectOfStudy(title, producer, samplingAuthority, regulatoryDocument);

        objectOfStudyRepository.save(objectOfStudy);
    }

    @Override
    public void edit(ObjectOfStudy objectOfStudy, String title, String producer,
                     SamplingAuthority samplingAuthority, RegulatoryDocument regulatoryDocument) {
        objectOfStudy.setTitle(title);
        objectOfStudy.setProducer(producer);
        objectOfStudy.setSamplingAuthority(samplingAuthority);
        objectOfStudy.setRegulatoryDocument(regulatoryDocument);

        objectOfStudyRepository.save(objectOfStudy);
    }

    @Override
    public void delete(Integer id) {
        objectOfStudyRepository.deleteById(id);
    }
}
