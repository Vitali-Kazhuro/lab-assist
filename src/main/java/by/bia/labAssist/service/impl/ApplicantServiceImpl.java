package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.Applicant;
import by.bia.labAssist.repository.ApplicantRepository;
import by.bia.labAssist.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ApplicantServiceImpl implements ApplicantService {
    @Autowired
    private ApplicantRepository applicantRepository;

    @Override
    public List<Applicant> findAll() {
        return applicantRepository.findAll();
    }

    @Override
    public List<Applicant> findAllByOrganizationContains(String search) {
        return applicantRepository.findAllByOrganizationContainsIgnoreCase(search);
    }

    @Override
    public Applicant findById(Integer id) {
        return applicantRepository.findById(id).get();
    }

    @Override
    public void create(String organization, String address, String mailingAddress, String iban,
                       String bank, String bankAddress, String bic, String unp, String okpo, String telephones,
                       String email, String contractNumber, String contractDate, String headPosition, String headName) {
        LocalDate formattedContractDate = LocalDate.parse(contractDate, DateTimeFormatter.ISO_LOCAL_DATE);

        applicantRepository.save(new Applicant(organization, address, mailingAddress, iban, bank, bankAddress,
                bic, unp, okpo, telephones, email, contractNumber, formattedContractDate, headPosition, headName));
    }

    @Override
    public void edit(Applicant applicant, String organization, String address, String mailingAddress, String iban,
                     String bank, String bankAddress, String bic, String unp, String okpo, String telephones,
                     String email, String contractNumber, String contractDate, String headPosition, String headName) {
        LocalDate formattedContractDate = LocalDate.parse(contractDate, DateTimeFormatter.ISO_LOCAL_DATE);

        applicant.setOrganization(organization);
        applicant.setAddress(address);
        applicant.setMailingAddress(mailingAddress);
        applicant.setIban(iban);
        applicant.setBank(bank);
        applicant.setBankAddress(bankAddress);
        applicant.setBic(bic);
        applicant.setUnp(unp);
        applicant.setOkpo(okpo);
        applicant.setTelephones(telephones);
        applicant.setEmail(email);
        applicant.setContractNumber(contractNumber);
        applicant.setContractDate(formattedContractDate);
        applicant.setHeadPosition(headPosition);
        applicant.setHeadName(headName);

        applicantRepository.save(applicant);
    }

    @Override
    public void delete(Integer id) {
        applicantRepository.deleteById(id);
    }
}
