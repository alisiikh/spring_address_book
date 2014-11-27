package org.addressbook.web.controller;

import org.addressbook.persistence.domain.PhysicalAddress;
import org.addressbook.service.PhysicalAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.inject.Inject;
import javax.validation.Valid;

/**
 * @author alisiikh.
 */
@Controller
@SessionAttributes(types = PhysicalAddress.class)
@RequestMapping("/address")
public class PhysicalAddressController {

    @Inject
    private PhysicalAddressService physicalAddressService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("addresses", physicalAddressService.list());

        return "/address/list";
    }

    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public String view(@PathVariable("id") PhysicalAddress physicalAddress, Model model) {
        model.addAttribute("address", physicalAddress);

        return "/address/view";
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") PhysicalAddress physicalAddress, Model model) {
        model.addAttribute("address", physicalAddress);

        return "/address/edit";
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("address", physicalAddressService.create());

        return "/address/create";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("address") @Valid PhysicalAddress physicalAddress, BindingResult bindingResult, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "/address/create";
        } else {
            physicalAddressService.save(physicalAddress);
            sessionStatus.setComplete();
            return "redirect:/address/view/" + physicalAddress.getId();
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("address") @Valid PhysicalAddress physicalAddress, BindingResult bindingResult, SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "/address/edit";
        } else {
            physicalAddressService.save(physicalAddress);
            sessionStatus.setComplete();
            return "redirect:/address/view/" + physicalAddress.getId();
        }
    }
}
