package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOikea() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void latausToimii() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 10.10", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimiiKunTarpeeksiArvoa() {
        kortti.lataaRahaa(500);
        kortti.otaRahaa(300);
        assertEquals("saldo: 2.10", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenEiOnnistuJosArvoaEiOleTarpeeksi() {
        kortti.otaRahaa(50);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaTrueKunOttaminenOnnistuu() {
        assertTrue(kortti.otaRahaa(10));
    }
    
    @Test
    public void otaRahaaPalauttaaFalseKunOttaminenEiOnnistu() {
        assertFalse(kortti.otaRahaa(500));
    }
}
