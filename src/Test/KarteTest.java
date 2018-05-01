package Test;

import model.Karte;
import model.Kartenfarbe;
import model.Kartenwert;

import static org.junit.jupiter.api.Assertions.*;

class KarteTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void testEquals() {
        Karte k1 = new Karte(Kartenwert.DAME, Kartenfarbe.HERZ);
        Karte k2 = new Karte(Kartenwert.DAME, Kartenfarbe.HERZ);

        assertEquals(k1, k2);
    }

    @org.junit.jupiter.api.Test
    void testNotEquals1() {
        Karte k1 = new Karte(Kartenwert.DAME, Kartenfarbe.HERZ);
        Karte k2 = new Karte(Kartenwert.DAME, Kartenfarbe.KARO);

        assertNotEquals(k1, k2);
    }

    @org.junit.jupiter.api.Test
    void testNotEquals2() {
        Karte k1 = new Karte(Kartenwert.BUBE, Kartenfarbe.KARO);
        Karte k2 = new Karte(Kartenwert.DAME, Kartenfarbe.KARO);

        assertNotEquals(k1, k2);
    }

}