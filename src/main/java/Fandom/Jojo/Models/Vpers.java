package Fandom.Jojo.Models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table (name = "vpers")
public class Vpers {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 2",min = 2,max=100)
    public String imavpers;

    @Min(message = "возраст не может быть ниже 5",value = 5)
    @Max(message = "возраст не должно превышать 100",value = 100)
    @NotNull(message = "Число не должно быть пустым")
    private Integer vozrastpers;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 3",min = 3,max=100)
    private String haracterpers;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Standv standv;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private User user;

    @OneToOne(optional = true, mappedBy = "vpers")
    public Vsu owner;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getimavpers() {
        return imavpers;
    }

    public void setimavpers(String imavpers) {
        this.imavpers = imavpers;
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

    public Standv getStandv() {
        return standv;
    }

    public void setStandv(Standv standv) {
        this.standv = standv;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOwner(Vsu owner) {
        this.owner = owner;
    }



    public Vpers(String imavpers, Integer vozrastpers, String haracterpers, Standv standv, User user) {
        this.imavpers = imavpers;
        this.vozrastpers = vozrastpers;
        this.haracterpers = haracterpers;
        this.standv = standv;
        this.user = user;
    }

    public Vpers(){

    }


}
