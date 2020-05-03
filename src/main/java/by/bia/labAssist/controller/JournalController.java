package by.bia.labAssist.controller;

import by.bia.labAssist.model.Sample;
import by.bia.labAssist.model.TestReport;
import by.bia.labAssist.model.Weather;
import by.bia.labAssist.service.SampleService;
import by.bia.labAssist.service.TestReportService;
import by.bia.labAssist.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class JournalController {
    @Autowired
    private SampleService sampleService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private TestReportService testReportService;

    @GetMapping("all_samples_in")
    public String allSamplesIn(Model model, HttpSession session,
                               @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 16) Pageable pageable){
        if (session.getAttribute("editSample") != null){
            model.addAttribute("editSample", session.getAttribute("editSample"));
        }

        Page<Sample> allSamplesPage = sampleService.findAllPages(pageable);
        model.addAttribute("allSamplesPage", allSamplesPage);

        return "allSamplesIn";
    }

    @GetMapping("all_samples_out")
    public String allSamplesOut(Model model,
                                @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 16) Pageable pageable){
        Page<TestReport> allTestReportsPage = testReportService.findAllPages(pageable);
        model.addAttribute("allTestReportsPage", allTestReportsPage);

        return "allSamplesOut";
    }

    @GetMapping("weather")
    public String weather(Model model, HttpSession session,
                          @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC, size = 19) Pageable pageable){
        if (session.getAttribute("editWeather") != null){
            model.addAttribute("editWeather", session.getAttribute("editWeather"));
        }

        Page<Weather> weatherPage = weatherService.findAllPages(pageable);
        model.addAttribute("weatherPage", weatherPage);

        return "weather";
    }

    @PostMapping("addWeather")
    public String addWeather(@RequestParam String date,
                             @RequestParam Float k53_10_temperature,
                             @RequestParam Float k53_16_temperature,
                             @RequestParam Float k42_10_temperature,
                             @RequestParam Float k42_16_temperature,
                             @RequestParam Float k53_10_humidity,
                             @RequestParam Float k53_16_humidity,
                             @RequestParam Float k42_10_humidity,
                             @RequestParam Float k42_16_humidity,
                             @RequestParam Integer k53_10_pressure,
                             @RequestParam Integer k53_16_pressure,
                             @RequestParam Integer k42_10_pressure,
                             @RequestParam Integer k42_16_pressure){
        weatherService.save(date, k53_10_temperature, k53_16_temperature, k42_10_temperature,
                k42_16_temperature, k53_10_humidity, k53_16_humidity, k42_10_humidity, k42_16_humidity,
                k53_10_pressure, k53_16_pressure, k42_10_pressure, k42_16_pressure);

        return "redirect:/weather";
    }

    @PostMapping("chooseWeather")
    public String chooseWeather(@RequestParam Integer weatherSelect, HttpSession session){
        Weather editWeather = weatherService.findById(weatherSelect);
        session.setAttribute("editWeather", editWeather);

        return "redirect:/weather";
    }

    @PostMapping("editWeather")
    public String editWeather(@RequestParam String date,
                             @RequestParam Float k53_10_temperature,
                             @RequestParam Float k53_16_temperature,
                             @RequestParam Float k42_10_temperature,
                             @RequestParam Float k42_16_temperature,
                             @RequestParam Float k53_10_humidity,
                             @RequestParam Float k53_16_humidity,
                             @RequestParam Float k42_10_humidity,
                             @RequestParam Float k42_16_humidity,
                             @RequestParam Integer k53_10_pressure,
                             @RequestParam Integer k53_16_pressure,
                             @RequestParam Integer k42_10_pressure,
                             @RequestParam Integer k42_16_pressure,
                              HttpSession session){
        Weather editWeather = (Weather) session.getAttribute("editWeather");

        weatherService.edit(editWeather, date, k53_10_temperature, k53_16_temperature, k42_10_temperature,
                k42_16_temperature, k53_10_humidity, k53_16_humidity, k42_10_humidity, k42_16_humidity,
                k53_10_pressure, k53_16_pressure, k42_10_pressure, k42_16_pressure);

        session.removeAttribute("editWeather");

        return "redirect:/weather";
    }

    @PostMapping("deleteWeather")
    public String deleteWeather(@RequestParam Integer editWeatherId, HttpSession session){
        weatherService.delete(editWeatherId);

        session.removeAttribute("editWeather");

        return "redirect:/weather";
    }
}
