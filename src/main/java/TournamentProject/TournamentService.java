package TournamentProject;

import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TournamentService {

    public List<Tournament> getAllTournaments(Session session) {
        List<Tournament> tournaments = session.createQuery("from Tournament").list();
        return tournaments;
    }


    public void createTournament (Session session){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter tournament's title:");
        String title = scanner.nextLine();
        System.out.println("Enter tounament's prize:");
        int prize = scanner.nextInt();
        scanner.nextLine();

        Tournament tournament = new Tournament(title, prize);
        session.beginTransaction();
        session.save(tournament);
        session.getTransaction().commit();
    }

}
