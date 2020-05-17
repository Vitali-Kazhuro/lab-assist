package by.bia.labAssist.service;

import by.bia.labAssist.model.Element;

import java.util.List;

public interface ElementService {

    List<Element> findAll();

    Element findById(Integer id);

    void create(String title, String symbol);

    void edit(Element elementEdit, String title, String symbol);

    void delete(Integer elementId);
}
