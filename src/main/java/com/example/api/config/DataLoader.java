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
                categoriasRepo.save(new Categoria(null, "RPG"));
                categoriasRepo.save(new Categoria(null, "Acción"));
                categoriasRepo.save(new Categoria(null, "Metroidvania"));
                categoriasRepo.save(new Categoria(null, "Aventura"));
                categoriasRepo.save(new Categoria(null, "Shooter"));
                categoriasRepo.save(new Categoria(null, "Deportes"));
                categoriasRepo.save(new Categoria(null, "Exploración"));
            }
            if (productosRepo.count() == 0) {

                productosRepo.save(new Productos(
                        null,
                        "Persona 3: Reload",
                        "Persona 3 Reload es un remake del RPG de 2006 Persona 3.",
                        "/images/pic01.jpg",
                        18500.0,
                        2,
                        "RPG"
                ));

                productosRepo.save(new Productos(
                        null,
                        "God of War: Ragnarok",
                        "Secuela de God Of War de kratos y su hijo.",
                        "/images/pic02.jpg",
                        25500.0,
                        4,
                        "Acción"
                ));

                productosRepo.save(new Productos(
                        null,
                        "SilkSong",
                        "Secuela de Hollow Knight controlas a hornet.",
                        "/images/pic03.jpg",
                        10500.0,
                        1,
                        "Metroidvania"
                ));

                productosRepo.save(new Productos(
                        null,
                        "Lego: Batman",
                        "Juego de aventuras de lego donde eres batman.",
                        "/images/pic04.jpg",
                        5500.0,
                        3,
                        "Aventura"
                ));

                productosRepo.save(new Productos(
                        null,
                        "Elden Ring",
                        "Juego de mundo abierto donde debes derrotar bosses.",
                        "/images/pic05.jpg",
                        30000.0,
                        5,
                        "RPG"
                ));

                productosRepo.save(new Productos(
                        null,
                        "Sekiro",
                        "Acción intensa ambientada en Japón samurai insano.",
                        "/images/pic06.jpg",
                        22000.0,
                        9,
                        "Acción"
                ));

                productosRepo.save(new Productos(
                        null,
                        "Call Of Duty BO2",
                        "Clásico shooter de la saga Call Of Duty.",
                        "/images/blackOPs.jpg",
                        19990.0,
                        2,
                        "Shooter"
                ));

                productosRepo.save(new Productos(
                        null,
                        "2K26",
                        "Nueva edición del simulador NBA de 2K.",
                        "/images/2K26.jpg",
                        40000.0,
                        3,
                        "Deportes"
                ));

                productosRepo.save(new Productos(
                        null,
                        "No Man's Sky",
                        "Exploración espacial en universo infinito.",
                        "/images/nomansky.jpg",
                        15000.0,
                        4,
                        "Exploración"
                ));
            }
        };
    }
}
