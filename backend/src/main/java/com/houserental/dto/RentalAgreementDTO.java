package com.houserental.dto;

import com.houserental.model.RentalAgreement;

public class RentalAgreementDTO {
    private String id;
    private String houseId;
    private String tenantId;
    private String startDate;
    private String endDate;
    private double deposit;

    public RentalAgreementDTO(RentalAgreement agreement) {
        this.id = agreement.getId();
        this.houseId = agreement.getHouseId();
        this.tenantId = agreement.getTenantId();
        this.startDate = agreement.getStartDate().toString();
        this.endDate = agreement.getEndDate().toString();
        this.deposit = agreement.getDeposit();
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getHouseId() { return houseId; }
    public void setHouseId(String houseId) { this.houseId = houseId; }
    
    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }
    
    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }
    
    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
    
    public double getDeposit() { return deposit; }
    public void setDeposit(double deposit) { this.deposit = deposit; }
}