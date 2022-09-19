package com.onsafety.demoproj.services;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.onsafety.demoproj.exception.InvalidCPF;
import com.onsafety.demoproj.models.Pessoa;

@Service
public class ValidaService {
    private final static Pattern numReg = Pattern.compile("^\\d{11}$");

    private static Integer FromString(String s, int index) {
        return Integer.parseInt(Character.toString(s.charAt(index)));
    }

    public static String ValidaCPF(String cpf) {
        String novo_cpf;
        Integer i, soma, resto;

        novo_cpf = cpf.replace(".", "");
        novo_cpf = novo_cpf.replace("-", "");
        
        if ( numReg.matcher(novo_cpf).matches() == false )
            throw new InvalidCPF();

        soma = 0;
        for ( i = 1; i < 10 ; i++ ) {
            soma += FromString(novo_cpf,i-1) * (11 - i);
        }
        
        resto = (soma*10)%11;
        
        if (resto == 10 || resto == 11) 
            throw new InvalidCPF();

        if (resto != FromString(novo_cpf,9)) 
            throw new InvalidCPF();

        soma = 0;
        for ( i = 1; i < 11 ; i++ ) {
            soma += FromString(novo_cpf,i-1) * (12 - i);
        }
        
        resto = (soma*10)%11;
        
        if (resto == 10 || resto == 11) 
            throw new InvalidCPF();

        if (resto != FromString(novo_cpf,10)) 
            throw new InvalidCPF();
        
        return novo_cpf;
    }

    public static List<Pessoa> ValidaVetorCPF(List<Pessoa> pessoas){
        return pessoas.stream().map(pessoa ->{
            pessoa.setCpf(ValidaCPF(pessoa.getCpf()));
            return pessoa;
        }).collect(Collectors.toList());
    }
}
