package auditoria;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

@Entity(name = "CustomRevisionEntity")
@Table(name = "CUSTOM_REV_INFO")
@RevisionEntity( CustomRevisionEntityListener.class )
public  class CustomRevisionEntity extends DefaultRevisionEntity {

    private String username;
    private Date hora; 
    private String ip; 

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
   
    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
   
    public String getUsername() {
        return username;
    }

    public void setUsername( String username ) {
        this.username = username;
    }
}