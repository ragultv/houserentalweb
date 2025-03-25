package com.houserental.service;

import java.time.LocalDate;
import java.util.List;

import com.houserental.model.RentalAgreement;

public interface AgreementService {
    RentalAgreement bookHouse(String houseId, String tenantId, LocalDate startDate, LocalDate endDate, double deposit);
    void recordPayment(String agreementId, LocalDate date, double amount);
    List<RentalAgreement> getAllAgreements();
    RentalAgreement getAgreementById(String id);
}