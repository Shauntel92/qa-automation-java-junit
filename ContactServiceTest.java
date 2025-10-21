import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

public class ContactServiceTest {

    @Test
    void addContact_enforcesUniqueId() {
        ContactService svc = new ContactService();
        Contact c1 = new Contact("ID1", "Amy", "Ng", "1111111111", "A St");
        svc.addContact(c1);

        Contact c2 = new Contact("ID1", "Ben", "Wu", "2222222222", "B St");
        assertThrows(IllegalArgumentException.class, () -> svc.addContact(c2));
    }

    @Test
    void deleteContact_removesExisting_andReturnsTrue() {
        ContactService svc = new ContactService();
        svc.addContact("ID2", "Chris", "Lee", "3333333333", "C St");

        assertTrue(svc.deleteContact("ID2"));
        assertFalse(svc.deleteContact("ID2")); // already removed
    }

    @Test
    void updateOperations_modifyFields() {
        ContactService svc = new ContactService();
        svc.addContact("ID3", "Dana", "Kim", "4444444444", "Old Addr");

        svc.updateFirstName("ID3", "Dan");
        svc.updateLastName("ID3", "K");
        svc.updatePhone("ID3", "5555555555");
        svc.updateAddress("ID3", "New Addr 123");

        Contact updated = svc.getContactOrNull("ID3");
        assertNotNull(updated);
        assertAll(
            () -> assertEquals("Dan", updated.getFirstName()),
            () -> assertEquals("K", updated.getLastName()),
            () -> assertEquals("5555555555", updated.getPhone()),
            () -> assertEquals("New Addr 123", updated.getAddress())
        );
    }

    @Test
    void updateOnMissingContact_throws() {
        ContactService svc = new ContactService();
        assertThrows(NoSuchElementException.class, () -> svc.updateFirstName("MISSING", "X"));
        assertThrows(NoSuchElementException.class, () -> svc.updateLastName("MISSING", "Y"));
        assertThrows(NoSuchElementException.class, () -> svc.updatePhone("MISSING", "0123456789"));
        assertThrows(NoSuchElementException.class, () -> svc.updateAddress("MISSING", "Somewhere"));
    }

    @Test
    void updateWithInvalidValues_throws() {
        ContactService svc = new ContactService();
        svc.addContact("ID4", "Eve", "Li", "6666666666", "Addr");

        assertThrows(IllegalArgumentException.class, () -> svc.updateFirstName("ID4", null));
        assertThrows(IllegalArgumentException.class, () -> svc.updateLastName("ID4", "THISISLONGERTHANTEN"));
        assertThrows(IllegalArgumentException.class, () -> svc.updatePhone("ID4", "abc"));
        assertThrows(IllegalArgumentException.class, () -> svc.updateAddress("ID4", "0123456789012345678901234567891")); // 31 chars
    }
}