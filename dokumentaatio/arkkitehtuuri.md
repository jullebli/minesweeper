## Arkkitehtuurikuvaus

### Rakenne

Sovelluksen pakkausrakenteessa on kaksi tasoa: minesweeper.domain ja minesweeper.ui.

Pakkaus minesweeper.ui sisältää tekstikäyttöliittymän ja graafisen käyttöliittymän. Minesweeper.domain sisältää sovelluslogiikasta huolehtivan Game-luokan. Tekstikäyttöliittymä on vain sovelluksen testaamista varten.

### Käyttöliittymä

Graafisessa käyttöliittymässä on kaksi erilaista näkymää:
- aloitusnäkymä
- pelinäkymä

Graafinen käyttöliittymä on toteutettu javafx:llä. Käyttöliittymä on pyritty eriyttämään sovelluslogiikasta. Käyttöliittymä vain kutsuu Game-luokan metodeja. Se ei pidä tallessa pelin tilaa vaan tämä tieto on Game-luokassa.

MinesweeperUI luo kaksi erillistä näkymää erillisinä luokkina (SetupView, PlayView). Nämä luokat tarjoavat getView-metodin, joka palauttaa BorderPane-olion, jonka MinesweeperUI-luokka voi asettaa Scenessä olevan päätaso BorderPanen sisään. MinesweeperUI-luokka luo alussa SetupView-olion ja jokaista peliä varten luodaan erillinen PlayView-olio.

#### Aloitusnäkymä (SetupView)

Aloitusnäkymässä voi luoda uuden pelin määrittämällä miinakentän leveyden ja korkeuden. Siinä voi myös jatkaa tiedostoon tallennettua peliä.

Luokka tarjoaa setOnSetupComplete-metodin, jolla voi asettaa käsittelijän (SetupCompleteEventHandler), jota kutsutaan kun peli on valmis alkamaan. Tällainen toimintatapa mahdollistaa sen, että näkymien välisen siirtymän sovelluslogiikan voi määrittää yhdessä paikassa MinesweeperUI-luokassa.

Näkymä huolehtii syötteiden järkevyyden tarkastamisesta ja virhetilanteiden ilmoittamisesta pelaajalle. Näkymään tulee virhetilanteen aiheuttajan lähelle punainen teksti, mikä paljastaa virhetilanteen ja pelaaja voi korjata syötettä tai toimintaansa.

#### Pelinäkymä (PlayView)

Pelinäkymä ottaa parametrinaan Game-olion. Jokaista peliä varten luodaan uusi näkymä. Miinakenttä näytetään nappeina, joiden klikkaaminen hiiren vasemmalla näppäimellä kutsuu Game-luokan open-metodia ja klikkaaminen hiiren oikealla näppäimellä kutsuu Game-luokan setFlag-metodia.

Pelinäkymä tarjoaa mahdollisuuden aloitusnäkymään siirtymiseen start over -napin avulla. Napin painaminen kutsuu MinesweeperUI-luokan asettamaa tapahtumankäsittelijää, joka vaihtaa näkymää. Pelinäkymä tarjoaa nappien avulla myös toiminnot pelin lopettamiseen ja tallentamiseen.

Näkymä huolehtii virhetilanteista ja tiedoston tallentamiseen liittyvistä virhetilanteista tulee punainen teksti tallentamisen epäonnistumisesta. Tallentamisen onnistuessa näkyviin tulee vihreä teksti, joka vahvistaa tallennuksen onnistumisen. Näkymä pyytää käyttäjältä vahvistusta jos halutaan palata aloitusnäkymään tai poistua kesken pelin.

### Sovelluslogiikka

Sovelluslogiikka on kokonaan Game-luokassa. Luokka pitää kirjaa muun muassa miinojen, lippujen ja avattujen ruutujen paikoista. Luokka tarjoaa metodit ruutujen avaamiseen ja lippujen lisäåmiseen sekä poistamiseen. Ruutujen avaaminen toimii rekursiivisesti, jos avatun ruudun ympärillä ei ole yhtään miinaa. Eli silloin myös ruudun viereiset ruudut avataan. Tätä toistetaan kunnes vastaan tulee ruutu, jonka vieressä on miina. Tämä onnistuu sen takia, että luokka tarjoaa metodin ruudun ympärillä olevien miinojen laskemiseen.

