
import java.util.List;
import java.util.Scanner;

public class PtApp {

    String inputFromUser;
    int choice = 0;
    boolean bool = true;
    Repository repo = new Repository();

    PtApp() {

        System.out.println("1: Lista över medlemmar "
                + "\n2: Se lista över kundernas pass "
                + "\n3: Se alla noteringar "
                + "\n4: Lägga in ny notering");

        Scanner sc = new Scanner(System.in);

        while (bool) { // Meny val
            inputFromUser = sc.nextLine();
            try {
                choice = Integer.parseInt(inputFromUser);
                bool = false;
            } catch (NumberFormatException e) {
                bool = false;
            }
        }
        switch (choice) {

            case 1:
                displayMembers();
                break;

            case 2:
                repo.displayPass();
                break;

            case 3:
                repo.displayNotes();
                break;

            case 4:
                String trainerId;
                String memberId;
                String note;
                displayStaff();

                System.out.println("Ange ditt tränar ID: ");
                trainerId = sc.nextLine();
                if (checkIfStaff(trainerId)) {
                    System.out.println("Välkommen");
                    repo.getStaff().printStaff();

                    System.out.println("Vem vill du lägga in notering på? (personnummer)");
                    memberId = sc.nextLine();
                    if (checkIfMember(memberId)) {

                        System.out.println("Skriv notering: ");
                        note = sc.nextLine();

                        repo.insertNote(note, repo.getMember().getId(), repo.getStaff().getId());
                        break;
                    } else {
                        System.out.println("Avslutar programmet");
                        break;
                    }
                } else {
                    System.out.println("Avslutar programmet");
                    break;
                }

            default:
                System.out.println("Avslutar programmet");
                break;
        }
    }

    private void displayMembers() {
        for (Member m : repo.getMembers()) {
            System.out.printf("%-7s | %-10s | %-12s\n", m.getForename(), m.getSurname(), m.getIdNumber());
        }
    }

    private void displayStaff() {
        for (Staff f : repo.getStaffMembers()) {
            System.out.printf("%-10s | %-10s | %-12s\n", f.getForename(), f.getSurname(), f.getIdNumber());
        }
    }

    public boolean checkIfMember(String idNumber) {
        List<Member> members = repo.getMembers();

        for (Member m : members) {
            if (m.getIdNumber().equals(idNumber)) {
                repo.setMember(m);
                return true;
            }
        }
        System.out.println("Användare finns ej.");
        return false;
    }

    public boolean checkIfStaff(String idNumber) {
        List<Staff> staff = repo.getStaffMembers();

        for (Staff f : staff) {
            if (f.getIdNumber().equals(idNumber)) {
                repo.setStaff(f);
                return true;
            }
        }
        System.out.println("Användare finns ej.");
        return false;
    }

    public static void main(String[] args) {

        PtApp x = new PtApp();
    }
}
