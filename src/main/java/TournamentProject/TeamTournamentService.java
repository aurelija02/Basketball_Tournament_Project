package TournamentProject;

import org.hibernate.Session;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TeamTournamentService {

    public List<TeamTournament> getAllTeamTournaments(Session session) {
        List<TeamTournament> teamTournaments = session.createQuery("from TeamTournament").list();
        return teamTournaments;
    }
    public void printTeamsInTournamentLeaderboard (Session session){

        Scanner scanner = new Scanner(System.in);
        TournamentService tournamentService = new TournamentService();

        System.out.println("Choose a tournament id:");

        List<Tournament> tournamentList = tournamentService.getAllTournaments(session);
        for (Tournament tournament : tournamentList){
            System.out.println(tournament.getId()+" "+tournament.getTitle());
        }
        Long chosenTournamentId = scanner.nextLong();
        scanner.nextLine();

        List<TeamTournament> teamTournamentList = getAllTeamTournaments(session);
        List<TeamTournament> filteredTeamTournament = teamTournamentList
                .stream()
                .filter(teamTournament -> teamTournament.getTournament().getId().equals(chosenTournamentId))
                .sorted(Comparator.comparing(TeamTournament::getTeamPoints, Comparator.reverseOrder()))
                .collect(Collectors.toList());

        System.out.println("Tournament name: "+ filteredTeamTournament.get(0).getTournament().getTitle());
        for (TeamTournament teamTournament : filteredTeamTournament){
            System.out.println("Team name: "+ teamTournament.getTeam().getName()+" --> Points in tournament: "+teamTournament.getTeamPoints());
        }

    }

   public void addTeamToTournament (Session session){

        TournamentService tournamentService = new TournamentService();
        TeamService teamService = new TeamService();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose team's ID");

        List<Team> teams = teamService.getAllTeams(session);
        for (Team team : teams){
            System.out.println(team.getId()+" "+team.getName()+" "+team.getCountry());
        }
        Long chosenTeamId = scanner.nextLong();
        scanner.nextLine();

        List<Team> chosenTeam = teams
                .stream()
                .filter(team -> team.getId().equals(chosenTeamId))
                .collect(Collectors.toList());
        Team team = chosenTeam.get(0);

        System.out.println("Choose tournaments's ID");
        List<Tournament> tournaments = tournamentService.getAllTournaments(session);
        for (Tournament tournament:tournaments){
            System.out.println(tournament.getId()+" "+tournament.getTitle());
        }
        Long chosenTournamentId= scanner.nextLong();
        scanner.nextLine();
        List<Tournament> chosenTournament = tournaments
                .stream()
                .filter(tournament -> tournament.getId().equals(chosenTournamentId))
                .collect(Collectors.toList());

        TeamTournament teamTournament = new TeamTournament();
        teamTournament.setTeam(team);
        teamTournament.setTournament(chosenTournament.get(0));
        teamTournament.setTeamPoints(0);

        session.beginTransaction();
        session.save(teamTournament);
        session.getTransaction().commit();

    }
}
