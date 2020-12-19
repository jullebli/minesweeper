## Testausdokumentti

Ohjelmaa on testattu yksikkötesteillä käyttäen junitia. Ohjelmaa on myös testattu manuaalisesti käyttäen ensin tekstikäyttöliittymää ja loppuvaiheilla graafista käyttöliittymää.

### Yksikkötestaus

Sovelluslogiikka on yhdessä luokassa nimeltä Game. Luokalle on tehty kattavat testit junitilla. Testaamista varten on tehty erillinen konstruktori, sillä miinaharavassa miinat asetetaan satunnaisesti mikä tekee testaamisesta vaikeaa. Yksikkötestit testaavat Game-luokan kaikkia julkisia metodeja, mukaan lukien tiedostojen kirjoittamista ja lukemista pelin tallentamisominaisuuteen liittyen.

#### Testikattavuus

Testikattavuuteen ei ole otettu mukaan käyttöliittymän luokkia. Testit kattavat sovelluslogiikan kaikki rivit, pois lukien muutamia tiedoston lukemiseen liittyviä virhetilanteita. Näitä on kuitenkin testattu manuaalisesti. 

Game-luokan testien rivikattavuus on 96% ja haarautumiskattavuus 84%.

<img src="https://raw.githubusercontent.com/jullebli/minesweeper/master/dokumentaatio/kuvat/MinesweeperTestauskattavuus.png" />

### Manuaalinen testaus

Sovellusta on testattu manuaalisesti yrittäen löytää mahdollisimman paljon virhetilanteita. Tämä on pääosin tehty käyttämällä graafista käyttöliittymää odottamattomilla tavoilla eli esimerkiksi syöttämällä negatiivisia arvoja ja yrittämällä tallentaa peliä hakemistoon, johon ei ole kirjoitusoikeuksia. Kaikissa tunnetuissa vikatilanteissa sovellus ilmoittaa käyttöliittymässä virhetilanteesta kaatumatta.
