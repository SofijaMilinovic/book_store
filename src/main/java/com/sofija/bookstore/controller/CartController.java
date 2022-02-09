package com.sofija.bookstore.controller;

import com.sofija.bookstore.data.CartData;
import com.sofija.bookstore.facade.CartFacade;
import com.sofija.bookstore.facade.UserFacade;
import com.sofija.bookstore.transfer.Response;
import com.sofija.bookstore.transfer.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/carts")
@CrossOrigin
public class CartController {

    @Resource
    private CartFacade cartFacade;

    @Resource
    private UserFacade userFacade;

    @PostMapping("/total-sum")
    public Response getTotalSum(@RequestBody CartData cartData) {
        boolean goldenCustomer = userFacade.isGoldenCustomer(cartData.getUserId());
        double totalSum = cartFacade.getTotalSum(cartData, goldenCustomer);
        return ResponseUtil.createResponse(totalSum, HttpStatus.OK.value(), getMessage(goldenCustomer));
    }

    private String getMessage(boolean goldenCustomer) {
        if (goldenCustomer) {
            return String.format("Golden customer - you get %s%% discount", cartFacade.getGoldenCustomerDiscount());
        }
        return null;
    }
}
