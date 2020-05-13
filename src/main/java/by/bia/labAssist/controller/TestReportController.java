package by.bia.labAssist.controller;

import by.bia.labAssist.model.*;
import by.bia.labAssist.service.*;
import by.bia.labAssist.util.PrintUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
public class TestReportController {
    @Autowired
    private ApplicantService applicantService;
    @Autowired
    private TestMethodService testMethodService;
    @Autowired
    private TestReportService testReportService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private SampleNormService sampleNormService;
    @Autowired
    private WeatherService weatherService;

    @GetMapping("start_test_report")
    public String startTestReport(HttpSession session, Model model,
                                  @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        TestReport testReport = (TestReport) session.getAttribute("testReport");

        Page<TestReport> allTestReportsPage = testReportService.findAllPages(pageable);
        model.addAttribute("allTestReportsPage", allTestReportsPage);

        if(testReport != null){
            TestReport testReportInContext = testReportService.findById(testReport.getId());
            model.addAttribute("testReport", testReportInContext);
        }

        return "startTestReport";
    }

    @GetMapping("add_results")
    public String addResultsPage(Model model, HttpSession session){
        TestReport testReport = (TestReport) session.getAttribute("testReport");
        //проверка выблрали ли мы протокол на предыдущих этапах
        if(testReport == null){
            return "redirect:/start_test_report";
        }

        TestReport testReportInContext = testReportService.findById(testReport.getId());
        model.addAttribute("testReport", testReportInContext);

        return "addResults";
    }

    @PostMapping("addResults")
    public String addResults(@RequestParam Map<String, String> form, HttpSession session){
        TestReport testReport = (TestReport) session.getAttribute("testReport");
        //проверка выблрали ли мы протокол на предыдущих этапах
        if(testReport == null){
            return "redirect:/start_test_report";
        }

        TestReport testReportInContext = testReportService.findById(testReport.getId());
        for (Sample sample: testReportInContext.getSamples()){
            sampleNormService.processResults(sample, form);
        }

        return "redirect:/add_results";
    }

    @GetMapping("fill_test_report")
    public String testReport(Model model, HttpSession session){
        if(session.getAttribute("objectOfStudy") == null && session.getAttribute("testReport") == null){
            return "redirect:/objects_of_study";
        }

        List<TestMethod> testMethods = testMethodService.findAll();
        List<Employee> employees = employeeService.findAll();

        model.addAttribute("testMethods", testMethods);
        model.addAttribute("employees", employees);

        if(session.getAttribute("testReport")!= null){
            TestReport tR = (TestReport) session.getAttribute("testReport");
            TestReport testReport = testReportService.findById(tR.getId());
            model.addAttribute("testReport", testReport);
        }

        return "fillTestReport";
    }

    @PostMapping("editTestReport")
    public String editTestReport(@RequestParam Integer protocolNumber,
                                 @RequestParam String date,
                                 @RequestParam Integer testMethod1Select,
                                 @RequestParam Integer testMethod2Select,
                                 @RequestParam String startDate,
                                 @RequestParam String endDate,
                                 @RequestParam Integer testReportDoer1Select,
                                 @RequestParam Integer testReportDoer2Select,
                                 HttpSession session){
        TestMethod testMethod1 = testMethodService.findById(testMethod1Select);
        TestMethod testMethod2 = testMethodService.findById(testMethod2Select);

        TestReport tR = (TestReport) session.getAttribute("testReport");
        TestReport testReport = testReportService.findById(tR.getId());

        Employee employee1 = employeeService.findById(testReportDoer1Select);
        Employee employee2 = employeeService.findById(testReportDoer2Select);

        TestReport testReportEdit = testReportService.edit(testReport, protocolNumber, date, testMethod1, testMethod2,
                startDate, endDate, employee1, employee2);

        session.setAttribute("testReport", testReportEdit);

        return "redirect:/choose_element_and_fill_sample";
    }

    @PostMapping("changeTestReport")
    public String changeTestReport(@RequestParam Integer testReportSelect, HttpSession session){
        TestReport testReport = testReportService.findById(testReportSelect);
        session.setAttribute("testReport", testReport);

        return "redirect:/fill_test_report";
    }

