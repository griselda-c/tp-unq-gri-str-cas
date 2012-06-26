package griselda.alejandro;

import org.neo4j.graphdb.Node;
import org.neo4j.kernel.AbstractGraphDatabase;

public interface Persistible {

    public Node persistirEn(AbstractGraphDatabase instancia);

    public Integer getId();

}
