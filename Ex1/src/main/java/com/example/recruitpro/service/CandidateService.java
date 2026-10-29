package com.example.recruitpro.service;

import com.example.recruitpro.dto.CandidateCreateDTO;
import com.example.recruitpro.entity.Candidate;

public interface CandidateService {
    Candidate createCandidate(CandidateCreateDTO dto);
}
