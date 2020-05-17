package by.bia.labAssist.service;

import by.bia.labAssist.model.Applicant;

import java.util.List;

/**
 * Provides service logic for{@link Applicant} entity
 */
public interface ApplicantService {
    /**
     * Returns List with all Applicant instances
     * @return {@link List<Applicant>} object
     */
    List<Applicant> findAll();

    /**
     * Returns List of Applicant instances, which organization contains searched phrase
     * @param search searched phrase that Applicant's organization must contain
     * @return {@link List<Applicant>} object
     */
    List<Applicant> findAllByOrganizationContains(String search);

    /**
     * Returns Applicant instance by id
     * @param id Applicant id
     * @return {@link Applicant} object
     */
    Applicant findById(Integer id);

    /**
     * Creates new Applicant instance and persists it into database
     * @param organization Applicant organization
     * @param address Applicant address
     * @param mailingAddress Applicant mailingAddress
     * @param iban Applicant iban
     * @param bank Applicant bank
     * @param bankAddress Applicant bankAddress
     * @param bic Applicant bic
     * @param unn Applicant unn
     * @param okpo Applicant okpo
     * @param telephones Applicant telephones
     * @param email Applicant email
     * @param contractNumber Applicant contractNumber
     * @param contractDate Applicant contractDate
     * @param headPosition Applicant headPosition
     * @param headName Applicant headName
     */
    void create(String organization, String address, String mailingAddress, String iban,
                String bank, String bankAddress, String bic, String unn, String okpo, String telephones,
                String email, String contractNumber, String contractDate, String headPosition, String headName);

    /**
     * Edits passed Applicant instance and persists it into database
     * @param applicant edited instance of Applicant
     * @param organization Applicant organization
     * @param address Applicant address
     * @param mailingAddress Applicant mailingAddress
     * @param iban Applicant iban
     * @param bank Applicant bank
     * @param bankAddress Applicant bankAddress
     * @param bic Applicant bic
     * @param unn Applicant unn
     * @param okpo Applicant okpo
     * @param telephones Applicant telephones
     * @param email Applicant email
     * @param contractNumber Applicant contractNumber
     * @param contractDate Applicant contractDate
     * @param headPosition Applicant headPosition
     * @param headName Applicant headName
     */
    void edit(Applicant applicant, String organization, String address, String mailingAddress, String iban,
              String bank, String bankAddress, String bic, String unn, String okpo, String telephones,
              String email, String contractNumber, String contractDate, String headPosition, String headName);

    /**
     * Deletes instance of Applicant from database
     * @param id Applicant id
     */
    void delete(Integer id);
}
