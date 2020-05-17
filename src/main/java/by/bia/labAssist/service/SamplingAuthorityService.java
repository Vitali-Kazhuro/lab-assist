package by.bia.labAssist.service;

import by.bia.labAssist.model.Applicant;
import by.bia.labAssist.model.SamplingAuthority;

import java.util.List;

public interface SamplingAuthorityService {
    SamplingAuthority findById(Integer id);

    List<SamplingAuthority> findAllByApplicantIdAndTitleContains(Integer id, String search);

    List<SamplingAuthority> findByApplicantId(Integer id);

    void create(String title, Applicant applicant);

    void edit(SamplingAuthority samplingAuthorityEdit, String title);

    void delete(Integer id);
}
