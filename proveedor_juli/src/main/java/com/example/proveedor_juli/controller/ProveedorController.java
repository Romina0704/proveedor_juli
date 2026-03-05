package com.example.proveedor_juli.controller;

import com.example.proveedor_juli.entity.Proveedor;
import com.example.proveedor_juli.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping
    public String listar(Model model) {
        List<Proveedor> lista = proveedorRepository.findAll();
        model.addAttribute("proveedores", lista);
        return "proveedores";
    }

    @GetMapping("/nuevo")
    public String nuevoProveedor(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "form-proveedor";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Proveedor proveedor) {
        proveedorRepository.save(proveedor);
        return "redirect:/proveedores";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {

        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        model.addAttribute("proveedor", proveedor);
        return "form-proveedor";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        proveedorRepository.deleteById(id);
        return "redirect:/proveedores";
    }
}