package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.repositories.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

import java.security.Principal;

@Controller
public class TradeController {

    @Autowired
    private TradeRepository tradeRepository;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeRepository.findAll());

        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTradeForm(Model model, Principal principal) {
        model.addAttribute("username", principal != null ? principal.getName() : "anonymous");
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {

        System.out.println(">>> Trade submitted: " + trade);
        if (result.hasErrors()) {
            System.out.println(">>> Form has errors!");
            return "trade/add";
        }
        tradeRepository.save(trade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id:" + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                              BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeRepository.save(trade);
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {

        Trade trade = tradeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Trade Id:" + id));
        tradeRepository.delete(trade);
        return "redirect:/trade/list";
    }
}
