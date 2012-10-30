package com.platzerworld.weihnachtsmarkt.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.platzerworld.weihnachtsmarkt.dao.EMFService;
import com.platzerworld.weihnachtsmarkt.model.Biergarten;
import com.platzerworld.weihnachtsmarkt.vo.BiergartenJSON;
import com.platzerworld.weihnachtsmarkt.vo.BiergartenVO;

// http://biergartenservice.appspot.com/platzerworld/biergarten/holebiergarten

@Path("/biergarten/")
public class BiergartenService {
	@GET
	@Produces("text/plain")
	@Path("/insertbiergarten")
	public String insertBiergarten() {
		EntityManager em = null;
		Biergarten bg;	
		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Zum Aumeister";
		bg.strasse="Sondermeierstrasse 1";
		bg.plz="80939";
		bg.ort="München (Schwabing)";
		bg.telefon="089 / 18 93 142 0";
		bg.email="aumeister@aumeister.de";
		bg.url="www.aumeister.de";
		bg.latitude="48,185977";
		bg.longitude="11,620038";
		bg.desc="Am Nordrand des Englischen Gartens gelegen";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Hirschgarten";
		bg.strasse="Hirschgarten 1";
		bg.plz="80639";
		bg.ort="München (Neuhausen)";
		bg.telefon="089/17 99 91 19";
		bg.email="";
		bg.url="www.hirschgarten.de";
		bg.latitude="48,149882";
		bg.longitude="11,511167";
		bg.desc="Größter Biergarten, ein echter Klassiker";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Augustinerkeller";
		bg.strasse="Arnulfstr. 52";
		bg.plz="80335";
		bg.ort="München";
		bg.telefon="089 594393";
		bg.email="";
		bg.url="www.augustinerkeller.de";
		bg.latitude="48,149925";
		bg.longitude="11,511156";
		bg.desc="Ideal, um vor der Hektik in der Innenstadt abzutauchen";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Chinesischer Turm";
		bg.strasse="Englischer Garten 3";
		bg.plz="80538";
		bg.ort="München";
		bg.telefon="089-38387320";
		bg.email="";
		bg.url="www.chinaturm.de";
		bg.latitude="48,152447";
		bg.longitude="11,591912";
		bg.desc="Bunter Biergarten im Herzen des Englischen Gartens";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Erdinger Weißbiergarten";
		bg.strasse="Heiglhofstr. 12";
		bg.plz="81377";
		bg.ort="München (Großhadern)";
		bg.telefon="089-7194300";
		bg.email="";
		bg.url="www.gasthaus-zum-erdinger-weissbraeu.de";
		bg.latitude="48,112957";
		bg.longitude="11,478772";
		bg.desc="Wunderbar schattig unter einem Dach aus Kastanien";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Franziskanergarten";
		bg.strasse="Friedenspromenade 45";
		bg.plz="81827";
		bg.ort="München";
		bg.telefon="089-4300996";
		bg.email="";
		bg.url="www.franziskanergarten.de";
		bg.latitude="48,109452";
		bg.longitude="11,670973";
		bg.desc="Klassiker unter den Biergärten mit Hausmannskost";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Freihamer Wirtshaus";
		bg.strasse="Freihamer Allee 21";
		bg.plz="81249";
		bg.ort="München (Freiham)";
		bg.telefon="089-89746991";
		bg.email="";
		bg.url="www.freihamer-wirtshaus.de";
		bg.latitude="48,137055";
		bg.longitude="11,402382";
		bg.desc="Ideal für einen Ausflug mit dem Radl";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Harlachinger Einkehr";
		bg.strasse="Karolinger-Allee 34";
		bg.plz="81545";
		bg.ort="München (Harlaching)";
		bg.telefon="089-64209093";
		bg.email="";
		bg.url="www.harlachinger-einkehr.de";
		bg.latitude="48,095693";
		bg.longitude="11,557966";
		bg.desc="Zur Happy-Hour von 16 bis 18 Uhr und ab 22 Uhr Mass Bier 5 €";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Hinterbrühl";
		bg.strasse="Hinterbrühl 2";
		bg.plz="81479";
		bg.ort="München (Thalkirchen)";
		bg.telefon="089-794494";
		bg.email="";
		bg.url="www.www.gasthof-hinterbrühl.de";
		bg.latitude="48,086409";
		bg.longitude="11,54069";
		bg.desc="Reich von Ex-Löwenkönig Wildmooser, urig und schön";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Hirschau";
		bg.strasse="Gyßlingerstr. 15";
		bg.plz="80805";
		bg.ort="München (Schwabing)";
		bg.telefon="089-3221080";
		bg.email="";
		bg.url="www.hirschau-muenchen.de";
		bg.latitude="48,162031";
		bg.longitude="011,602587";
		bg.desc="Treffpunkt der Müncher Familien mit Kindern";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Hofbräukeller";
		bg.strasse="Innere Wiener Str. 19";
		bg.plz="81667";
		bg.ort="München (Haidhausen)";
		bg.telefon="089-4599250";
		bg.email="";
		bg.url="www.hofbraeukeller.de";
		bg.latitude="48,134121";
		bg.longitude="11,595277";
		bg.desc="Biergarten-Oase mit Sausalitos-Sommerbar";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Hopfengarten";
		bg.strasse="Siegenburger Str. 43";
		bg.plz="81373";
		bg.ort="München (Westpark)";
		bg.telefon="089-7608846";
		bg.email="";
		bg.url="www.hopfen-garten.de";
		bg.latitude="48,125481";
		bg.longitude="11,526718";
		bg.desc="Gemütlicher Biergarten mit einem große Spielplatz";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Iberl";
		bg.strasse="Wilhelm-Leibl-Str. 22";
		bg.plz="81479";
		bg.ort="München";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,070996";
		bg.longitude="11,5149";
		bg.desc="Traumplatz an der Würm, ideal für Familienausflüge";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Laimer's";
		bg.strasse="Agricolastr. 16";
		bg.plz="80687";
		bg.ort="München";
		bg.telefon="089 5466401";
		bg.email="";
		bg.url="www.laimers.com";
		bg.latitude="48,141234";
		bg.longitude="11,492523";
		bg.desc="Gemütlicher versteckter Biergarten mit angesagter Bar";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Langwieder See";
		bg.strasse="Kreuzkapellenstr. 89";
		bg.plz="81249";
		bg.ort="München (Langwied)";
		bg.telefon="089-864860";
		bg.email="";
		bg.url=" www.langwiedersee.de";
		bg.latitude="48,197735";
		bg.longitude="11,414366";
		bg.desc="Direkt am Seeufer gelegen, ideal nach einem Badetag";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Leiberheim";
		bg.strasse="Nixenweg 9";
		bg.plz="81739";
		bg.ort="München (Waldperlach)";
		bg.telefon="089-4300000";
		bg.email="";
		bg.url="www.leiberheim.de";
		bg.latitude="48,078467";
		bg.longitude="11,671759";
		bg.desc="Familienparadies, verschweigen, klassisch";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Löwenbräukeller";
		bg.strasse="Nymphenburgestr. 2";
		bg.plz="80335";
		bg.ort="München (Neuhausen)";
		bg.telefon="089-54726690";
		bg.email="";
		bg.url="www.loewenbraeukeller.com";
		bg.latitude="48,148264";
		bg.longitude="11,558626";
		bg.desc="Sehr sonnig";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Max-Emanuel-Brauerei";
		bg.strasse="Adalbertstr. 33";
		bg.plz="80779";
		bg.ort="München (Schwabing)";
		bg.telefon="089-2715158";
		bg.email="";
		bg.url="www.max-emanuel-brauerei.de";
		bg.latitude="48,152572";
		bg.longitude="11,575953";
		bg.desc="An der Uni, kein typischer Biergarten";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Maxhof";
		bg.strasse="Mühlthalerstr. 91";
		bg.plz="81475";
		bg.ort="München (Maxhof)";
		bg.telefon="089-75698870";
		bg.email="";
		bg.url="www.gasthaus-maxhof.de";
		bg.latitude="48,086563";
		bg.longitude="11,478682";
		bg.desc="In der Nähe des Forstenrieder Parks, mit Spielplatz";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Gutshof Menterschwaige";
		bg.strasse="Menterschwaigstr. 4";
		bg.plz="81545";
		bg.ort="München (Harlaching)";
		bg.telefon="089-640732";
		bg.email="www.menterschwaige.de";
		bg.url="";
		bg.latitude="48,081399";
		bg.longitude="11,545245";
		bg.desc="Urbild eines Ausflugsbiergartens hoch über der Isar";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Michaeli-Garten";
		bg.strasse="Feichstr. 10";
		bg.plz="81735";
		bg.ort="München (Neuperlach)";
		bg.telefon="089-43552424";
		bg.email="";
		bg.url="www.michaeligarten.de";
		bg.latitude="48,115355";
		bg.longitude="11,638518";
		bg.desc="Am Ufer des Ostpark-Sees, schön zum Entspannen";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Muffatwerk";
		bg.strasse="Zellstraße 4";
		bg.plz="81667";
		bg.ort="München (Haidhausen)";
		bg.telefon="+49-(0)89-45875073";
		bg.email="";
		bg.url="www.muffatwerk.de/de/pages/biergarten";
		bg.latitude="48,132973";
		bg.longitude="11,588972";
		bg.desc="Schattige Plätze unter Münchens größter Markise";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Münchner Haupt";
		bg.strasse="Zielstattstr. 6";
		bg.plz="81379";
		bg.ort="München (Sendling)";
		bg.telefon="089-786940";
		bg.email="";
		bg.url="www.muenchnerhaupt.de";
		bg.latitude="48,104576";
		bg.longitude="11,535119";
		bg.desc="Traditioneller Biergarten mit großer Essensauswahl";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Park Cafè";
		bg.strasse="Sophienstr. 7";
		bg.plz="80333";
		bg.ort="München (Innenstadt)";
		bg.telefon="089-51617980";
		bg.email="";
		bg.url="www.parkcafe089.de";
		bg.latitude="48,142478";
		bg.longitude="11,564746";
		bg.desc="Schönes Wirtshaus, schöne Terrasse, Liegestühle";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Paulaner Bräuhaus";
		bg.strasse="Kapuziner Platz 5";
		bg.plz="80337";
		bg.ort="München (Sendling)";
		bg.telefon="089-5446110";
		bg.email="";
		bg.url="www.paulaner-brauhaus.com/muenchen/facilities";
		bg.latitude="48,126479";
		bg.longitude="11,558783";
		bg.desc="Ein echter Stadtgarten, verschiedene Saisonbiere";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Paulaner am Nockerberg";
		bg.strasse="Hochstr. 77";
		bg.plz="81541";
		bg.ort="München (Giesing)";
		bg.telefon="089-4599130";
		bg.email="";
		bg.url="www.nockherberg.com";
		bg.latitude="48,121668";
		bg.longitude="11,582175";
		bg.desc="Berühmt, moderne Service-Straße, alte Bäume";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Prinzregent-Garten";
		bg.strasse="Benedikterstraße 35";
		bg.plz="81241";
		bg.ort="München (Pasing)";
		bg.telefon="089-8202760";
		bg.email="";
		bg.url="www.prinzregentgarten.de";
		bg.latitude="48,140645";
		bg.longitude="11,468852";
		bg.desc="Nobler Eingang, dahinter lockt Gemütlichkeit";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Rosengarten";
		bg.strasse="Westendstr. 305";
		bg.plz="81377";
		bg.ort="München";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,123122";
		bg.longitude="11,508018";
		bg.desc="Schattig, großer Abenteuerspielplatz in der Nähe";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Seehaus";
		bg.strasse="Kleinhesselohe 2";
		bg.plz="80802";
		bg.ort="München (Schwabing)";
		bg.telefon="089-3816130";
		bg.email="";
		bg.url="www.kuffler-gastronomie.de/de/muenchen/seehaus/index.php";
		bg.latitude="48,160504";
		bg.longitude="11,598365";
		bg.desc="Nicht nur für Promis, Traumlage am See";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Spektakel";
		bg.strasse="Pfeuferstr. 32";
		bg.plz="81737";
		bg.ort="München (Sendling)";
		bg.telefon="089-76758359";
		bg.email="";
		bg.url="www.spektakel-muenchen.de";
		bg.latitude="48,123315";
		bg.longitude="11,541197";
		bg.desc="Gemütlicher Nachbarschafts-Biergarten";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="St. Emmeramsmühle";
		bg.strasse="St. Emmeram 41";
		bg.plz="81925";
		bg.ort="München";
		bg.telefon="089 953971";
		bg.email="";
		bg.url="www.emmeramsmuehle.de";
		bg.latitude="48,176019";
		bg.longitude="11,627263";
		bg.desc="Klassischer Biergarten mit schönen, alten Gemäuers";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Taxisgarten";
		bg.strasse="Taxisstr. 12";
		bg.plz="80637";
		bg.ort="München (Neuhausen)";
		bg.telefon="089-156827";
		bg.email="";
		bg.url="www.taxisgarten.de";
		bg.latitude="48,162679";
		bg.longitude="11,532211";
		bg.desc="Traumhaft, um einen Abend zu genießen, sehr beliebt";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Viktualienmarkt";
		bg.strasse="Viktualienmarkt 6";
		bg.plz="80331";
		bg.ort="München (Innenstadt)";
		bg.telefon="089-297545";
		bg.email="";
		bg.url=" www.biergarten-viktualienmarkt.de";
		bg.latitude="48,135238";
		bg.longitude="11,57639";
		bg.desc="Mehr Flair geht nicht";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Villa Flora";
		bg.strasse="Hansastr. 44";
		bg.plz="80636";
		bg.ort="München";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,131672";
		bg.longitude="11,529931";
		bg.desc="Mediterranes Flair mitten in München, keine Selbstbedienug";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Waldheim";
		bg.strasse="Waldheim 1";
		bg.plz="81377";
		bg.ort="München";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,102735";
		bg.longitude="11,478862";
		bg.desc="Täglich wechselndes Tagsgericht, Bierpass";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Weyprechthof";
		bg.strasse="Max-Liebermann-Str. 6";
		bg.plz="80937";
		bg.ort="München (Am Hart-Harthof)";
		bg.telefon="089-3111950";
		bg.email="";
		bg.url="www.weyprechthof.de";
		bg.latitude="48,205563";
		bg.longitude="11,572136";
		bg.desc="U-Bahn-Anschluss (Harthof), Dorfcharakter";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Cirkuswiese";
		bg.strasse="Theresienhöhe 7";
		bg.plz="80339";
		bg.ort="München";
		bg.telefon="089-500593800";
		bg.email="";
		bg.url="www.hacker-pschorrbraeu.de";
		bg.latitude="48,136394";
		bg.longitude="11,548508";
		bg.desc="Der Biergarten Cirkuswiese befindet sich gleich neben der Theresienwiese und ist einer der am wenigsten besuchten Biergärten Münchens.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Wirtshaus Zamdorfer";
		bg.strasse="Schwarzwaldstr. 2a";
		bg.plz="81677";
		bg.ort="München (Bogenhausen)";
		bg.telefon="089-916921";
		bg.email="";
		bg.url="www.zamdorfer.de";
		bg.latitude="48,139913";
		bg.longitude="11,628815";
		bg.desc="Lauschiger Biergarten am Rand von Bogenhausen";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Zum Grünen Baum";
		bg.strasse="Verdistr. 47";
		bg.plz="81247";
		bg.ort="München (Obermenzing)";
		bg.telefon="089-81089314";
		bg.email="";
		bg.url="";
		bg.latitude="48,163986";
		bg.longitude="11,475914";
		bg.desc="Schmuckes Wirtshaus, preisgünstiges Essen";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Zum Flaucher";
		bg.strasse="Isarauenstr. 8";
		bg.plz="81379";
		bg.ort="München (Thalkirchen)";
		bg.telefon="089-7232677";
		bg.email="";
		bg.url="www.zum-flaucher.de";
		bg.latitude="48,108487";
		bg.longitude="11,557472";
		bg.desc="Super Radl-Treff, sehr schöner Kinderspielplatz";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Zum Hirschen";
		bg.strasse="Sollner Str. 43";
		bg.plz="81479";
		bg.ort="München";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,08105";
		bg.longitude="11,526835";
		bg.desc="Familienfreundlicher Garten mit S-Bahn-Anschluss";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Zur Mühle";
		bg.strasse="Kirchplatz 5";
		bg.plz="85737";
		bg.ort="München";
		bg.telefon="089-960930";
		bg.email="";
		bg.url="www.hotel-muehle.de";
		bg.latitude="48,226636";
		bg.longitude="11,674572";
		bg.desc="Typischer Wirtsgarten mitten im Ort";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Alter Wirt";
		bg.strasse="Margaretenstr. 31";
		bg.plz="82152";
		bg.ort="Krailling";
		bg.telefon="089-89198444";
		bg.email="";
		bg.url="www.alterwirtkrailling.de";
		bg.latitude="48,098601";
		bg.longitude="11,417625";
		bg.desc="Schöner Traditionsbierbarten direkt an der Würm";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Alter Wirt Etterschlag";
		bg.strasse="Inninger Str. 6";
		bg.plz="82237";
		bg.ort="Etterschlag";
		bg.telefon="08153-8282";
		bg.email="alterwirt@t-online.de";
		bg.url="www.alter-wirt-etterschlag.de";
		bg.latitude="48,086114";
		bg.longitude="11,200545";
		bg.desc="Dorfbiergarten mit festen Holzbänken, Spielplatz";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Zur Aubinger Einkehr";
		bg.strasse="Größweinsteinplatz 7";
		bg.plz="81249";
		bg.ort="München (Neuaubing)";
		bg.telefon="089-875581";
		bg.email="";
		bg.url="www.zur-aubinger-einkehr.de";
		bg.latitude="48,143608";
		bg.longitude="11,416981";
		bg.desc="Traditioneller Biergarten, an Feiertagen gibt es Live-Musik";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Aujäger";
		bg.strasse="Austraße 4";
		bg.plz="82544";
		bg.ort="Puppling";
		bg.telefon="08171-78556";
		bg.email="";
		bg.url="www.aujaeger-puppling.de";
		bg.latitude="47,919466";
		bg.longitude="11,449878";
		bg.desc="Freundlich Raststation bei Wanderung oder Radtour";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Bartewirt";
		bg.strasse="Grubstr. 1";
		bg.plz="83626";
		bg.ort="83626 Valley/Kreuzstraße";
		bg.telefon="08024-7718";
		bg.email="";
		bg.url="www.bartewirt.de";
		bg.latitude="47,92075";
		bg.longitude="11,755291";
		bg.desc="Schmucker Biergarten mit guter S-Bahn-Anbindung";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Bergl";
		bg.strasse="Bergl 1";
		bg.plz="85764";
		bg.ort="Oberschleißheim";
		bg.telefon="089-3150105";
		bg.email="";
		bg.url="http://www.hacker-pschorr.de/freizeit/gastroguide/details.php?gastroID=542";
		bg.latitude="48,260454";
		bg.longitude="11,56782";
		bg.desc="Idyllischer Platz mitten im Wald, rustikales Flair";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Brauhaus Bruck";
		bg.strasse="Augsburger Str. 41";
		bg.plz="82256";
		bg.ort="Fürstenfeldbruck";
		bg.telefon="+49-(0)8141-25490";
		bg.email="";
		bg.url="www.brauhaus-bruck.de";
		bg.latitude="48,182759";
		bg.longitude="11,252339";
		bg.desc="Brauerei-Biergarten, großer Spielplatz";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Bräustüberl Weihenstephan";
		bg.strasse="Weihenstephaner Berg 10";
		bg.plz="85354";
		bg.ort="Freising (Weihenstephan)";
		bg.telefon="08161-13004";
		bg.email="";
		bg.url="www.braeustueberl-weihenstephan.de";
		bg.latitude="48,395787";
		bg.longitude="11,728761";
		bg.desc="Angeblich älteste Brauerei der Welt, seit 1040 in Betrieb.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Zum Bruckenfischer";
		bg.strasse="Dürnstein 1";
		bg.plz="82544";
		bg.ort="Egling";
		bg.telefon="08178-3635";
		bg.email="";
		bg.url="";
		bg.latitude="47,971996";
		bg.longitude="11,476609";
		bg.desc="Treff für Radler und Wanderer direkt an der Isar.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Dampfschiff";
		bg.strasse="Graf-Rasso-Straße 40";
		bg.plz="82284";
		bg.ort="Grafrath";
		bg.telefon="+49-(0)8144-1314";
		bg.email="";
		bg.url="www.dampfschiff.com";
		bg.latitude="48,123302";
		bg.longitude="11,156391";
		bg.desc="Idylische Lage direkt an der Amper.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Diablo";
		bg.strasse="Römerstr. 11";
		bg.plz="82205";
		bg.ort="Gilching";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,107539";
		bg.longitude="11,301885";
		bg.desc="Mexikaner mit bayerischem Biergarten.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Einkehr zur Schwaige";
		bg.strasse="Forst-Kasten-Allee 114";
		bg.plz="81475";
		bg.ort="München (Fürstenried)";
		bg.telefon="+49-(0)89-7591968";
		bg.email="";
		bg.url="";
		bg.latitude="48,095154";
		bg.longitude="11,482942";
		bg.desc="Traditioneller Biergarten neben dem Schloss.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Emminger Hof";
		bg.strasse="Emminger Hof 1";
		bg.plz="86941";
		bg.ort="St. Ottilien";
		bg.telefon="08193-5238";
		bg.email="";
		bg.url="www.emminger-hof.de";
		bg.latitude="48,097058";
		bg.longitude="11,043558";
		bg.desc="Grandioser Blick auf Klostergarten und Gebirge.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Forsthaus Kasten";
		bg.strasse="Im Forstenrieder Park";
		bg.plz="82131";
		bg.ort="Gauting Forst Kasten";
		bg.telefon="089-8500360";
		bg.email="";
		bg.url=" www.forst-kasten.de";
		bg.latitude="48,075432";
		bg.longitude="11,416919";
		bg.desc="Ruhiger Ausflugsbiergarten auf schöner Lichtung.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Fürstenfelder";
		bg.strasse="Fürstenfeld 15";
		bg.plz="82256";
		bg.ort="Fürstenfeldbruck";
		bg.telefon="08141-88875-410";
		bg.email="";
		bg.url="www.fuerstenfelder.com";
		bg.latitude="48,172702";
		bg.longitude="11,252245";
		bg.desc="Blick aufs Kloster, überweigend Bioprodukte.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Gasthof Grub";
		bg.strasse="Parsdorfer Str. 5";
		bg.plz="85586";
		bg.ort="Poing-Grub";
		bg.telefon="089-9032336";
		bg.email="";
		bg.url="";
		bg.latitude="48,165051";
		bg.longitude="11,782354";
		bg.desc="Über 100 Jahre altes Wirtshaus mit viel Flair.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Gasthaus zur Mühle";
		bg.strasse="Mühltal 10";
		bg.plz="82064";
		bg.ort="Straßlach";
		bg.telefon="08178-3630";
		bg.email="";
		bg.url="www.gasthausmuehle.de";
		bg.latitude="47,997342";
		bg.longitude="11,485463";
		bg.desc="Paradiesisch gelegen zwischen Wald und Isar.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Gut Keferloh";
		bg.strasse="Keferloh 2";
		bg.plz="85630";
		bg.ort="Grasbrunn";
		bg.telefon="089-469248";
		bg.email="";
		bg.url="www.gut-keferloh.de";
		bg.latitude="48,095846";
		bg.longitude="11,727382";
		bg.desc="Traditionsbiergarten, klassische Einkehr.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Heide Volm";
		bg.strasse="Bahnhofstr. 51";
		bg.plz="82152";
		bg.ort="Planegg";
		bg.telefon="089-8572029";
		bg.email="";
		bg.url="www.heidevolm.de";
		bg.latitude="48,104191";
		bg.longitude="11,414583";
		bg.desc="Neu: Brezn-Backstüberl mit Blick in die Backstube.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Heimstettener See";
		bg.strasse="Am Heimstettener See";
		bg.plz="85609";
		bg.ort="Aschheim";
		bg.telefon="089-9031697";
		bg.email="";
		bg.url="www.heimstettenersee.de";
		bg.latitude="48,158131";
		bg.longitude="11,737542";
		bg.desc="Direkt an einem kleinen Badesee.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Insel-Mühle";
		bg.strasse="Von-Kahr-Str. 87";
		bg.plz="80999";
		bg.ort="München (Untermenzing)";
		bg.telefon="089-81010";
		bg.email="";
		bg.url="www.inselmuehle-muenchen.com";
		bg.latitude="48,176633";
		bg.longitude="11,462053";
		bg.desc="Traumplatz an der Würm, ideal für Familienausflüge.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Kloster Andechs Bräustüberl";
		bg.strasse="Bergstraße 2";
		bg.plz="82346";
		bg.ort="Erling-Andechs";
		bg.telefon="+49-(0)8152-3760";
		bg.email="";
		bg.url="www.andechs.de";
		bg.latitude="47,973729";
		bg.longitude="11,183481";
		bg.desc="Berühmt für sein Bier, der Aufstieg lohnt sich.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Klosterbräu Schäftlarn";
		bg.strasse="Kloster Schäftlarn 16";
		bg.plz="82067";
		bg.ort="Ebenhausen-Schäftlarn";
		bg.telefon="08178-3694";
		bg.email="";
		bg.url="www.klosterbraeustueberl-schaeftlarn.de";
		bg.latitude="47,978105";
		bg.longitude="11,466288";
		bg.desc="Im  Isartalgrund gelegen, sehr schöner Ausblick.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Kottmeier";
		bg.strasse="Bräuhausstr. 18";
		bg.plz="82152";
		bg.ort="Planegg";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,105576";
		bg.longitude="11,422949";
		bg.desc="Direkt am Ufer der Würm, große Essensauswahl.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Kugler-Alm";
		bg.strasse="Linienstr. 93";
		bg.plz="82041";
		bg.ort="Deisenhofen";
		bg.telefon="089-61390120";
		bg.email="";
		bg.url="www.kugleralm.de";
		bg.latitude="48,035897";
		bg.longitude="11,575076";
		bg.desc="Schöner Familenbiergarten, perfekt für Radl-Touren.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Landgasthof Alter Wirt";
		bg.strasse="Hauptstr. 36";
		bg.plz="85716";
		bg.ort="Unterschleißheim";
		bg.telefon="089-3707340";
		bg.email="";
		bg.url="www.alterwirt-ush.de";
		bg.latitude="48,277442";
		bg.longitude="11,567654";
		bg.desc="Staatliches denkmalgeschütztes Wirtshaus.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Schlossgaststätte Leutstetten";
		bg.strasse="Altostraße 11";
		bg.plz="82319";
		bg.ort="Starnberg (Leutstetten)";
		bg.telefon="08151-8156";
		bg.email="";
		bg.url="www.schlossgaststaette-leutstetten.de";
		bg.latitude="48,029872";
		bg.longitude="11,368519";
		bg.desc="Ideal für Wanderungen, griabig und schattig.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Lochhamer's";
		bg.strasse="Lochhamer Str. 4";
		bg.plz="82166";
		bg.ort="Gräfelfing";
		bg.telefon="089-85484651";
		bg.email="";
		bg.url="";
		bg.latitude="48,125668";
		bg.longitude="11,442231";
		bg.desc="Von Wasserrad angetriebener Hendlgrill.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Maisinger Seehof";
		bg.strasse="Seestr. 14";
		bg.plz="82343";
		bg.ort="Maising-Pöcking";
		bg.telefon="08151-744242";
		bg.email="";
		bg.url="";
		bg.latitude="47,981658";
		bg.longitude="11,282774";
		bg.desc="Der Biergarten Maisinger Seehof befindet sich direkt am Maisinger See.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Plantage!";
		bg.strasse="Plantage 2";
		bg.plz="85354";
		bg.ort="Freising";
		bg.telefon="08161-63155";
		bg.email="";
		bg.url="www.plantage-freising.de";
		bg.latitude="48,416436";
		bg.longitude="11,727771";
		bg.desc="Kleinod im Wald, an Sonn- und Feiertagen Live-Musik.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Räuber-Kneißl-Garten";
		bg.strasse="Hauptstr. 24";
		bg.plz="82216";
		bg.ort="Maisach";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,217029";
		bg.longitude="11,263553";
		bg.desc="Bekann für Kneißl Dunkel, wird hier frisch gebraut.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Schloßwirtschaft Oberschleißheim";
		bg.strasse="Maximilianshof 2";
		bg.plz="85764";
		bg.ort="Oberschleißheim";
		bg.telefon="089-3151555";
		bg.email="";
		bg.url="www.schlosswirtschaft-oberschleissheim.de";
		bg.latitude="48,248117";
		bg.longitude="11,558886";
		bg.desc="Alte Kastanien, sehr schattig, Blick aufs Schloss.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Schlosswirtschaft Mariabrunn";
		bg.strasse="Gut Mariabrunn 3";
		bg.plz="85244";
		bg.ort="Röhrmoos";
		bg.telefon="08139-8661";
		bg.email="";
		bg.url="www.schlosswirtschaft-mariabrunn.de";
		bg.latitude="48,312658";
		bg.longitude="11,478068";
		bg.desc="Natur, Kirche, Brauerei, Ruhe - kurz: Traumgarten.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Schweiger's Landgasthof";
		bg.strasse="Manhartsdorf 2-4";
		bg.plz="85456";
		bg.ort="Wartenberg";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,388791";
		bg.longitude="11,94609";
		bg.desc="Ein idyllischer Kasteniengarten am S-Bahn-Halt Grub.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Seehaus Schreyegg";
		bg.strasse="Landsbergerstr. 78";
		bg.plz="82266";
		bg.ort="Inning am Ammersee";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,077009";
		bg.longitude="11,132905";
		bg.desc="Innen sehr nobel, Tische direkt am Seeufer.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Seehof Herrsching";
		bg.strasse="Seestr. 58";
		bg.plz="82211";
		bg.ort="Herrsching";
		bg.telefon="+49-(0)8152-9350";
		bg.email="";
		bg.url="www.seehof-ammersee.de";
		bg.latitude="47,995226";
		bg.longitude="11,169225";
		bg.desc="Seepromenade führt mitten durch, Postkartenblick.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Seepost";
		bg.strasse="Bahnhofstraße 2";
		bg.plz="86938";
		bg.ort="Schondorf am Ammersee";
		bg.telefon="08192-933753";
		bg.email="";
		bg.url=" www.seepost-ammersee.de";
		bg.latitude="48,052764";
		bg.longitude="11,100346";
		bg.desc="Behäbiger Biergarten am Ammerseeufer mit Dampfersteg.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Tutzinger Biergarten";
		bg.strasse="Midgardstr. 3-5";
		bg.plz="82327";
		bg.ort="Tutzing";
		bg.telefon="+49-(0)8158-1216";
		bg.email="";
		bg.url="www.haering-wirtschaft.de";
		bg.latitude="47,913829";
		bg.longitude="11,288334";
		bg.desc="Nur durch den Uferweg vom See getrennt.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Waldgasthof Buchenhain";
		bg.strasse="Am Klettergarten 7";
		bg.plz="82065";
		bg.ort="Buchhain";
		bg.telefon="089-7930124";
		bg.email="";
		bg.url="www.hotelbuchenhain.de";
		bg.latitude="48,030548";
		bg.longitude="11,498265";
		bg.desc="Viele Brotzeiten, alte Bäume und viel Platz für Kinder.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Waldwirtschaft Großhesselohe";
		bg.strasse="Georg-Kalb-Str. 3";
		bg.plz="82049";
		bg.ort="Großhesselohe";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,067046";
		bg.longitude="11,5394";
		bg.desc="Südlich von München.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Bavariapark";
		bg.strasse="Theresienhöhe 15";
		bg.plz="80339";
		bg.ort="München";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,131635";
		bg.longitude="11,544043";
		bg.desc="Wirtshaus am Bavariapark mit Augustiner Biergarten auf der Theresienhöhe";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Concordia Park";
		bg.strasse="Landshuter Allee 165";
		bg.plz="80637";
		bg.ort="München (Neuhausen)";
		bg.telefon="089-155241";
		bg.email="";
		bg.url="";
		bg.latitude="48,163593";
		bg.longitude="11,535422";
		bg.desc="Concordia Park ist ein nicht so bekannter Biergarten im Münchner Stadtteil Neuhausen.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Neue Fasanerie";
		bg.strasse="Hartmannshofstraße 20";
		bg.plz="80997";
		bg.ort="München (Moosach)";
		bg.telefon="089-1495607";
		bg.email="";
		bg.url="www.neue-fasanerie.de";
		bg.latitude="48,173141";
		bg.longitude="11,493296";
		bg.desc="Das Restaurant Neue Fasanerie liegt idyllisch im Hartmannshofer Park.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Gasthaus Siebenbrunn";
		bg.strasse="Siebenbrunner Straße 5";
		bg.plz="81543";
		bg.ort="München (Thalkirchen)";
		bg.telefon="089-80033777";
		bg.email="";
		bg.url="www.biergarten-siebenbrunn.de";
		bg.latitude="48,099844";
		bg.longitude="11,560594";
		bg.desc=" Inmitten des grünen Landschaftsschutzgebietes Isarauen – direkt neben dem Tierpark Hellabrunn. Die längste Abendsonne genießen";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Tassilogarten";
		bg.strasse="Auerfeldstraße 18";
		bg.plz="81541";
		bg.ort="München";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,123296";
		bg.longitude="11,595444";
		bg.desc="Der Tassilogarten ist ein sonniger Treffpunkt in Haidhausen für ein gemischtes Publikum.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="’s Truderinger Wirtshaus";
		bg.strasse="Kirchtruderinger Straße 17";
		bg.plz="81829";
		bg.ort="München";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,1259";
		bg.longitude="11,674258";
		bg.desc="’s Truderinger Wirtshaus steht für große Portionen, nette Leute und vernünftige Preise.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Osterwaldgarten";
		bg.strasse="Keferstraße 12";
		bg.plz="80802";
		bg.ort="München";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,161828";
		bg.longitude="11,594308";
		bg.desc="Der Osterwaldgarten lockt ein buntes Publikum an – und biete gute Saisonküche. ";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Mangostin Garden";
		bg.strasse="Maria-Einsiedel-Straße 2";
		bg.plz="81379";
		bg.ort="München";
		bg.telefon="";
		bg.email="";
		bg.url="";
		bg.latitude="48,100173";
		bg.longitude="11,546052";
		bg.desc="Das Mangostin Garden kombiniert asiatische Küche mit bayerischer Tradition.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Alte Villa";
		bg.strasse="Seestraße 32";
		bg.plz="86919";
		bg.ort="Utting am Ammersee";
		bg.telefon="08806-534456";
		bg.email="";
		bg.url="www.alte-villa-biergarten.de";
		bg.latitude="48,026292";
		bg.longitude="11,098021";
		bg.desc="Der Biergarten Alte Villa befindet sich neben einer alten bayrischen Villa am Ammersee, südwestlich von München.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Alter Wirt Ramersdorf";
		bg.strasse="Aribonenstraße 8";
		bg.plz="81669";
		bg.ort="München (Ramersdorf)";
		bg.telefon="089-6891862";
		bg.email="";
		bg.url="www.alterwirt-muenchen.de";
		bg.latitude="48,11502";
		bg.longitude="11,61527";
		bg.desc="Den Alter Wirt Ramersdorf im Münchner Ortsteil Ramersdorf gibt es seit 1504";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Mühlenpark";
		bg.strasse="Mühlgasse 48";
		bg.plz="85748";
		bg.ort="Garching";
		bg.telefon="089-3204975";
		bg.email="";
		bg.url="www.biergarten-muehlenpark.de";
		bg.latitude="48,246272";
		bg.longitude="11,661435";
		bg.desc="Der Biergarten befindet sich auf dem Anwesen einer ehemaligen Holz- und Getreidemühle nähe Garching.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Bootshaus";
		bg.strasse="Zentralländstraße 16";
		bg.plz="81379";
		bg.ort="München (Thalkirchen)";
		bg.telefon="089-78017761";
		bg.email="";
		bg.url="";
		bg.latitude="48,098905";
		bg.longitude="11,547634";
		bg.desc="Der Bootshaus Biergarten befindet sich im Münchner Stadtteil Thalkirchen, unweit vom Tierpark und nur einen kleinen Fußmarsch von der Isar entfernt.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Brückerl";
		bg.strasse="Goteboldstr. 189";
		bg.plz="81249";
		bg.ort="München";
		bg.telefon="089-81896164";
		bg.email="";
		bg.url="www.brueckerl-langwied.de";
		bg.latitude="48,191536";
		bg.longitude="11,421795";
		bg.desc="Der sehr kleine Brückerl Biergarten befindet sich am Langwieder Bach unweit vom Langwieder See";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Fischer";
		bg.strasse="Landsberger Straße 79";
		bg.plz="82266";
		bg.ort="Stegen am Ammersee";
		bg.telefon="08143-447655";
		bg.email="";
		bg.url="www.fischer-stegen.com";
		bg.latitude="48,077281";
		bg.longitude="11.131932";
		bg.desc="Der Fischer Biergarten befindet sich direkt am nördlichsten Ufer vom Ammersee";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Forsthaus St. Hubertus";
		bg.strasse="St. Hubertus 1";
		bg.plz="85560";
		bg.ort="Ebersberg";
		bg.telefon="08092-8579996";
		bg.email="";
		bg.url="www.forsthaus-hubertus.de";
		bg.latitude="48,107992";
		bg.longitude="11,932561";
		bg.desc="Das Forsthaus St. Hubertus befindet sich inmitten des Ebersberger Forst's, cirka 35 Kilometer östlich von München.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Grünwalder Forstwirt";
		bg.strasse="Riedweg 41";
		bg.plz="82067";
		bg.ort="Straßlach";
		bg.telefon="08170-213";
		bg.email="";
		bg.url=" www.gruenwalder-forstwirt.de";
		bg.latitude="48,011819";
		bg.longitude="11,522553";
		bg.desc="Der Grünwalder Forstwirt befindet sich in idyllischer Lage auf einem 30.000m² Grundstück in Straßlach, 16 Kilometer vom Münchner Stadtzentrum entfernt.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Hofbrauhaus-Keller Freising";
		bg.strasse="Lankesbergstraße 5";
		bg.plz="85356";
		bg.ort="Freising";
		bg.telefon="08161-938800";
		bg.email="";
		bg.url="";
		bg.latitude="48,406286";
		bg.longitude="11,748317";
		bg.desc="Der Hofbrauhaus-Keller befindet sich in einer ruhigen Wohngegend am Lankesberg in Freising";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Waldwirtschaft Hohenlindener Sauschütt";
		bg.strasse="Sauschütt 1";
		bg.plz="85664";
		bg.ort="Hohenlinden";
		bg.telefon="08124-446478";
		bg.email="";
		bg.url="";
		bg.latitude="48,157115";
		bg.longitude="11,996542";
		bg.desc="Der Hohenlindener Sauschütt Biergarten befindet sich im östlichen Teil vom Ebersberger Forst, cirka 35 Kilometer östlich von München.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Kloster Andechs Biergarten";
		bg.strasse="Bergstraße 2";
		bg.plz="82346";
		bg.ort="Erling-Andechs";
		bg.telefon="08152-3760";
		bg.email="";
		bg.url="www.andechs.de";
		bg.latitude="47,973729";
		bg.longitude="11,183481";
		bg.desc="Der Biergarten von Kloster Andechs befindet sich innerhalb der Klostermauern von Andechs.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Kraillinger Brauerei";
		bg.strasse="Margaretenstraße 59";
		bg.plz="82152";
		bg.ort="Krailling";
		bg.telefon="089-8571718";
		bg.email="";
		bg.url="www.kraillinger-brauerei.de";
		bg.latitude="48,096369";
		bg.longitude="11,411099";
		bg.desc="Klassischer Biergarten direkt an der Würm in Krailling";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Landgasthof Langwied";
		bg.strasse="Waidachanger 9";
		bg.plz="81249";
		bg.ort="München";
		bg.telefon="089-89546184";
		bg.email="";
		bg.url="www.landgasthof-langwied.com";
		bg.latitude="48,181381";
		bg.longitude="11,423405";
		bg.desc="Der Landgasthof Langwied Hotel, Restaurant und Biergarten befindet sich im kleinen Dorf Langwied.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Lindengarten";
		bg.strasse="Solalindernstr. 50";
		bg.plz="81825";
		bg.ort="München";
		bg.telefon="+49-(0)89-430 9178";
		bg.email="info@lindengarten.eu";
		bg.url="www.lindengarten.eu";
		bg.latitude="48,115028";
		bg.longitude="11,678488";
		bg.desc="Truderinger Traditionswirtshaus Lindengarten mit Biergarten.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Liebhard's Bräustüberl zu Aying";
		bg.strasse="Münchener Straße 2";
		bg.plz="85653";
		bg.ort="Aying";
		bg.telefon="08095-1345";
		bg.email="";
		bg.url="www.liebhards-aying.de";
		bg.latitude="47,97101";
		bg.longitude="11,77983";
		bg.desc="Liebhard's Bräustüberl Restaurant und Biergarten befindet sich in Aying.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Olympia-Alm";
		bg.strasse="Martin-Luther-King Weg 8";
		bg.plz="80809";
		bg.ort="München";
		bg.telefon="089-3009924";
		bg.email="";
		bg.url="www.olympiaalm.de";
		bg.latitude="48,171114";
		bg.longitude="11,554349";
		bg.desc="Olympia-Alm im Olympia-Park";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Paulaner Seegarten";
		bg.strasse="Hochstraße 71";
		bg.plz="85757";
		bg.ort="Karlsfeld";
		bg.telefon="08131 3909420";
		bg.email="info@paulaner-seegarten.de";
		bg.url="www.paulaner-seegarten.de";
		bg.latitude="48,236872";
		bg.longitude="11,472543";
		bg.desc="Der Seegarten liegt direkt am Karlsfelder See und bietet eine gute Möglichkeit, in schöner Umgebung zu speisen und/oder die Seele baumeln zu lassen.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Pe. Es. Kottmeier";
		bg.strasse="Bräuhausstraße 18";
		bg.plz="82152";
		bg.ort="Planegg";
		bg.telefon="089-89930030";
		bg.email="";
		bg.url="www.pe-es.de";
		bg.latitude="48,10553";
		bg.longitude="11,423055";
		bg.desc="Das Pe. Es. Kottmeier befindet sich in Planegg, südwestlich von München's Innenstadt.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Postgarten";
		bg.strasse="Andechstrasse 1";
		bg.plz="82211";
		bg.ort="Herrsching";
		bg.telefon="08152 396 270";
		bg.email="";
		bg.url=" www.post-herrsching.de";
		bg.latitude="47,998044";
		bg.longitude="11,177318";
		bg.desc="Der Biergarten Postgarten befindet sich in dem kleinen Städtchen Herrsching, am Ostufer vom Ammersee gelegen.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Schiaßn";
		bg.strasse="Neues Schießfeld 2";
		bg.plz="854345";
		bg.ort="Erding";
		bg.telefon="08122-8809890";
		bg.email="";
		bg.url="schiassn.com";
		bg.latitude="48,313891";
		bg.longitude="11,910569";
		bg.desc="Der 2010 eröffnete Biergarten Schiaßn befindet sich in der Stadt Erding.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Schießstätte Allach";
		bg.strasse="Servetstraße 1";
		bg.plz="80999 ";
		bg.ort="München (Allach)";
		bg.telefon="089-189229-13";
		bg.email="";
		bg.url="";
		bg.latitude="48,195731";
		bg.longitude="11,456144";
		bg.desc="Der Biergarten Schießstätte Allach liegt an der Fahrradroute Pasing - Dachau, immer der Würm entlang. ";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Schlossallee Haag";
		bg.strasse="Freisinger Straße 1";
		bg.plz="85410";
		bg.ort="Haag an der Amper";
		bg.telefon="08167-350";
		bg.email="";
		bg.url="www.biergarten-haag.de";
		bg.latitude="48,458249";
		bg.longitude="11,832423";
		bg.desc="Der Biergarten Schlossallee Haag befindet sich nahe dem Fluß Amper, nur einige Kilometer nördlich von Freising.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Schloss Dachau Biergarten";
		bg.strasse="Schloßstraße 2";
		bg.plz="85221";
		bg.ort="Dachau";
		bg.telefon="08131-2799278";
		bg.email="";
		bg.url="www.schloss-dachau.com";
		bg.latitude="48,258399";
		bg.longitude="11,433463";
		bg.desc="Der kleine Biergarten befindet sich am Renaissance Schloß in Dachau.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Seehaus Schreyegg";
		bg.strasse="Landsberger Straße 78";
		bg.plz="82266";
		bg.ort="Stegen am Ammersee";
		bg.telefon="08143-992537";
		bg.email="";
		bg.url=" www.seehaus-schreyegg.com";
		bg.latitude="48,076991";
		bg.longitude="11,132922";
		bg.desc="Der Seehaus Schreyegg Biergarten befindet sich in dem kleinen Dorf Stegen am nördlichsten Ende vom Ammersee.";
		bg.favorit=false;
		em.persist(bg);
		em.close();


		em = EMFService.get().createEntityManager();
		bg = new Biergarten();
		bg.name="Wirtshaus am Hart";
		bg.strasse="Sudetendeutsche Straße 40";
		bg.plz="80937";
		bg.ort="München (Am Hart)";
		bg.telefon="";
		bg.email="089-3116039";
		bg.url="www.theater-platzl.de";
		bg.latitude="48,196493";
		bg.longitude="11,576453";
		bg.desc="";
		bg.favorit=false;
		em.persist(bg);
		em.close();






		
		return "Hello Biergarten OK!";
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) //application/json
	@Path("/holebiergarten")
	public String getBiergarten() {
		List<BiergartenVO> biergartenTOs = new ArrayList<BiergartenVO>();
		BiergartenJSON biergartenJSON = new BiergartenJSON();
		biergartenJSON.setBiergartenid("GPL");
		BiergartenVO bg = null;
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select b from Biergarten b");
		
		List<Biergarten> biergaerten = q.getResultList();
		for (Biergarten biergarten : biergaerten) {
			bg = new BiergartenVO();
			bg.id = biergarten.id;
			bg.name = biergarten.name;
			bg.strasse = biergarten.strasse;
			bg.plz = biergarten.plz;
			bg.ort = biergarten.ort;
			bg.telefon = biergarten.telefon;
			bg.email = biergarten.email;
			bg.url = biergarten.url;
			bg.latitude = biergarten.latitude;
			bg.longitude = biergarten.longitude;
			bg.desc = biergarten.desc;
			bg.desclong = biergarten.descLong;
			bg.mass = biergarten.mass;
			bg.apfelschorle = biergarten.apfelschorle;
			bg.riesenbreze = biergarten.riesenbreze;
			bg.obazda = biergarten.obazda;
			bg.biermarke = biergarten.biermarke;
			bg.lieblingsgericht = biergarten.lieblingsgericht;
			bg.speisenkommentar = biergarten.speisekommentar;
			bg.favorit = biergarten.favorit;
			biergartenTOs.add(bg);
		}
		
		biergartenJSON.setBiergartenListe(biergartenTOs);
		
		 
		com.google.appengine.repackaged.com.google.gson.Gson gson = new com.google.appengine.repackaged.com.google.gson.Gson();
		String data = gson.toJson(biergartenJSON);
		
		
		return data;
		
	}
	
