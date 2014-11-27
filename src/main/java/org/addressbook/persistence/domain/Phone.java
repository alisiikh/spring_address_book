package org.addressbook.persistence.domain;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author alisiikh.
 */
@Entity
public class Phone extends AbstractPersistentObject {

    @NotNull
    private String phoneNumber;

    public Phone() {
    }

    public Phone(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String number) {
        this.phoneNumber = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Phone phone = (Phone) o;

        if (phoneNumber != null ? !phoneNumber.equals(phone.phoneNumber) : phone.phoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
