package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointServices;

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
public class CurveController {

	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(CurveController.class);

	@Autowired
	CurvePointServices curvePointServices;

	@RequestMapping("/curvePoint/list")
	public String home(Model model) {
		model.addAttribute("listAllCurvePoint", curvePointServices.findAllCurvePoints());

		return "curvePoint/list";
	}

	@GetMapping("/curvePoint/add")
	public String addCurveForm(CurvePoint bid) {
		return "curvePoint/add";
	}

	@PostMapping("/curvePoint/validate")
	public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {

		if (result.hasErrors()) {

			return "redirect:/curvePoint/add";

		}

		curvePointServices.saveANewCurvedPoint(curvePoint);

		return "redirect:/curvePoint/list";
	}

	@GetMapping("/curvePoint/update/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {

		logger.info("=========== WTC =============" + id);
		model.addAttribute("curvePointToUpdate", curvePointServices.findSpecificCurvePoint(id));

		return "curvePoint/update";
	}

	@PostMapping("/curvePoint/update/{id}")
	public String updateCurve(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint, BindingResult result,
			Model model) {

		curvePoint.setId(id);

		Boolean updateCurvePoint = curvePointServices.updateAGivenCurvePoint(curvePoint);

		if (updateCurvePoint == true) {

			return "redirect:/curvePoint/list";

		} else {

			model.addAttribute("couldNotUpdate", true);
			return "redirect:/curvePoint/list";

		}

	}

	@GetMapping("/curvePoint/delete/{id}")
	public String deleteCurve(@PathVariable("id") Integer id, Model model) {

		curvePointServices.deleteAGivenCurvePoint(id);

		return "redirect:/curvePoint/list";

	}

	public void setCurvePointServices(CurvePointServices curvePointServices) {
		this.curvePointServices = curvePointServices;
	}
	
	
	
}
