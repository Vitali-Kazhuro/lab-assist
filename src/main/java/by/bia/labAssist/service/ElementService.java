package by.bia.labAssist.service;

import by.bia.labAssist.model.Element;

import java.util.List;

/**
 * Provides service logic for{@link Element} entity
 */
public interface ElementService {
    /**
     * Returns List with all Element instances
     * @return {@link List<Element>} object
     */
    List<Element> findAll();

    /**
     * Returns Element instance by id
     * @param id Element id
     * @return {@link Element} object
     */
    Element findById(Integer id);

    /**
     * Creates new Element instance and persists it into database
     * @param title Element title
     * @param symbol Element symbol
     */
    void create(String title, String symbol);

    /**
     * Edits passed Element instance and persists it into database
     * @param elementEdit edited instance of Element
     * @param title Element title
     * @param symbol Element symbol
     */
    void edit(Element elementEdit, String title, String symbol);

    /**
     * Deletes instance of Element from database
     * @param id Element id
     */
    void delete(Integer id);
}
