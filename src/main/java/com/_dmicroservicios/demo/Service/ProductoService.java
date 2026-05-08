package com._dmicroservicios.demo.Service;
import com._dmicroservicios.demo.Model.Producto;

import java.util.List;

import org.springframework.stereotype.Service;
import com._dmicroservicios.demo.Dto.ProductoDTO;
import com._dmicroservicios.demo.Repository.ProductoRepository;

@Service
public class ProductoService {
    
    
    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    public Producto registrarProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto(productoDTO.getNombre(), productoDTO.getDescripcion(), productoDTO.getPrecio(), productoDTO.getStock(), productoDTO.getCategoria(), true);
        return productoRepository.save(producto);
    }


     public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }



    public Producto actualizarProducto(Long id, ProductoDTO productoDTO) {
        Producto productoExistente = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        productoExistente.setNombre(productoDTO.getNombre());
        productoExistente.setDescripcion(productoDTO.getDescripcion());
        productoExistente.setPrecio(productoDTO.getPrecio());
        productoExistente.setStock(productoDTO.getStock());
        productoExistente.setCategoria(productoDTO.getCategoria());
        return productoRepository.save(productoExistente);
    }

    public void eliminarProducto(Long id) {
        Producto productoExistente = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        productoExistente.setActivo(false);
        productoRepository.save(productoExistente);
    }

    public Producto obtenerProductoPorId(Long id) {
        return productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto obtenerProductoPorNombre(String nombre) {
        return productoRepository.findByNombreProducto(nombre);
    }

    public Producto obtenerProductoPorCategoria(String categoria) {
        return productoRepository.findByCategoriaProducto(categoria);
    }





}
