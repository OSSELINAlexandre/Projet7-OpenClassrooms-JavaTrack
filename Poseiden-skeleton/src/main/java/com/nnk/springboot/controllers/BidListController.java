package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListServices;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class BidListController {
	
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(BidListController.class);

	
	@Autowired
	BidListServices bidListServices;
	

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
    	
    	model.addAttribute("listAllBids", bidListServices.findAllBids());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        // TODO: check data valid and save to db, after saving return bid list	
    	
    	if(result.hasErrors()) {
    		
            return "bidList/add";

    	}
    	
    	bidListServices.saveANewBid(bid);
    	
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	

        model.addAttribute("bidListToUpdate", bidListServices.findSpecificBid(id));
    	
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
    	
    	bidList.setBidlistid(id);
    	Boolean updateABid = bidListServices.updateAGivenBid(bidList);

		logger.info("///////////////////////" + updateABid);
		
    	if(updateABid == true) {
    		
            return "redirect:/bidList/list";
            

    	}else {
    		
    		model.addAttribute("couldNotUpdate" , true);
            return "redirect:/bidList/list";

    	}
    	
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        
    	bidListServices.deleteAGivenBid(id);
    	
    		
        return "redirect:/bidList/list";
    	
    }

	public void setBidListServices(BidListServices bidListServices) {
		this.bidListServices = bidListServices;
	}

    
}
