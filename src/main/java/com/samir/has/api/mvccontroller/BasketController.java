package com.samir.has.api.mvccontroller;


import com.samir.has.api.object.Basket;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.person.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller("mvcBasketController")
@SessionAttributes("basket")
@RequestMapping("/basket")
public class BasketController {

    @ModelAttribute("basket")
    private void addBasketSessionAttribute(Model model){
        model.addAttribute("basket",new Basket());
    }

    @GetMapping("")
    public String basketView(){
        return "basket/basket_home_page";
    }

    @PostMapping("/add/{productRef}")
    public String  addProduct(@PathVariable LocalUniqueId productRef,
                              @ModelAttribute("basket") Basket basket,
                              @RequestParam("quantity") int itemQuantity){
        int quantity = 0;
        if (basket.containsKey(productRef))
            quantity = basket.get(productRef);
        basket.put(productRef,quantity+itemQuantity);
        return "basket/basket_home_page";
    }

    @PostMapping("/add/{productRef}/{quantity}")
    public String  addProduct(@PathVariable LocalUniqueId productRef,
                              @PathVariable("quantity") int itemQuantity,
                              @ModelAttribute("basket") Basket basket){
        int quantity = 0;
        if (basket.containsKey(productRef))
            quantity = basket.get(productRef);
        basket.put(productRef,quantity+itemQuantity);
        return "basket/basket_home_page";
    }

    @PostMapping("/update/{productRef}")
    public String  updateProduct(@PathVariable LocalUniqueId productRef,
                                 @RequestParam("quantity") int quantity,
                                 @ModelAttribute("basket") Basket basket){
        if (quantity > 0)
            basket.put(productRef,quantity);
        if (quantity == 0)
            basket.remove(productRef);
        return "basket/basket_home_page";
    }

    @GetMapping("/remove/{productRef}")
    public String  removeProduct(@PathVariable("productRef") LocalUniqueId productRef,
                                 @ModelAttribute("basket") Basket basket){
        int quantity = basket.get(productRef) -1;
        if (quantity > 0)
            basket.replace(productRef, quantity);
        else
            basket.remove(productRef);
        return "basket/basket_home_page";
    }

    @GetMapping("/buy")
    public String validationPage(@ModelAttribute("basket") Basket basket,
                                 RedirectAttributes redirectAttributes){
        if (basket.size() != 0){
            redirectAttributes.addFlashAttribute("basket",basket);
            return "redirect:/shop/shopping/validation";
        }
        else
            return "redirect:/shop/shopping";
    }

    @PostMapping("/buy/{productRef}")
    public String validationPage(@PathVariable LocalUniqueId productRef,
                                 @RequestParam("quantity") int itemQuantity,
                                 @ModelAttribute("basket") Basket basket,
                                 RedirectAttributes redirectAttributes){
        basket.put(productRef,itemQuantity);
        redirectAttributes.addFlashAttribute("basket",basket);
        return "redirect:/shop/shopping/validation";
    }

    @PostMapping("/payment")
    public String paymentPage(@RequestParam("totalCost") float totalCost, Model model){
        model.addAttribute("totalCost",totalCost);
        return "shop/payment";
    }

    @PostMapping("/payment/invoice")
    public String invoicePage(@RequestParam("delivery") String delivery,
                              @RequestParam("totalCost") float totalCost,
                              @ModelAttribute("connectedCustomer") Customer connectedCustomer,
                              @ModelAttribute("basket") Basket basket,
                              RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute("connectedCustomer",connectedCustomer);
        redirectAttributes.addFlashAttribute("delivery",delivery);
        redirectAttributes.addFlashAttribute("basket",basket);
        redirectAttributes.addFlashAttribute("totalCost",totalCost);

        return "redirect:/invoice/basket/invoice";
    }
}
