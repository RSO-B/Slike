package entities;

import javax.persistence.*;
import java.util.List;

@Entity(name = "slika")
@NamedQueries(value =
        {
                @NamedQuery(name = "Slika.getAll", query="SELECT s FROM slika s"),
                @NamedQuery(name = "Slika.findByAlbum", query="SELECT s FROM slika s WHERE s.album_id = :cudstomerId")
        })
public class Slika {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String naslov;

    private String opis;

    private String path;

    @Column(name = "album_id")
    private String album_id;

//    @ElementCollection
//    private List<String> itemIds;

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

//    public List<String> getItemIds() {
//        return itemIds;
//    }
//
//    public void setItemIds(List<String> itemIds) {
//        this.itemIds = itemIds;
//    }

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

    public Slika(String naslov, String opis, String path, String album_id) {
        this.naslov = naslov;
        this.opis = opis;
        this.path = path;
        this.album_id = album_id;
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
