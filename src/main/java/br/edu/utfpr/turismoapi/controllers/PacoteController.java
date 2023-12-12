package br.edu.utfpr.turismoapi.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.turismoapi.dto.PacoteDTO;
import br.edu.utfpr.turismoapi.models.Pacote;
import br.edu.utfpr.turismoapi.models.Pacote.EstadoPacote;
import br.edu.utfpr.turismoapi.models.Passeio;
import br.edu.utfpr.turismoapi.repositories.PacoteRepository;
import br.edu.utfpr.turismoapi.repositories.PasseioRepository;

@RestController
@RequestMapping("/pacotes")
public class PacoteController {

    @Autowired
    private PacoteRepository pacoteRepository;

    @Autowired
    private PasseioRepository passeioRepository;

    @GetMapping
    public List<PacoteDTO> listarPacotes() {
        List<Pacote> pacotes = pacoteRepository.findAll();
        return pacotes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacoteDTO> buscarPacotePorId(@PathVariable UUID id) {
        return pacoteRepository.findById(id)
                .map(pacote -> ResponseEntity.ok(convertToDto(pacote)))
                .orElse(ResponseEntity.notFound().build());
    }

@PostMapping
public ResponseEntity<PacoteDTO> criarPacote(@RequestBody PacoteDTO pacoteDTO) {
    Pacote pacote = convertToEntity(pacoteDTO);
    pacote.setEstado(EstadoPacote.DISPONIVEL);
    Pacote savedPacote = pacoteRepository.save(pacote);
    return ResponseEntity.status(201).body(convertToDto(savedPacote));
}

    @PutMapping("/{id}")
    public ResponseEntity<PacoteDTO> atualizarPacote(@PathVariable UUID id, @RequestBody PacoteDTO pacoteDTO) {
        Optional<Pacote> optionalPacote = pacoteRepository.findById(id);
        
        if (optionalPacote.isPresent()) {
            Pacote existingPacote = optionalPacote.get();
            updatePacoteFromDTO(existingPacote, pacoteDTO);
            existingPacote.setEstado(pacoteDTO.getEstado());
            
            // Use save method to handle both save and update operations
            Pacote savedPacote = pacoteRepository.save(existingPacote);
            System.out.println(savedPacote);
            System.out.println(existingPacote);
            System.out.println(pacoteDTO);
            return ResponseEntity.ok(convertToDto(savedPacote));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


@DeleteMapping("/{id}")
public ResponseEntity<Void> excluirPacote(@PathVariable UUID id) {
    return pacoteRepository.findById(id)
            .map(pacote -> {
                pacoteRepository.delete(pacote);
                return ResponseEntity.noContent().<Void>build();
            })
            .orElse(ResponseEntity.notFound().build());
}

private PacoteDTO convertToDto(Pacote pacote) {
    PacoteDTO pacoteDTO = new PacoteDTO();
    pacoteDTO.setId(pacote.getId());
    pacoteDTO.setNome(pacote.getNome());
    pacoteDTO.setPreco(pacote.getPreco());
    pacoteDTO.setUsername(pacote.getUsername());
    pacoteDTO.setEstado(pacote.getEstado());

    // Força a inicialização da coleção antes de sair do contexto da transação
    pacote.getTours().size();

    pacoteDTO.setTourIds(pacote.getTours().stream()
            .map(Passeio::getId)
            .collect(Collectors.toList()));
    return pacoteDTO;
}


private Pacote convertToEntity(PacoteDTO pacoteDTO) {
    Pacote pacote = new Pacote();
    pacote.setNome(pacoteDTO.getNome());
    pacote.setPreco(pacoteDTO.getPreco());
    pacote.setUsername(pacoteDTO.getUsername());
    List<Passeio> tours = passeioRepository.findAllById(pacoteDTO.getTourIds());
    pacote.setTours(tours);
    return pacote;
}

    private void updatePacoteFromDTO(Pacote pacote, PacoteDTO pacoteDTO) {
        pacote.setNome(pacoteDTO.getNome());
        pacote.setPreco(pacoteDTO.getPreco());
        pacote.setUsername(pacoteDTO.getUsername());
        
        // Buscar passeios pelo ID e atualizar a lista de passeios do pacote
        List<Passeio> tours = passeioRepository.findAllById(pacoteDTO.getTourIds());
        pacote.setTours(tours);
    }
}
