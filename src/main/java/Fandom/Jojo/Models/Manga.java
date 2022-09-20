package Fandom.Jojo.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "manga")
public class Manga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 2",min = 2,max=100)
    public String ima;

    @Min(message = "год не может быть ниже 1950",value = 1950)
    @Max(message = "год не может превышать текущий",value = 2022)
    @NotNull(message = "Число не должно быть пустым")
    private Integer godvixodam;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 3",min = 3,max=100)
    private String companym;


    @OneToOne(optional = true, mappedBy = "manga")
    private Sezon owner;


    public Manga(String ima, Integer godvixodam,String companym) {
        this.ima = ima;
        this.godvixodam = godvixodam;
        this.companym = companym;
    }

    public Manga(){

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

    public Integer getgodvixodam() {
        return godvixodam;
    }

    public void setgodvixodam(Integer godvixodam) {
        this.godvixodam = godvixodam;
    }

    public String getcompanym() {
        return companym;
    }

    public void setcompanym(String companym) {
        this.companym = companym;
    }

    public void setOwner(Sezon owner) {
        this.owner = owner;
    }

}

