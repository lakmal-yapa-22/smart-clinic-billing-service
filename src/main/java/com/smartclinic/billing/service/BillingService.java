package com.smartclinic.billing.service;

import com.smartclinic.billing.entity.Billing;
import com.smartclinic.billing.repository.BillingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BillingService {

    private final BillingRepository repository;

    public BillingService(BillingRepository repository) {
        this.repository = repository;
    }

    public List<Billing> findAll() {
        return repository.findAll();
    }

    public Billing findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Billing record not found"));
    }

    public List<Billing> findByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    public Billing create(Billing billing) {
        billing.setId(null);
        return repository.save(billing);
    }

    public Billing update(Long id, Billing billing) {
        Billing existing = findById(id);
        existing.setPatientId(billing.getPatientId());
        existing.setAmount(billing.getAmount());
        existing.setPaymentStatus(billing.getPaymentStatus());
        existing.setDescription(billing.getDescription());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.delete(findById(id));
    }
}
