package Fandom.Jojo.Models;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "seu")
public class Seu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 2",min = 2,max=100)
    public String ima;

    @Min(message = "возраст не может быть ниже 5",value = 5)
    @Max(message = "возраст не должно превышать 100",value = 100)
    @NotNull(message = "Число не должно быть пустым")
    private Integer vozrastseu;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 3",min = 3,max=100)
    private String nacionalnost;

    @OneToOne(optional = true, mappedBy = "seu")
    private Pers owner;



    public Seu(String ima, Integer vozrastseu,String nacionalnost) {
        this.ima = ima;
        this.vozrastseu = vozrastseu;
        this.nacionalnost = nacionalnost;
    }

    public Seu(){

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

    public Integer getvozrastseu() {
        return vozrastseu;
    }

    public void setvozrastseu(Integer vozrastseu) {
        this.vozrastseu = vozrastseu;
    }

    public String getnacionalnost() {
        return nacionalnost;
    }

    public void setnacionalnost(String nacionalnost) {
        this.nacionalnost = nacionalnost;
    }

    public void setOwner(Pers owner) {
        this.owner = owner;
    }

}

