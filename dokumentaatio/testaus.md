## Testausdokumentti

Ohjelmaa on testattu yksikkötesteillä käyttäen JUnitia. Ohjelmaa on myös testattu manuaalisesti käyttäen ensin tekstikäyttöliittymää ja loppuvaiheilla graafista käyttöliittymää.

### Yksikkötestaus

Sovelluslogiikka on yhdessä luokassa nimeltä Game. Luokalle on tehty kattavat testit JUnitilla GameTest-luokkaan. Testaamista varten on tehty erillinen konstruktori, sillä miinaharavassa miinat asetetaan satunnaisesti mikä tekee testaamisesta vaikeaa. Yksikkötestit testaavat Game-luokan kaikkia julkisia metodeja, mukaan lukien tiedostojen kirjoittamista ja lukemista pelin tallentamisominaisuuteen liittyen.

#### Testikattavuus

Testikattavuuteen ei ole otettu mukaan käyttöliittymän luokkia. Testit kattavat sovelluslogiikan kaikki rivit, pois lukien muutamia tiedoston lukemiseen liittyviä virhetilanteita. Näitä on kuitenkin testattu manuaalisesti. 

Käyttöliittymän luokkia lukuunottamatta sovelluksen testien rivikattavuus on 96% ja haarautumakattavuus 84%.

<img src="https://raw.githubusercontent.com/jullebli/minesweeper/master/dokumentaatio/kuvat/MinesweeperTestauskattavuus.png" />

### Järjestelmätestaus

Sovelluksen järjestelmätestaus on suoritettu manuaalisesti.

Sovellus on haettu ja sitä on testattu käyttöohjeessa kuvaamalla tavalla Linux-ympäristössä.

### Toiminnallisuudet

Sovellusta on testattu manuaalisesti yrittäen löytää mahdollisimman paljon virhetilanteita. Tämä on pääosin tehty käyttämällä graafista käyttöliittymää odottamattomilla tavoilla, esimerkiksi syöttämällä negatiivisia arvoja ja yrittämällä tallentaa peliä hakemistoon, johon ei ole kirjoitusoikeuksia. Kaikissa tunnetuissa vikatilanteissa sovellus ilmoittaa käyttöliittymässä virhetilanteesta kaatumatta sopivalla tekstillä.

## Sovellukseen jääneet laatuongelmat

Sovellusta on testattu muutamilla virheellisillä tiedostoilla. Toisaalta ei olekaan mahdollista testata sitä kaikilla mahdollisilla virheellisillä tiedostoilla.
