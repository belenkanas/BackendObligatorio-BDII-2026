package com.obligatorio.backend.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.obligatorio.backend.model.Entrada;
import com.obligatorio.backend.model.General;
import com.obligatorio.backend.model.Sector;
import com.obligatorio.backend.model.SectorEvento;
import com.obligatorio.backend.model.SectorEventoId;
import com.obligatorio.backend.model.SectorId;
import com.obligatorio.backend.model.Venta;
import com.obligatorio.backend.repository.EntradaRepository;
import com.obligatorio.backend.repository.GeneralRepository;
import com.obligatorio.backend.repository.SectorEventoRepository;
import com.obligatorio.backend.repository.SectorRepository;
import com.obligatorio.backend.service.EntradaService;
import com.obligatorio.backend.service.VentaService;


@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @Autowired
    private EntradaService entradaService;

    @Autowired
    private GeneralRepository generalRepository;

    @Autowired
    private SectorEventoRepository sectorEventoRepository;

    @Autowired
    private SectorRepository sectorRepository;

    @Autowired
    private EntradaRepository entradaRepository;

    @GetMapping
    public List<Venta> obtenerTodos() { return ventaService.obtenerTodos(); }

    @GetMapping("/{id}")
    public Optional<Venta> obtenerPorId(@PathVariable Integer id) { return ventaService.obtenerPorId(id); }

    @GetMapping("/usuario/{idGeneral}")
    public List<Venta> obtenerPorGeneral(@PathVariable Integer idGeneral) { return ventaService.obtenerPorGeneral(idGeneral); }

    @PostMapping
    public Venta crear(@RequestBody Venta venta) { return ventaService.crear(venta); }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) { ventaService.eliminar(id); }

    @Transactional
    @PostMapping("/comprar")
    public ResponseEntity<?> comprar(@RequestBody Map<String, Object> datos) {

        Integer idGeneral = (Integer) datos.get("idGeneral");
        String nombreSector = (String) datos.get("nombreSector");
        String estadioNombre = (String) datos.get("estadioNombre");
        String estadioDireccionPais = (String) datos.get("estadioDireccionPais");
        String estadioDireccionCiudad = (String) datos.get("estadioDireccionCiudad");
        String fechaHoraStr = (String) datos.get("fechaHoraPartido");
        String equipoLocal = (String) datos.get("nombrePaisEquipoLocal");
        String equipoVisitante = (String) datos.get("nombrePaisEquipoVisitante");
        Integer cantidad = (Integer) datos.get("cantidad");

        // maximo 5 entradas por transaccion
        if (cantidad == null || cantidad < 1 || cantidad > 5) {
            return ResponseEntity.badRequest().body("La cantidad debe ser entre 1 y 5");
        }

        Optional<General> generalOpt = generalRepository.findById(idGeneral);
        if (generalOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        SectorEventoId sectorEventoId = new SectorEventoId();
        sectorEventoId.setNombreSector(nombreSector);
        sectorEventoId.setEstadioNombre(estadioNombre);
        sectorEventoId.setEstadioDireccionPais(estadioDireccionPais);
        sectorEventoId.setEstadioDireccionCiudad(estadioDireccionCiudad);
        sectorEventoId.setFechaHoraPartido(LocalDateTime.parse(fechaHoraStr));

        Optional<SectorEvento> sectorEventoOpt = sectorEventoRepository.findById(sectorEventoId);
        if (sectorEventoOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Sector o evento no encontrado");
        }

        SectorId sectorId = new SectorId();
        sectorId.setNombre(nombreSector);
        sectorId.setEstadioNombre(estadioNombre);
        sectorId.setEstadioDireccionPais(estadioDireccionPais);
        sectorId.setEstadioDireccionCiudad(estadioDireccionCiudad);

        Optional<Sector> sectorOpt = sectorRepository.findById(sectorId);
        if (sectorOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Sector no encontrado");
        }

        int capacidadMax = sectorOpt.get().getCapacidadMax();
        long entradasVendidas = entradaRepository.countBySectorEvento(
            nombreSector, estadioNombre, estadioDireccionPais,
            estadioDireccionCiudad, LocalDateTime.parse(fechaHoraStr)
        );

        if (entradasVendidas + cantidad > capacidadMax) {
            return ResponseEntity.badRequest().body("No hay suficiente capacidad en el sector");
        }

        BigDecimal costoPorEntrada = sectorEventoOpt.get().getCosto();
        BigDecimal subtotal = costoPorEntrada.multiply(BigDecimal.valueOf(cantidad));
        BigDecimal comision = subtotal.multiply(BigDecimal.valueOf(0.05));
        BigDecimal costoFinal = subtotal.add(comision);

        Venta venta = new Venta();
        venta.setFechaHora(LocalDateTime.now());
        venta.setComision(comision);
        venta.setCostoFinal(costoFinal);
        venta.setEstado("confirmada");
        venta.setGeneral(generalOpt.get());
        venta = ventaService.crear(venta);

        for (int i = 0; i < cantidad; i++) {
            Entrada entrada = new Entrada();
            entrada.setEstado("activo");
            entrada.setCantTransferida(0);
            entrada.setNombreSector(nombreSector);
            entrada.setEstadioNombre(estadioNombre);
            entrada.setEstadioDireccionPais(estadioDireccionPais);
            entrada.setEstadioDireccionCiudad(estadioDireccionCiudad);
            entrada.setFechaHoraPartido(LocalDateTime.parse(fechaHoraStr));
            entrada.setNombrePaisEquipoLocal(equipoLocal);
            entrada.setNombrePaisEquipoVisitante(equipoVisitante);
            entrada.setIdGeneralPropietario(idGeneral);
            entrada.setIdVenta(venta.getIdVenta());
            entradaService.crear(entrada);
        }

        return ResponseEntity.ok(Map.of(
            "mensaje", "Compra realizada correctamente",
            "idVenta", venta.getIdVenta(),
            "costoFinal", costoFinal,
            "cantidad", cantidad
        ));
    }
}
