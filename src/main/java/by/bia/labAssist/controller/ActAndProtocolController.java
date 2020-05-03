package by.bia.labAssist.controller;

import by.bia.labAssist.model.Applicant;
import by.bia.labAssist.model.TestReport;
import by.bia.labAssist.service.ApplicantService;
import by.bia.labAssist.service.TestReportService;
import by.bia.labAssist.util.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Controller
public class ActAndProtocolController {
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private TestReportService testReportService;

    @GetMapping("act_and_protocol")
    public String actAndProtocol(Model model){
        List<Applicant> applicants = applicantService.findAll();
        model.addAttribute("applicants", applicants);

        return "actAndProtocol";
    }

    @PostMapping("chooseApplicantAndDate")
    public String chooseApplicantAndDate(@RequestParam Integer applicantSelect,
                                         @RequestParam String date,
                                         @RequestParam Integer price,
                                         HttpSession session){
        Applicant applicantToAct = applicantService.findById(applicantSelect);
        LocalDate formattedDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);

        List<TestReport> testReports = testReportService.findAllByDateAfterAndApplicantId(formattedDate, applicantSelect);

        Map<String, Object> passageMap = testReportService.createActPassages(testReports, price);
        passageMap.put("applicant", applicantToAct);

        session.setAttribute("passageMap", passageMap);
        session.setAttribute("actAndProtocolTestReports", testReports);

        return "redirect:/act_and_protocol_check";
    }

    @GetMapping("act_and_protocol_check")
    public String actAndProtocolCheck(HttpSession session, Model model) {
        Map<String, Object> passageMap = (Map<String, Object>)session.getAttribute("passageMap");
        List<TestReport> actAndProtocolTestReports = (List<TestReport>)session.getAttribute("actAndProtocolTestReports");

        List<TestReport> testReports = testReportService.findAll(actAndProtocolTestReports);
        model.addAttribute("allTestReports", testReports);

        model.addAllAttributes(passageMap);

        return "actAndProtocolCheck";
    }

    @PostMapping("printActAndProtocol")
    public String printActAndProtocol(@RequestParam Integer number, HttpSession session){
        Map<String, Object> passageMap = (Map<String,Object>)session.getAttribute("passageMap");
        passageMap.put("number", number);

        PrintUtil.print(passageMap, "templates/template_act.docx", "acts/act" + number);
        PrintUtil.print(passageMap, "templates/template_protocol.docx", "protocols/protocol" + number);

        return "redirect:/act_and_protocol";
    }

    @GetMapping(value = "/downloadAct",
            produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<Resource> downloadAct(@RequestParam Integer number, HttpSession session) {
        Map<String, Object> passageMap = (Map<String,Object>)session.getAttribute("passageMap");
        passageMap.put("number", number);

        String fileName = "act" + number + ".docx";

        final Resource file = new InputStreamResource(
                PrintUtil.downloadFile(passageMap, "templates/template_act.docx"));

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file);
    }

    @GetMapping(value = "/downloadProtocol",
            produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<Resource> downloadProtocol(@RequestParam Integer number, HttpSession session) {
        Map<String, Object> passageMap = (Map<String,Object>)session.getAttribute("passageMap");
        passageMap.put("number", number);

        String fileName = "protocol" + number + ".docx";

        final Resource file = new InputStreamResource(
                PrintUtil.downloadFile(passageMap, "templates/template_protocol.docx"));

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .body(file);
    }

}
