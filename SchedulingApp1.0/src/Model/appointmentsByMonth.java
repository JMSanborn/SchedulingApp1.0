package Model;

public class appointmentsByMonth {
    public String appointmentMonth;
    public int appointmentTotal;

    /**
     *
     * @param appointmentMonth the appointment month to set
     * @param appointmentTotal the appointment total to set
     */
    public appointmentsByMonth(String appointmentMonth, int appointmentTotal) {
        this.appointmentMonth = appointmentMonth;
        this.appointmentTotal = appointmentTotal;
    }

    /**
     * @return the appointment month
     * For future expansion
     */
    public String getAppointmentMonth() {
        return appointmentMonth;
    }

    /**
     * @return the appointment total
     * For future expansion
     */
    public int getAppointmentTotal() {
        return appointmentTotal;
    }
}
