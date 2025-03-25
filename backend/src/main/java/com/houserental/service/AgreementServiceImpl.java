package com.houserental.service;

import com.houserental.model.*;
import com.houserental.repository.AgreementRepository;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AgreementServiceImpl implements AgreementService {
    private final AgreementRepository agreementRepository;
    private final HouseService houseService;
    private final TenantService tenantService;
    private final MongoOperations mongoOperations;

    public AgreementServiceImpl(AgreementRepository agreementRepository, HouseService houseService,
                                 TenantService tenantService, MongoOperations mongoOperations) {
        this.agreementRepository = agreementRepository;
        this.houseService = houseService;
        this.tenantService = tenantService;
        this.mongoOperations = mongoOperations;
    }

    @Override
    public RentalAgreement bookHouse(String houseId, String tenantId, LocalDate startDate,
                                     LocalDate endDate, double deposit) {
        House house = houseService.getHouseById(houseId);
        if (house.isBooked()) {
            throw new IllegalArgumentException("House already booked.");
        }
        Tenant tenant = tenantService.getTenantById(tenantId);
        if (deposit < 0) {
            throw new IllegalArgumentException("Deposit cannot be negative.");
        }
        String agreementId = getNextAgreementId();
        RentalAgreement agreement = new RentalAgreement(agreementId, houseId, tenantId, startDate, endDate, deposit);
        agreement = agreementRepository.save(agreement);
        house.setBooked(true);
        house.setTenantId(tenantId);
        houseService.updateHouse(house);
        return agreement;
    }

    private String getNextAgreementId() {
    Counter counter = mongoOperations.findAndModify(
        Query.query(Criteria.where("_id").is("agreement")),
        new Update().inc("value", 1),
        org.springframework.data.mongodb.core.FindAndModifyOptions.options().returnNew(true).upsert(true),
        Counter.class
    );
    return "RA" + (counter != null ? counter.getValue() : 1);
    }

    @Override
    public void recordPayment(String agreementId, LocalDate date, double amount) {
        RentalAgreement agreement = agreementRepository.findById(agreementId)
            .orElseThrow(() -> new IllegalArgumentException("Agreement not found."));
        agreement.getPayments().add(new Payment(date, amount));
        agreementRepository.save(agreement);
    }

    @Override
    public RentalAgreement getAgreementById(String id) {
        return agreementRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Agreement not found."));
    }

    @Override
    public List<RentalAgreement> getAllAgreements() {
        return agreementRepository.findAll();
    }
}