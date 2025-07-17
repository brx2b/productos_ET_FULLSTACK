package com.perfulandia.productservice.Controller;


import com.perfulandia.productservice.controller.ProductoController;
import com.perfulandia.productservice.model.Producto;
import com.perfulandia.productservice.service.ProductoService;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.context.bean.override.mockito.MockitoBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
@WebMvcTest(ProductoController.class)
public class ProductoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductoService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    @DisplayName("Testing Controller 1 - Obtener todo")
    void testGetAll() throws Exception {
        when(service.listar()).thenReturn(List.of(new Producto(1L, "Carolina Herrera",25000,20)));

        mockMvc.perform(get("/api/productos"))
                .andExpect(status().isOk())//código 200
                .andExpect(jsonPath("$[0].nombre").value("Carolina Herrera"));
    }
    //POST
    @Test
    @DisplayName("Testing Controller 2 - Guardar POST")
    void testPost() throws Exception{

        Producto v = new Producto( 0, "Paco Rabanne Phantom", 55000,10);

        when(service.guardar(any())).thenReturn(new Producto(1L,"Dior", 84990,5));

        mockMvc.perform(post("/api/productos")
                        .contentType("application/json")//indicar que el contenido es JSON
                        .content(mapper.writeValueAsString(v)))// Convertimos el objeto JSON
                .andExpect(status().isOk()) //200
                .andExpect(jsonPath("$.nombre").value("Dior"));
    }


    @Test
    @DisplayName("Testing Controller 3 - DELETE")
    void testDelete() throws Exception {
        doNothing().when(service).eliminar(1L);

        mockMvc.perform(delete("/api/productos/1"))
                .andExpect(status().isOk());
    }


}
