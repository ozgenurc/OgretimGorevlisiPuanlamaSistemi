package ratings;

/**
 *
 * @author ozgen
 */
public class Graduate extends Student {

    private String subject;
    private String advisor;

    //Parametresiz constructor
    public Graduate() {
        super(0, null, null);
        subject = null;
    }

    //Parametreli constructor
    public Graduate(int studentID, String name, String surname, String subject, String advisor) {
        super(studentID, name, surname);
        this.subject = subject;
        this.advisor = advisor;
    }

    //Copy constructor
    public Graduate(Graduate cGraduate) {
        super(cGraduate);
        subject = cGraduate.subject;
        advisor = cGraduate.advisor;
    }

    //toString metodu
    @Override
    public String toString() {

        return super.toString() + "\nSubject: " + getSubject() + "\nAdvisor: " + getAdvisor();
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the advisor
     */
    public String getAdvisor() {
        return advisor;
    }

    /**
     * @param advisor the advisor to set
     */
    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }
}
