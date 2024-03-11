package net.tchoufa.inventoryservice.web;

import lombok.RequiredArgsConstructor;
import net.tchoufa.inventoryservice.entities.Product;
import net.tchoufa.inventoryservice.repository.ProductRepository;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductRestController {
    private final ProductRepository productRepository;

    @GetMapping("/products")
        //@PreAuthorize("hasAuthority('ADMIN')")
    List<Product> getProductList() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
        //@PreAuthorize("hasAuthority('USER')")
    Optional<Product> getProductById(@PathVariable UUID id) {
        return productRepository.findById(id);
    }

    @GetMapping("/security")
    public Authentication authentication(Authentication authentication) {
        return authentication;
    }

}
