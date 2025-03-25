package com.houserental.service;

import java.util.List;

import com.houserental.model.House;
import com.houserental.model.Tenant;

public interface TenantService {
    Tenant registerTenant(Tenant tenant);
    List<Tenant> getAllTenants();
    Tenant getTenantById(String id);
    List<House> matchTenantWithHouses(String tenantId); // New method

}