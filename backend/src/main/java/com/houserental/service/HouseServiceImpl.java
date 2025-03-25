package com.houserental.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.houserental.model.House;
import com.houserental.repository.HouseRepository;

@Service
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;

    public HouseServiceImpl(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public House addHouse(House house) {
        if (house.getPrice() < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        if (houseRepository.existsById(house.getId())) {
            throw new IllegalArgumentException("House ID already exists.");
        }
        return houseRepository.save(house);
    }

    public void removeHouse(String id) {
        House house = houseRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("House not found."));
        if (house.isBooked()) {
            throw new IllegalArgumentException("Cannot remove booked house.");
        }
        houseRepository.deleteById(id);
    }

    public List<House> searchHouses(String location, double maxPrice) {
        return houseRepository.findAll().stream()
            .filter(h -> !h.isBooked())
            .filter(h -> h.getLocation().equalsIgnoreCase(location) && h.getPrice() <= maxPrice)
            .collect(Collectors.toList());
    }

    @Override
    public House getHouseById(String id) {
        return houseRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("House not found."));
    }

    @Override
    public void updateHouse(House house) {
        houseRepository.save(house);
    }

    @Override
    public List<House> getAllHouses() {
        return houseRepository.findAll();
    }
}