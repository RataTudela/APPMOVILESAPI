package com.example.api.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.api.model.Categoria;
import com.example.api.model.Productos;
import com.example.api.model.Usuario;
import com.example.api.repository.CategoriaRepository;
import com.example.api.repository.ProductosRepository;
import com.example.api.repository.UsuarioRepository;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(
            ProductosRepository productosRepo,
            CategoriaRepository categoriasRepo,
            UsuarioRepository usuarioRepo
    ) {
        return args -> {
            if (!usuarioRepo.existsByEmail("admin@duocuc.cl")) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador");
                admin.setEmail("admin@duocuc.cl");
                admin.setContrasena("admin123");
                admin.setTelefono("000000000");
                admin.setRegion("Santiago");
                admin.setComuna("Maipú");

                usuarioRepo.save(admin);
            }
            if (categoriasRepo.count() == 0) {
                categoriasRepo.save(new Categoria(null, "Ropa"));
                categoriasRepo.save(new Categoria(null, "Skate"));
                categoriasRepo.save(new Categoria(null, "Tabla"));
                categoriasRepo.save(new Categoria(null, "Ruedas"));
            }
            if (productosRepo.count() == 0) {

                productosRepo.save(new Productos(
                        null,
                        "Foundation - Tabla Star y Moon",
                        "Solo la Tabla de skate Foundation Star, Color Amarillo.",
                        "https://wallride.cl/cdn/shop/files/WhatsAppImage2025-11-18at16.31.29_360x.jpg?v=1763494887",
                        18500.0,
                        2,
                        "Tabla"
                ));

                productosRepo.save(new Productos(
                        null,
                        "Foundation - Skate Completo",
                        "Tabla de skate completa Foundation Oval Blue, Color Azul.",
                        "https://wallride.cl/cdn/shop/files/oval-blue-complete_360x.png?v=1759856249",
                        84500.0,
                        4,
                        "Skate"
                ));

                productosRepo.save(new Productos(
                        null,
                        "Foundation - Tabla Star y Moon",
                        "Tabla de skate Foundation Star, Color Verde.",
                        "https://wallride.cl/cdn/shop/files/WhatsAppImage2025-11-18at16.20.31_360x.jpg?v=1763593055",
                        60500.0,
                        1,
                        "Tabla"
                ));

                productosRepo.save(new Productos(
                        null,
                        "Creature - Camisa Manga Larga Reversible.",
                        "Camisa de manga larga reversible Creature Switch.",
                        "https://wallride.cl/cdn/shop/files/44643733_156577_80474_Switch_Reversible_Creature_Mens_Shirt_color_black_green_1000x_dbd37117-72f7-48e9-b5d0-42c1828b4910_360x.webp?v=1762886591",
                        15500.0,
                        3,
                        "Ropa"
                ));

                productosRepo.save(new Productos(
                        null,
                        "Slimeball - Ruedas OG Classic 99A",
                        "Ruedas Slimeball OG Classic 99A Verdes Fosforecente.",
                        "https://wallride.cl/cdn/shop/files/63753_1800x_5e43b02b-9f7f-4e76-94b1-bbe4f57528bb_360x.webp?v=1762875961",
                        30000.0,
                        5,
                        "Ruedas"
                ));

                productosRepo.save(new Productos(
                        null,
                        "Slimeball - Ruedas OG White 101A",
                        "Ruedas Slimeball OG White 101A Blanco Perlado.",
                        "https://wallride.cl/cdn/shop/files/images_11_b1e6d6b4-b44d-4b09-b821-55b503e286fb_360x.jpg?v=1762877253",
                        35000.0,
                        9,
                        "Ruedas"
                ));

                productosRepo.save(new Productos(
                        null,
                        "Santa Cruz - Poleron Roskopp Dissect",
                        "Poleron Santa Cruz Roskopp Dissect Hoodie Cement.",
                        "https://wallride.cl/cdn/shop/files/44252766_157221_80288_Roskopp_Dissect_Pullover_Hoodie_Santa_Cruz_Mens_Sweatshirt_color_cement_1800x_04dddcc4-8701-4a42-b188-616ce25bd5b2_360x.webp?v=1762874999",
                        29990.0,
                        2,
                        "Ropa"
                ));

                productosRepo.save(new Productos(
                        null,
                        "PILL - Tabla Completa Stay Free 1",
                        "Tabla de skate completa PILL Stay Free Blue.",
                        "https://wallride.cl/cdn/shop/products/banana-friends-complete-b2b_1800x1800.jpg?v=1628519727",
                        40000.0,
                        3,
                        "Skate"
                ));

                productosRepo.save(new Productos(
                        null,
                        "RIPNDIP - Poleron Lord Nermal",
                        "Poleron RIPNDIP Lord Nermal Hoodie Leco Fossile.",
                        "https://wallride.cl/cdn/shop/files/IMG_2967_4000x4000_e967b2a0-6c53-43f5-a197-8e93efd06499_360x.webp?v=1763651965",
                        15000.0,
                        4,
                        "Ropa"
                ));
            }
        };
    }
}
