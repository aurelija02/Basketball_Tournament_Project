package TournamentProject;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class TeamService {

    public List<Team> getAllTeams(Session session) {
        List<Team> teams = session.createQuery("from Team").list();
        return teams;
    }
    public Team getTeamById (Session session, Long id) {
        Query query = session.createQuery("from Team where id = :id");
        query.setLong("id", id);
        Team team = (Team) query.uniqueResult();

        return team;
    }

    public void createTeam (Session session) {

        TournamentService tournamentService = new TournamentService();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter team's name:");
        String name = scanner.nextLine();
        System.out.println("Enter team's home country:");
        String country = scanner.nextLine();
        System.out.println("Enter team's budget:");
        Integer budget = scanner.nextInt();
        scanner.nextLine();

        Team team = new Team(name, country, budget);
        session.beginTransaction();
        session.save(team);
        session.getTransaction().commit();
    }
}
