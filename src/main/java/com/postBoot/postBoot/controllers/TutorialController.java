package com.postBoot.postBoot.controllers;

import com.postBoot.postBoot.model.Tutorial;
import com.postBoot.postBoot.repository.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController  {
    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/tutorials")
    public ResponseEntity<List<Tutorial>> getAllTutorials(@RequestParam(required = false) String title){
        try {
            List<Tutorial> tutorials=new ArrayList<Tutorial>();
            if (title==null)
                tutorialRepository.findAll().forEach(tutorials::add);
            else
                tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
            if(tutorials.isEmpty()){
                return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tutorials,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id){
        Optional<Tutorial> tutorialData= tutorialRepository.findById(id);
        if (tutorialData.isPresent()) {
            return new ResponseEntity<>(tutorialData.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tutorials")
    public ResponseEntity<Tutorial> createTutorial(@RequestBody Tutorial tutorial){
        try {
            Tutorial _tutorial=tutorialRepository.save(new Tutorial(tutorial.getTitle(),tutorial.getDescription(),false));
            return new ResponseEntity<>(_tutorial,HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tutorials/{id}")
    public ResponseEntity<Tutorial> updateTutorialById(@PathVariable("id") long id,@RequestBody Tutorial tutorial){
        Optional<Tutorial> tutorialData=tutorialRepository.findById(id);
        if(tutorialData.isPresent()){
            Tutorial _tutorial=tutorialData.get();
            _tutorial.setTitle(_tutorial.getTitle());
            _tutorial.setDescription(_tutorial.getDescription());
        }
    }
}
