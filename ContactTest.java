import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    @Test
    void constructor_setsValidFields() {
        Contact c = new Contact("ABC123", "Alice", "Smith", "1234567890", "123 Main St");
        assertEquals("ABC123", c.getContactId());
        assertEquals("Alice", c.getFirstName());
        assertEquals("Smith", c.getLastName());
        assertEquals("1234567890", c.getPhone());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    void id_isNotUpdatable() {
        Contact c = new Contact("ID1", "Bob", "Jones", "0123456789", "456 Oak Ave");
        // There is no setter; compile-time enforced. Just assert present.
        assertEquals("ID1", c.getContactId());
    }

    @Test
    void invalidId_throws() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "A", "B", "0123456789", "Addr")
        );
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("THIS_ID_IS_TOO_LONG", "A", "B", "0123456789", "Addr")
        );
    }

    @Test
    void invalidFirstName_throws() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID2", null, "B", "0123456789", "Addr")
        );
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID3", "ABCDEFGHIJK", "B", "0123456789", "Addr") // 11 chars
        );
    }

    @Test
    void invalidLastName_throws() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID4", "A", null, "0123456789", "Addr")
        );
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID5", "A", "ABCDEFGHIJK", "0123456789", "Addr") // 11 chars
        );
    }

    @Test
    void invalidPhone_throws() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID6", "A", "B", null, "Addr")
        );
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID7", "A", "B", "12345", "Addr") // too short
        );
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID8", "A", "B", "12345678901", "Addr") // too long
        );
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID9", "A", "B", "abcdefghij", "Addr") // not digits
        );
    }

    @Test
    void invalidAddress_throws() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID10", "A", "B", "0123456789", null)
        );
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("ID11", "A", "B", "0123456789", "0123456789012345678901234567891") // 31 chars
        );
    }
}