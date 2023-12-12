package br.edu.utfpr.turismoapi.controllers;

import br.edu.utfpr.turismoapi.models.Reserva;
import br.edu.utfpr.turismoapi.repositories.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Reserva> buscarReservaPorId(@PathVariable UUID id) {
        return reservaRepository.findById(id);
    }

    @PostMapping
    public Reserva criarReserva(@RequestBody Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @PutMapping("/{id}")
    public Reserva atualizarReserva(@PathVariable UUID id, @RequestBody Reserva reservaAtualizada) {
        reservaAtualizada.setId(id); // Certifique-se de definir o ID correto
        return reservaRepository.save(reservaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void excluirReserva(@PathVariable UUID id) {
        reservaRepository.deleteById(id);
    }
}