    @PostMapping("selectTestReport")
    public String selectTestReport(@RequestParam Integer testReportSelect, HttpSession session){
        TestReport testReport = testReportService.findById(testReportSelect);
        session.setAttribute("testReport", testReport);

        return "redirect:/start_test_report";
    }

    @GetMapping("all_test_reports")
    public String allTestReports(Model model, HttpSession session,
                                 @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable){
        if (session.getAttribute("testReport") != null){
            TestReport editTestReport = (TestReport) session.getAttribute("testReport");
            model.addAttribute("editTestReport", editTestReport);
        }

        Page<TestReport> allTestReportsPage = testReportService.findAllPages(pageable);
        model.addAttribute("allTestReportsPage", allTestReportsPage);

        return "allTestReports";
    }

    @PostMapping("deleteTestReport")
    public String deleteTestReport(@RequestParam Integer testReportId, HttpSession session){
        testReportService.delete(testReportId);

        session.removeAttribute("testReport");
        session.removeAttribute("editTestReport");

        return "redirect:/start_test_report";
    }

    @PostMapping("fillTestReport")
    public String fillTestReport(@RequestParam Integer protocolNumber,
                                 @RequestParam String date,
                                 @RequestParam Integer testMethod1Select,
                                 @RequestParam Integer testMethod2Select,
                                 @RequestParam String startDate,
                                 @RequestParam String endDate,
                                 @RequestParam Integer testReportDoer1Select,
                                 @RequestParam Integer testReportDoer2Select,
                                 HttpSession session){
        if (session.getAttribute("objectOfStudy") == null){
            return "redirect:/objects_of_study";
        }

        TestMethod testMethod1 = testMethodService.findById(testMethod1Select);
        TestMethod testMethod2 = testMethodService.findById(testMethod2Select);
        Employee employee1 = employeeService.findById(testReportDoer1Select);
        Employee employee2 = employeeService.findById(testReportDoer2Select);

        Applicant applicant = (Applicant) session.getAttribute("applicant");
        Applicant applicantInContext = applicantService.findById(applicant.getId());

        TestReport testReport = testReportService.save(protocolNumber, date, testMethod1, testMethod2,
                startDate, endDate, employee1, employee2, applicantInContext);

        session.setAttribute("testReport", testReport);

        return "redirect:/choose_element_and_fill_sample";
    }

    @GetMapping("check_and_print")
    public String checkAndPrintPage(Model model, HttpSession session){
        TestReport tR = (TestReport)session.getAttribute("testReport");
        if(tR == null){
            return "redirect:/start_test_report";
        }
        TestReport testReport = testReportService.findById(tR.getId());

        try{
            String weatherPassage = weatherService.getWeatherPassage(testReport.getStartDate(), testReport.getEndDate());
            model.addAttribute("weather", weatherPassage);
        }catch (NoSuchElementException ex){
            return "errors/noMeteoDataError";
        }

        Map<String, Object> protocolPassages = testReportService.createProtocolPassages(testReport);
        model.addAllAttributes(protocolPassages);
        session.setAttribute("printMap", model.asMap());
        session.setAttribute("fileName", testReport.getTestReportFileName());

        return "checkAndPrint";
    }

    @PostMapping("checkAndPrint")
    public String checkAndPrint(HttpSession session){
        //TODO добавить возможность загрузки тэмплэйта на сервер?

        String fileName = (String) session.getAttribute("fileName");
        PrintUtil.print((Map)session.getAttribute("printMap"), "templates/template_prot_isp_Nsample.docx",
                "prot_isp/" + fileName);

        session.removeAttribute("testReport");
        return "redirect:/";
    }

    @GetMapping(value = "/downloadTestProtocol",
            produces = "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
    public ResponseEntity<Resource> downloadTestProtocol(HttpSession session) {
        String fileName = session.getAttribute("fileName") + ".docx";

        ContentDisposition contentDisposition = ContentDisposition.builder("attachment")//для кириллицы в названии файла
                .filename(fileName, StandardCharsets.UTF_8)
                .build();

        final Resource file = new InputStreamResource(
                PrintUtil.downloadFile((Map) session.getAttribute("printMap"),
                        "templates/template_prot_isp_Nsample.docx"));

        session.removeAttribute("testReport");
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString())
                .body(file);
    }
}
