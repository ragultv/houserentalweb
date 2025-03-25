package com.houserental.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.houserental.model.House;
import com.houserental.model.Tenant;
import com.houserental.repository.TenantRepository;

@Service
public class TenantServiceImpl implements TenantService {
    private final TenantRepository tenantRepository;
    private final HouseService houseService;

    public TenantServiceImpl(TenantRepository tenantRepository, HouseService houseService) {
        this.tenantRepository = tenantRepository;
        this.houseService = houseService;
    }

    @Override
    public Tenant registerTenant(Tenant tenant) {
        if (tenantRepository.existsById(tenant.getId())) {
            throw new IllegalArgumentException("Tenant ID already exists.");
        }
        return tenantRepository.save(tenant);
    }

    public List<House> matchTenantWithHouses(String tenantId) {
        Tenant tenant = tenantRepository.findById(tenantId)
            .orElseThrow(() -> new IllegalArgumentException("Tenant not found."));
        return houseService.getAllHouses().stream()
            .filter(h -> !h.isBooked())
            .filter(h -> h.getLocation().equalsIgnoreCase(tenant.getPreferredLocation()))
            .collect(Collectors.toList());
    }

    @Override
    public List<Tenant> getAllTenants() {
        return tenantRepository.findAll();
    }

    @Override
    public Tenant getTenantById(String id) {
        return tenantRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tenant not found."));
    }
}