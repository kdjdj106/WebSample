package com.example.websample.controller;

import com.example.websample.dto.ErrorResponse;
import com.example.websample.exception.ErrorCode;
import com.example.websample.exception.WebSampleException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class SampleController {

    @GetMapping("/order/{orderId}")
    public String getOrder(@PathVariable("orderId") String id) throws IllegalAccessException {
        log.info("Get some order : " + id);
        if ("500".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_BIG_ID_ERROR
                    ,"500 is too small orderID");
        }
        if ("3".equals(id)) {
            throw new WebSampleException(
                    ErrorCode.TOO_SMALL_ID_ERROR
                    ,"3 is too small orderID");
        }

        return "orderId: " + id + ", " + "orderAmount:1000";
    }




    @DeleteMapping("/order/{orderId}")
    public String deleteOrder(@PathVariable("orderId") String id) {
        log.info("Delete some order : " + id);
        return "Delete orderId: " + id;
    }

    @GetMapping("/order")
    public String getOrderWithRequestParam(
            @RequestParam(value = "orderId", required = false, defaultValue = "defaultId") String id,
            @RequestParam("orderAmount") Integer amount) {
        log.info("Get order : " + id + ", amount : " + amount);
        return "orderId: " + id + ", " + "orderAmount: " + amount;
    }

    @PostMapping("/order")
    public String createOrder(
            @RequestBody CreateOrderRequest createOrderRequest,
            @RequestHeader String userAccountId) {
        log.info("Create order : " + createOrderRequest + ", userAccountId : " + userAccountId);
        return "orderId: " + createOrderRequest.getOrderId() + ", " +
                "orderAmount: " + createOrderRequest.getOrderAmount();
    }

    @PutMapping("/order")
    public String createOrder() {
        log.info("Create order");
        return "order created -> orderId:1, orederAmount:1000";
    }

    @Data
    public static class CreateOrderRequest {
        private String orderId;
        private Integer orderAmount;

    }

}
