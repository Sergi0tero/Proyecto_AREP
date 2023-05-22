package controllers;

import dto.PacienteDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.PacienteService;

@Controller
@RequestMapping("/paciente")
public class APIcontroller {
    PacienteService pacienteService;

    @GetMapping("/{id}")
    public PacienteDto getPerson(@PathVariable String id){
        return pacienteService.getPacienteById(id);
    }

    @PostMapping
    public PacienteDto crearPaciente(@RequestBody PacienteDto pacienteDto){
        pacienteService.createPaciente(pacienteDto);
        return pacienteDto;
    }


    @PutMapping("/{id}")
    public void updateBehaviors(@PathVariable String id, @RequestBody String behaviors){
        pacienteService
    }

}
