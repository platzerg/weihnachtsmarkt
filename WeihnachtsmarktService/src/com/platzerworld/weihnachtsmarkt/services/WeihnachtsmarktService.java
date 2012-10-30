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
import com.platzerworld.weihnachtsmarkt.model.Weihnachtsmarkt;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktJSON;
import com.platzerworld.weihnachtsmarkt.vo.WeihnachtsmarktVO;

// http://biergartenservice.appspot.com/platzerworld/biergarten/holebiergarten

// http://weihnachtsmarktservice.appspot.com/platzerworld/weihnachtsmarkt/insertweihnachtsmarkt

// http://weihnachtsmarktservice.appspot.com/platzerworld/weihnachtsmarkt/holeweihnachtsmaerkte

// http://weihnachtsmarktservice.appspot.com/platzerworld/weihnachtsmarkt/holefavoriten

@Path("/weihnachtsmarkt/")
public class WeihnachtsmarktService {
	@GET
	@Produces("text/plain")
	@Path("/insertweihnachtsmarkt")
	public String insertBiergarten() {
		return "Hello Weihnachtsmarkt OK!";
		/*
		EntityManager em = null;
		Weihnachtsmarkt weihnachtsmarkt;	
		em = EMFService.get().createEntityManager();
		weihnachtsmarkt = new Weihnachtsmarkt();
		weihnachtsmarkt.name="Mittelalterliche Weihnachtsmarkt";
		weihnachtsmarkt.oeffnungszeit="26.11.2012 - 23.12.2012 11:00 - 20:00";
		weihnachtsmarkt.strasse="Wittelsbacher Platz";
		weihnachtsmarkt.plz="80333";
		weihnachtsmarkt.ort="München (Maxvorstadt)";
		weihnachtsmarkt.telefon="089 / 568 24 66 - 0";
		weihnachtsmarkt.email="diego_ertl@yahoo.de";
		weihnachtsmarkt.url="www.mittelaltermarkt-muenchen.de";
		weihnachtsmarkt.latitude="48,143269";
		weihnachtsmarkt.longitude="11.576145";
		weihnachtsmarkt.desc="Bereits anno 1410 wurde hier eine „Nikolausdult“ erwähnt. Der Mittelaltermarkt auf dem Wittelsbacherplatz (nahe Odeonsplatz) zeigt, wie es anno dazumal gewesen und zugegangen sein könnte. Er  knüpft an diese alte, bayerische Tradition an und gilt wegen seines historischen Gepräges als besonders stimmungsvoll. Für vier Wochen zieht dort lebendiges Mittelalter ein.";
		weihnachtsmarkt.favorit=false;
		em.persist(weihnachtsmarkt);
		em.close();


		em = EMFService.get().createEntityManager();
		weihnachtsmarkt = new Weihnachtsmarkt();
		weihnachtsmarkt.name="Weihnachtsmarkt Schwabing";
		weihnachtsmarkt.oeffnungszeit="30.11.2012 - 24.12.2012 Mo-Fr 12:00 – 20:30 Uhr, Sa/So 11:00 – 20:30 Uhr, 24.12.2012 11:00 - 14:00";
		weihnachtsmarkt.strasse="Münchner Freiheit";
		weihnachtsmarkt.plz="80802";
		weihnachtsmarkt.ort="München (Schwabing)";
		weihnachtsmarkt.telefon="089/33 81 33";
		weihnachtsmarkt.email="";
		weihnachtsmarkt.url="www.hirschgarten.de";
		weihnachtsmarkt.latitude="48,163169";
		weihnachtsmarkt.longitude="11,586939";
		weihnachtsmarkt.desc="Bereits zu Beginn der 70er Jahre stellten etliche Künstler, die im Sommer auf der \"Kunstmeile\" auf der Leopoldstraße vertreten waren, in der Vorweihnachtszeit im ehemaligen Schweizer Haus ihre Arbeiten aus. 1975 entstand daraus die Idee, einen Künstlermarkt, mit richtigen Ständen und einem Veranstaltungsprogramm auf die Beine zu stellen. Mit Unterstützung des damaligen Bezirksausschusses unter der Leitung von Frau v. Welser-Ude wurde 1976 der Schwabinger Weihnachtsmarkt gegründet. Von Beginn an war der Markt als Künstlermarkt konzipiert und genehmigt. Die Internationalität der ca. 100 Aussteller und Ausstellerinnen, das hohe Niveau der künstlerischen Arbeiten, sowie die Qualität im Handwerklichen, das Kulturprogramm und die jährlich wechselnde Dekoration machen das unverwechselbare Flair des Marktes aus.";
		weihnachtsmarkt.favorit=false;
		em.persist(weihnachtsmarkt);
		em.close();
	*/
		
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON) //application/json
	@Path("/holeweihnachtsmaerkte")
	public String getBiergarten() {
		return "Hello Weihnachtsmarkt holeweihnachtsmaerkte OK!";
		/*
		List<WeihnachtsmarktVO> biergartenTOs = new ArrayList<WeihnachtsmarktVO>();
		WeihnachtsmarktJSON biergartenJSON = new WeihnachtsmarktJSON();
		biergartenJSON.setBiergartenid("GPL");
		WeihnachtsmarktVO bg = null;
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select b from Biergarten b");
		
		List<Weihnachtsmarkt> biergaerten = q.getResultList();
		for (Weihnachtsmarkt biergarten : biergaerten) {
			bg = new WeihnachtsmarktVO();
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
			bg.gluehwein = biergarten.gluehwein;
			bg.lieblingsgericht = biergarten.lieblingsgericht;
			bg.speisenkommentar = biergarten.speisekommentar;
			bg.favorit = biergarten.favorit;
			biergartenTOs.add(bg);
		}
		
		biergartenJSON.setBiergartenListe(biergartenTOs);
		
		 
		com.google.appengine.repackaged.com.google.gson.Gson gson = new com.google.appengine.repackaged.com.google.gson.Gson();
		String data = gson.toJson(biergartenJSON);
		
		
		return data;
		*/
		
	}
	
