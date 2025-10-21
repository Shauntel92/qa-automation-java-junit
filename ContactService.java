import java.util.*;

/**
 * In-memory ContactService for adding, updating, and deleting Contact objects.
 * Uses a HashMap keyed by contactId to ensure uniqueness.
 */
public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    /** Adds a contact; enforces unique contactId. */
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact cannot be null");
        }
        String id = contact.getContactId();
        if (contacts.containsKey(id)) {
            throw new IllegalArgumentException("contactId already exists: " + id);
        }
        contacts.put(id, contact);
    }

    /** Convenience factory-style add. */
    public void addContact(String contactId, String firstName, String lastName, String phone, String address) {
        addContact(new Contact(contactId, firstName, lastName, phone, address));
    }

    /** Deletes a contact by ID; returns true if removed, false if not found. */
    public boolean deleteContact(String contactId) {
        return contacts.remove(contactId) != null;
    }

    // ---- Update operations: throw NoSuchElementException if contact not found ----
    public void updateFirstName(String contactId, String newFirstName) {
        Contact c = getRequired(contactId);
        c.setFirstNameInternal(newFirstName);
    }

    public void updateLastName(String contactId, String newLastName) {
        Contact c = getRequired(contactId);
        c.setLastNameInternal(newLastName);
    }

    public void updatePhone(String contactId, String newPhone) {
        Contact c = getRequired(contactId);
        c.setPhoneInternal(newPhone);
    }

    public void updateAddress(String contactId, String newAddress) {
        Contact c = getRequired(contactId);
        c.setAddressInternal(newAddress);
    }

    /** Retrieves a contact if present, else null. */
    public Contact getContactOrNull(String contactId) {
        return contacts.get(contactId);
    }

    /** Returns a read-only view of all contacts (useful for debugging). */
    public Collection<Contact> listContacts() {
        return Collections.unmodifiableCollection(contacts.values());
    }

    private Contact getRequired(String contactId) {
        Contact c = contacts.get(contactId);
        if (c == null) {
            throw new NoSuchElementException("No contact with id: " + contactId);
        }
        return c;
    }
}