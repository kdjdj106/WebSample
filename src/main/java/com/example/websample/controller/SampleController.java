package com.example.websample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class SampleController {

    @GetMapping ( "/order/{orderId}")
    public  String getOrder(@PathVariable("orderId") String id){
        log.info("Get some order : " +id);
        return "orderId: "+ id +", "+"orderAmount:1000";
    }
    @GetMapping ( "/order")
    public  String getOrderWithRequestParam(
            @RequestParam("orderId") String id,
            @RequestParam("orderAmount") Integer amount){
        log.info("Get order : " +id + ", amount : " + amount);
        return "orderId: "+ id +", "+"orderAmount: "+ amount;
    }
    @PutMapping ( "/order")
    public  String createOrder(){
        log.info("Create order");
        return "order created -> orderId:1, orederAmount:1000";
    }

}
