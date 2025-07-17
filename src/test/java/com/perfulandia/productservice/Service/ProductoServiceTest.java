package com.perfulandia.productservice.Service;
import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.repository.ProductoRepository;

import com.perfulandia.productservice.service.ProductoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//Librerías para usar mockito
import org.mockito.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
public class ProductoServiceTest {
    @InjectMocks
    private ProductoService service;
    //Creando un mock del repositorio //objeto simulado
    @Mock
    private ProductoRepository repo;

    //Constructor para inicializar test antes de cada prueba
    public ProductoServiceTest(){

        //Abre e inializa los mocks anotados con @Mock y @InjectMocks
        MockitoAnnotations.openMocks(this);
    }
    @Test
    @DisplayName("Testing 1 - FindAll Service")
    void testFindAll(){
        //Simular la creación de un objeto de videojuego
        when(repo.findAll()).thenReturn(List.of(new Producto(1L,"Valenti Born",40000,35)));
        //Llamar al metodo de servicio que sera probado
        List<Producto> resultado =  service.listar();
        //Verificacion
        assertEquals(1, resultado.size());
    }

}
