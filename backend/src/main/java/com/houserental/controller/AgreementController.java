package com.houserental.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.houserental.model.Payment;
import com.houserental.dto.RentalAgreementDTO;
import java.time.LocalDate;
import java.util.stream.Collectors;
import com.houserental.model.BookHouseRequest;
import com.houserental.model.RentalAgreement;
import com.houserental.service.AgreementService;

@RestController
@RequestMapping("/agreements")
@CrossOrigin(origins = "http://localhost:3000")
public class AgreementController {
    private final AgreementService agreementService;

    public AgreementController(AgreementService agreementService) {
        this.agreementService = agreementService;
    }

    @PostMapping
    public RentalAgreement bookHouse(@RequestBody BookHouseRequest request) {
        return (RentalAgreement) agreementService.bookHouse(
            request.getHouseId(), request.getTenantId(), 
            LocalDate.parse(request.getStartDate()), 
            LocalDate.parse(request.getEndDate()), 
            request.getDeposit()
        );
    }

    @PostMapping("/{id}/payments")
    public void recordPayment(@PathVariable String id, @RequestBody Payment payment) {
        agreementService.recordPayment(id, payment.getDate(), payment.getAmount());
    }

    @GetMapping
    public List<RentalAgreementDTO> getAllAgreements() {
        return agreementService.getAllAgreements().stream()
            .map(RentalAgreementDTO::new)
            .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RentalAgreementDTO getAgreementById(@PathVariable String id) {
        return new RentalAgreementDTO(agreementService.getAgreementById(id));
    }
}