package com.mycompany.gymmanager.controller;


import com.mycompany.gymmanager.entity.BranchSettings;
import com.mycompany.gymmanager.service.BranchSettingsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branch-settings")
public class BranchSettingsController {

    private final BranchSettingsService branchSettingsService;

    public BranchSettingsController(BranchSettingsService branchSettingsService) {
        this.branchSettingsService = branchSettingsService;
    }

    @PostMapping
    public ResponseEntity<BranchSettings> create(@RequestBody BranchSettings settings) {
        BranchSettings created = branchSettingsService.create(settings);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<BranchSettings>> getAll() {
        List<BranchSettings> list = branchSettingsService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BranchSettings> getById(@PathVariable("id") Integer id) {
        BranchSettings settings = branchSettingsService.getById(id);
        return ResponseEntity.ok(settings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BranchSettings> update(@PathVariable("id") Integer id, @RequestBody BranchSettings update) {
        BranchSettings updated = branchSettingsService.update(id, update);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        branchSettingsService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

