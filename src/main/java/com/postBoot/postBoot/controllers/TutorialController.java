package com.postBoot.postBoot.controllers;

import com.postBoot.postBoot.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController  {
    @Autowired
    TutorialRepository tutorialRepository;
}
