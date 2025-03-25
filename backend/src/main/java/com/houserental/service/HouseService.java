package com.houserental.service;

import java.util.List;
import com.houserental.model.House;

public interface HouseService {
    House addHouse(House house);
    void removeHouse(String id);
    House getHouseById(String id);
    void updateHouse(House house);
    List<House> getAllHouses();
    List<House> searchHouses(String location, double maxPrice);
}