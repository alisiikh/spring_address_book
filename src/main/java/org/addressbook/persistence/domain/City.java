package org.addressbook.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author alisiikh.
 */
@Entity
public class City extends AbstractPersistentObject {

    private String name;

    @ManyToOne
    private Country country;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public City(String name, Country country) {
        this.country = country;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (country != null ? !country.equals(city.country) : city.country != null) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }
}
