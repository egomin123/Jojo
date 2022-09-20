package Fandom.Jojo.Models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Entity
@Table(name = "sozdatel")
public class Sozdatel {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 2",min = 2,max=100)
    public String ima;

    @Min(message = "возраст не может быть ниже 5",value = 5)
    @Max(message = "возраст не должно превышать 100",value = 100)
    @NotNull(message = "Число не должно быть пустым")
    private Integer vozrastsozdatel;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 3",min = 3,max=100)
    private String dolznost;

    @OneToOne(optional = true, mappedBy = "sozdatel")
    private Sezon owner;



    public Sozdatel(String ima, Integer vozrastsozdatel,String dolznost) {
        this.ima = ima;
        this.vozrastsozdatel = vozrastsozdatel;
        this.dolznost = dolznost;
    }

    public Sozdatel(){

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

    public Integer getvozrastsozdatel() {
        return vozrastsozdatel;
    }

    public void setvozrastsozdatel(Integer vozrastsozdatel) {
        this.vozrastsozdatel = vozrastsozdatel;
    }

    public String getdolznost() {
        return dolznost;
    }

    public void setdolznost(String dolznost) {
        this.dolznost = dolznost;
    }

    public void setOwner(Sezon owner) {
        this.owner = owner;
    }
}
