package by.bia.labAssist.controller;

import by.bia.labAssist.model.*;
import by.bia.labAssist.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class ObjectOfStudyController {
    @Autowired
    private SamplingAuthorityService samplingAuthorityService;
    @Autowired
    private ObjectOfStudyService objectOfStudyService;
    @Autowired
    private RegulatoryDocumentService regulatoryDocumentService;
    @Autowired
    private NormService normService;
    @Autowired
    private ElementService elementService;

    @GetMapping("objects_of_study")
    public String objectOfStudyList(Model model, HttpSession session){
        SamplingAuthority samplingAuthority = (SamplingAuthority) session.getAttribute("samplingAuthority");
        if(samplingAuthority == null){
            return "redirect:/sampling_authorities";
        }

        List<ObjectOfStudy> objectsOfStudy = objectOfStudyService.findBySamplingAuthorityId(samplingAuthority.getId());
        List<RegulatoryDocument> regulatoryDocuments = regulatoryDocumentService.findAll();
        List<Element> elements = elementService.findAll();

        model.addAttribute("objectsOfStudy", objectsOfStudy);
        model.addAttribute("regulatoryDocuments", regulatoryDocuments);
        model.addAttribute("allElements", elements);

        if(session.getAttribute("objectOfStudy")!= null){
            model.addAttribute("objectOfStudySelected", session.getAttribute("objectOfStudy"));
        }

        return "chooseObjectOfStudy";
    }

    @PostMapping("addObjectOfStudy")
    public String addObjectOfStudy(@RequestParam Integer regulatoryDocumentSelect,
                                   @RequestParam String title,
                                   @RequestParam String producer,
                                   HttpSession session){
        RegulatoryDocument regulatoryDocument = regulatoryDocumentService.findById(regulatoryDocumentSelect);

        SamplingAuthority samplingAuthority = (SamplingAuthority) session.getAttribute("samplingAuthority");
        SamplingAuthority samplingAuthorityInContext = samplingAuthorityService.findById(samplingAuthority.getId());

        objectOfStudyService.save(title, producer, samplingAuthorityInContext, regulatoryDocument);

        return "redirect:/objects_of_study";
    }

    @PostMapping("editObjectOfStudyPage")
    public String editObjectOfStudyPage(@RequestParam Integer objectOfStudySelect,
                                        Model model, HttpSession session){
        ObjectOfStudy objectOfStudy = objectOfStudyService.findById(objectOfStudySelect);
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        List<SamplingAuthority> samplingAuthorityList = samplingAuthorityService.findByApplicantId(applicant.getId());
        List<RegulatoryDocument> regulatoryDocumentList = regulatoryDocumentService.findAll();

        model.addAttribute("samplingAuthorityList", samplingAuthorityList);
        model.addAttribute("regulatoryDocumentList", regulatoryDocumentList);
        model.addAttribute("objectOfStudy", objectOfStudy);
        session.setAttribute("objectOfStudyEdit", objectOfStudy);

        return "editObjectOfStudy";
    }

    @PostMapping("editObjectOfStudy")
    public String editObjectOfStudy(@RequestParam String title,
                                    @RequestParam String producer,
                                    @RequestParam Integer samplingAuthoritySelect,
                                    @RequestParam Integer regulatoryDocumentSelect,
                                    HttpSession session){
        ObjectOfStudy objectOfStudyEdit = (ObjectOfStudy) session.getAttribute("objectOfStudyEdit");
        SamplingAuthority samplingAuthority = samplingAuthorityService.findById(samplingAuthoritySelect);
        RegulatoryDocument regulatoryDocument = regulatoryDocumentService.findById(regulatoryDocumentSelect);

        objectOfStudyService.edit(objectOfStudyEdit, title, producer, samplingAuthority, regulatoryDocument);

        return "redirect:/objects_of_study";
    }

    @PostMapping("deleteObjectOfStudy")
    public String deleteObjectOfStudy(@RequestParam Integer objectOfStudyId){
        objectOfStudyService.delete(objectOfStudyId);

        return "redirect:/objects_of_study";
    }

    @PostMapping("newRegulatoryDocument")
    public String newRegulatoryDocument(HttpSession session){
        session.removeAttribute("regulatoryDocumentEdit");

        return "redirect:/add_regulatory_document";
    }

    @GetMapping("add_regulatory_document")
    public String addRegulatoryDocumentPage(Model model){
        List<Element> elements = elementService.findAll();

        model.addAttribute("allElements", elements);

        return "addAndEditRegulatoryDocument";
    }

    @PostMapping("addRegulatoryDocument")
    public String addRegulatoryDocument(@RequestParam String title,
                                        @RequestParam Map<String, String> form){
        RegulatoryDocument regulatoryDocument = regulatoryDocumentService.save(title);
        List<Element> allElements = elementService.findAll();
        normService.saveNormForNewRegulatoryDocument(form, allElements, regulatoryDocument);

        return "redirect:/objects_of_study";
    }

    @PostMapping("editRegulatoryDocumentPage")
    public String editRegulatoryDocumentPage(@RequestParam Integer regulatoryDocumentSelect,
                                             Model model, HttpSession session){
        RegulatoryDocument regulatoryDocument = regulatoryDocumentService.findById(regulatoryDocumentSelect);
        List<Element> allElements = elementService.findAll();

        model.addAttribute("allElements", allElements);
        session.setAttribute("regulatoryDocumentEdit", regulatoryDocument);

        return "addAndEditRegulatoryDocument";
    }

    @GetMapping("edit_regulatory_document")
    public String chooseRegulatoryDocumentToEdit(Model model){
        model.addAttribute("regulatoryDocuments", regulatoryDocumentService.findAll());

        return "editRegulatoryDocument";
    }

    @PostMapping("editRegulatoryDocument")
    public String editRegulatoryDocument(@RequestParam String title,
                                         @RequestParam Map<String, String> form,
                                         HttpSession session){
        RegulatoryDocument regulatoryDocumentEdit = (RegulatoryDocument) session.getAttribute("regulatoryDocumentEdit");
        List<Element> allElements = elementService.findAll();
        try {
            RegulatoryDocument regulatoryDocumentNormsEdit = normService.edit(form, allElements, regulatoryDocumentEdit);
            regulatoryDocumentService.edit(regulatoryDocumentNormsEdit, title);
        }catch (Exception ex){
            ex.printStackTrace();
            return "error/regulatoryDocumentEditError";
        }

        return "redirect:/objects_of_study";
    }

    @PostMapping("deleteRegulatoryDocument")
    public String deleteRegulatoryDocument(@RequestParam Integer regulatoryDocumentId){
        regulatoryDocumentService.delete(regulatoryDocumentId);

        return "redirect:/objects_of_study";
    }

    @PostMapping("chooseObjectOfStudy")
    public String chooseObjectOfStudy(@RequestParam Integer objectOfStudySelect, HttpSession session){
        ObjectOfStudy objectOfStudy = objectOfStudyService.findById(objectOfStudySelect);
        RegulatoryDocument regulatoryDocument = regulatoryDocumentService.findById(objectOfStudy.getRegulatoryDocument().getId());

        session.setAttribute("objectOfStudy", objectOfStudy);
        session.setAttribute("regulatoryDocument", regulatoryDocument);

        return "redirect:/fill_test_report";
    }
}
