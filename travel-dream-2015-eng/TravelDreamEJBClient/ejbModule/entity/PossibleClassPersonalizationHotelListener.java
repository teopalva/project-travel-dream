package entity;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class PossibleClassPersonalizationHotelListener {
    @PreUpdate
    @PrePersist
    @PreRemove
    public void setPrimaryKey(PossibleClassPersonalizationHotel p) {
        Hotel hotel = p.getHotel();
        ClassPersonalization classP = p.getClassPersonalization();
        PossibleClassPersonalizationHotelPK pk = new PossibleClassPersonalizationHotelPK();
        pk.setHotelId(hotel.getId());
        pk.setClassPersonalizationId(classP.getId());
        p.setId(pk);
    }
}