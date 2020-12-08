##Käyttöohje

Ohjelma käynnistetään komennolla
```
java -jar target/Minesweeper-1.0-SNAPSHOT.jar 
```

## Pelaaminen

Tekstikäyttöliittymä kysyy pelikentän kokoa. Y koordinaatti kuvaa pelikentän korkeutta. Vasemman yläkulman y-koordinaatti on 0 ja oikean alakulman y koordinaatti on pelikentän koko - 1. X koordinaatti kuvaa pelikentän leveyttä. Vasemman yläkulman x koordinaatti on 0 ja oikean alakulman x koordinaatti on pelikentän koko - 1.



Jokaisella vuorolla peli näyttää pelikentän ja kysyy haluaako pelaaja avata ruudun, muokata lipun sijaintia tai tallentaa pelin. Nykyisessä ohjelman versiossa pelin voi tallentaa, mutta tallennettua peliä ei voi jatkaa myöhemmin. Tekstikäyttöliittymästä näkee mikä millainen syöte komennon suorittamiseksi tulee antaa. Jos syöte ei ole mikään ehdotetuista niin ohjelma sanoo "Unknown command" ja kysyy komentoa uudestaan.

### Ruudun avaaminen

Jos valitaan komentosyöte "o" eli halutaan avata ruutu, niin ohjelma kysyy ruudun koordinaatteja. Koordinaatit syöttämällä ohjelma avaa ruudun. Jos ruudussa oli miina, niin peii päättyy. Jos ruudussa ei ollut miina, avaa ohjelma ruudun. Jos ruudun naapuriruuduissa ei ole yhtäkään miinaa, niin ohjelma avaa myös ruudun naapuriruudut. Tässä tapauksessa ohjelma avaa kaikki vastaantulleet ruudut, joiden vieressä ei ole yhtäkään miinaa. Avattu ruutu esitetään pelikentällä numerona. Numero kertoo kuinka monta miinaa on yhteensä sen naapuriruuduissa.

### Lipun laittaminen tai poistaminen

Jos valitaan komentosyöte "f" eli halutaan muokata lipun sijaintia, niin ohjelma kysyy sen ruudun koordinaatteja, johon halutaan laittaa lippu tai poistaa lippu. Jos koordinaattien ruudussa ei ole lippua niin ohjelma laittaa siihen lipun. Lippua esittää merkki "F". Jos koordinaatin ruudussa on jo lippu, niin ohjelma poistaa sen.

### Pelin tallentaminen

Jos valitaan komentosyöte "s", niin ohjelma tallentaa pelin tiedostoon. Ohjelma kysyy tiedoston nimeä. Tallentaminen saattaa epäonnistua, mutta jos se onnistuu niin ohjelma luo Minesweeper-alihakemistoon annetulla tiedoston nimellä tekstitiedoston, joka sisältää peliin liittyvää tietoa. Jos samanniminen tekstitiedosto on jo olemassa, niin ohjelma ylikirjoittaa sen tiedot.
