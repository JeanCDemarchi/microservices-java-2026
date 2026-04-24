package br.edu.atitus.productservice.controllers;

import br.edu.atitus.productservice.entities.ProductEntity;
import br.edu.atitus.productservice.repositories.ProductRepository;
import br.edu.atitus.productservice.dtos.ProductDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    // GET - listar todos
    @GetMapping
    public List<ProductDTO> getAll() {
        return repository.findAll().stream()
                .map(p -> new ProductDTO(
                        p.getId(),
                        p.getDescription(),
                        p.getBrand(),
                        p.getModel(),
                        p.getPrice(),
                        p.getCurrency(),
                        p.getStock(),
                        "local",
                        p.getPrice(),
                        p.getCurrency()
                ))
                .collect(Collectors.toList());
    }

    // POST - criar produto
    @PostMapping
    public ProductEntity create(@RequestBody ProductEntity product) {
        return repository.save(product);
    }
    // GET por ID com query param (OBRIGATÓRIO)
    @GetMapping("/{idproduct}")
    public ProductDTO getById(
            @PathVariable Long idproduct,
            @RequestParam String targetCurrency
    ) {

        ProductEntity product = repository.findById(idproduct)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return new ProductDTO(
                product.getId(),
                product.getDescription(),
                product.getBrand(),
                product.getModel(),
                product.getPrice(),
                product.getCurrency(),
                product.getStock(),
                "Product-service running on Port: 8080",
                null,
                targetCurrency
        );
    }
}
