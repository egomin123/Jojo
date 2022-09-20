package Fandom.Jojo.Models;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "stand")
public class Stand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Имя не может быть больше 100 и меньше 2",min = 2,max=100)
    public String ima;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "название способности не может быть больше 100 и меньше 3",min = 3,max=100)
    private String skill;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 3",min = 3,max=100)
    private String haracter;

    @OneToOne(optional = true, mappedBy = "stand")
    private Pers owner;


    public Stand(String ima, String skill,String haracter) {
        this.ima = ima;
        this.skill = skill;
        this.haracter = haracter;
    }

    public Stand(){

    }

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

    public String getskill() {
        return skill;
    }

    public void setskill(String skill) {
        this.skill = skill;
    }

    public String getharacter() {
        return haracter;
    }

    public void setharacter(String haracter) {
        this.haracter = haracter;
    }

    public void setOwner(Pers owner) {
        this.owner = owner;
    }

}

