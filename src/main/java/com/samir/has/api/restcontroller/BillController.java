package com.samir.has.api.restcontroller;

import com.samir.has.api.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("restBillController")
@RequestMapping
public class BillController {

    private final BillService billService;

    @Autowired
    public BillController(@Qualifier("billService") BillService billService) {
        this.billService = billService;
    }




}
