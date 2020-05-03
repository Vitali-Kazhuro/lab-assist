package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.ObjectOfStudy;
import by.bia.labAssist.model.Sample;
import by.bia.labAssist.model.SampleNorm;
import by.bia.labAssist.model.TestReport;
import by.bia.labAssist.repository.SampleRepository;
import by.bia.labAssist.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SampleServiceImpl implements SampleService {
    @Autowired
    private SampleRepository sampleRepository;

    @Override
    public Sample save(String cipher, String series, String samplingReport, String quantity,
                       ObjectOfStudy objectOfStudy, TestReport testReport) {
        Sample sample = new Sample(cipher, series, samplingReport, quantity, objectOfStudy, testReport);

        sampleRepository.save(sample);

        return sample;
    }

    @Override
    @Transactional
    public Sample edit(Sample sampleEdit, String cipher, String series, String samplingReport, String quantity) {
        sampleEdit.setCipher(cipher);
        sampleEdit.setSeries(series);
        sampleEdit.setSamplingReport(samplingReport);
        sampleEdit.setQuantity(quantity);
        //костыль, потому что иначе на моменте сохранения, если убирали галку с элемента на котором она была до этого,
        //вылетала ошибка что не может найти сэмпл-норму этого элемента по айди, и ничего не помогало
        //видимо какой-то баг с памятью под старый лист сэмпл-норм, ну или я хз
        List<SampleNorm> newListBecauseBug = new ArrayList<>(sampleEdit.getSampleNorms());
        sampleEdit.setSampleNorms(newListBecauseBug);

        sampleRepository.save(sampleEdit);

        return sampleEdit;
    }

    @Override
    public void delete(Integer id) {
        sampleRepository.deleteById(id);
    }

    @Override
    public List<Sample> findAll() {
        return sampleRepository.findAll();
    }

    @Override
    public Page<Sample> findAllPages(Pageable pageable) {
        return sampleRepository.findAll(pageable);
    }

    @Override
    public Sample findById(Integer id) {
        return sampleRepository.findById(id).get();
    }
}
