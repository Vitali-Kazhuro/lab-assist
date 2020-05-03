package by.bia.labAssist.controller;

import by.bia.labAssist.model.*;
import by.bia.labAssist.service.NormService;
import by.bia.labAssist.service.ObjectOfStudyService;
import by.bia.labAssist.service.SampleNormService;
import by.bia.labAssist.service.SampleService;
import by.bia.labAssist.util.Attributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class SampleController {
    @Autowired
    private SampleService sampleService;
    @Autowired
    private ObjectOfStudyService objectOfStudyService;
    @Autowired
    private NormService normService;
    @Autowired
    private SampleNormService sampleNormService;

    @GetMapping("choose_element_and_fill_sample")
    public String elementsAndSample(Model model, HttpSession session){
        RegulatoryDocument regulatoryDocument = (RegulatoryDocument) session.getAttribute("regulatoryDocument");
        //проверка выблрали ли мы объект исследования на предыдущем этапе
        if(regulatoryDocument == null){
            return "redirect:/objects_of_study";
        }

        List<Norm> norms = normService.findByRegulatoryDocumentId(regulatoryDocument.getId());

        session.setAttribute("allNorms", norms);

        ////model.addAttribute("allNorms", norms);

        //если мы уже создали sample, то отобразим параметры уже созданного
        if(session.getAttribute("sample")!= null){// && session.getAttribute("normList")!= null){
            //List<Norm> checkedNorms = (List<Norm>) session.getAttribute("normList");
            Sample sample = (Sample) session.getAttribute("sample");
            ///model.addAttribute("normList", sample.getNorms());//checkedNorms);
            model.addAttribute("normList", sample.getSampleNorms().stream().map(SampleNorm::getNorm).collect(Collectors.toList()));

            ////model.addAttribute("sample", sample);
        }

        return "chooseElementAndFillSample";
    }

    /*@PostMapping("editElementAndSample")
    public String editElementsAndSample(@RequestParam Map<String, String> form,
                                        @RequestParam String cipher,
                                        @RequestParam String series,
                                        @RequestParam String samplingReport,
                                        @RequestParam String quantity,
                                        HttpSession session){
        List<Norm> allNorms = (List<Norm>) session.getAttribute("allNorms");
        List<Norm> checkedNormsEdit = normService.getCheckedNorms(form, allNorms);

        //session.setAttribute("normList", checkedNormsEdit);

        Sample sample = (Sample) session.getAttribute("sample");

        Sample sampleEdit = sampleService.edit(sample, cipher, series, samplingReport, quantity, checkedNormsEdit);

        session.setAttribute("sample", sampleEdit);

        return "redirect:/check_and_print";
    }*/

    @PostMapping("editElementAndSample")
    public String editElementsAndSample(@RequestParam Map<String, String> form,
                                        @RequestParam String cipher,
                                        @RequestParam String series,
                                        @RequestParam String samplingReport,
                                        @RequestParam String quantity,
                                        HttpSession session){
        List<Norm> allNorms = (List<Norm>) session.getAttribute("allNorms");
        List<Norm> checkedNormsEdit = normService.getCheckedNorms(form, allNorms);

        Sample sample = (Sample) session.getAttribute("sample");

        /*Sample sampleEdit = sampleService.edit(sample, cipher, series, samplingReport, quantity);

        Sample sampleWithSampleNormsEdit = sampleNormService.editSampleNormForSample(sampleEdit, checkedNormsEdit);*/

        Sample sampleWithSampleNormsEdit = sampleNormService.editSampleNormForSample(sample, checkedNormsEdit);

        //Sample sampleEdit =
        sampleService.edit(sampleWithSampleNormsEdit, cipher, series, samplingReport, quantity);

        Attributes.clearSession(session);

        //session.setAttribute("sample", sampleEdit);//sampleWithSampleNormsEdit);

        return "redirect:/all_samples_in"; //"redirect:/check_and_print";
    }

    /*@PostMapping("chooseElementAndFillSample")
    public String chooseElementsAndFillSample(@RequestParam Map<String, String> form,
                                              @RequestParam String cipher,
                                              @RequestParam String series,
                                              @RequestParam String samplingReport,
                                              @RequestParam String quantity,
                                              HttpSession session){
        TestReport testReport = (TestReport) session.getAttribute("testReport");

        //проверяем был ли заполнен testReport
        if(testReport == null){
            return "redirect:/fill_test_report";
        }

        List<Norm> allNorms = (List<Norm>) session.getAttribute("allNorms");
        List<Norm> checkedNorms = normService.getCheckedNorms(form, allNorms);

        //session.setAttribute("normList", checkedNorms);

        ObjectOfStudy objectOfStudy = (ObjectOfStudy) session.getAttribute("objectOfStudy");
        ObjectOfStudy objectOfStudyInContext = objectOfStudyService.findById(objectOfStudy.getId());

        Sample sample = sampleService.save(cipher, series, samplingReport, quantity, objectOfStudyInContext,
                testReport, checkedNorms);

        session.setAttribute("sample", sample);

        return "redirect:/check_and_print";
    }*/

    @PostMapping("chooseElementAndFillSample")
    public String chooseElementsAndFillSample(@RequestParam Map<String, String> form,
                                              @RequestParam String cipher,
                                              @RequestParam String series,
                                              @RequestParam String samplingReport,
                                              @RequestParam String quantity,
                                              HttpSession session){
        TestReport testReport = (TestReport) session.getAttribute("testReport");

        //проверяем был ли заполнен testReport
        if(testReport == null){
            return "redirect:/fill_test_report";
        }

        List<Norm> allNorms = (List<Norm>) session.getAttribute("allNorms");
        List<Norm> checkedNorms = normService.getCheckedNorms(form, allNorms);

        ObjectOfStudy objectOfStudy = (ObjectOfStudy) session.getAttribute("objectOfStudy");
        ObjectOfStudy objectOfStudyInContext = objectOfStudyService.findById(objectOfStudy.getId());

        Sample sample = sampleService.save(cipher, series, samplingReport, quantity, objectOfStudyInContext,
                testReport);

        //Sample sampleWithSampleNorms =
        sampleNormService.createSampleNormForSample(sample, checkedNorms);

        Attributes.clearSession(session);

        //session.setAttribute("sample", sampleWithSampleNorms);

        return "redirect:/all_samples_in";//"redirect:/check_and_print";
    }

    @PostMapping("chooseSample")
    public String chooseSample(@RequestParam Integer sampleSelect, HttpSession session){
        Sample sample = sampleService.findById(sampleSelect);
        session.setAttribute("testReport", sample.getTestReport());
        session.setAttribute("applicant", sample.getObjectOfStudy().getSamplingAuthority().getApplicant());
        session.setAttribute("samplingAuthority", sample.getObjectOfStudy().getSamplingAuthority());
        session.setAttribute("objectOfStudy", sample.getObjectOfStudy());
        session.setAttribute("regulatoryDocument", sample.getObjectOfStudy().getRegulatoryDocument());
        session.setAttribute("normList", sample.getSampleNorms().stream().map(SampleNorm::getNorm).collect(Collectors.toList()));
        session.setAttribute("sample", sample);
        //для подсвечивания в списке для выбора в журнале поступлений
        session.setAttribute("editSample", sample);

        return "redirect:/choose_element_and_fill_sample";
    }

    @PostMapping("deleteSample")
    public String deleteSample(@RequestParam Integer sampleId, HttpSession session){
        sampleService.delete(sampleId);
        session.removeAttribute("sample");
        session.removeAttribute("editSample");

        return "redirect:/all_samples_in";
    }

/*    @GetMapping("add_results")
    public String addResultsPage(Model model, HttpSession session){
        Sample sample = (Sample) session.getAttribute("sample");

        //проверка выблрали ли мы образец на предыдущих этапах
        if(sample == null){
            return "redirect:/start_test_report";
        }

        model.addAttribute("allSampleNorms", sample.getSampleNorms());

        return "addResults";
    }

    @PostMapping("addResults")
    public String addResults(@RequestParam Map<String, String> form, HttpSession session){
        Sample sample = (Sample) session.getAttribute("sample");

        sampleNormService.processResults(sample, form);

        return "redirect:/add_results";
    }*/

    @GetMapping("register_sample")
    public String registerSample(){
        return "registerSample";
    }

    @PostMapping("newSample")
    public String newReport(HttpSession session){
        /*for (Attributes attribute: Attributes.values()){
            if(session.getAttribute(attribute.title)!= null){
                session.removeAttribute(attribute.title);
            }
        }
        session.removeAttribute("editSample");*/
        Attributes.clearSession(session);
        session.setAttribute("new", true);

        //return "redirect:/fill_test_report";
        return "redirect:/applicants";
    }

}
