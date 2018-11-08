package entities;

import javax.persistence.*;

@Entity
@NamedQueries(value =
        {
                @NamedQuery(name = "Slika.getAll", query="SELECT s FROM Slika s"),
        })
public class Slika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String naslov;

    private String opis;

    private String path;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Slika(String naslov, String opis, String path) {
        this.naslov = naslov;
        this.opis = opis;
        this.path = path;
    }

    public Slika() {
    }

    @Override
    public String toString() {
        return "Slika{" +
                "id=" + id +
                ", naslov='" + naslov + '\'' +
                ", opis='" + opis + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
