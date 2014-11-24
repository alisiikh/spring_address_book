package org.addressbook.persistence.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author alisiikh.
 */
@Entity
public class Country extends AbstractPersistentObject {

    private int isoNumber;

    @Column(unique = true)
    private String iso3;
    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JoinColumn
    private Set<City> cities = new HashSet<>();

    public Country() {
    }

    public Country(int isoNumber, String iso3, String name) {
        this.isoNumber = isoNumber;
        this.iso3 = iso3;
        this.name = name;
    }

    public Country(int isoNumber, String iso3, String name, Set<City> cities) {
        this.isoNumber = isoNumber;
        this.iso3 = iso3;
        this.name = name;
        this.cities = cities;
    }

    public int getIsoNumber() {
        return isoNumber;
    }

    public void setIsoNumber(int isoNumber) {
        this.isoNumber = isoNumber;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<City> getCities() {
        return cities;
    }

    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    public void addToCities(City city) {
        cities.add(city);
    }

    public void removeFromCities(City city) {
        cities.remove(city);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Country country = (Country) o;

        if (isoNumber != country.isoNumber) return false;
        if (iso3 != null ? !iso3.equals(country.iso3) : country.iso3 != null) return false;
        if (name != null ? !name.equals(country.name) : country.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = isoNumber;
        result = 31 * result + (iso3 != null ? iso3.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
