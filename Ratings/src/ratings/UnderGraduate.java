package ratings;

/**
 *
 * @author ozgen
 */
public class UnderGraduate extends Student {

    String track;

    //Parametresiz constructor
    public UnderGraduate() {
        super(0, null, null);
        track = null;
    }

    //Parametreli constructor
    public UnderGraduate(int studentID, String name, String surname, String track) {
        super(studentID, name, surname);
        this.track = track;
    }

    //Copy constructor
    public UnderGraduate(UnderGraduate cUnderGraduate) {
        super(cUnderGraduate);
        track = cUnderGraduate.track;
    }

    //toString metodu
    @Override
    public String toString() {
        return super.toString() + "\nTrack: " + getTrack();
    }

    /**
     * @return the track
     */
    public String getTrack() {
        return track;
    }

    /**
     * @param track the track to set
     */
    public void setTrack(String track) {
        this.track = track;
    }

    Object getOccupation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
