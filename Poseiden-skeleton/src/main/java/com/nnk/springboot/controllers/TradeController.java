package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeServices;

import org.apache.logging.log4j.LogManager;
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

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(RatingController.class);

	@Autowired
	TradeServices tradeServices;

	@RequestMapping("/trade/list")
	public String home(Model model) {
		model.addAttribute("listAllTrades", tradeServices.findAllTrades());

		return "trade/list";
	}

	@GetMapping("/trade/add")
	public String addTrade(Trade trade) {
		return "trade/add";
	}

	@PostMapping("/trade/validate")
	public String validate(@Valid Trade trade, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "redirect:/trade/add";

		}

		trade = tradeServices.setIfTradeIfASellOrABuy(trade);
		tradeServices.saveANewTrade(trade);

		return "redirect:/trade/list";

	}

	@GetMapping("/trade/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		logger.info("=========== WTC =============" + id);

		model.addAttribute("tradeToUpdate", tradeServices.findSpecificTrade(id));

		return "trade/update";
	}

	@PostMapping("/trade/update/{id}")
	public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade, BindingResult result, Model model) {

		trade.setTradeId(id);

		Boolean updateCurvePoint = tradeServices.updateAGivenTrade(trade);

		if (updateCurvePoint == true) {

			return "redirect:/trade/list";

		} else {

			model.addAttribute("couldNotUpdate", true);
			return "redirect:/trade/list";

		}
	}

	@GetMapping("/trade/delete/{id}")
	public String deleteTrade(@PathVariable("id") Integer id, Model model) {

		tradeServices.deleteAGivenTrade(id);

		return "redirect:/trade/list";

	}

	public void setTradeServices(TradeServices tradeServices) {
		this.tradeServices = tradeServices;
	}
	
	
	
}
