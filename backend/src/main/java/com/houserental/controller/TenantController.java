package com.houserental.controller;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.houserental.model.House;
import com.houserental.model.Tenant;
import com.houserental.service.TenantService;

@RestController
@RequestMapping("/tenants")
@CrossOrigin(origins = "http://localhost:3000")
public class TenantController {
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public Tenant registerTenant(@RequestBody Tenant tenant) {
        return tenantService.registerTenant(tenant);
    }

    @GetMapping
    public List<Tenant> getAllTenants() {
        return tenantService.getAllTenants();
    }

    @GetMapping("/{id}/matches")
    public List<House> matchTenantWithHouses(@PathVariable String id) {
        return tenantService.matchTenantWithHouses(id);
    }
}