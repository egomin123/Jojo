package Fandom.Jojo.Models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Table(name = "sezon")
public class Sezon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;




    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 2",min = 2,max=100)
    public String namesezon;

    @Min(message = "дата не может быть ниже 1950",value = 1950)
    @Max(message = "дата не может быть больше текущего года",value = 2022)
    @NotNull(message = "Число не должно быть пустым")
    private Integer datavixoda;

    @Min(message = "Число не может быть ниже 1",value = 1)
    @Max(message = "Число не должно превышать 1000",value = 1000)
    @NotNull(message = "Число не должно быть пустым")
    private Integer kolvoseria;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Video video;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Sozdatel sozdatel;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Manga manga;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Pers pers;

    @OneToOne(optional = true, mappedBy = "sezon")
    private Predsezon owner;

    @Min(message = "дата не может быть ниже 1950",value = 1950)
    @Max(message = "дата не может быть больше текущего года",value = 2022)
    @NotNull(message = "Число не должно быть пустым")
    private Integer dataanonsa;

    @Min(message = "дата не может быть ниже 1950",value = 1950)
    @Max(message = "дата не может быть больше текущего года",value = 2022)
    @NotNull(message = "Число не должно быть пустым")
    private Integer datakonca;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getnamesezon() {
        return namesezon;
    }

    public void setnamesezon(String namesezon) {
        this.namesezon = namesezon;
    }


    public Integer getdatavixoda() {
        return datavixoda;
    }

    public void setdatavixoda(Integer datavixoda) {
        this.datavixoda = datavixoda;
    }

    public Integer getkolvoseria() {
        return kolvoseria;
    }

    public void setkolvoseria(Integer kolvoseria) {
        this.kolvoseria = kolvoseria;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Sozdatel getSozdatel() {
        return sozdatel;
    }

    public void setSozdatel(Sozdatel sozdatel) {
        this.sozdatel = sozdatel;
    }

    public Manga getManga() {
        return manga;
    }

    public void setManga(Manga manga) {
        this.manga = manga;
    }

    public Pers getPers() {
        return pers;
    }

    public void setPers(Pers pers) {
        this.pers = pers;
    }

    public Integer getdataanonsa() {
        return dataanonsa;
    }

    public void setdataanonsa(Integer dataanonsa) {
        this.dataanonsa = dataanonsa;
    }

    public Integer getdatakonca() {
        return datakonca;
    }

    public void setdatakonca(Integer datakonca) {
        this.datakonca = datakonca;
    }

    public void setOwner(Predsezon owner) {
        this.owner = owner;
    }

    public Sezon(String namesezon, Integer datavixoda, Integer kolvoseria, Video video, Sozdatel sozdatel, Manga manga, Pers pers, Integer dataanonsa, Integer datakonca ) {
        this.namesezon = namesezon;
        this.datavixoda = datavixoda;
        this.kolvoseria = kolvoseria;
        this.video = video;
        this.sozdatel = sozdatel;
        this.manga = manga;
        this.pers = pers;
        this.dataanonsa = dataanonsa;
        this.datakonca = datakonca;
    }

    public Sezon(){

    }


}
