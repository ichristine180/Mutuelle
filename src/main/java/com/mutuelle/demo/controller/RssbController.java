package com.mutuelle.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RssbController
{

    @GetMapping("/healthcenter/pay")
    public void startHealthCenterPayment(@PathVariable final long paymentLogId)
    {

    }

    @PostMapping("/healthcenter/pay")
    public void processHealthCenterPayment(@PathVariable final long paymentLogId)
    {

    }

}
