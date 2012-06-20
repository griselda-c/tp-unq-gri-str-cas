package griselda.alejandro;

import org.neo4j.kernel.AbstractGraphDatabase;

/**
 * TODO: description
 */
public interface Persistible {

    public void persistirEn(AbstractGraphDatabase instancia);
}
