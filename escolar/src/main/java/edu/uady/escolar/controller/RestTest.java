package edu.uady.escolar.controller;

import jakarta.websocket.server.PathParam;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")
@Log4j2
public class RestTest {

    @GetMapping(value = "/saludo")
    public String saludo(){
        return "Hola Mundo";
    }

    @GetMapping(value = "/saludo2/{nombre}")
    public String Saludo2(@PathVariable(name = "nombre") String nombre, @PathParam("apellidos") String apellidos,
    @PathParam("edad") int edad){
        log.info("nombre : "+nombre);
        log.info("apellidos : "+apellidos);
        log.info("Edad: "+edad);
        return "Hola "+nombre +" "+apellidos;
    }
}