	@GET
	@Produces("application/json")
	@Path("/holefavoriten")
	public String getFavoriten() {
		List<BiergartenVO> biergartenTOs = new ArrayList<BiergartenVO>();
		BiergartenJSON biergartenJSON = new BiergartenJSON();
		biergartenJSON.setBiergartenid("GPL");
		
		BiergartenVO bg = null;
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select b from Biergarten b where b.favorit=true");
		//q.setParameter("myfavorit", "true");
				
		List<Biergarten> biergaerten = q.getResultList();
		for (Biergarten biergarten : biergaerten) {
			bg = new BiergartenVO();
			bg.id = biergarten.id;
			bg.name = biergarten.name;
			bg.strasse = biergarten.strasse;
			bg.plz = biergarten.plz;
			bg.ort = biergarten.ort;
			bg.telefon = biergarten.telefon;
			bg.email = biergarten.email;
			bg.url = biergarten.url;
			bg.latitude = biergarten.latitude;
			bg.longitude = biergarten.longitude;
			bg.desc = biergarten.desc;
			bg.desclong = biergarten.descLong;
			bg.mass = biergarten.mass;
			bg.apfelschorle = biergarten.apfelschorle;
			bg.riesenbreze = biergarten.riesenbreze;
			bg.obazda = biergarten.obazda;
			bg.biermarke = biergarten.biermarke;
			bg.lieblingsgericht = biergarten.lieblingsgericht;
			bg.speisenkommentar = biergarten.speisekommentar;
			bg.favorit = biergarten.favorit;
			biergartenTOs.add(bg);
		}
		 
		biergartenJSON.setBiergartenListe(biergartenTOs);
		
		 
		com.google.appengine.repackaged.com.google.gson.Gson gson = new com.google.appengine.repackaged.com.google.gson.Gson();
		String data = gson.toJson(biergartenJSON);
		
		return data;
		
	}
	
	
	
	

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public BiergartenJSON getBiergartenInJSON() {	
		List<BiergartenVO> biergartenTOs = new ArrayList<BiergartenVO>();
		BiergartenJSON biergartenJSON = new BiergartenJSON();
		biergartenJSON.setBiergartenid("GPL");		
		BiergartenVO bg = null;
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select b from Biergarten b");
		
		List<Biergarten> biergaerten = q.getResultList();
		for (Biergarten biergarten : biergaerten) {
			bg = new BiergartenVO();
			bg.id = biergarten.id;
			bg.name = biergarten.name;
			bg.strasse = biergarten.strasse;
			bg.plz = biergarten.plz;
			bg.ort = biergarten.ort;
			bg.telefon = biergarten.telefon;
			bg.email = biergarten.email;
			bg.url = biergarten.url;
			bg.latitude = biergarten.latitude;
			bg.longitude = biergarten.longitude;
			bg.desc = biergarten.desc;
			bg.desclong = biergarten.descLong;
			bg.mass = biergarten.mass;
			bg.apfelschorle = biergarten.apfelschorle;
			bg.riesenbreze = biergarten.riesenbreze;
			bg.obazda = biergarten.obazda;
			bg.biermarke = biergarten.biermarke;
			bg.lieblingsgericht = biergarten.lieblingsgericht;
			bg.speisenkommentar = biergarten.speisekommentar;
			bg.favorit = biergarten.favorit;
			biergartenTOs.add(bg);
		}
		
		biergartenJSON.setBiergartenListe(biergartenTOs);
	
		return biergartenJSON;
	
	}
	
	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBiergartenInJSON(BiergartenJSON biergartenJSON) {
	
		String result = "Track saved : ";
		return Response.status(201).entity(result).build();
	
	}
}





