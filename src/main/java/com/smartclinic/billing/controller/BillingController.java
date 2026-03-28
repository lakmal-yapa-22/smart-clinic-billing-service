package com.smartclinic.billing.controller;

import com.smartclinic.billing.entity.Billing;
import com.smartclinic.billing.service.BillingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billings")
@CrossOrigin(origins = "*")
public class BillingController {

    private final BillingService service;

    public BillingController(BillingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Billing> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Billing findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping("/patient/{patientId}")
    public List<Billing> findByPatientId(@PathVariable Long patientId) {
        return service.findByPatientId(patientId);
    }

    @PostMapping
    public Billing create(@Valid @RequestBody Billing billing) {
        return service.create(billing);
    }

    @PutMapping("/{id}")
    public Billing update(@PathVariable Long id, @Valid @RequestBody Billing billing) {
        return service.update(id, billing);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
