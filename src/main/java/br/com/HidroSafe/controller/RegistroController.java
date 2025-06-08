package br.com.HidroSafe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.HidroSafe.model.Registro;
import br.com.HidroSafe.repository.RegistroRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/registros")
@Slf4j
public class RegistroController {
    
    @Autowired
    private RegistroRepository repository;

    @GetMapping
    public Page<Registro> index(
        @PageableDefault(size = 3, sort = "nivelAgua", direction = Direction.ASC) Pageable pageable) {

        return repository.findAll(pageable);
    }

    @GetMapping("{id}")
    public Registro get(@PathVariable Long id) {
        log.info("buscando registro " + id);
        return getRegistro(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Registro post(@RequestBody @Valid Registro registro) {
        log.info("cadastrando registro " + registro.getId());
        
        return repository.save(registro);
    }

    @PutMapping("{id}")
    public Registro update(@PathVariable Long id, @RequestBody @Valid Registro registro) {
        log.info("atualizando registro " + id + " para " + registro);
        registro.setId(id);

        return repository.save(registro);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("deletando registro " + id);
        repository.delete(getRegistro(id));
    }

    private Registro getRegistro(Long id) {
        return repository
                    .findById(id)
                    .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
                    );
    }
}
