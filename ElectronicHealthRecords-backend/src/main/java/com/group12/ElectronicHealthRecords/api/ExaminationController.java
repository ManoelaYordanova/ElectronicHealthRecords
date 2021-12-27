package com.group12.ElectronicHealthRecords.api;

import com.group12.ElectronicHealthRecords.beans.ExaminationRequest;
import com.group12.ElectronicHealthRecords.repositories.ExaminationRepository;
import com.group12.ElectronicHealthRecords.services.ExaminationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ExaminationController {

    ExaminationRepository examinationRepository;
    private  final ExaminationService examinationService;

    @GetMapping("/examination")
    public ResponseEntity<?> getPatientExaminationByEgn(@RequestParam String egn) {
        return examinationService.getPatientExaminationByEgn(egn);
    }

    @PostMapping("/examination/save")
    public ResponseEntity<?> createExamination(@RequestBody ExaminationRequest examinationRequest) {
        return examinationService.createExamination(examinationRequest);
    }

    @PutMapping("/examination/update")
    public ResponseEntity<?> updateExamination(@RequestBody ExaminationRequest examinationRequest) {
        return examinationService.updateExamination(examinationRequest);
    }


}
