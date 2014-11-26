package org.addressbook.web.controller;

import org.addressbook.persistence.domain.PhysicalAddress;
import org.addressbook.service.PhysicalAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

/**
 * @author alisiikh.
 */
@Controller
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
    public String save(@ModelAttribute PhysicalAddress physicalAddress, Model model) {
        try {
            physicalAddressService.save(physicalAddress);
        } catch (Exception e) {
            model.addAttribute("address", physicalAddress);

            return "/address/create";
        }

        return "redirect:/address/view/" + physicalAddress.getId();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute PhysicalAddress physicalAddress, Model model) {
        try {
            physicalAddressService.save(physicalAddress);
        } catch (Exception e) {
            model.addAttribute("address", physicalAddress);

            return "/address/edit";
        }

        return "redirect:/address/view/" + physicalAddress.getId();
    }
}
