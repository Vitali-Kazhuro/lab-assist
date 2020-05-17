package by.bia.labAssist.service;

import by.bia.labAssist.model.TestMethod;

import java.util.List;

/**
 * Provides service logic for{@link TestMethod} entity
 */
public interface TestMethodService {
    /**
     * Returns List with all TestMethod instances
     * @return {@link List<TestMethod>} object
     */
    List<TestMethod> findAll();

    /**
     * Returns TestMethod by id
     * @param id TestMethod id
     * @return {@link TestMethod} object
     */
    TestMethod findById(Integer id);

    /**
     * Creates new TestMethod instance and persists it into database
     * @param title TestMethod title
     */
    void create(String title);

    /**
     * Edits passed TestMethod instance and persists it into database
     * @param testMethodEdit edited instance of TestMethod
     * @param title TestMethod title
     */
    void edit(TestMethod testMethodEdit, String title);

    /**
     * Deletes instance of TestMethod from database
     * @param id TestMethod id
     */
    void delete(Integer id);
}
