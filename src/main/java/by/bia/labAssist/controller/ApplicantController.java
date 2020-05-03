package by.bia.labAssist.controller;

import by.bia.labAssist.model.Applicant;
import by.bia.labAssist.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ApplicantController {
    @Autowired
    private ApplicantService applicantService;

    @GetMapping("applicants")
    public String applicantList(Model model, HttpSession session) {
        if (session.getAttribute("applicant") == null && session.getAttribute("new") == null){
            return "redirect:/register_sample";
        }

        List<Applicant> applicants = applicantService.findAll();
        model.addAttribute("applicants", applicants);

        if(session.getAttribute("applicant")!= null){
            /*Applicant applicant = (Applicant) session.getAttribute("applicant");
            model.addAttribute("applicantSelected", applicant);*/
            model.addAttribute("applicantSelected", session.getAttribute("applicant"));
        }

        return "chooseApplicant";
    }

    @PostMapping("addApplicant")
    public String addApplicant(@RequestParam String organization,   @RequestParam String address,
                               @RequestParam String mailingAddress, @RequestParam String iban,
                               @RequestParam String bank,           @RequestParam String bankAddress,
                               @RequestParam String bic,            @RequestParam String unn,
                               @RequestParam String okpo,           @RequestParam String telephones,
                               @RequestParam String email,          @RequestParam String contractNumber,
                               @RequestParam String contractDate,   @RequestParam String headPosition,
                               @RequestParam String headName){
        applicantService.save(organization, address, mailingAddress, iban, bank, bankAddress,
                bic, unn, okpo, telephones, email, contractNumber, contractDate, headPosition, headName);

        return "redirect:/applicants";
    }

    @PostMapping("editApplicantPage")
    public String editApplicantPage(@RequestParam Integer applicantSelect, Model model, HttpSession session){
        Applicant applicant = applicantService.findById(applicantSelect);
        model.addAttribute("applicant", applicant);         //TODO а зачем?
        session.setAttribute("applicantEdit", applicant);

        return "editApplicant";
    }

    @PostMapping("editApplicant")
    public String editApplicant(@RequestParam String organization,   @RequestParam String address,
                                @RequestParam String mailingAddress, @RequestParam String iban,
                                @RequestParam String bank,           @RequestParam String bankAddress,
                                @RequestParam String bic,            @RequestParam String unn,
                                @RequestParam String okpo,           @RequestParam String telephones,
                                @RequestParam String email,          @RequestParam String contractNumber,
                                @RequestParam String contractDate,   @RequestParam String headPosition,
                                @RequestParam String headName,       HttpSession session){
        Applicant applicantEdit = (Applicant) session.getAttribute("applicantEdit");
        applicantService.edit(applicantEdit, organization, address, mailingAddress, iban, bank, bankAddress,
                bic, unn, okpo, telephones, email, contractNumber, contractDate, headPosition, headName);

        return "redirect:/applicants";
    }

    @PostMapping("deleteApplicant")
    public String deleteApplicant(@RequestParam Integer applicantId){
        applicantService.delete(applicantId);

        return "redirect:/applicants";
    }

    @PostMapping("chooseApplicant")
    public String chooseApplicant(@RequestParam Integer applicantSelect, HttpSession session){
        Applicant applicant = applicantService.findById(applicantSelect);
        session.setAttribute("applicant", applicant);

        return "redirect:/sampling_authorities";
    }
}
