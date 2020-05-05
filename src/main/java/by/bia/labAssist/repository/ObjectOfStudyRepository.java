package by.bia.labAssist.repository;

import by.bia.labAssist.model.ObjectOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjectOfStudyRepository extends JpaRepository<ObjectOfStudy, Integer> {

    List<ObjectOfStudy> findBySamplingAuthorityId(Integer id);

    List<ObjectOfStudy> findBySamplingAuthorityIdAndTitleContains(Integer id, String search);
}

