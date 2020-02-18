package ratings;

/**
 *
 * @author ozgen
 */
public class Student {

    int studentID;
    String name;
    String surname;

    //Parametresiz constructor
    public Student() {
        studentID = 0;
        name = null;
        surname = null;
    }

    //Parametreli constructor
    public Student(int studentID, String name, String surname) {
        this.studentID = studentID;
        this.name = name;
        this.surname = surname;
    }

    //Copy constructor
    public Student(Student cStudent) {
        studentID = cStudent.studentID;
        name = cStudent.name;
        surname = cStudent.surname;
    }

    //toString metodu
    @Override
    public String toString() {
        return "Student ID: " + studentID + "\nName: " + name + "\nSurname: " + surname;

    }

    /**
     * @return the studentID
     */
    public int getStudentID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

}
