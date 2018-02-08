
public class Member {
    String forename;
    String surname;
    String idNumber;
    int id;
    
    Member(){}

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getIdNumber() {
        return idNumber;
    }
     
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }
    
    public int getId() {
        return id;
    }
     
    public void setId(int id) {
        this.id = id;
    }
    
    public void printMember() {
        System.out.println(getForename() + " " + getSurname());
    } 
}