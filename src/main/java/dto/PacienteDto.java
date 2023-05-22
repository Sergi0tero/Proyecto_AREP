package dto;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class PacienteDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private int edad;
    private int lugarDeNacimiento;
    private String correo;
    private int celular;
    private String happy;
    private String surprised;
    private String sad;

    public PacienteDto(String name, int edad, int lugarDeNacimiento, String correo, int celular) {
        this.name = name;
        this.edad = edad;
        this.lugarDeNacimiento = lugarDeNacimiento;
        this.correo = correo;
        this.celular = celular;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getLugarDeNacimiento() {
        return lugarDeNacimiento;
    }

    public void setLugarDeNacimiento(int lugarDeNacimiento) {
        this.lugarDeNacimiento = lugarDeNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getHappy() {
        return happy;
    }

    public void setHappy(String happy) {
        this.happy = happy;
    }

    public String getSurprised() {
        return surprised;
    }

    public void setSurprised(String surprised) {
        this.surprised = surprised;
    }

    public String getSad() {
        return sad;
    }

    public void setSad(String sad) {
        this.sad = sad;
    }
}
