package to.adian.unofficialenterkomputer.data;

import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class ConvertersTest {

    private Converters converters;
    private String stringFixture;
    private URL urlFixture;

    @Before
    public void setUp() throws MalformedURLException {
        converters = new Converters();
        stringFixture = "https://example.com/example.json";
        urlFixture = new URL("https", "example.com", "/example.json");
    }

    @Test
    public void stringToUrl_givenValidString_returnsUrl() {
        URL result = converters.stringToURL(stringFixture);
        assertEquals(urlFixture, result);
    }

    @Test
    public void stringToUrl_givenRealValidString_returnsUrl() throws MalformedURLException {
        URL expected = new URL("https", "www.enterkomputer.com", "/api/product/accessories.json");
        URL result = converters.stringToURL("https://www.enterkomputer.com/api/product/accessories.json");
        assertEquals(expected, result);
    }

    @Test
    public void urlToString_givenValidUrl_returnsString() {
        String result = converters.urlToString(urlFixture);
        assertEquals(stringFixture, result);
    }
}