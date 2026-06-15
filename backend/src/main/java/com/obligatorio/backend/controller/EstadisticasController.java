package com.obligatorio.backend.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.Entrada;
import com.obligatorio.backend.model.TokenEscaneadoValido;
import com.obligatorio.backend.model.Venta;
import com.obligatorio.backend.repository.EntradaRepository;
import com.obligatorio.backend.repository.TokenEscaneadoValidoRepository;
import com.obligatorio.backend.repository.VentaRepository;

@RestController
@RequestMapping("/estadisticas")
@CrossOrigin(origins = "*")
public class EstadisticasController {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private TokenEscaneadoValidoRepository tokenEscaneadoRepository;

    @Autowired
    private VentaRepository ventaRepository;

    /**
     * 1. Entradas vendidas por evento
     * GET /estadisticas/entradas-por-evento
     * Retorna: [{ evento: "...", totalEntradas: N }]
     */
    @GetMapping("/entradas-por-evento")
    public ResponseEntity<?> entradasPorEvento() {
        List<Entrada> todas = entradaRepository.findAll();

        Map<String, Long> agrupadas = todas.stream()
            .collect(Collectors.groupingBy(e -> {
                String local = e.getNombrePaisEquipoLocal() != null ? e.getNombrePaisEquipoLocal() : "?";
                String visitante = e.getNombrePaisEquipoVisitante() != null ? e.getNombrePaisEquipoVisitante() : "?";
                String fecha = e.getFechaHoraPartido() != null ? e.getFechaHoraPartido().toLocalDate().toString() : "?";
                return local + " vs " + visitante + " (" + fecha + ")";
            }, Collectors.counting()));

        List<Map<String, Object>> resultado = agrupadas.entrySet().stream()
            .map(entry -> Map.<String, Object>of(
                "evento", entry.getKey(),
                "totalEntradas", entry.getValue()
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    /**
     * 2. Entradas escaneadas por funcionario
     * GET /estadisticas/escaneos-por-funcionario
     * Retorna: [{ nroLegajo: "...", totalEscaneos: N }]
     */
    @GetMapping("/escaneos-por-funcionario")
    public ResponseEntity<?> escaneosPorFuncionario() {
        List<TokenEscaneadoValido> todos = tokenEscaneadoRepository.findAll();

        Map<String, Long> agrupados = todos.stream()
            .collect(Collectors.groupingBy(
                t -> t.getId().getNroLegajoFuncionario(),
                Collectors.counting()
            ));

        List<Map<String, Object>> resultado = agrupados.entrySet().stream()
            .map(entry -> Map.<String, Object>of(
                "nroLegajo", entry.getKey(),
                "totalEscaneos", entry.getValue()
            ))
            .sorted((a, b) -> Long.compare(
                (Long) b.get("totalEscaneos"),
                (Long) a.get("totalEscaneos")
            ))
            .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    /**
     * 3. Historial de transacciones (todas las ventas)
     * GET /estadisticas/transacciones
     * Retorna lista de ventas completa con id_general
     */
    @GetMapping("/transacciones")
    public ResponseEntity<?> historialTransacciones() {
        List<Venta> ventas = ventaRepository.findAll();

        List<Map<String, Object>> resultado = ventas.stream()
            .map(v -> Map.<String, Object>of(
                "idVenta", v.getIdVenta() != null ? v.getIdVenta() : 0,
                "fechaHora", v.getFechaHora() != null ? v.getFechaHora().toString() : "",
                "costoFinal", v.getCostoFinal() != null ? v.getCostoFinal() : 0,
                "comision", v.getComision() != null ? v.getComision() : 0,
                "estado", v.getEstado() != null ? v.getEstado() : "",
                "idGeneral", v.getGeneral() != null && v.getGeneral().getId_general() != null
                    ? v.getGeneral().getId_general() : 0
            ))
            .sorted((a, b) -> {
                String fa = (String) a.get("fechaHora");
                String fb = (String) b.get("fechaHora");
                return fb.compareTo(fa);
            })
            .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    /**
     * 4. Ranking de mayores compradores (por cantidad de entradas)
     * GET /estadisticas/ranking-compradores
     * Retorna: [{ idGeneral: N, totalEntradas: N }] ordenado desc
     */
    @GetMapping("/ranking-compradores")
    public ResponseEntity<?> rankingCompradores() {
        List<Entrada> todas = entradaRepository.findAll();

        Map<Integer, Long> porGeneral = todas.stream()
            .filter(e -> e.getIdGeneralPropietario() != null)
            .collect(Collectors.groupingBy(
                Entrada::getIdGeneralPropietario,
                Collectors.counting()
            ));

        List<Map<String, Object>> resultado = porGeneral.entrySet().stream()
            .map(entry -> Map.<String, Object>of(
                "idGeneral", entry.getKey(),
                "totalEntradas", entry.getValue()
            ))
            .sorted((a, b) -> Long.compare(
                (Long) b.get("totalEntradas"),
                (Long) a.get("totalEntradas")
            ))
            .limit(20)
            .collect(Collectors.toList());

        return ResponseEntity.ok(resultado);
    }

    /**
     * 5. Entradas de un evento específico
     * GET /estadisticas/entradas-por-evento/{local}/{visitante}
     */
    @GetMapping("/entradas-por-evento/{local}/{visitante}")
    public ResponseEntity<?> entradasDeEvento(
            @PathVariable String local,
            @PathVariable String visitante) {

        List<Entrada> todas = entradaRepository.findAll();
        long total = todas.stream()
            .filter(e -> local.equalsIgnoreCase(e.getNombrePaisEquipoLocal())
                      && visitante.equalsIgnoreCase(e.getNombrePaisEquipoVisitante()))
            .count();

        return ResponseEntity.ok(Map.of(
            "evento", local + " vs " + visitante,
            "totalEntradas", total
        ));
    }
}