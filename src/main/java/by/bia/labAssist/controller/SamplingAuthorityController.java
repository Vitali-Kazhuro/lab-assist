package by.bia.labAssist.controller;

import by.bia.labAssist.model.Applicant;
import by.bia.labAssist.model.SamplingAuthority;
import by.bia.labAssist.service.ApplicantService;
import by.bia.labAssist.service.SamplingAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class SamplingAuthorityController {
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private SamplingAuthorityService samplingAuthorityService;

    @GetMapping("sampling_authorities")
    public String samplingAuthorityList(Model model, HttpSession session) {
        Applicant applicant = (Applicant) session.getAttribute("applicant");
        if(applicant == null){
            return "redirect:/register_sample";
        }
        List<SamplingAuthority> samplingAuthorities = samplingAuthorityService.findByApplicantId(applicant.getId());
        model.addAttribute("samplingAuthorities", samplingAuthorities);

        if(session.getAttribute("samplingAuthority")!= null){
            model.addAttribute("samplingAuthoritySelected", session.getAttribute("samplingAuthority"));
        }

        return "chooseSamplingAuthority";
    }

    @PostMapping("addSamplingAuthority")
    public String addSamplingAuthority(@RequestParam String title, HttpSession session){
        Applicant applicant = (Applicant)session.getAttribute("applicant");
        Applicant applicantInContext = applicantService.findById(applicant.getId());

        samplingAuthorityService.save(title, applicantInContext);

        return "redirect:/sampling_authorities";
    }

    @PostMapping("editSamplingAuthorityPage")
    public String editSamplingAuthorityPage(@RequestParam Integer samplingAuthoritySelect, Model model,
                                            HttpSession session){
        SamplingAuthority samplingAuthority = samplingAuthorityService.findById(samplingAuthoritySelect);
        model.addAttribute("samplingAuthority", samplingAuthority);
        session.setAttribute("samplingAuthorityEdit", samplingAuthority);

        return "editSamplingAuthority";
    }

    @PostMapping("editSamplingAuthority")
    public String editSamplingAuthority(@RequestParam String title, HttpSession session){
        SamplingAuthority samplingAuthorityEdit = (SamplingAuthority) session.getAttribute("samplingAuthorityEdit");
        samplingAuthorityService.edit(samplingAuthorityEdit, title);

        return "redirect:/sampling_authorities";
    }

    @PostMapping("deleteSamplingAuthority")
    public String deleteSamplingAuthority(@RequestParam Integer samplingAuthorityId){
        samplingAuthorityService.delete(samplingAuthorityId);

        return "redirect:/sampling_authorities";
    }

    @PostMapping("chooseSamplingAuthority")
    public String chooseSamplingAuthority(@RequestParam Integer samplingAuthoritySelect, HttpSession session){
        SamplingAuthority samplingAuthority = samplingAuthorityService.findById(samplingAuthoritySelect);
        session.setAttribute("samplingAuthority", samplingAuthority);

        return "redirect:/objects_of_study";
    }
}
