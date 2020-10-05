package com.samir.has.api.mvccontroller;


import com.samir.has.api.object.person.Customer;
import com.samir.has.api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller("mvcCustomerController")
@RequestMapping("/customer")
public class CustomerController {

    private Customer connectedCustomer = null;
    private final CustomerService customerService;

    @Autowired
    public CustomerController(@Qualifier("customerService") CustomerService customerService) {
        this.customerService = customerService;
    }

    public String getClassName(){
        return this.getClass().getSimpleName();
    }

    @GetMapping("/login")
    public String loginCustomer(){
        return "customer/login";
    }

    @PostMapping("/login")
    public String customerLoginForm(String login, String pwd){
        if (customerService.isCustomer(login,pwd)){
            connectedCustomer = customerService.selectCustomerByLogin(login,pwd);
            return "customer/customer_home_page";
        }
        else return "customer/login";
    }

    @GetMapping("/signup")
    public String customerRegistrationForm(Model model){
        model.addAttribute("newCustomer", new Customer());
        return "customer/new_customer";
    }

    @PostMapping("/new/save")
    public String saveNewCustomer(@ModelAttribute Customer customer){
        customerService.addCustomer(customer);
        return "index";
    }

    @GetMapping("/me")
    public String editCustomerProfile(Model model){
        model.addAttribute("connectedCustomer",connectedCustomer);
        return "customer/update_customer";
    }

    @PostMapping("/update")
    public String updateCustomer(@ModelAttribute Customer customer){
        customerService.updateCustomer(customer.getCustomerId(),customer);
        return "customer/customer_home_page";
    }

}
