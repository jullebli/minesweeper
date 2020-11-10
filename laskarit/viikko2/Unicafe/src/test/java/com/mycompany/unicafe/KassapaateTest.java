package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassa;

    @Before
    public void setUp() {
        kassa = new Kassapaate();        
    }

    @Test
    public void luotuKassapaateRahamaaraOikea() {
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void luodussaKassapaatteessaMyytyNolla() {
        assertEquals(0, kassa.edullisiaLounaitaMyyty()
                + kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiOstoOnnistuuKunMaksuRiittaa() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiOstoVaihtorahaOikein() {
        assertEquals(100, kassa.syoMaukkaasti(500));
    }

    @Test
    public void maukkaidenLounaidenMaaraKasvaaKunMaksuRiittaa() {
        kassa.syoMaukkaasti(500);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiOstoOnnistuuKunMaksuRiittaa() {
        kassa.syoEdullisesti(500);
        assertEquals(100240, kassa.kassassaRahaa());

    }

    @Test
    public void syoEdullisestiOstoVaihtorahaOikein() {
        assertEquals(60, kassa.syoEdullisesti(300));
    }

    @Test
    public void edullistenLounaidenMaaraKasvaaKunMaksuRiittaa() {
        kassa.syoEdullisesti(300);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiOstoEiOnnistuKunMaksuRiittamaton() {
        kassa.syoMaukkaasti(300);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoMaukkaastiOstoEiOnnistuVaihtorahaOikein() {
        assertEquals(300, kassa.syoMaukkaasti(300));
    }

    @Test
    public void maukkaidenLounaidenMaaraEiKasvaKunMaksuRiittamaton() {
        kassa.syoMaukkaasti(300);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiOstoEiOnnistuKunMaksuRiittamaton() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiOstoEiOnnistuVaihtorahaOikein() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }

    @Test
    public void edullistenLounaidenMaaraEiKasvaKunMaksuRiittamaton() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKortillaOnnistuuJaPalauttaaTrue() {
        Maksukortti kortti = new Maksukortti(500);
        assertTrue(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void syoMaukkaastiKortillaOnnistuuJaKortinSaldoLaskee() {
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }

    @Test
    public void syoMaukkaastiKortillaOnnistuuJaMyytyjenMaukkaidenMaaraKasvaa() {
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKortillaEiOnnistuJaSaldoEiMuutu() {
        Maksukortti kortti = new Maksukortti(300);
        kassa.syoMaukkaasti(kortti);
        assertEquals(300, kortti.saldo());
    }

    @Test
    public void syoMaukkaastiKortillaEiOnnistuJaLounaidenMaaraEiMuutu() {
        Maksukortti kortti = new Maksukortti(300);
        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty()
                + kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoMaukkaastiKortillaEiOnnistuJaPalauttaaFalse() {
        Maksukortti kortti = new Maksukortti(300);
        assertFalse(kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void syoMaukkaastiKortillaOnnistuuJaKassanRahamaaraEiKasva() {
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void syoEdullisestiKortillaOnnistuuJaPalauttaaTrue() {
        Maksukortti kortti = new Maksukortti(500);
        assertTrue(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void syoEdullisestiKortillaOnnistuuJaKortinSaldoLaskee() {
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoEdullisesti(kortti);
        assertEquals(260, kortti.saldo());
    }

    @Test
    public void syoEdullisestiKortillaOnnistuuJaMyytyjenEdullistenMaaraKasvaa() {
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKortillaEiOnnistuJaSaldoEiMuutu() {
        Maksukortti kortti = new Maksukortti(200);
        kassa.syoEdullisesti(kortti);
        assertEquals(200, kortti.saldo());
    }

    @Test
    public void syoEdullisestiKortillaEiOnnistuJaLounaidenMaaraEiMuutu() {
        Maksukortti kortti = new Maksukortti(200);
        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty()
                + kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void syoEdullisestiKortillaEiOnnistuJaPalauttaaFalse() {
        Maksukortti kortti = new Maksukortti(200);
        assertFalse(kassa.syoEdullisesti(kortti));
    }

    @Test
    public void syoEdullisestiKortillaOnnistuuJaKassanRahamaaraEiKasva() {
        Maksukortti kortti = new Maksukortti(500);
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiaLadataanNiinKortinSaldoMuuttuuSamanVerran() {
        Maksukortti kortti = new Maksukortti(200);
        kassa.lataaRahaaKortille(kortti, 100);
        assertEquals(300, kortti.saldo());
    }
    
    @Test
    public void korttiaLadataanJaKassanRahamaaraKasvaaSamanVerran() {
        Maksukortti kortti = new Maksukortti(200);
        kassa.lataaRahaaKortille(kortti, 200);
        assertEquals(100200, kassa.kassassaRahaa());
    }
    
    @Test
    public void josLadataanNegatiivisellaArvollaNiinSaldoEiMuutu() {
        Maksukortti kortti = new Maksukortti(200);
        kassa.lataaRahaaKortille(kortti, -50);
        assertEquals(200, kortti.saldo());
    }

}
