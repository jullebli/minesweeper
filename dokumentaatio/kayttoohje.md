## Käyttöohje

Ohjelma käynnistetään komennolla
```
java -jar target/Minesweeper-1.0-SNAPSHOT.jar 
```

## Pelaaminen

Graafisessa käyttöliittymässä on kaksi näkymää. Aloitusnäkymässä valitaan uuden pelin asetukset tai jatketaan tallennettua peliä. Pelinäkymässä pelataan peliä. Peli käynnistyy aloitusnäkymästä.

### Aloitusnäkymä

Aloitusnäkymässä voi aloittaa uuden pelin antamalla miinakentän leveyden, korkeuden ja painamalla Start-nappia. Vanhaa tallennettua peliä voi jatkaa painamalla Load saved game -nappia. Peli avaa tiedoston valitsemista varten erillisen ikkunan. Tallennetut pelit ovat tiedostoja, joiden pääte on .minesave.

### Pelinäkymä

Pelinäkymässä on ruuduista koostuva miinakenttä, Start over -nappi, Save game -nappi ja Quit -nappi.

#### Ruudun avaaminen

Pelinäkymässä voi avata ruutuja klikkaamalla niitä hiiren vasemmalla näppäimellä. Avaamattomat ruudut on merkitty kysymysmerkillä. Pelaaja häviää pelin, jos hän avaa ruudun, jossa on miina. Peliä ei voi enää pelata ja se näyttää kaikkien miinojen sijainnit "*"-merkillä. Jos ruudussa ei ollut miinaa, ruudun kohdalle tulee luku, joka kuvastaa kuinka monta miinaa on sen naapuriruuduissa. Jos naapuriruuduissa on nolla miinaa, peli avaa myös nollaruudun naapuriruudut. Nollaruudut näytetään tyhjinä nolla luvun sijaan.

Pelaaja voittaa pelin, jos hän avaa kaikki muut ruudut kuin miinalliset ruudut.

#### Lipun asettaminen ja poistaminen

Ruudun voi merkitä lipulla klikkaamalla sitä hiiren oikealla näppäimellä. Liputettu ruutu on merkitty "F"-kirjaimella eikä sitä voi avata. Lipun voi poistaa hiiren oikealla näppäimellä. Lippu auttaa pelaaja olemaan painamatta ruutuja, joissa on todennäköisesti miina.

#### Aloitusnäkymään palaaminen

Aloitusnäkymään voi palata Start over -nappia painamalla. Jos peli on kesken, niin peli kysyy vahvistusta poistumiseen. Keskeytettyä ei tallenneta.

#### Pelin tallentaminen

Pelin voi tallentaa painamalla Save game -nappia. Peli avaa tiedoston tallentamista varten erillisen ikkunan. Pelaaja voi valita tallennetulle pelitiedostolle haluamansa nimen. Pääte .minesave lisätään tähän nimeen mikäli sitä ei ole annettu valmiina. Peliä voi jatkaa tallentamisen jälkeen.

#### Pelin lopettaminen

Pelin voi lopettaa painamalla Quit-nappia. Jos peli on käynnissä, niin peli kysyy vahvistusta poistumiseen.
