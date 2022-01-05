package com.example.wineservice.controller;

import com.example.wineservice.model.Wine;
import com.example.wineservice.repository.WineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class WineController {
    @Autowired
    private WineRepository wineRepository;

    @PostConstruct
    public void fillDB(){
        if(wineRepository.count()==0){
            wineRepository.save(new Wine("Château Pion", "Côtes de Bergerac", "Frankrijk", 4.3, "Fernao Pires"));
            wineRepository.save(new Wine("Monbazillac", "Monbazillac", "Frankrijk", 3.9, "Semillon"));
            wineRepository.save(new Wine("Nebukadnesar 2018", "Western Cape", "Zuid-Afrika", 4.5, "Merlot"));
            wineRepository.save(new Wine("Aria di Caiarossa", "Toscane", "Italië", 4.1, "Sangiovese"));
        }
        //System.out.println("Wines test: " +wineRepository.findWinesByCountry("Frankrijk").size());
    }

    @GetMapping("/wines/country/{country}")
    public List<Wine> getWinesByCountry(@PathVariable String country){
        return wineRepository.findWinesByCountry(country);
    }

    @GetMapping("/wines/country/{country}/region/{region}")
    public List<Wine> getWinesByCountryAndRegion(@PathVariable String country, @PathVariable String region){
        return wineRepository.findWinesByCountryAndRegion(country, region);
    }

    @GetMapping("/wines/grape/{grapeName}")
    public List<Wine> getWinesByGrapeName(@PathVariable String grapeName){
        return wineRepository.findWinesByGrapeName(grapeName);
    }

    @GetMapping("/wines/name/{name}")
    public Wine getWineByNameAndCountryAndRegion(@PathVariable String name){
        return wineRepository.findWineByName(name);
    }

    @GetMapping("/wines")
    public List<Wine> getWines(){
        return wineRepository.findAll();
    }

    @PostMapping("/wines")
    public Wine addWine(@RequestBody Wine wine){
        wineRepository.save(wine);
        return wine;
    }

    @PutMapping("/wines")
    public Wine updateWine(@RequestBody Wine updatedWine){
        Wine retrievedWine = wineRepository.findWineByName(updatedWine.getName());

        retrievedWine.setScore(updatedWine.getScore());

        wineRepository.save(retrievedWine);
        return retrievedWine;
    }

    @DeleteMapping("/wines/name/{name}")
    public ResponseEntity deleteWine(@PathVariable String name){
        Wine wine = wineRepository.findWineByName(name);
        if(wine!=null){
            wineRepository.delete(wine);
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.notFound().build();
    }
}