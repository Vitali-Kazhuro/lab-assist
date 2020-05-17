package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.TestMethod;
import by.bia.labAssist.repository.TestMethodRepository;
import by.bia.labAssist.service.TestMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestMethodServiceImpl implements TestMethodService {
    @Autowired
    private TestMethodRepository testMethodRepository;

    @Override
    public List<TestMethod> findAll() {
        return testMethodRepository.findAll();
    }

    @Override
    public TestMethod findById(Integer id) {
        return testMethodRepository.findById(id).get();
    }

    @Override
    public void create(String title) {
        testMethodRepository.save(new TestMethod(title));
    }

    @Override
    public void edit(TestMethod testMethodEdit, String title) {
        testMethodEdit.setTitle(title);
        testMethodRepository.save(testMethodEdit);
    }

    @Override
    public void delete(Integer testMethodId) {
        testMethodRepository.deleteById(testMethodId);
    }
}
