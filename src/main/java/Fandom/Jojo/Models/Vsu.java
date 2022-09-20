package Fandom.Jojo.Models;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "vsu")
public class Vsu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 2",min = 2,max=100)
    public String ima;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Сюжет не может быть больше 2000 и меньше 2",min = 2,max=100)
    private String samvsu;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Vpers vpers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getima() {
        return ima;
    }

    public void setima(String ima) {
        this.ima = ima;
    }

    public String getsamvsu() {
        return samvsu;
    }

    public void setsamvsu(String samvsu) {
        this.samvsu = samvsu;
    }

    public Vpers getVpers() {
        return vpers;
    }

    public void setVpers(Vpers vpers) {
        this.vpers = vpers;
    }


    public Vsu(String ima, String samvsu, Vpers vpers) {
        this.ima = ima;
        this.samvsu = samvsu;
        this.vpers = vpers;
    }

    public Vsu(){

    }


}
