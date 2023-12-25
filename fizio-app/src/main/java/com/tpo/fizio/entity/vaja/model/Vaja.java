package com.tpo.fizio.entity.vaja.model;

import com.tpo.fizio.entity.vnos.model.Vnos;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @author Tadej Delopst
 */
@Entity
@Table(name = "VAJA")
public class Vaja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_VAJE", nullable = false, updatable = false)
    private Long id;

    @Column(name = "IME_VAJE")
    private String ime;

    @Column(name = "OPIS_VAJE", length = 5000)
    private String opis;

    @Column(name = "URL", length = 500)
    private String url;

    @OneToMany(mappedBy = "vaja", cascade = CascadeType.ALL)
    private List<Vnos> vnosi;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Vnos> getVnosi() {
        return vnosi;
    }

    public void setVnosi(List<Vnos> vnosi) {
        this.vnosi = vnosi;
    }
}

