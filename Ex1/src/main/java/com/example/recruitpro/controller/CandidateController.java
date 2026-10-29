package com.example.recruitpro.controller;

import com.example.recruitpro.dto.CandidateCreateDTO;
import com.example.recruitpro.entity.Candidate;
import com.example.recruitpro.service.CandidateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping({"/api/v1/candidates", "/api/candidates"})
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<Candidate> createCandidate(@Valid @RequestBody CandidateCreateDTO dto) {
        Candidate createdCandidate = candidateService.createCandidate(dto);
        return new ResponseEntity<>(createdCandidate, HttpStatus.CREATED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationException(MethodArgumentNotValidException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dữ liệu không hợp lệ !");
    }
}
