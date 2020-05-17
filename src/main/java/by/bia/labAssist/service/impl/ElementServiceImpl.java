package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.Element;
import by.bia.labAssist.repository.ElementRepository;
import by.bia.labAssist.service.ElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElementServiceImpl implements ElementService {
    @Autowired
    private ElementRepository elementRepository;

    @Override
    public List<Element> findAll() {
        return elementRepository.findAll();
    }

    @Override
    public Element findById(Integer id) {
        return elementRepository.findById(id).get();
    }

    @Override
    public void create(String title, String symbol) {
        elementRepository.save(new Element(title, symbol));
    }

    @Override
    public void edit(Element elementEdit, String title, String symbol) {
        elementEdit.setTitle(title);
        elementEdit.setSymbol(symbol);

        elementRepository.save(elementEdit);
    }

    @Override
    public void delete(Integer elementId) {
        elementRepository.deleteById(elementId);
    }
}
