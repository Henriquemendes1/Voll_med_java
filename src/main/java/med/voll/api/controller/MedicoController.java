package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedicoDTO;
import med.voll.api.medico.DadosListagemMedicoDTO;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados){
        repository.save(new Medico(dados));
    }

    @GetMapping
    public Page<DadosListagemMedicoDTO> listar(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedicoDTO::new);
    }

}
