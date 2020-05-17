package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.Applicant;
import by.bia.labAssist.model.SamplingAuthority;
import by.bia.labAssist.repository.SamplingAuthorityRepository;
import by.bia.labAssist.service.SamplingAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SamplingAuthorityServiceImpl implements SamplingAuthorityService {
    @Autowired
    private SamplingAuthorityRepository samplingAuthorityRepository;

    @Override
    public SamplingAuthority findById(Integer id) {
        return samplingAuthorityRepository.findById(id).get();
    }

    @Override
    public List<SamplingAuthority> findAllByApplicantIdAndTitleContains(Integer id, String search) {
        return samplingAuthorityRepository.findAllByApplicantIdAndTitleContains(id, search);
    }

    @Override
    public List<SamplingAuthority> findByApplicantId(Integer id) {
        return samplingAuthorityRepository.findByApplicantId(id);
    }

    @Override
    public void create(String title, Applicant applicant) {
        SamplingAuthority samplingAuthority = new SamplingAuthority();
        samplingAuthority.setTitle(title);
        samplingAuthority.setApplicant(applicant);

        samplingAuthorityRepository.save(samplingAuthority);
    }

    @Override
    public void edit(SamplingAuthority samplingAuthorityEdit, String title) {
        samplingAuthorityEdit.setTitle(title);

        samplingAuthorityRepository.save(samplingAuthorityEdit);
    }

    @Override
    public void delete(Integer id) {
        samplingAuthorityRepository.deleteById(id);
    }
}
