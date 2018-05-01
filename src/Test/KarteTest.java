package Test;

import model.Karte;
import model.Kartenfarbe;
import model.Kartenwert;
import org.junit.Before;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class KarteTest {

    @BeforeAll
    static void before() {
        // Bevor irgendein Test durchgeführt wurde
    }

    @BeforeEach
    void setUp() {
        // Vor jedem Test
    }

    @AfterEach
    void tearDown() {
        // Nach jedem Test
    }

    @AfterAll
    static void after() {
        // Nachdem alle Tests durchgeführt wurden
    }

    @Test
    void testEquals() {
        Karte k1 = new Karte(Kartenwert.DAME, Kartenfarbe.HERZ);
        Karte k2 = new Karte(Kartenwert.DAME, Kartenfarbe.HERZ);

        assertEquals(k1, k2);
    }

    @Test
    void testNotEquals1() {
        Karte k1 = new Karte(Kartenwert.DAME, Kartenfarbe.HERZ);
        Karte k2 = new Karte(Kartenwert.DAME, Kartenfarbe.KARO);

        assertNotEquals(k1, k2);
    }

    @Test
    void testNotEquals2() {
        Karte k1 = new Karte(Kartenwert.BUBE, Kartenfarbe.KARO);
        Karte k2 = new Karte(Kartenwert.DAME, Kartenfarbe.KARO);

        assertNotEquals(k1, k2);
    }
}