/*
 * http://beergardenservice.appspot.com/platzerworld/biergarten/post
 * http://beergardenservice.appspot.com/platzerworld/biergarten/get
 * 
 * http://beergardenservice.appspot.com/platzerworld/biergarten/insertbiergarten
   http://beergardenservice.appspot.com/platzerworld/biergarten/holebiergarten
   http://beergardenservice.appspot.com/platzerworld/biergarten/holefavoriten
   
 * http://[your-application-id].appspot.com/resources/hr/employee or
 * http://[your-application-id].appspot.com/rest/hr/employee
 * 
 * http://127.0.0.1:8888/resources/biergarten/all/tug@grallandco.com
 * 
 * 
 * http://127.0.0.1:8888/resources/hr/employee
 * 
 * http://biergartenservice.appspot.com/resources/platzerworld/biergarten
 * 
 * http://biergartenservice.appspot.com/
 * 
 * http://localhost:8080/com.platzerworld.biergarten.jersey/rest/hello
 * 
 * http://127.0.0.1:8888/com.platzerworld.biergarten.jersey/rest/hello
 * 
 * http://biergartenservice.appspot.com/resources/hr/employee or
 * http://biergartenservice.appspot.com/rest/hr/employee
 * 
 * http://platzerworld.appspot.com/resources/hr/employee
 * 
 * 
 * http://biergartenservice.appspot.com/resources/biergarten/all/tug@grallandco.com
 */
