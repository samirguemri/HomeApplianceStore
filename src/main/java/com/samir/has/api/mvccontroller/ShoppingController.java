package com.samir.has.api.mvccontroller;

import com.samir.has.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("mvcShoppingController")
@RequestMapping("/shop")
public class ShoppingController {

    private final ProductService productService;

    @Autowired
    public ShoppingController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/shopping")
    public String storeHomePage(Model model){
        model.addAttribute("productList",productService.selectAllProducts());
        return "shop/store_home_page";
    }

    public String addProductToBasket(){
        return "";
    }
}
