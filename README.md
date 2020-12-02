# **Ohjelmistotekniikka, harjoitustyö: Miinaharava**

Sovelluksen avulla pelaaja voi pelata miinaharavapeliä. Pelissä on tarkoitus avata kaikki muut ruudut kuin ne joissa on miina. Pelaaja voittaa pelin, kun kaikki ruudut paitsi ne joissa on miinoja on avattu. Pelaaja häviää pelin jos hän yrittää avata ruutua, jossa on miina. Jos avatun ruudun viereisissä ruuduissa on miinoja, niin ruutuun tulee luku, joka kuvastaa montako miinaa sen viereisissä ruuduissa on yhteensä.


Koodin pystyy suorittamaan Netbeansissä ja Minesweeper-alihakemistossa komennolla: 
```
mvn compile exec:java -Dexec.mainClass=minesweeper.ui.MinesweeperTextUi
```
tai sitten jar-komennolla:
```
java -jar target/Minesweeper-1.0-SNAPSHOT.jar 
```
Komentoriviltä suoritettavat komennot:
testaus:
```
mvn test
```
testiraportin suoritus:
```
mvn test jacoco:report
```
suoritettavan jarin generointi:
```
mvn package
```

checkstyletarkastuksen suorittaminen:
```
mvn jxr:jxr checkstyle:checkstyle
```


## Dokumentaatio

[Vaatimusmäärittely](https://github.com/jullebli/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/jullebli/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/jullebli/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)


## Releaset

[Viikon 5 release](https://github.com/jullebli/ot-harjoitustyo/releases/tag/viikko5)
