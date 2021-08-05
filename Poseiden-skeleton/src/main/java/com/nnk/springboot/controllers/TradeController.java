package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class TradeController {


	@Autowired
	TradeServices tradeServices;

	// READ
	@RequestMapping("/trade/list")
	public String home(Model model) {
		model.addAttribute("listAllTrades", tradeServices.findAllTrades());

		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addTrade(Trade trade) {
		return "trade/add";
	}

	// CREATE
	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "trade/add";

		}

		trade = tradeServices.setIfTradeIfASellOrABuy(trade);
		tradeServices.saveANewTrade(trade);

		return "redirect:/trade/list";

	}

	// READ
	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {


		model.addAttribute("tradeToUpdate", tradeServices.findSpecificTrade(id));

		return "trade/update";
	}

	// UPDATE
	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {

		trade.setTradeId(id);

		tradeServices.updateAGivenTrade(trade);


		return "redirect:/trade/list";

	}

	// DELETE
	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {

		tradeServices.deleteAGivenTrade(id);

		return "redirect:/trade/list";

	}

	// SETTER solely needed for testing purposes, can be deleted without incident on
	// code.

	public void setTradeServices(TradeServices tradeServices) {
		this.tradeServices = tradeServices;
	}

}
