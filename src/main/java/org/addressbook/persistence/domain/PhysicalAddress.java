package org.addressbook.persistence.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

/**
 * @author alisiikh.
 */
@Entity
public class PhysicalAddress extends AbstractPersistentObject {

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "address_person")
    private Person person = new Person();
    @NotBlank
    @Size(min = 0, max = 255)
    private String street1;
    private String street2;

    @NotBlank
    @Size(min = 0, max = 10)
    private String postalCode;

    @NotNull
    @ManyToOne
    private Country country;

    @ManyToOne
    private City city;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "address_phone",
            joinColumns = {@JoinColumn(name = "address_id")},
            inverseJoinColumns = {@JoinColumn(name = "phone_id")})
    private Set<Phone> phones = new HashSet<>();

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public void addToPhones(Phone phone) {
        phones.add(phone);
    }

    public void removeFromPhones(Phone phone) {
        phones.remove(phone);
    }

    public void addToPhones(String phoneNumber) {
        phones.add(new Phone(phoneNumber));
    }

    public void removeFromPhones(String phoneNumber) {
        phones.stream()
                .filter(p -> p.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .ifPresent(phones::remove);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getStreet1() {
        return street1;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhysicalAddress that = (PhysicalAddress) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (person != null ? !person.equals(that.person) : that.person != null) return false;
        if (phones != null ? !phones.equals(that.phones) : that.phones != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;
        if (street1 != null ? !street1.equals(that.street1) : that.street1 != null) return false;
        if (street2 != null ? !street2.equals(that.street2) : that.street2 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = person != null ? person.hashCode() : 0;
        result = 31 * result + (street1 != null ? street1.hashCode() : 0);
        result = 31 * result + (street2 != null ? street2.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (phones != null ? phones.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhysicalAddress{" +
                "city=" + city +
                ", country=" + country +
                ", postalCode='" + postalCode + '\'' +
                ", street2='" + street2 + '\'' +
                ", street1='" + street1 + '\'' +
                ", person=" + person +
                '}';
    }
}
