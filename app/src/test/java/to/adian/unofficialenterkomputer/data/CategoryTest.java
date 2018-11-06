package to.adian.unofficialenterkomputer.data;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {

    private Category fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new Category(1, "Lorem Ipsum", "https://localhost/category");
    }

    @Test
    public void test_getId() {
        assertEquals(1, fixture.getId());
    }

    @Test
    public void test_getName() {
        assertEquals("Lorem Ipsum", fixture.getName());
    }

    @Test
    public void test_getEndpoint() {
        assertEquals("https://localhost/category", fixture.getEndpoint());
    }

    @Test
    public void test_toString() {
        assertEquals("Lorem Ipsum", fixture.toString());
    }
}