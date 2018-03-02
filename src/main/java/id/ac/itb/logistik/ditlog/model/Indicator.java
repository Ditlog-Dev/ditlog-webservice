package id.ac.itb.logistik.ditlog.model;

import javax.persistence.Entity;

/**
 * Not implemented yet. Just an example
 */
@Entity
public class Indicator {
    Long id;
    String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
