package com.example.nativequery.controller;


import com.example.nativequery.model.Tutorial;
import com.example.nativequery.repo.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/tt")
public class TutorialController {

    @Autowired
    TutorialRepository tutorialRepository;

    @GetMapping("/view")
    public List<Tutorial> viewTutorial (){
        return tutorialRepository.findAll();
    }
    @GetMapping("/boolean/{bool}")
    public List<Tutorial> getTutorialByPublished(@PathVariable("bool") Boolean isPublished){
        return tutorialRepository.findByPublished(isPublished);
    }
    @GetMapping("/title/{id}")
    public List<Tutorial> getTutorialByTitle(@PathVariable("id") String title){
        return tutorialRepository.findByTitleLike(title);
    }
    @GetMapping("/title1/{id}")
    public List<Tutorial> getTutorialDataByTitle(@PathVariable("id") String title){
        return tutorialRepository.findByTitleLikeCaseInsensitive(title);
    }
    @GetMapping("/level/{id}")
    public List<Tutorial> getTutorialDataByLevel(@PathVariable("id") int level){
        return tutorialRepository.findByLevelGreaterThanEqual(level);
    }

    @GetMapping("/date/{date}")
    public List<Tutorial> getTutorialDataByDate(@PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date ){
        return tutorialRepository.findByDateGreaterThanEqual(date);
    }
    @GetMapping("/level")
    public List<Tutorial> viewTutorialLevelDesc(){
        return tutorialRepository.findAllOrderByLevelDesc();
    }
    @GetMapping("/published")
    public List<Tutorial> viewTutorialPublishedDesc(){
        return tutorialRepository.findAllPublishedOrderByCreatedDesc();
    }
    @GetMapping("/level1/{start}/{end}")
    public List<Tutorial> viewTutorialLevelBetween(@PathVariable("start") int start, @PathVariable("end") int end){
        return tutorialRepository.findByLevelBetween(start,end);
    }
    @GetMapping("/date1/{start}/{end}")
    public List<Tutorial> viewTutorialDateBetween(@PathVariable("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Date start, @PathVariable("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Date end){
        return tutorialRepository.findByDateBetween(start,end);
    }
    @GetMapping("/level1/{start}/{end}/{bool}")
    public List<Tutorial> viewTutorialLevelBetween(@PathVariable("start") int start, @PathVariable("end") int end, @PathVariable("bool") Boolean bool){
        return tutorialRepository.findByLevelBetween(start,end,bool);
    }
}