Luokassa on metodi isMine, jolla käyttöliittymä voi tarkastaa onko ruudussa miina. Tätä metodia käytetään vasta pelin päättyessä kun pelinäkymä näyttää miinojen sijainnit miinakentässä. Luokka tarjoaa myös metodin isRunning, jolla selvitetään onko peli vielä käynnissä ja voitosta kertovan metodin isVictory.

Luokalla on kolme konstruktoria. Konstruktorilla Game(width, heigth) voi luoda pelin satunnaisilla miinan sijainneilla. Miinoja on noin 10%:ssa ruutuja miinakentän pinta-alasta. Konstruktorilla Game(String[] minemap) luodaan peli, jota käytetään vain yksikkötesteissä. Konstruktori Game(String filename) luo pelin tiedostosta.

Luokka tarjoaa metodin, jolla pelin tilan voi tallentaa tiedostoon. 


### Tallennettujen pelien tiedostomuoto (.minesave)

Tallennettujen pelien tiedostopääte on .minesave. Nämä tiedostot ovat yksinkertaisia tekstitiedostoja. Tiedostorakenne on riveittäin seuraava:

- Tiedostomuodon tunniste: "Minesweepersavegame"
- Versionumero kokonaislukuna
- Miinojen paikat taulukkona
- Avattujen ruutujen paikat taulukkona
- Lippujen paikat taulukkona

Ennen kutakin taulukkoa on korkeus ja leveys omilla riveillään. Taulukon rivit koostuvat nollista ja ykkösistä. Taulukon rivien määrä kertoo miinakentän korkeuden ja rivin lukujen määrä leveyden.

Tässä on esimerkki tallennetun pelin tiedoston sisällöstä:

```
Minesweepersavegame
1
10
10
0010100010
1000000100
0000000000
0000000000
0100000000
0001000000
0000001000
0000000000
0000000010
0001000000
10
10
0000000000
0111111011
0111111111
0111111111
0011111111
0000111111
0000000111
0000000111
0000000000
0000000000
10
10
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
0000000000
```
    
### Päätoiminnallisuudet

Tässä luvussa kuvataan sanallisesti sovelluksen päätoiminnallisuuksia.

#### Uuden pelin aloittaminen

Kun aloitusnäkymään on syötetty sallittu leveys ja sallittu korkeus sekä on painettu "Start"-nappia niin sovelluksen suoritus etenee seuraavasti:

1. SetupView tarkistaa arvojen järkevyyden.
2. SetupView luo Game-olion game, joka alustaa itsensä ja asettaa miinat satunnaisiin paikkoihin.
3. SetupView kutsuu MinesweeperUI:n määrittämää SetupCompleteHandler-rajapinnan toteuttavaa käsittelijää ja sen handle-metodia ja antaa sille parametrina SetupCompleteEvent-olion, jolle on annettu parametrina juuri luodun gamen.
4. MinesweeperUI luo uuden PlayView-olion ja antaa sille parametriksi SetupCompleteEvent-olion Game-olion ja start metodin parametrina saadun Stage-olion.
5. MinesweeperUI asettaa PlayView-oliolle setOnStartOver-metodilla käsittelijän (StartOverEventHandler).
6. MinesweeperUI asettaa sen asetteluelementin (BorderPane) keskelle pelinäkymän getView-metodin paluuarvon. getView luo pelinäkymän käyttöliittymäkomponentit ja määrittää niiden käsittelijät jne.

#### Tallennetun pelin aloittaminen

Kun aloitusnäkymän "Load saved game"-nappia on painettu niin sovelluksen suoritus etenee seuraavasti:

