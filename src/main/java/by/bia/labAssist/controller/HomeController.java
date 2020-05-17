package by.bia.labAssist.controller;

import by.bia.labAssist.service.EmployeeService;
import by.bia.labAssist.model.Element;
import by.bia.labAssist.model.Employee;
import by.bia.labAssist.model.TestMethod;
import by.bia.labAssist.service.ElementService;
import by.bia.labAssist.service.TestMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private TestMethodService testMethodService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ElementService elementService;

    @GetMapping
    public String home(){
        return "home";
    }

    @GetMapping("add_and_edit_element")
    public String addAndEditElementPage(Model model){
        List<Element> allElements = elementService.findAll();

        model.addAttribute("allElements", allElements);
        return "addAndEditElement";
    }

    @PostMapping("addElement")
    public String addElement(@RequestParam String title, @RequestParam String symbol){
        elementService.create(title, symbol);

        return "redirect:add_and_edit_element";
    }

    @PostMapping("chooseElement")
    public String chooseElement(@RequestParam Integer elementSelect, HttpSession session){
        Element elementEdit = elementService.findById(elementSelect);

        session.setAttribute("elementEdit", elementEdit);
        return "redirect:add_and_edit_element";
    }

    @PostMapping("editElement")
    public String editElement(@RequestParam String title, @RequestParam String symbol, HttpSession session){
        Element elementEdit = (Element)session.getAttribute("elementEdit");
        elementService.edit(elementEdit, title, symbol);

        session.removeAttribute("elementEdit");
        return "redirect:add_and_edit_element";
    }

    @PostMapping("deleteElement")
    public String deleteElement(@RequestParam Integer elementId, HttpSession session){
        try {
            elementService.delete(elementId);
        }catch (Exception ex){
            return "errors/elementEmployeeTestMethodDeleteError";
        }

        session.removeAttribute("elementEdit");
        return "redirect:add_and_edit_element";
    }

    @GetMapping("add_and_edit_employee")
    public String addAndEditEmployee(Model model){
    List<Employee> allEmployees = employeeService.findAll();

    model.addAttribute("allEmployees", allEmployees);
    return "addAndEditEmployee";
    }

    @PostMapping("addEmployee")
    public String addEmployee(@RequestParam String name, @RequestParam String position){
        employeeService.create(name, position);

        return "redirect:add_and_edit_employee";
    }

    @PostMapping("chooseEmployee")
    public String chooseEmployee(@RequestParam Integer employeeSelect, HttpSession session){
        Employee employeeEdit = employeeService.findById(employeeSelect);

        session.setAttribute("employeeEdit", employeeEdit);
        return "redirect:add_and_edit_employee";
    }

    @PostMapping("editEmployee")
    public String editEmployee(@RequestParam String name, @RequestParam String position, HttpSession session){
        Employee employeeEdit = (Employee)session.getAttribute("employeeEdit");
        employeeService.edit(employeeEdit, name, position);

        session.removeAttribute("employeeEdit");
        return "redirect:add_and_edit_employee";
    }

    @PostMapping("deleteEmployee")
    public String deleteEmployee(@RequestParam Integer employeeId, HttpSession session){
        try {
            employeeService.delete(employeeId);
        }catch (Exception ex){
            return "errors/elementEmployeeTestMethodDeleteError";
        }

        session.removeAttribute("employeeEdit");
        return "redirect:add_and_edit_employee";
    }

    @GetMapping("add_and_edit_test_method")
    public String addAndEditTestMethodPage(Model model){
        List<TestMethod> allTestMethods = testMethodService.findAll();

        model.addAttribute("allTestMethods", allTestMethods);
        return "addAndEditTestMethod";
    }

    @PostMapping("addTestMethod")
    public String addTestMethod(@RequestParam String title){
        testMethodService.create(title);

        return "redirect:add_and_edit_test_method";
    }

    @PostMapping("chooseTestMethod")
    public String chooseTestMethod(@RequestParam Integer testMethodSelect, HttpSession session){
        TestMethod testMethodEdit = testMethodService.findById(testMethodSelect);

        session.setAttribute("testMethodEdit", testMethodEdit);
        return "redirect:add_and_edit_test_method";
    }

    @PostMapping("editTestMethod")
    public String editTestMethod(@RequestParam String title, HttpSession session){
        TestMethod testMethodEdit = (TestMethod)session.getAttribute("testMethodEdit");
        testMethodService.edit(testMethodEdit, title);

        session.removeAttribute("testMethodEdit");
        return "redirect:add_and_edit_test_method";
    }

    @PostMapping("deleteTestMethod")
    public String deleteTestMethod(@RequestParam Integer testMethodId, HttpSession session){
        try {
            testMethodService.delete(testMethodId);
        }catch (Exception ex){
            return "errors/elementEmployeeTestMethodDeleteError";
        }

        session.removeAttribute("testMethodEdit");
        return "redirect:add_and_edit_test_method";
    }
}