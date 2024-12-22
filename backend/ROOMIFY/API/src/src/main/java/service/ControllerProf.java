package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import EjbSession.UtilisateurServiceLocal;
import entities.Utilisateur;


@Path("/profs")
@Stateless
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ControllerProf {
	@Inject
	UtilisateurServiceLocal srv;
	
	
	
	
	
	
	
	
	
	
	
	
}
