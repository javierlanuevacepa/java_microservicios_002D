package com._dmicroservicios.demo.Service;
import com._dmicroservicios.demo.Model.AuditoriaEvent;
import com._dmicroservicios.demo.Model.Producto;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;
import com._dmicroservicios.demo.Dto.ProductoDTO;
import com._dmicroservicios.demo.Repository.ProductoRepository;

@Service
public class ProductoService {
    
    
    private final ProductoRepository productoRepository;
    private final SqsService sqsService;

    public ProductoService(ProductoRepository productoRepository, SqsService sqsService) {
        this.productoRepository = productoRepository;
        this.sqsService = sqsService;
    }


    public Producto registrarProducto(ProductoDTO productoDTO) {
        Producto producto = new Producto(productoDTO.getNombre(), productoDTO.getDescripcion(), productoDTO.getPrecio(), productoDTO.getStock(), productoDTO.getCategoria(), true);

        AuditoriaEvent evento = new AuditoriaEvent(
        "CREAR",
        producto.getId(),
        "admin@techstore.cl",
        Instant.now()
        );
    sqsService.enviarAuditoria(evento);


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


        AuditoriaEvent evento = new AuditoriaEvent(
        "ACTUALIZAR",
        productoExistente.getId(),
        "admin@techstore.cl",
        Instant.now()
        );
    sqsService.enviarAuditoria(evento);



        return productoRepository.save(productoExistente);
    }

    public void eliminarProducto(Long id) {
        Producto productoExistente = productoRepository.findById(id).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        productoExistente.setActivo(false);

        AuditoriaEvent evento = new AuditoriaEvent(
        "ELIMINAR",
        productoExistente.getId(),
        "admin@techstore.cl",
        Instant.now()
        );
       sqsService.enviarAuditoria(evento);


        productoRepository.save(productoExistente);
    }
}
