package Test;

import control.NotEnoughMoneyException;
import model.Karte;
import model.Kartenfarbe;
import model.Kartenwert;
import model.Spieler;
import org.junit.Before;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class SpielerTest {

    private Spieler spieler;

    @BeforeAll
    static void before() {
        // Bevor irgendein Test durchgeführt wurde
    }

    @BeforeEach
    void setUp() {
        // Vor jedem Test
        spieler = new Spieler(500);
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
    void testKartenSumme() {
        spieler.erhalteKarte(new Karte(Kartenwert.DREI, Kartenfarbe.PIK));
        spieler.erhalteKarte(new Karte(Kartenwert.ACHT, Kartenfarbe.HERZ));
        spieler.erhalteKarte(new Karte(Kartenwert.FUENF, Kartenfarbe.KARO));

        assertEquals(16, spieler.getKartenSumme());
    }

    @Test
    void testKartenSummeLetzteAss() {
        spieler.erhalteKarte(new Karte(Kartenwert.DREI, Kartenfarbe.PIK));
        spieler.erhalteKarte(new Karte(Kartenwert.ACHT, Kartenfarbe.HERZ));
        spieler.erhalteKarte(new Karte(Kartenwert.FUENF, Kartenfarbe.KARO));
        spieler.erhalteKarte(new Karte(Kartenwert.ASS, Kartenfarbe.KARO));

        assertEquals(17, spieler.getKartenSumme());
    }

    @Test
    void testEinsatzZuviel() {
        try {
            spieler.setEinsatz(600);
        }
        catch (NotEnoughMoneyException e) {
            assertEquals(0, spieler.getBudget());
            return;
        }
        fail("testEinsatzZuviel: Exception not thrown");
    }

    @Test
    void testEinsatzZuviel2() {
        try {
            spieler.setEinsatz(600);
        }
        catch(NotEnoughMoneyException e) {
            assertEquals(500, spieler.getEinsatz());
            return;
        }
        fail("testEinsatzZuviel2: Exception not thrown");
    }

    @Test
    void testEinsatz() {
        try {
            spieler.setEinsatz(400);
        } catch (NotEnoughMoneyException e) {
            e.printStackTrace();
        }
        assertEquals(100, spieler.getBudget());
    }
}