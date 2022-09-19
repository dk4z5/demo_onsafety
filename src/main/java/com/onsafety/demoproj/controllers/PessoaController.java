package com.onsafety.demoproj.controllers;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import com.onsafety.demoproj.dto.PessoaDTO;
import com.onsafety.demoproj.dto.PessoaRespostaDTO;
import com.onsafety.demoproj.models.Pessoa;
import com.onsafety.demoproj.services.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    
    @Autowired
    private ModelMapper modelMapper;

    private PessoaService pService;

    PessoaController(PessoaService pessoaService) {
        super();
        pService = pessoaService;
    }

    @GetMapping
    public List<PessoaRespostaDTO> getPessoas() {
        return pService.listaPessoas().stream()
            .map(ps -> modelMapper.map(ps, PessoaRespostaDTO.class))
            .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> inserirPessoa(@RequestBody PessoaDTO pessoaDTO) {

        Pessoa pessoaReq = modelMapper.map(pessoaDTO, Pessoa.class);

        Pessoa pessoa = pService.inserePessoa(pessoaReq);

        PessoaDTO pessoaRes = modelMapper.map(pessoa, PessoaDTO.class);

        return new ResponseEntity<PessoaDTO>(pessoaRes, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public PessoaDTO getPessoaId(@PathVariable(name = "id") Long id) {
        Pessoa pessoa = pService.buscaPessoa(id);
        
        PessoaDTO pessoaRes = modelMapper.map(pessoa, PessoaDTO.class);
        
        return pessoaRes;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePessoaId(@PathVariable(name = "id") Long id) {
        pService.deletaPessoa(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public PessoaDTO atualizaPessoaId(@PathVariable(name = "id") Long id, @RequestBody PessoaDTO pessoaDTO) {
        Pessoa pessoaReq = modelMapper.map(pessoaDTO, Pessoa.class);

        Pessoa pessoa = pService.updatePessoa(id, pessoaReq);
        
        PessoaDTO pessoaRes = modelMapper.map(pessoa, PessoaDTO.class);
        
        return pessoaRes;
    }

    @PatchMapping("/{id}")
    public PessoaDTO atualizaCampoPessoaId(@PathVariable(name = "id") Long id, @RequestBody Map<String, Object> fields) {
        Pessoa pessoa_ini = pService.buscaPessoa(id);
        PessoaDTO pessoaReq = modelMapper.map(pessoa_ini, PessoaDTO.class);
        
        fields.forEach((k, v) -> {
            Field field = ReflectionUtils.findField(PessoaDTO.class, k);
            field.setAccessible(true);
            ReflectionUtils.setField(field, pessoaReq, v);
        });

        Pessoa pessoaRes = modelMapper.map(pessoaReq, Pessoa.class);
        
        Pessoa atualizada = pService.updatePessoa(id, pessoaRes);
        PessoaDTO pessoa_att = modelMapper.map(atualizada, PessoaDTO.class);
        
        return pessoa_att;
    }
}
