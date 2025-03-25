package com.houserental.model;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.houserental.model.Payment; // Ensure this is the correct package for the Payment class

@Document(collection = "agreements")
@Data
public class RentalAgreement {
    @Id
    private String id;
    private String houseId;
    private String tenantId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double deposit;
    private List<Payment> payments = new ArrayList<>();

    public RentalAgreement(String id, String houseId, String tenantId, LocalDate startDate, LocalDate endDate, double deposit) {
        this.id = id;
        this.houseId = houseId;
        this.tenantId = tenantId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.deposit = deposit;
    }

    public LocalDate getNextDueDate() {
        return startDate.plusMonths(payments.size());
    }

    public boolean isPaymentOverdue(LocalDate currentDate) {
        return currentDate.isAfter(getNextDueDate());
    }
    // Assuming this is part of the RentalAgreement class
    public List<Payment> getPayments() {
        return this.payments;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getHouseId() {
        return houseId;
    }
    public String getTenantId() {
        return tenantId;
    }
    public LocalDate getStartDate() {
    return this.startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public double getDeposit() {
        return deposit;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


}