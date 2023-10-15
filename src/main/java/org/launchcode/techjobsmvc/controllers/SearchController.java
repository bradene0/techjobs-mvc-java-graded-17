package org.launchcode.techjobsmvc.controllers;

import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; // Use @PostMapping
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices); // Use ListController.columnChoices
        return "search";
    }

    // Add a handler to process a search request and render the updated search view.
    @PostMapping(value = "results")
    public String displaySearchResults(Model model,
                                       @RequestParam String searchType,
                                       @RequestParam String searchTerm) {
        ArrayList<Job> jobs;

        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm); // Use findByValue to filter by the keyword.
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
        }

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);

        return "search";
    }}
