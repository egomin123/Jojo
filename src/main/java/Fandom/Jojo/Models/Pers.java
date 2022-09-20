package Fandom.Jojo.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table (name = "pers")
public class Pers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 2",min = 2,max=100)
    public String imapers;

    @Min(message = "возраст не может быть ниже 5",value = 5)
    @Max(message = "возраст не должно превышать 100",value = 100)
    @NotNull(message = "Число не должно быть пустым")
    private Integer vozrastpers;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 3",min = 3,max=100)
    private String haracterpers;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Stand stand;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Seu seu;

    @OneToOne(optional = true, mappedBy = "pers")
    private Sezon owner;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getimapers() {
        return imapers;
    }

    public void setimapers(String imapers) {
        this.imapers = imapers;
    }


    public Integer getvozrastpers() {
        return vozrastpers;
    }

    public void setvozrastpers(Integer vozrastpers) {
        this.vozrastpers = vozrastpers;
    }

    public String getharacterpers() {
        return haracterpers;
    }

    public void setharacterpers(String haracterpers) {
        this.haracterpers = haracterpers;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    public Seu getSeu() {
        return seu;
    }

    public void setSeu(Seu seu) {
        this.seu = seu;
    }

    public void setOwner(Sezon owner) {
        this.owner = owner;
    }


    public Pers(String imapers, Integer vozrastpers, String haracterpers, Stand stand, Seu seu) {
        this.imapers = imapers;
        this.vozrastpers = vozrastpers;
        this.haracterpers = haracterpers;
        this.stand = stand;
        this.seu = seu;
    }

    public Pers(){

    }


}
