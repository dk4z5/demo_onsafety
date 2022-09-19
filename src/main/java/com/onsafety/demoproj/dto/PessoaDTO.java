package com.onsafety.demoproj.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PessoaDTO {
    Long id;
    String nome;
    String cpf;
    LocalDate dataNascimento;
    String email;   
}
