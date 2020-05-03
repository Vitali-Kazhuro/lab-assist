package by.bia.labAssist.service;

import by.bia.labAssist.model.TestMethod;

import java.util.List;

public interface TestMethodService {
    List<TestMethod> findAll();

    TestMethod findById(Integer id);

    void save(String title);

    void edit(TestMethod testMethodEdit, String title);

    void delete(Integer testMethodId);
}