1. SetupView luo uuden FileChooser-olion ja kutsuu sen showOpenDialog-metodia, joka pyytää pelaajaa valitsemaan tiedoston avautuneesta ikkunasta. Metodi palaa vasta kun valinta on tehty.
2. SetupView tarkastaa, että pelaaja todella valitsi tiedoston.
3. SetUpView luo Game-olion parametrina tiedosto. Game avaa tiedoston, lukee sieltä tiedot ja alustaa itsensä.
4. Suoritus etenee kuten kohdassa "Uuden pelin aloittaminen".

#### Ruudun avaaminen

Kun peli on luotu ja pelaaja klikkaa miinakentän ruutua hiiren vasemmalla näppäimellä niin sovelluksen suoritus etenee seuraavasti:

1. PlayView tarkistaa ensin Game-luokan olioltaan, että peli on yhä käynnissä kutsumalla isRunning-metodia.
2. Peli on käynnissä. Joten sitten tarkastetaan onko painetussa ruudussa lippua. Ei ole.
3. PlayView kutsuu Game-olionsa open-metodia.
4. open-metodi tarkastaa onko ruutua jo avattu. Ei ole.
5. open-metodi asettaa ruudun tilan avatuksi ja kasvattaa avattujen ruutujen määrää yhdellä.
6. open-metodi tarkastaa onko avatussa ruudussa miina. Jos ruudussa olisi miina, niin running ja victory asetettaisiin falseksi. Mutta tässä ruudussa ei ollut miinaa.
7. open-metodi tarkistaa täyttyvätkö voiton ehdot eli on avattu kaikki miinattomat ruudut. Ei täyty.
8. open-metodi kutsuu metodia openNeighbouringSquares ruudun koordinaateilla.
9. openNeighbouringSquares-metodi kutsuu countMines-metodia selvittääkseen onko ruudun ympärillä miinoja. Tässä tapauksessa miinoja ei ole.
10. openNeighbouringSquares-metodi kutsuu open-metodia kaikille viereisille ruuduille, sillä ruudun ympärillä ei ollut miinoja.
11. open-metodi ja openNigbourinSquares-metodi kutsuvat toisiaan rekursiivisesti niin kauan kuin löytyy ruutuja, joiden vieressä ei ole yhtäkään miinaa.
12. Lopulta palataan open-metodiin joka palauttaa tiedon siitä onko peli vielä käynnissä.
13. PlayView kutsuu updateView-metodia
14. UpdateView-metodi tarkastaa onko peli käynnissä. Peli on käynnissä.
15. updateView-metodi asettaa jokaisen ruudun Labelin vastaamaan ruudun tilaa. Mikäli ruutu on avaamaton, tekstiksi asetetaan kysymysmerkki. Mikäli ruudussa on lippu, asetetaan tesktiksi "F". Mikäli ruutu on avattu, niin tekstiksi asetetaan viereisissä ruuduissa olevien miinojen määrä Game-olion countMines-metodin perusteella tai tesktiksi tyhjä mikäli ympäröiviä miinoja ei ole. Mikäli peli on päättynyt ja ruudussa on miina, niin se merkitään tähdellä.

#### Muut toiminnot

Lipun lisääminen ja poistaminen toimii suurin piirtein samoin kuin ruudun avaaminen, mutta open-metodia ei kutsuta ja ruutua ei avata. Ruutuun tulee teksti "F" tai jos siinä oli jo lippu niin sitten kirjain poistuu.

Tallentaminen toimii PlayViewissä kuten avaaminen SetupViewissä. Sovellus avaa ikkunan, jossa valitaan tallennuspaikka ja tiedoston nimi. Peliä voi jatkaa tallentamisen jälkeen.


### Sovelluksen rakenteeseen jääneet heikkoudet

Käyttöliittymän ulkoasuun ei ole käytetty paljoa aikaa, mutta sovelluslogiikan ja syötteenkäsittelyn pitäisi olla kunnossa. Jos käyttöliittymää haluaa parantaa niin kannattaa mahdollisesti käyttää FXML-määrittelyä. Tiedostoon kirjoittamista ja lukemista ei ole eriytetty sovelluslogiikasta, tämä mahdollistaisi Game-luokan lyhentämisen.
