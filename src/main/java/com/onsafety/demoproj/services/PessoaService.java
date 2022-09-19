package com.onsafety.demoproj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onsafety.demoproj.models.Pessoa;
import com.onsafety.demoproj.repository.PessoaRepository;
import com.onsafety.demoproj.exception.NotFoundException;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepo;

    public Pessoa inserePessoa(Pessoa p) {
        p.setCpf(ValidaService.ValidaCPF(p.getCpf()));
        return pessoaRepo.save(p);
    }

    public List<Pessoa> listaPessoas() {
        return pessoaRepo.findAll();
    }

    public Pessoa buscaPessoa(Long id) {
        Pessoa pessoa = pessoaRepo.findById(id).orElseThrow(() -> new NotFoundException());
        return pessoa;
    }

    public Pessoa updatePessoa(Long id, Pessoa pessoa_novo) {
        Pessoa pessoa = buscaPessoa(id);
        pessoa.setNome(pessoa_novo.getNome());
        pessoa.setCpf(pessoa_novo.getCpf());
        pessoa.setEmail(pessoa_novo.getEmail());
        
        pessoa.setDataNascimento(pessoa_novo.getDataNascimento());

        pessoaRepo.save(pessoa);

        return pessoa;
    }

    public void deletaPessoa(Long id) {
        Pessoa pessoa = buscaPessoa(id);
        pessoaRepo.delete(pessoa);
    }
}
