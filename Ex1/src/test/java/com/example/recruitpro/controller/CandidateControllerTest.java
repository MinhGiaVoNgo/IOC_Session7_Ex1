package com.example.recruitpro.controller;

import com.example.recruitpro.dto.CandidateCreateDTO;
import com.example.recruitpro.entity.Candidate;
import com.example.recruitpro.service.CandidateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CandidateController.class)
class CandidateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CandidateService candidateService;

    @Test
    @DisplayName("POST /api/v1/candidates - Invalid data should return 400 with 'Dữ liệu không hợp lệ !'")
    void testCreateCandidate_InvalidData() throws Exception {
        // email invalid ("huongcaoha123")
        CandidateCreateDTO dto = CandidateCreateDTO.builder()
                .fullName("Nguyễn Công Hưởng")
                .email("huongcaoha123")
                .age(18)
                .yearsOfExperience(10)
                .build();

        mockMvc.perform(post("/api/v1/candidates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Dữ liệu không hợp lệ !"));
    }

    @Test
    @DisplayName("POST /api/v1/candidates - Valid data should return 201 with Candidate details")
    void testCreateCandidate_ValidData() throws Exception {
        CandidateCreateDTO dto = CandidateCreateDTO.builder()
                .fullName("Nguyễn Công Hưởng")
                .email("huongcaoha123@gmail.com")
                .age(18)
                .yearsOfExperience(10)
                .build();

        Candidate savedCandidate = Candidate.builder()
                .id(1L)
                .fullName("Nguyễn Công Hưởng")
                .email("huongcaoha123@gmail.com")
                .age(18)
                .yearsOfExperience(10)
                .build();

        when(candidateService.createCandidate(any(CandidateCreateDTO.class))).thenReturn(savedCandidate);

        mockMvc.perform(post("/api/v1/candidates")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("Nguyễn Công Hưởng"))
                .andExpect(jsonPath("$.email").value("huongcaoha123@gmail.com"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.yearsOfExperience").value(10));
    }
}
