package by.bia.labAssist.repository;

import by.bia.labAssist.model.SamplingAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SamplingAuthorityRepository extends JpaRepository<SamplingAuthority, Integer> {

    List<SamplingAuthority> findByApplicantId(Integer id);

    List<SamplingAuthority> findAllByApplicantIdAndTitleContainsIgnoreCase(Integer id, String search);
}

