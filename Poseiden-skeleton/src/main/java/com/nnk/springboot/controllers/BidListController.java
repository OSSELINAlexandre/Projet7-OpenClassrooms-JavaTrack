package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListServices;

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
public class BidListController {
	

	
	@Autowired
	BidListServices bidListServices;
	

	//READ
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

    //CREATE
    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
    	
    	if(result.hasErrors()) {
    		
            return "bidList/add";

    	}
    	
    	bidListServices.saveANewBid(bid);
    	
        return "redirect:/bidList/list";
    }

    //READ
    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	
    	

        model.addAttribute("bidListToUpdate", bidListServices.findSpecificBid(id));
    	
        return "bidList/update";
    }

    
    //UPDATE
    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
    	
    	bidList.setBidlistid(id);
    	bidListServices.updateAGivenBid(bidList);

	
    		
        return "redirect:/bidList/list";
            
    	
    }

    //DELETE
    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        
    	bidListServices.deleteAGivenBid(id);
    	
    		
        return "redirect:/bidList/list";
    	
    }
    
    
    //SETTER solely needed for testing purposes, can be deleted without incident on code.

	public void setBidListServices(BidListServices bidListServices) {
		this.bidListServices = bidListServices;
	}

    
}
