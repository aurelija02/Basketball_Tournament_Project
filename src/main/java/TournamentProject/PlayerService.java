package TournamentProject;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PlayerService {

    public List<Player> getAllPlayers(Session session) {
        List<Player> players = session.createQuery("from Player").list();
        return players;
    }

    public void registerPlayer (Session session) {

        TeamService teamService = new TeamService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter player's name:");
        String playerName = scanner.nextLine();
        System.out.println("Enter player's surname:");
        String playerSurname = scanner.nextLine();
        System.out.println("Enter player's age:");
        Integer playerAge = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter player's height in centimeters:");
        Integer height = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter player's weight:");
        Integer weight = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter player's playing position:");
        String playerPosition = scanner.nextLine();
        System.out.println("Choose a team ID in which this player is going to play");

        List<Team> teams = teamService.getAllTeams(session);
        for (Team team : teams){
            System.out.println(team.getId()+" "+team.getName()+" "+team.getCountry());
        }
        Long chosenTeanId = scanner.nextLong();
        scanner.nextLine();
        List<Team> chosenTeamList = teams
                .stream()
                .filter(team -> team.getId().equals(chosenTeanId))
                .collect(Collectors.toList());

        Player player = new Player(playerName, playerSurname, playerAge, height, weight, playerPosition);
        player.setTeam(chosenTeamList.get(0));

        session.beginTransaction();
        session.save(player);
        session.getTransaction().commit();
        System.out.println("Player registered succefully");
    }

}
