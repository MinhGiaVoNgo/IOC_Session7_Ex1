package com.example.recruitpro.service;

import com.example.recruitpro.dto.CandidateCreateDTO;
import com.example.recruitpro.entity.Candidate;
import com.example.recruitpro.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public Candidate createCandidate(CandidateCreateDTO dto) {
        Candidate candidate = Candidate.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .yearsOfExperience(dto.getYearsOfExperience())
                .build();
        return candidateRepository.save(candidate);
    }
}
