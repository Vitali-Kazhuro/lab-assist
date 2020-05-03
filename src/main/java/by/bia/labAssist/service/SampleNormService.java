package by.bia.labAssist.service;

import by.bia.labAssist.model.Norm;
import by.bia.labAssist.model.Sample;

import java.util.List;
import java.util.Map;

public interface SampleNormService {

    Sample createSampleNormForSample(Sample sample, List<Norm> checkedNorms);

    Sample editSampleNormForSample(Sample sample, List<Norm> checkedNormsEdit);

    void processResults(Sample sample, Map<String, String> form);

    //void addResults(SampleNorm sampleNorm, Float result1, Float result2);
   // void addResults(SampleNorm sampleNorm, Double result1, Double result2);

/*    Map<String,Float> calculateMeanAndError(SampleNorm sampleNorm);*/



}
