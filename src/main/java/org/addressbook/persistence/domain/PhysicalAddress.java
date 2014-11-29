package org.addressbook.persistence.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author alisiikh.
 */
@Entity
public class PhysicalAddress extends AbstractPersistentObject {

    private String email;
    private String companyName;
    private String postalCode;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
        if (companyName != null ? !companyName.equals(that.companyName) : that.companyName != null) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (postalCode != null ? !postalCode.equals(that.postalCode) : that.postalCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (postalCode != null ? postalCode.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PhysicalAddress{" +
                "email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country=" + country +
                ", city=" + city +
                '}';
    }
}
