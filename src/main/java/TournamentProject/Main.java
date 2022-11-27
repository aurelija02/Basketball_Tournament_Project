package TournamentProject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        PlayerService playerService = new PlayerService();
        TeamService teamService = new TeamService();
        TournamentService tournamentService = new TournamentService();
        MatchService matchService = new MatchService();
        TeamTournamentService teamTournamentService = new TeamTournamentService();

        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the tournament system");
        String input = "";

        while (!input.equals("7")) {
            printMainMenu();
            input = scanner.nextLine();

            switch (input){
                case "1":
                    playerService.registerPlayer(session);
                    break;
                case "2":
                    teamService.createTeam(session);
                    break;
                case "3":
                    teamTournamentService.addTeamToTournament(session);
                    break;
                case "4":
                    tournamentService.createTournament(session);
                    break;
                case "5":
                    matchService.createMatch(session);
                    break;
                case "6":
                    teamTournamentService.printTeamsInTournamentLeaderboard(session);
                    break;
                case "7":
                    break;
                default:
                    System.out.println("Wrong input. Try again");
                    break;
            }

        }

        session.close();
    }
    public static void printMainMenu (){
        System.out.println("----------------------------");
        System.out.println("Choose what you want to do:");
        System.out.println("1 - Register new player to a team");
        System.out.println("2 - Register new team");
        System.out.println("3 - Add existing team to the specific tournament");
        System.out.println("4 - Create a tournament");
        System.out.println("5 - Start new match");
        System.out.println("6 - Check specific tournament's leaderboard");
        System.out.println("7 - EXIT");
    }
}

