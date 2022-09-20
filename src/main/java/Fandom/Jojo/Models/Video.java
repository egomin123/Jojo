package Fandom.Jojo.Models;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "video")
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 2",min = 2,max=100)
    public String ima;

    @Min(message = "год не может быть ниже 1950",value = 1950)
    @Max(message = "год не может превышать текущий",value = 2022)
    @NotNull(message = "Число не должно быть пустым")
    private Integer godvixoda;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 3",min = 3,max=100)
    private String company;

    @OneToOne(optional = true, mappedBy = "video")
    private Sezon owner;

    public Video(String ima, Integer godvixoda,String company) {
        this.ima = ima;
        this.godvixoda = godvixoda;
        this.company = company;
    }

    public Video(){

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

    public Integer getgodvixoda() {
        return godvixoda;
    }

    public void setgodvixoda(Integer godvixoda) {
        this.godvixoda = godvixoda;
    }

    public String getcompany() {
        return company;
    }

    public void setcompany(String company) {
        this.company = company;
    }

    public void setOwner(Sezon owner) {
        this.owner = owner;
    }

}
