# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksen avulla pelaaja voi pelata miinaharavapeliä. Pelissä on tarkoitus avata kaikki muut ruudut kuin ne joissa on miina. Pelaaja voittaa pelin, kun kaikki ruudut paitsi ne, joissa on miinoja, on avattu. Pelaaja häviää pelin, jos hän yrittää avata ruutua, jossa on miina. Jos avatun ruudun viereisissä ruuduissa on miinoja, niin ruutuun tulee luku, joka kuvastaa montako miinaa sen viereisissä ruuduissa on yhteensä. Pelaaja voi tallentaa pelin tiedostoon ja jatkaa sen pelaamista myöhemmin.

Sovellus käyttää graafista käyttöliittymää, mutta kehityksen aikainen tekstikäyttöliittymä on sovelluksessa mukana.


## Suunnitellut toiminnallisuudet perusversioon

- pelaaja voi aloittaa uuden pelin
  - pelaaja voi valita kentän koon
- pelaaja voi avata ruutuja
- pelaaja voi poistua pelistä
- tekstikäyttöliittymä kehitykseen
- graafinen käyttöliittymä pelaamiseen
- pelaaja voi merkitä ruutuja lipulla
- jos pelaaja avaa ruudun, jonka ympärillä ei ole yhtään miinaa, niin peli avaa automaattisesti kaikki vieressä olevat ruudut, kunnes avatun ruudun vieressä on miina/miinoja
- pelaaja voi tallentaa pelin
- pelaaja voi jatkaa tallennettua peliä myöhemmin
- kun pelaaja häviää, paljastetaan miinojen sijainti

## Jatkokehitysideat

- pelaaja voi tutustua pelin sääntöihin sovelluksen sisällä
- pelaaja voi valita eri vaikeustason uudelle pelille (easy, normal, hard jne.)
- ensimmäisellä avauksella ei voi osua miinaan
