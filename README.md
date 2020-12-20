# **Ohjelmistotekniikka, harjoitustyö: Miinaharava**

Sovelluksen avulla pelaaja voi pelata miinaharavapeliä. Pelissä on tarkoitus avata kaikki muut ruudut kuin ne joissa on miina. Pelaaja voittaa pelin, kun kaikki ruudut paitsi ne joissa on miinoja on avattu. Pelaaja häviää pelin jos hän yrittää avata ruutua, jossa on miina. Jos avatun ruudun viereisissä ruuduissa on miinoja, niin ruutuun tulee luku, joka kuvastaa montako miinaa sen viereisissä ruuduissa on yhteensä.


## Komentorivikomennot:


Koodin pystyy suorittamaan Netbeansissä ja Minesweeper-alihakemistossa komennolla:
```
mvn compile exec:java -Dexec.mainClass=minesweeper.ui.Main
```
tai sitten jar-komennolla:
```
java -jar target/Minesweeper-7.0.jar 
```


testaus:
```
mvn test
```


testiraportin suoritus:
```
mvn test jacoco:report
```
raporttia voi tarkastella avaamalla selaimella tiedosto target/site/jacoco/index.html



suoritettavan jarin generointi:
```
mvn package
```
generoi hakemistoon target suoritettavan jar-tiedoston Minesweeper-1.0-SNAPSHOT.jar



checkstyle-tarkastuksen suorittaminen:
```
mvn jxr:jxr checkstyle:checkstyle
```
Virheilmoituksia voi tarkastella avaamalla selaimella tiedoston target/site/checkstyle.html



javaDocin generointi:
```
mvn javadoc:javadoc
```
JavaDocia voi tarkastella avaamalla selaimella tiedosto target/site/apidocs/index.html

Huomaa, että komennon ajaminen saattaa vaatia JAVA_HOME-ympäristömuuttujan asettamisen, esimerkiksi seuraavasti: 
```
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
```


## Dokumentaatio

[Vaatimusmäärittely](https://github.com/jullebli/ot-harjoitustyo/blob/master/dokumentaatio/vaatimusmaarittely.md)

[Työaikakirjanpito](https://github.com/jullebli/ot-harjoitustyo/blob/master/dokumentaatio/tuntikirjanpito.md)

[Arkkitehtuurikuvaus](https://github.com/jullebli/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Käyttöohje](https://github.com/jullebli/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

[Testausdokumentti](https://github.com/jullebli/minesweeper/blob/master/dokumentaatio/testaus.md)


## Releaset

[Viikon 5 release](https://github.com/jullebli/ot-harjoitustyo/releases/tag/viikko5)

[Viikon 6 release](https://github.com/jullebli/ot-harjoitustyo/releases/tag/viikko6)

[loppupalautus](https://github.com/jullebli/minesweeper/releases/tag/loppupalautus)
