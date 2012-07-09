package griselda.alejandro;

import org.neo4j.graphdb.RelationshipType;

public enum Relacion implements RelationshipType {
	ROOT$CLASE,
	CLASE$INSTANCIA,	
	
	EQUIPO$JUGADOR,
	EQUIPO$TECNICO,
	EQUIPO$FORMACION,
	
	FORMACION$EQUIPO,
	FORMACION$TITULAR,
	FORMACION$SUPLENTE,
	
	TITULAR$JUGADOR,
	
	JUGADOR$HABILIDAD,
	
	PSIMPLE$EQUIPO2,
	PSIMPLE$EQUIPO1,
	
	P_COPA$P_SIMPLE1,
	P_COPA$P_SIMPLE2

}
