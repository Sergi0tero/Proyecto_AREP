package com.example.demo.controllers;

import com.google.gson.Gson;
import com.example.demo.dto.PacienteDto;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import com.example.demo.services.PacienteService;


@RestController
@RequestMapping("/paciente")
public class APIcontroller {

    @Autowired
    PacienteService pacienteService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getPerson(@PathVariable String id){
        return new ResponseEntity<>(new Gson().toJson(pacienteService.getPacienteById(id)), HttpStatus.ACCEPTED);
    }

    @PostMapping
    public ResponseEntity<?> crearPaciente(@RequestBody PacienteDto pacienteDto){
        pacienteService.createPaciente(pacienteDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public void updateBehaviors(@PathVariable String id, @RequestBody String behaviors){
         pacienteService.updateBehaviors(id, new JSONObject(behaviors));
    }

}
