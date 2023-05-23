package com.example.demo.services;

import com.example.demo.dto.PacienteDto;
import org.json.JSONObject;
import com.example.demo.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;
import java.util.Objects;

@EnableJpaRepositories(basePackageClasses = PacienteRepository.class)
@Service
public class PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;



    public void createPaciente(PacienteDto pacienteDto){
        pacienteRepository.save(pacienteDto);
    }

    public List<PacienteDto> getPacientes(){
        return pacienteRepository.findAll();
    }

    public PacienteDto getPacienteById(String id){
        return pacienteRepository.findById(Integer.parseInt(id)).get();
    }

    public String getPacientBehaviors(String id){
        PacienteDto pacient = getPacienteById(id);
        return "Happy: " + pacient.getHappy() + ", Sad:" + pacient.getSad() + ", Surprised: " + pacient.getSurprised();
    }

    public void updateBehaviors(String id, JSONObject behaviors){
        PacienteDto paciente = getPacienteById(id);
        for (String name: behaviors.keySet()){
            System.out.println("1");
            if (Objects.equals(name, "Happy")){
                System.out.println("2");
                System.out.println(behaviors.getInt(name));
                paciente.setHappy(behaviors.getInt(name));
            } else if (Objects.equals(name, "Sad")){
                System.out.println("3");
                System.out.println(behaviors.getInt(name));
                paciente.setSad(behaviors.getInt(name));
            } else if (Objects.equals(name, "Surprised")){
                System.out.println("4");
                System.out.println(behaviors.getInt(name));
                paciente.setSurprised(behaviors.getInt(name));
            }

        }
        pacienteRepository.save(paciente);
    }
}
