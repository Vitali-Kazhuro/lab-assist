package by.bia.labAssist.repository;

import by.bia.labAssist.model.Norm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NormRepository extends JpaRepository<Norm, Integer> {

    List<Norm> findByRegulatoryDocumentId(Integer id);

    void deleteAllByRegulatoryDocumentId(Integer id);
//для выбора старого протокола
   /* List<Norm> findAllBySamples(List<Sample> samples);*/

}
