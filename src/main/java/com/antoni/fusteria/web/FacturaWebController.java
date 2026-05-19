package com.antoni.fusteria.web;

import com.antoni.fusteria.domain.model.*;
import com.antoni.fusteria.service.ClientService;
import com.antoni.fusteria.service.FacturaService;
import com.antoni.fusteria.service.TreballService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/factures")
public class FacturaWebController {

    @Autowired private FacturaService facturaService;
    @Autowired private ClientService clientService;
    @Autowired private TreballService treballService;

    @GetMapping
    public String llistat(Model model) {
        model.addAttribute("factures", facturaService.getAllFactures().stream()
                .map(facturaService::toDto).toList());
        return "factures/llistat";
    }

    @GetMapping("/nova")
    public String novaForm(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        model.addAttribute("treballs", treballService.getAllTreballs());
        model.addAttribute("identificacions", IdentificacioClient.values());
        return "factures/formulari";
    }

    @PostMapping("/nova")
    public String crearFactura(
            @RequestParam Long clientId,
            @RequestParam(required = false) List<Long> treballIds,
            @RequestParam IdentificacioClient identificacioClient,
            @RequestParam(required = false) String metodePagament,
            @RequestParam(required = false) String observacions) {
        List<Treball> treballs = new ArrayList<>();
        if (treballIds != null) {
            for (Long id : treballIds) {
                Treball t = treballService.getTreballById(id);
                if (t != null) treballs.add(t);
            }
        }
        Factura factura = new Factura();
        factura.setClient(clientService.getClientById(clientId));
        factura.setTreballs(treballs);
        factura.setIdentificacioClient(identificacioClient);
        factura.setMetodePagament(metodePagament);
        factura.setObservacions(observacions);
        facturaService.createFactura(factura);
        return "redirect:/factures";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarFactura(@PathVariable Long id) {
        facturaService.deleteFactura(id);
        return "redirect:/factures";
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> descarregarPdf(@PathVariable Long id) {
        Factura f = facturaService.getFacturaById(id);
        if (f == null) return ResponseEntity.notFound().build();
        byte[] pdf = facturaService.generarPdfFactura(f);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "factura-" + f.getNumeroFactura() + ".pdf");
        return new ResponseEntity<>(pdf, headers, HttpStatus.OK);
    }
}
