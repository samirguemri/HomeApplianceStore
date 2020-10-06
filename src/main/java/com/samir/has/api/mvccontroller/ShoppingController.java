package com.samir.has.api.mvccontroller;

import com.samir.has.api.object.Basket;
import com.samir.has.api.object.LocalUniqueId;
import com.samir.has.api.object.product.Product;
import com.samir.has.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller("mvcShoppingController")
@SessionAttributes("productList")
@RequestMapping("/shop")
public class ShoppingController {

    private final ProductService productService;

    @Autowired
    public ShoppingController(@Qualifier("productService") ProductService productService) {
        this.productService = productService;
    }

    @ModelAttribute("productList")
    private void addProductListSessionAttribute(Model model){
        model.addAttribute("productList",productService.selectAllProducts());         ;
    }

    @GetMapping("/shopping")
    public String storeHomePage(@ModelAttribute List<Product> productList, Model model){
        model.addAttribute("productList",productList);
        return "shop/store_home_page";
    }

    @GetMapping("/shopping/validation")
    public String shoppingValidation(@ModelAttribute("basket") Basket basket,
                                     @ModelAttribute("productList") List<Product> productList,
                                     Model model){
        model.addAttribute("basket",basket);
        model.addAttribute("productList",productList);
        model.addAttribute("totalCost",calculateTotalCost(basket,productList));
        return "shop/validate_basket";
    }

    private double calculateTotalCost(Basket basket, List<Product> productList){
        double total = 0;
        for (Map.Entry<LocalUniqueId,Integer> entry : basket.entrySet()){
            double price = productList.stream()
                                    .filter(product -> product.getProductRef().equals(entry.getKey()))
                                    .findFirst().get()
                                    .getPrice();
            total += price * entry.getValue();

        }
        return total;
    }

}
