package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.Norm;
import by.bia.labAssist.model.Element;
import by.bia.labAssist.model.RegulatoryDocument;
import by.bia.labAssist.repository.NormRepository;
import by.bia.labAssist.service.NormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class NormServiceImpl implements NormService {
    @Autowired
    private NormRepository normRepository;

    @Override
    public List<Norm> findByRegulatoryDocumentId(Integer id) {
        return normRepository.findByRegulatoryDocumentId(id);
    }

    @Override
    public List<Norm> getCheckedNorms(Map<String, String> form, List<Norm> allNorms) {
        List<Norm> checkedNorms = new ArrayList<>();

        for (Norm norm : allNorms) {
            if (form.containsKey(norm.getElement().getTitle())){
                checkedNorms.add(norm);
            }
        }

        return checkedNorms;
    }

    @Override
    public void saveNormForNewRegulatoryDocument(Map<String, String> form,
                                                 List<Element> allElements,
                                                 RegulatoryDocument regulatoryDocument) {
        for(Element element: allElements){
            if(form.containsKey(element.getTitle())){
                String value = form.get(element.getTitle() + "-norm");

                String units = form.get(element.getTitle() + "-units");
                Norm norm = new Norm(value, units, element, regulatoryDocument);

                normRepository.save(norm);
            }
        }
    }

    @Override
    public RegulatoryDocument edit(Map<String, String> form, List<Element> allElements, RegulatoryDocument regulatoryDocumentEdit) {
        List<Norm> normsToDelete = new ArrayList<>();
        for (Norm norm: regulatoryDocumentEdit.getNorms()) {
            if (form.containsKey(norm.getElement().getTitle())){
                norm.setValue(form.get(norm.getElement().getTitle() + "-norm"));

                norm.setUnits(form.get(norm.getElement().getTitle() + "-units"));

                normRepository.save(norm);
            } else{
                normsToDelete.add(norm);
            }
        }
        normRepository.deleteAll(normsToDelete);
        regulatoryDocumentEdit.getNorms().removeAll(normsToDelete);

        for(Element element: allElements){
            if(form.containsKey(element.getTitle()) && !regulatoryDocumentEdit
                                                        .getNorms()
                                                        .stream()
                                                        .map(Norm::getElement)
                                                        .map(Element::getTitle)
                                                        .collect(Collectors.toList())
                                                        .contains(element.getTitle())){
                String value = form.get(element.getTitle() + "-norm");

                String units = form.get(element.getTitle() + "-units");
                Norm norm = new Norm(value, units, element, regulatoryDocumentEdit);

                regulatoryDocumentEdit.getNorms().add(norm);

                normRepository.save(norm);
            }
        }
        
        return regulatoryDocumentEdit;
    }
}
