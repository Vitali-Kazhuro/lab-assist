package by.bia.labAssist.service;

import by.bia.labAssist.model.Applicant;

import java.util.List;

public interface ApplicantService {
    List<Applicant> findAll();

    List<Applicant> findAllByOrganizationContains(String search);

    Applicant findById(Integer id);

    void save(String organization, String address, String mailingAddress, String iban,
              String bank, String bankAddress, String bic, String unn, String okpo, String telephones,
              String email, String contractNumber, String contractDate, String headPosition, String headName);

    void edit(Applicant applicant, String organization, String address, String mailingAddress, String iban,
              String bank, String bankAddress, String bic, String unn, String okpo, String telephones,
              String email, String contractNumber, String contractDate, String headPosition, String headName);

    void delete(Integer id);

}
