package core.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "route")
public class Route extends BaseEntity<Long> {
    @Column(name = "source")
    private String source;

    @Column(name = "destination")
    private String destination;

    @OneToMany(mappedBy = "route")
    Set<Train> routeTrains;

    public Route() {

    }

    public Route(Long id, String source, String destination) {
        setId(id);
        this.source = source;
        this.destination = destination;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "Route{" +
                "source=" + source +
                ", destination=" + destination +
                "} " + super.toString();
    }
}
