package Fandom.Jojo.Models;

import javax.persistence.*;
import javax.validation.constraints.*;


@Entity
@Table(name = "predsezon")
public class Predsezon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не может быть пустым")
    @Size(message = "Строка не может быть больше 100 и меньше 3",min = 3,max=100)
    private String namepredsezon;
    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private User user;

    @OneToOne(optional = true, cascade = CascadeType.ALL)
    private Sezon sezon;






    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Sezon getSezon() {
        return sezon;
    }

    public void setSezon(Sezon sezon) {
        this.sezon = sezon;
    }

    public String getnamepredsezon() {
        return namepredsezon;
    }

    public void setnamepredsezon(String namepredsezon) {
        this.namepredsezon = namepredsezon;
    }

    public Predsezon(String namepredsezon, User user, Sezon sezon) {
        this.user = user;
        this.sezon = sezon;
        this.namepredsezon = namepredsezon;
    }

    public Predsezon(){

    }


}
