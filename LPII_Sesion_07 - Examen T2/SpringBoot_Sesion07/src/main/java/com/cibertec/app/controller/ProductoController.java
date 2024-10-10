package com.cibertec.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cibertec.app.entity.Producto;
import com.cibertec.app.service.CategoriaService;
import com.cibertec.app.service.ProductoService;

@Controller
public class ProductoController {
	@Autowired
	ProductoService ProductoService;
	@Autowired
	CategoriaService CategoriaService;
	
	@GetMapping("/producto")
	public String listProducto(Model model) {
		model.addAttribute("productos", ProductoService.getAllProducto());
		return "producto/index";
	}
	@GetMapping("/producto/new")
	public String createProductoForm(Model model) {
		Producto producto = new Producto();
		model.addAttribute("producto", producto);
		model.addAttribute("categoriaList", CategoriaService.getAllCategoria());
		return "/producto/create";
	}
	@PostMapping("/producto")
	public String saveProducto(@ModelAttribute("producto") Producto producto) {
		ProductoService.saveProducto(producto);
		return "redirect:/producto";
	}
	@GetMapping("/producto/edit/{id}")
	public String editProductoForm(@PathVariable Integer idProd, Model model) {
		Producto st = ProductoService.findProductoById(idProd);
		model.addAttribute("producto", st);
		return "producto/edit";
	}
	@PostMapping("/producto/{id}")
	public String deleteProducto(@PathVariable Integer idProd) {
		ProductoService.deleteProductoById(idProd);
		return "redirect:/producto";
	}
}

