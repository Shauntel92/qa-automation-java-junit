import java.util.Objects;

/**
 * Contact domain object.
 * 
 * Constraints:
 * - contactId: required, unique, max 10 chars, non-null, not updatable
 * - firstName: required, max 10 chars, non-null
 * - lastName:  required, max 10 chars, non-null
 * - phone:     required, exactly 10 digits, non-null
 * - address:   required, max 30 chars, non-null
 */
public class Contact {

    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        validateId(contactId);
        this.contactId = contactId;

        setFirstNameInternal(firstName);
        setLastNameInternal(lastName);
        setPhoneInternal(phone);
        setAddressInternal(address);
    }

    // ---- Getters ----
    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    // ---- Internal setters (package-private): updatable by the service only ----
    void setFirstNameInternal(String firstName) {
        if (firstName == null || firstName.length() > 10) {
            throw new IllegalArgumentException("firstName must be non-null and at most 10 characters");
        }
        this.firstName = firstName;
    }

    void setLastNameInternal(String lastName) {
        if (lastName == null || lastName.length() > 10) {
            throw new IllegalArgumentException("lastName must be non-null and at most 10 characters");
        }
        this.lastName = lastName;
    }

    void setPhoneInternal(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("phone must be exactly 10 digits");
        }
        this.phone = phone;
    }

    void setAddressInternal(String address) {
        if (address == null || address.length() > 30) {
            throw new IllegalArgumentException("address must be non-null and at most 30 characters");
        }
        this.address = address;
    }

    // ---- Validation helpers ----
    private static void validateId(String contactId) {
        if (contactId == null || contactId.length() > 10) {
            throw new IllegalArgumentException("contactId must be non-null and at most 10 characters");
        }
    }

    // ---- Equality based on ID (useful for collections/tests) ----
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(contactId, contact.contactId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId='" + contactId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}