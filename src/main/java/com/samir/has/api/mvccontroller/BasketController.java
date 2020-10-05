package com.samir.has.api.mvccontroller;


import com.samir.has.api.object.Basket;
import com.samir.has.api.object.LocalUniqueId;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller("mvcBasketController")
@RequestMapping("/basket")
@SessionAttributes("basket")
public class BasketController {

    @ModelAttribute("basket")
    private Basket creatBasket(){
        return new Basket();
    }

    @PostMapping("/add/{productRef}")
    public String  addProduct(@PathVariable LocalUniqueId productRef,
                               @ModelAttribute("basket") Basket basket,
                              @RequestParam("quantity") int itemQuantity){
        if(basket == null)
            creatBasket();
        int quantity = 0;
        if (basket.containsKey(productRef))
            quantity = basket.get(productRef);
        basket.put(productRef,quantity+itemQuantity);
        return "basket/basket_home_page";
    }

    @GetMapping("/remove/{productRef}")
    public String  removeProduct(@PathVariable LocalUniqueId productRef,
                                 @ModelAttribute("basket") Basket basket){
        basket.remove(productRef);
        return "basket/basket_home_page";
    }

    @GetMapping("/buy")
    public String  buyNow(@ModelAttribute("basket") Basket basket){
        if (!basket.isEmpty())
            return "shop/shoppingValidation";
        else
            return "shop/store_home_page";
    }

}
