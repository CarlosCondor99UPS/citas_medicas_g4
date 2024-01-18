package ups.edu.ec.sistemacitasmedicas.servicio;


public interface EmailSenderServicio {

    void sendEmail(String toUser, String subject, StringBuffer message);


}
