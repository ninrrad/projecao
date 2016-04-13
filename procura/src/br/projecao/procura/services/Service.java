package br.projecao.procura.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/message")
	public class Service {

		@GET
		@Path("/{id}/{nome}.img")
		public Response printMessage(@PathParam("id") String id, @PathParam("nome") String nome) {

			String result = "Id: " + id + " nome:" + nome;

			return Response.status(200).entity(result).build();

		}

	}