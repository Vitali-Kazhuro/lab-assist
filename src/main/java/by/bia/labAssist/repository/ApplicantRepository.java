package by.bia.labAssist.repository;

import by.bia.labAssist.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {

    List<Applicant> findAllByOrganizationContainsIgnoreCase(String search);
}

