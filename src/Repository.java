
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {

    private Member member;
    private Staff staff;
    private Properties p = new Properties();

    public Repository() {
        try {
            p.load(new FileInputStream("src/bgetesting/Settings.properties"));
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertNote(String notes, int memberId, int staffId) {
        String query = "insert into notes(notes, dateNotes, memberId, staffId) values (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement insertNotes = con.prepareStatement(query)) {

            insertNotes.setString(1, notes);
            insertNotes.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            insertNotes.setInt(3, memberId);
            insertNotes.setInt(4, staffId);
            insertNotes.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Staff> getStaffMembers() {
        List<Staff> staff = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement memberStmt
                = con.prepareStatement("select * from staff")) {
            ResultSet rs = memberStmt.executeQuery();
            while (rs.next()) {
                Staff temp = new Staff();
                temp.setForename(rs.getString("forename"));
                temp.setSurname(rs.getString("surname"));
                temp.setIdNumber(rs.getString("idNumber"));
                temp.setId(rs.getInt("id"));
                staff.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staff;
    }

    public List<Member> getMembers() {
        List<Member> members = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement memberStmt
                = con.prepareStatement("select * from member")) {
            ResultSet rs = memberStmt.executeQuery();
            while (rs.next()) {
                Member temp = new Member();
                temp.setForename(rs.getString("forename"));
                temp.setSurname(rs.getString("surname"));
                temp.setIdNumber(rs.getString("idNumber"));
                temp.setId(rs.getInt("id"));
                members.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return members;
    }

    public void displayPass() {
        String query = "select member.forename, workOutSession.startTime, hall.hallName, activity.activity\n"
                + "from member\n"
                + "right join attendance\n"
                + "on attendance.memberId = member.Id\n"
                + "inner join workOutSession\n"
                + "on workOutSession.id = attendance.workOutSessionId\n"
                + "inner join hall\n"
                + "on hall.id = workOutSession.hallId\n"
                + "inner join activity\n"
                + "on activity.id = workOutSession.activityid;";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                PreparedStatement memberStmt = con.prepareStatement(query)) {
            ResultSet rs = memberStmt.executeQuery();
            System.out.printf("%-8s | %-21s | %-11s | %s\n", "Namn", "Starttid", "Hall", "Aktivitet");
            createLine();
            while (rs.next()) {
                String name = rs.getString("forename");
                String startTime = rs.getString("startTime");
                String hall = rs.getString("hallName");
                String activity = rs.getString("activity");
                System.out.printf("%-8s | %-21s | %-11s | %s\n", name, startTime, hall, activity);
            }
            createLine();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void displayNotes() {

        String query = "select member.forename as Medlem, notes, staff.forename as Personlig_Tränare from member"
                + "\ninner join notes"
                + "\non notes.memberId = member.id"
                + "\ninner join staff"
                + "\non notes.staffId = staff.id;";

        try (Connection con = DriverManager.getConnection(p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            System.out.printf("%-7s | %-32s | %s\n", "Namn", "Notering", "Tränare");
            createLine();
            while (rs.next()) {
                String member = rs.getString("Medlem");
                String notes = rs.getString("notes");
                String staff = rs.getString("Personlig_Tränare");  
                System.out.printf("%-7s | %-32s | %s\n", member, notes, staff);
            }
            createLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Staff getStaff() {
        return staff;
    }
     private void createLine() {
        System.out.println("---------------------------------------------------------");
    }

}
