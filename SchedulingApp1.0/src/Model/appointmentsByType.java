package Model;

public class appointmentsByType {
    public String appointmentType;
    public int appointmentTotal;

    /**
     *
     * @param appointmentType the appointment type to set
     * @param appointmentTotal the appointment total to set
     */
    public appointmentsByType(String appointmentType, int appointmentTotal) {
        this.appointmentType = appointmentType;
        this.appointmentTotal = appointmentTotal;
    }

    /**
     * @return the appointment type
     * For future expansion
     */
    public String getAppointmentType() {
        return appointmentType;
    }

    /**
     * @return the appointment total
     * For future expansion
     */
    public int getAppointmentTotal() {
        return appointmentTotal;
    }
}
