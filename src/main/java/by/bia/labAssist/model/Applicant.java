package by.bia.labAssist.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@Entity
public class Applicant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String organization;
    private String address;
    private String mailingAddress;
    private String iban;
    private String bank;
    private String bankAddress;
    private String bic;
    private String unp;
    private String okpo;
    private String telephones;
    private String email;
    private String contractNumber;
    private LocalDate contractDate;
    private String headPosition;
    private String headName;

    @Transient
    private String contractDateS;

    @OneToMany(mappedBy = "applicant", cascade = CascadeType.ALL)
    private List<SamplingAuthority> samplingAuthorities;

    @OneToMany(mappedBy = "applicant")
    private List<TestReport> testReports;

    public Applicant() {
    }

    public Applicant(String organization, String address, String mailingAddress, String iban, String bank,
                     String bankAddress, String bic, String unp, String okpo, String telephones, String email,
                     String contractNumber, LocalDate contractDate, String headPosition, String headName) {
        this.organization = organization.replaceAll("\\s+", " ").trim();
        this.address = address.replaceAll("\\s+", " ").trim();
        this.mailingAddress = mailingAddress.replaceAll("\\s+", " ").trim();
        this.iban = iban.replaceAll("\\s+", " ").trim();
        this.bank = bank.replaceAll("\\s+", " ").trim();
        this.bankAddress = bankAddress.replaceAll("\\s+", " ").trim();
        this.bic = bic.replaceAll("\\s+", " ").trim();
        this.unp = unp.replaceAll("\\s+", " ").trim();
        this.okpo = okpo.replaceAll("\\s+", " ").trim();
        this.telephones = telephones.replaceAll("\\s+", " ").trim();
        this.email = email.replaceAll("\\s+", " ").trim();
        this.contractNumber = contractNumber.replaceAll("\\s+", " ").trim();
        this.contractDate = contractDate;
        this.headPosition = headPosition.replaceAll("\\s+", " ").trim();
        this.headName = headName.replaceAll("\\s+", " ").trim();
        this.contractDateS = contractDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
    }

    public String getContractDateS() {
        if(contractDateS == null){
            this.contractDateS = this.contractDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
        }
        return contractDateS;
    }

    public void setContractDateS(String contractDateS) {
        this.contractDateS = this.contractDate.format(DateTimeFormatter.ofPattern("dd MMMM uuuu г."));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization.replaceAll("\\s+", " ").trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address.replaceAll("\\s+", " ").trim();
    }

    public List<SamplingAuthority> getSamplingAuthorities() {
        return samplingAuthorities;
    }

    public void setSamplingAuthorities(List<SamplingAuthority> samplingAuthorities) {
        this.samplingAuthorities = samplingAuthorities;
    }

    public List<TestReport> getTestReports() {
        return testReports;
    }

    public void setTestReports(List<TestReport> testReports) {
        this.testReports = testReports;
    }

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress.replaceAll("\\s+", " ").trim();
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban.replaceAll("\\s+", " ").trim();
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank.replaceAll("\\s+", " ").trim();
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress.replaceAll("\\s+", " ").trim();
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic.replaceAll("\\s+", " ").trim();
    }

    public String getUnp() {
        return unp;
    }

    public void setUnp(String unp) {
        this.unp = unp.replaceAll("\\s+", " ").trim();
    }

    public String getOkpo() {
        return okpo;
    }

    public void setOkpo(String okpo) {
        this.okpo = okpo.replaceAll("\\s+", " ").trim();
    }

    public String getTelephones() {
        return telephones;
    }

    public void setTelephones(String telephones) {
        this.telephones = telephones.replaceAll("\\s+", " ").trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.replaceAll("\\s+", " ").trim();
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber.replaceAll("\\s+", " ").trim();
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public String getHeadPosition() {
        return headPosition;
    }

    public void setHeadPosition(String headPosition) {
        this.headPosition = headPosition.replaceAll("\\s+", " ").trim();
    }

    public String getHeadName() {
        return headName;
    }

    public void setHeadName(String headName) {
        this.headName = headName.replaceAll("\\s+", " ").trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Applicant applicant = (Applicant) o;
        return organization.equals(applicant.organization);
    }

    @Override
    public int hashCode() {
        return Objects.hash(organization);
    }

    public String getOrganizationForName(){
        if (this.organization.contains("«") && this.organization.contains("»")) {
            return this.organization.substring(this.organization.indexOf("«") + 1, this.organization.indexOf("»"));
        }else if (this.organization.contains("\""))
            return this.organization.substring(this.organization.indexOf("\"") + 1, this.organization.lastIndexOf("\""));
        return this.getOrganization();
    }
}
