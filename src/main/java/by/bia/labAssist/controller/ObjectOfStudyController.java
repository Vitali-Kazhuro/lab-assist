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

    @GetMapping("objectsOfStudy")
    public String objectsOfStudyPage(@RequestParam(required = false, defaultValue = "") String searchObjectOfStudy,
                                    @RequestParam(required = false, defaultValue = "") String searchRegulatoryDocument,
                                    Model model, HttpSession session){
        SamplingAuthority samplingAuthority = (SamplingAuthority) session.getAttribute("samplingAuthority");
        if(samplingAuthority == null){
            return "redirect:/samplingAuthorities";
        }

        List<ObjectOfStudy> objectsOfStudy;
        if(searchObjectOfStudy != null && !searchObjectOfStudy.isEmpty()) {
            objectsOfStudy = objectOfStudyService.findBySamplingAuthorityIdAndTitleContains(samplingAuthority.getId(), searchObjectOfStudy);
        } else{
            objectsOfStudy = objectOfStudyService.findBySamplingAuthorityId(samplingAuthority.getId());
        }
        List<RegulatoryDocument> regulatoryDocuments;

        if(searchRegulatoryDocument != null && !searchRegulatoryDocument.isEmpty()) {
            regulatoryDocuments = regulatoryDocumentService.findAllByTitleContains(searchRegulatoryDocument);
            session.setAttribute("searchRegDocument", true); //to show that we're searching and this section needs to stay opened
        } else{
            regulatoryDocuments = regulatoryDocumentService.findAll();
            session.setAttribute("searchRegDocument", false);
        }

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

        objectOfStudyService.create(title, producer, samplingAuthorityInContext, regulatoryDocument);

        session.setAttribute("newRegDocument", false);//to show that we should not leave this section opened next time

        return "redirect:/objectsOfStudy";
    }

    @PostMapping("goToEditObjectOfStudyPage")
    public String goToEditObjectOfStudyPage(@RequestParam Integer objectOfStudySelect, HttpSession session){
        ObjectOfStudy objectOfStudy = objectOfStudyService.findById(objectOfStudySelect);
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        List<SamplingAuthority> samplingAuthorityList = samplingAuthorityService.findByApplicantId(applicant.getId());
        List<RegulatoryDocument> regulatoryDocumentList = regulatoryDocumentService.findAll();

        session.setAttribute("samplingAuthorityList", samplingAuthorityList);
        session.setAttribute("regulatoryDocumentList", regulatoryDocumentList);
        session.setAttribute("objectOfStudy", objectOfStudy);
        session.setAttribute("objectOfStudyEdit", objectOfStudy);

        return "redirect:/editObjectOfStudyPage";
    }

    @GetMapping("editObjectOfStudyPage")
    public String editObjectOfStudyPage(@RequestParam(required = false, defaultValue = "") String searchRegulatoryDocument,
                                        Model model){
        List<RegulatoryDocument> regulatoryDocumentList;
        if(searchRegulatoryDocument != null && !searchRegulatoryDocument.isEmpty()) {
            regulatoryDocumentList = regulatoryDocumentService.findAllByTitleContains(searchRegulatoryDocument);
        } else{
            regulatoryDocumentList = regulatoryDocumentService.findAll();
        }
        model.addAttribute("regulatoryDocumentList", regulatoryDocumentList);

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

        return "redirect:/objectsOfStudy";
    }

    @PostMapping("deleteObjectOfStudy")
    public String deleteObjectOfStudy(@RequestParam Integer objectOfStudyId){
        objectOfStudyService.delete(objectOfStudyId);

        return "redirect:/objectsOfStudy";
    }

    @PostMapping("newRegulatoryDocument")
    public String newRegulatoryDocument(HttpSession session){
        session.removeAttribute("regulatoryDocumentEdit");

        return "redirect:/addRegulatoryDocument";
    }

    @GetMapping("addRegulatoryDocument")
    public String addRegulatoryDocumentPage(Model model){
        List<Element> elements = elementService.findAll();

        model.addAttribute("allElements", elements);

        return "addAndEditRegulatoryDocument";
    }

    @PostMapping("addRegulatoryDocument")
    public String addRegulatoryDocument(@RequestParam String title,
                                        @RequestParam Map<String, String> form,
                                        HttpSession session){
        RegulatoryDocument regulatoryDocument = regulatoryDocumentService.create(title);
        List<Element> allElements = elementService.findAll();
        normService.saveNormForNewRegulatoryDocument(form, allElements, regulatoryDocument);

        session.setAttribute("newRegDocument", true);//to show that section needs to stay opened

        return "redirect:/objectsOfStudy";
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

    @GetMapping("editRegulatoryDocument")
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
            return "errors/regulatoryDocumentEditError";
        }

        return "redirect:/objectsOfStudy";
    }

    @PostMapping("deleteRegulatoryDocument")
    public String deleteRegulatoryDocument(@RequestParam Integer regulatoryDocumentId){
        regulatoryDocumentService.delete(regulatoryDocumentId);

        return "redirect:/objectsOfStudy";
    }

    @PostMapping("chooseObjectOfStudy")
    public String chooseObjectOfStudy(@RequestParam Integer objectOfStudySelect, HttpSession session){
        ObjectOfStudy objectOfStudy = objectOfStudyService.findById(objectOfStudySelect);
        RegulatoryDocument regulatoryDocument = regulatoryDocumentService.findById(objectOfStudy.getRegulatoryDocument().getId());

        session.setAttribute("objectOfStudy", objectOfStudy);
        session.setAttribute("regulatoryDocument", regulatoryDocument);
        session.setAttribute("newRegDocument", false);//to show that we should not leave this section opened next time

        return "redirect:/fillTestReport";
    }
}
