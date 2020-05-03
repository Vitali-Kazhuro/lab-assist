package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.Norm;
import by.bia.labAssist.model.RegulatoryDocument;
import by.bia.labAssist.repository.RegulatoryDocumentRepository;
import by.bia.labAssist.service.RegulatoryDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegulatoryDocumentServiceImpl implements RegulatoryDocumentService {
    @Autowired
    private RegulatoryDocumentRepository regulatoryDocumentRepository;

    @Override
    public RegulatoryDocument findById(Integer id) {
        return regulatoryDocumentRepository.findById(id).get();
    }

    @Override
    public List<RegulatoryDocument> findAll() {
        return (List<RegulatoryDocument>)regulatoryDocumentRepository.findAll();
    }

    @Override
    public RegulatoryDocument save(String title) {
        RegulatoryDocument regulatoryDocument = new RegulatoryDocument(title);

        regulatoryDocumentRepository.save(regulatoryDocument);

        return regulatoryDocument;
    }

    @Override
    public void edit(RegulatoryDocument regulatoryDocumentEdit, String title) {
        regulatoryDocumentEdit.setTitle(title);

        //костыль, потому что иначе на моменте сохранения, если убирали галку с элемента на котором она была до этого,
        //вылетала ошибка что не может найти норму этого элемента по айди, и ничего не помогало
        //видимо какой-то баг с памятью под старый лист норм, ну или я хз
        List<Norm> newListBecauseBug = new ArrayList<>(regulatoryDocumentEdit.getNorms());
        regulatoryDocumentEdit.setNorms(newListBecauseBug);

        regulatoryDocumentRepository.save(regulatoryDocumentEdit);
    }

    @Override
    public void delete(Integer id) {
        regulatoryDocumentRepository.deleteById(id);
    }
}
