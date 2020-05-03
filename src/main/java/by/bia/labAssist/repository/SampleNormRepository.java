package by.bia.labAssist.repository;

import by.bia.labAssist.model.SampleNorm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleNormRepository extends JpaRepository<SampleNorm, Integer> {
    void deleteAllBySampleId(Integer id);
}
