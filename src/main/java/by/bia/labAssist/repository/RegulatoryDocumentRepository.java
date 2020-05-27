package by.bia.labAssist.repository;

import by.bia.labAssist.model.RegulatoryDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegulatoryDocumentRepository extends JpaRepository<RegulatoryDocument, Integer> {

    List<RegulatoryDocument> findAllByTitleContainsIgnoreCase(String search);
}

