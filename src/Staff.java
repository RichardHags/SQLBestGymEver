
public class Staff {
    String forename;
    String surname;
    String idNumber;
    int id;

    Staff(){}
 
    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }
    
    public String getIdNumber() {
        return idNumber;
    }
     
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
     public int getId() {
        return id;
    }
     
    public void setId(int id) {
        this.id = id;
    }
    
    public void printStaff() {
        System.out.println(getForename() + " " + getSurname());
    }
}