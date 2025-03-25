package com.houserental.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.houserental.service.HouseService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.houserental.model.House;

@RestController
@RequestMapping("/houses")
@CrossOrigin(origins = "http://localhost:3000")
public class HouseController {
    private final HouseService houseService;

    @Autowired

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public House addHouse(@RequestBody House house) {
        return (House) houseService.addHouse(house);
    }

    @DeleteMapping("/{id}")
    public void removeHouse(@PathVariable String id) {
        houseService.removeHouse(id);
    }

    @GetMapping
    public List<House> getAllHouses() {
        return houseService.getAllHouses().stream()
                .map(obj -> (House) obj)
                .collect(Collectors.toList());
    }

    @GetMapping("/search")
    public List<House> searchHouses(@RequestParam String location, @RequestParam double maxPrice) {
        return houseService.searchHouses(location, maxPrice).stream()
                .map(obj -> (House) obj)
                .collect(Collectors.toList());
    }
}