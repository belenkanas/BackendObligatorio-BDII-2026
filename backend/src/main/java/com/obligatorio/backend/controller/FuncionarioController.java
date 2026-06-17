package com.obligatorio.backend.controller;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.obligatorio.backend.model.Evento;
import com.obligatorio.backend.model.Funcionario;
import com.obligatorio.backend.model.FuncionarioAsignadoASector;
import com.obligatorio.backend.repository.EventoRepository;
import com.obligatorio.backend.service.FuncionarioService;
@RestController
@RequestMapping("/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {
    @Autowired
private FuncionarioService funcionarioService;
    @Autowired
private com.obligatorio.backend.service.FuncionarioAsignadoASectorService funcionarioAsignadoASectorService;
    @Autowired
private EventoRepository eventoRepository;
    @GetMapping
public List<Funcionario> obtenerTodos() {
return funcionarioService.obtenerTodos();
    }
    @GetMapping("/{id}")
public Optional<Funcionario> obtenerPorId(@PathVariable Integer id) {
return funcionarioService.obtenerPorId(id);
    }
    @GetMapping("/legajo/{nroLegajo}")
public Optional<Funcionario> obtenerPorNroLegajo(@PathVariable String nroLegajo) {
return funcionarioService.obtenerPorNroLegajo(nroLegajo);
    }
    @PostMapping
public Funcionario crear(@RequestBody Funcionario funcionario) {
return funcionarioService.crear(funcionario);
    }
    @DeleteMapping("/{id}")
public void eliminar(@PathVariable Integer id) {
funcionarioService.eliminar(id);
    }
    @GetMapping("/{id}/eventos")
public List<FuncionarioAsignadoASector> obtenerEventosAsignados(
            @PathVariable Integer id) {
Funcionario funcionario = funcionarioService
                .obtenerPorId(id)
                .orElseThrow();
return funcionarioAsignadoASectorService
                .obtenerPorLegajo(funcionario.getNroLegajo());
    }

    @GetMapping("/{id}/asignaciones")
    public ResponseEntity<?> obtenerAsignaciones(@PathVariable Integer id) {
        Optional<Funcionario> funcionarioOpt = funcionarioService.obtenerPorId(id);
        if (funcionarioOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Funcionario no encontrado");
        }

        String nroLegajo = funcionarioOpt.get().getNroLegajo();
        List<FuncionarioAsignadoASector> asignaciones = funcionarioAsignadoASectorService.obtenerPorLegajo(nroLegajo);

        Map<String, Map<String, Object>> eventosPorClave = new LinkedHashMap<>();

        for (FuncionarioAsignadoASector a : asignaciones) {
            String estadioNombre = a.getId().getEstadioNombre();
            String estadioPais = a.getId().getEstadioDireccionPais();
            String estadioCiudad = a.getId().getEstadioDireccionCiudad();
            java.time.LocalDateTime fecha = a.getId().getFechaHoraPartido();

            List<Evento> eventosDelEstadio = eventoRepository.findByEstadio(estadioNombre, estadioPais, estadioCiudad);
            Evento eventoMatch = eventosDelEstadio.stream()
                .filter(e -> e.getId().getFechaHoraPartido().equals(fecha))
                .findFirst()
                .orElse(null);

            if (eventoMatch == null) continue;

            String clave = estadioNombre + "|" + estadioPais + "|" + estadioCiudad + "|" + fecha;

            Map<String, Object> eventoData = eventosPorClave.computeIfAbsent(clave, k -> {
                Map<String, Object> m = new LinkedHashMap<>();
                m.put("evento", eventoMatch);
                m.put("sectores", new ArrayList<Map<String, Object>>());
                return m;
            });

            Map<String, Object> sectorInfo = new LinkedHashMap<>();
            sectorInfo.put("nombreSector", a.getId().getNombreSector());
            sectorInfo.put("idDispositivoEscaneo", a.getIdDispositivoEscaneo());

            @SuppressWarnings("unchecked")
            List<Map<String, Object>> sectoresList = (List<Map<String, Object>>) eventoData.get("sectores");
            sectoresList.add(sectorInfo);
        }

        return ResponseEntity.ok(eventosPorClave.values());
    }
}