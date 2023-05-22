package services;

import dto.PacienteDto;
import repositories.PacienteRepository;

import java.util.List;

public class PacienteService {

    private PacienteRepository pacienteRepository;

    public void createPaciente(PacienteDto pacienteDto){
        pacienteRepository.save(pacienteDto);
    }

    public void updateBehaviors(String id, String behaviors){
        for()
    }

    public List<PacienteDto> getPacientes(){
        return pacienteRepository.findAll();
    }

    public PacienteDto getPacienteById(String id){
        return pacienteRepository.findById(Long.parseLong(id)).get();
    }

    public String getPacientBehaviors(String id){
        PacienteDto pacient = getPacienteById(id);
        return "Happy: " + pacient.getHappy() + ", Sad:" + pacient.getSad() + ", Surprised: " + pacient.getSurprised();
    }
}