	@GET
	@Produces("application/json")
	@Path("/holefavoriten")
	public String getFavoriten() {
		return "Hello Weihnachtsmarkt holefavoriten OK!";
		
		/*
		List<WeihnachtsmarktVO> biergartenTOs = new ArrayList<WeihnachtsmarktVO>();
		WeihnachtsmarktJSON biergartenJSON = new WeihnachtsmarktJSON();
		biergartenJSON.setBiergartenid("GPL");
		
		WeihnachtsmarktVO bg = null;
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select b from Biergarten b where b.favorit=true");
		//q.setParameter("myfavorit", "true");
				
		List<Weihnachtsmarkt> biergaerten = q.getResultList();
		for (Weihnachtsmarkt biergarten : biergaerten) {
			bg = new WeihnachtsmarktVO();
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
			bg.gluehwein = biergarten.gluehwein;
			bg.lieblingsgericht = biergarten.lieblingsgericht;
			bg.speisenkommentar = biergarten.speisekommentar;
			bg.favorit = biergarten.favorit;
			biergartenTOs.add(bg);
		}
		 
		biergartenJSON.setBiergartenListe(biergartenTOs);
		
		 
		com.google.appengine.repackaged.com.google.gson.Gson gson = new com.google.appengine.repackaged.com.google.gson.Gson();
		String data = gson.toJson(biergartenJSON);
		
		return data;
		*/
		
	}
	
	
	
	

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public WeihnachtsmarktJSON getBiergartenInJSON() {	
		List<WeihnachtsmarktVO> biergartenTOs = new ArrayList<WeihnachtsmarktVO>();
		WeihnachtsmarktJSON biergartenJSON = new WeihnachtsmarktJSON();
		biergartenJSON.setBiergartenid("GPL");		
		WeihnachtsmarktVO bg = null;
		
		EntityManager em = EMFService.get().createEntityManager();
		Query q = em.createQuery("select b from Biergarten b");
		
		List<Weihnachtsmarkt> biergaerten = q.getResultList();
		for (Weihnachtsmarkt biergarten : biergaerten) {
			bg = new WeihnachtsmarktVO();
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
			bg.gluehwein = biergarten.gluehwein;
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
	public Response createBiergartenInJSON(WeihnachtsmarktJSON biergartenJSON) {
	
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
