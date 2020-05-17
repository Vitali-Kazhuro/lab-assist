package by.bia.labAssist.service.impl;

import by.bia.labAssist.model.Norm;
import by.bia.labAssist.model.Sample;
import by.bia.labAssist.model.SampleNorm;
import by.bia.labAssist.repository.SampleNormRepository;
import by.bia.labAssist.service.SampleNormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class SampleNormServiceImpl implements SampleNormService {
    @Autowired
    private SampleNormRepository sampleNormRepository;

    @Override
    public Sample createSampleNormForSample(Sample sample, List<Norm> checkedNorms) {
        for (Norm norm: checkedNorms){
            SampleNorm sampleNorm = new SampleNorm(sample, norm);
            sample.getSampleNorms().add(sampleNorm);
            sampleNormRepository.save(sampleNorm);
        }

        return sample;
    }

    @Override
    public Sample editSampleNormForSample(Sample sample, List<Norm> checkedNormsEdit) {
        List<SampleNorm> sampleNormsToDelete = new ArrayList<>();
        for(SampleNorm sampleNorm : sample.getSampleNorms()){
            if(!checkedNormsEdit.stream()
                                .map(Norm::getId)
                                .collect(Collectors.toList())
                                .contains(sampleNorm.getNorm().getId())){
                sampleNormsToDelete.add(sampleNorm);
            }
        }
        sampleNormRepository.deleteAll(sampleNormsToDelete);
        sample.getSampleNorms().removeAll(sampleNormsToDelete);

        for(Norm norm: checkedNormsEdit){
            if(!sample.getSampleNorms().stream()
                                        .map(SampleNorm::getNorm)
                                        .map(Norm::getId)
                                        .collect(Collectors.toList())
                                        .contains(norm.getId())){
                SampleNorm sampleNorm = new SampleNorm(sample, norm);
                sample.getSampleNorms().add(sampleNorm);
                sampleNormRepository.save(sampleNorm);
            }
        }
        return sample;
    }

    @Override
    public void processResults(Sample sample, Map<String, String> form) {
        for (SampleNorm sampleNorm: sample.getSampleNorms()){
            String sampleIdPlusSampleNormId = sample.getId().toString() + sampleNorm.getId().toString();
            if (form.containsKey(sampleIdPlusSampleNormId + "-limitCheck")) {
                sampleNorm.setDetectionLimit(form.get(sampleIdPlusSampleNormId + "-limit"));
                sampleNorm.setResult1(null);
                sampleNorm.setResult2(null);
                sampleNormRepository.save(sampleNorm);
                continue;
            }
            Double result1 = Double.parseDouble(form.get(sampleIdPlusSampleNormId + "-result1"));
            Double result2 = Double.parseDouble(form.get(sampleIdPlusSampleNormId + "-result2"));
            sampleNorm.setDetectionLimit("");
            sampleNorm.setResult1(result1);
            sampleNorm.setResult2(result2);
            sampleNormRepository.save(sampleNorm);
        }
    }
}
