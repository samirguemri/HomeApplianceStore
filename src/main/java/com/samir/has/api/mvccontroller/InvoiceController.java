package com.samir.has.api.mvccontroller;

import com.samir.has.api.object.Basket;
import com.samir.has.api.object.Invoice;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.livraison.*;
import com.samir.has.api.object.person.Customer;
import com.samir.has.api.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("mvcBillController")
@RequestMapping("/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(@Qualifier("invoiceService") InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/basket/invoice")
    public String creatInvoice(@ModelAttribute("delivery") String delivery,
                               @ModelAttribute("totalCost") float totalCost,
                               @ModelAttribute("connectedCustomer") Customer customer,
                               @ModelAttribute("basket") Basket basket,
                               Model model){
        Delivery deliveryMode = getDeliveryMode(delivery);
        if (customer == null)
            customer = new Customer();
        Invoice invoice = basketToInvoice(customer,deliveryMode,basket,totalCost);
        invoiceService.addInvoice(invoice);
        model.addAttribute("invoice", invoice);
        return "invoice/invoice";
    }

    private Delivery getDeliveryMode(String delivery){
        Delivery deliveryMode;
        switch (delivery){
            case "home": deliveryMode = new HomeDelivery();break;
            case "chrono": deliveryMode = new ExpressHomeDelivery("");break;
            case "relay": deliveryMode = new RelayPointDelivery(1);break;
            default: deliveryMode = new StorePickup();break;
        }
        return deliveryMode;
    }

    private Invoice basketToInvoice(Customer customer, Delivery deliveryMode, Basket basket, float totalCost){
        Invoice invoice = new Invoice(customer.getCustomerId(),deliveryMode);
        invoice.setProductsList(basket);
        invoice.setPrice(totalCost);
        return invoice;
    }

    @PostMapping("/send")
    public String mailingInvoice(@RequestParam("email") String email,
                                 @RequestParam("invoiceNumber") LocalUniqueId invoiceNumber,
                                 Model model){
        invoiceService.generateXml(invoiceNumber);
        model.addAttribute("email",email);
        return "invoice/send";
    }

}
