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
                //Double value = Double.parseDouble(form.get(element.getTitle() + "-norm"));
                String value = form.get(element.getTitle() + "-norm");

                String units = form.get(element.getTitle() + "-units");
                Norm norm = new Norm(value, units, element, regulatoryDocument);
                //Norm norm = new Norm(value, element, regulatoryDocument);

                normRepository.save(norm);
            }
        }
    }

    @Override
    public RegulatoryDocument edit(Map<String, String> form, List<Element> allElements, RegulatoryDocument regulatoryDocumentEdit) {

        //TODO новое решение {
        List<Norm> normsToDelete = new ArrayList<>();
        for (Norm norm: regulatoryDocumentEdit.getNorms()) {
            if (form.containsKey(norm.getElement().getTitle())){
                //norm.setValue(Double.parseDouble(form.get(norm.getElement().getTitle() + "-norm")));
                norm.setValue(form.get(norm.getElement().getTitle() + "-norm"));

                norm.setUnits(form.get(norm.getElement().getTitle() + "-units"));

                normRepository.save(norm);
            }
            else{
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
                //Double value = Double.parseDouble(form.get(element.getTitle() + "-norm"));
                String value = form.get(element.getTitle() + "-norm");

                String units = form.get(element.getTitle() + "-units");
                Norm norm = new Norm(value, units, element, regulatoryDocumentEdit);

                //Norm norm = new Norm(value, element, regulatoryDocumentEdit);

                regulatoryDocumentEdit.getNorms().add(norm);

                normRepository.save(norm);
            }
        }
        //TODO новое решение }

        /*regulatoryDocumentEdit.setNorms(new ArrayList<>());

        normRepository.deleteAllByRegulatoryDocumentId(regulatoryDocumentEdit.getId()); //TODO такое себе решение

        for(Element element: allElements){
            if(form.containsKey(element.getTitle())){
                Double value = Double.parseDouble(form.get(element.getTitle() + "-norm"));
                Norm norm = new Norm(value, element, regulatoryDocumentEdit);

                normRepository.save(norm);
            }
        }*/
        return regulatoryDocumentEdit;
    }

/*    @Override
    public List<Norm> findAllBySamples(List<Sample> samples) {
        return normRepository.findAllBySamples(samples);
    }*/
}
