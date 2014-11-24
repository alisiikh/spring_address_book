package org.addressbook.persistence.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author alisiikh.
 */
@Entity
public class Address extends AbstractPersistentObject {

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
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
}
