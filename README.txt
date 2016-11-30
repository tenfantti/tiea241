LaGoodLife- tekstiseikkailupeli. 
Tekijät Janita Sallanko ja Anette Karhu.

Toteutus:
Työskentelimme aina yhdessä harjoitustyön parissa. Suunnitelma tehtiin yhdessä.
Janita kirjoitti koodia ja Anette haki tietoa ja auttoi vieressä.

Ohjelmasta:
Ohjelmassa käytetään java.io ja java.util -kirjastoja. 
Kääntäjä ei välttämättä ota huomioon suomenkielen kirjaimia.
Ohjelma on toteutettu Eclipse Mars:lla ja kirjoitettu Javalla.

Ohjeet pelin pelaamiselle:
Peli alkaa taustatiedoilla ja pyytää käyttäjää valitsemaan mitä aikoo tehdä, esitellyillä 
vaihtoehdoilla.

Läpipelausohje peliin:
Komennot joilla pelissä pääsee eteenpäin, on laitettu järjestykseen 
ranskalaisilla viivoilla.

Tapa 1: Onnistunut nopea tenttiin lähtö.
-Nouse ylös
-mene tenttiin

-> Näin pääset nopeiten tenttiin ja voitat.

Tapa 2:
-nouse ylös
-keitä kahvia
-unohda kahvi ja juokse

-> Pääset tenttiin ajoissa.

Tapa 3:
-nouse ylös
-keitä kahvia
-ota kahvi
-juokse tenttiin

-> Kerkeät tenttiin.

Kaikki muut vaihtoehdot johtavat yliopistolaisen kuolemaan, eli häviöön.

Pääpiirteiden esittely:
Loimme pelille kontekstittoman kieliopin ja DFA:n joka noudattaa luomaamme kielioppia.

Kontekstiton kielioppimme on alla olevaa muotoa:
S ->[negaatio]verbi[rinnastuskonjuktio]kohde
verbi ->kato|painat|jatka|nouset|jää|mene|keitä|juo|ota|unohda|juokse|ole|nukkumaan
kohde ->tentti|keittiö|kahvi|kello|torkku|auto|pyörä
rinnastuskonjuktio ->ja
negaatio ->ei 





