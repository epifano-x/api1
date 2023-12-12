package br.edu.utfpr.turismoapi.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.turismoapi.dto.PessoaDTO;
import br.edu.utfpr.turismoapi.models.Pessoa;
import br.edu.utfpr.turismoapi.repositories.PessoaRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
    @Autowired
    PessoaRepository PessoaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/pages")
    public ResponseEntity<Page<Pessoa>> getAllPage(
            @PageableDefault(page = 0, size = 10, sort = "nome", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok()
                .body(PessoaRepository.findAll(pageable));
    }

    /**
     * Obter todas as pessoas do banco.
     */
    @GetMapping(value = { "", "/" })
    public List<Pessoa> getAll() {
        return PessoaRepository.findAll();
    }

    /**
     * Obter 1 pessoa pelo ID
     */
    @GetMapping("/{id}")
    @Operation(description = "abc")
    public ResponseEntity<Object> getById(@PathVariable String id) {
        Optional<Pessoa> PessoaOpt = PessoaRepository
                .findById(UUID.fromString(id));

        return PessoaOpt.isPresent()
                ? ResponseEntity.ok(PessoaOpt.get())
                : ResponseEntity.notFound().build();
    }

    /**
     * Criar uma pessoa na API
     */
    @PostMapping("")
    public ResponseEntity<Object> create(@Valid @RequestBody PessoaDTO pessoaDTO) {
        var pes = new Pessoa(); // Pessoa para persistir no DB
        BeanUtils.copyProperties(pessoaDTO, pes);

        pes.setPassword(passwordEncoder.encode(pessoaDTO.getPassword()));

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(PessoaRepository.save(pes));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao criar pessoa");
        }
    }

    /**
     * Atualizar 1 pessoa pelo ID
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable String id,
            @RequestBody PessoaDTO PessoaDTO) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Formato de UUID inválido");
        }

        // Buscando a pessoa no banco de dados
        var Pessoa = PessoaRepository.findById(uuid);

        // Verifica se ela existe
        if (Pessoa.isEmpty())
            return ResponseEntity.notFound().build();

        var PessoaToUpdate = Pessoa.get();
        BeanUtils.copyProperties(PessoaDTO, PessoaToUpdate);
        PessoaToUpdate.setUpdatedAt(LocalDateTime.now());

        try {
            return ResponseEntity.ok()
                    .body(PessoaRepository.save(PessoaToUpdate));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao atualizar pessoa");
        }
    }

    /**
     * Deletar uma pessoa pelo ID
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id) {
        UUID uuid;
        try {
            uuid = UUID.fromString(id);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body("Formato de UUID inválido");
        }

        var Pessoa = PessoaRepository.findById(uuid);

        if (Pessoa.isEmpty())
            return ResponseEntity.notFound().build();

        try {
            PessoaRepository.delete(Pessoa.get());
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return errors;
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Object> getByEmail(@PathVariable String email) {
    Optional<Pessoa> pessoaOpt = PessoaRepository.findByEmail(email);

    return pessoaOpt.isPresent()
            ? ResponseEntity.ok(pessoaOpt.get())
            : ResponseEntity.notFound().build();
    }

}