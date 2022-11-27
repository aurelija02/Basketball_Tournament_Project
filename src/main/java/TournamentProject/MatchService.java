package TournamentProject;

import org.hibernate.Session;
import org.w3c.dom.ls.LSException;

import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MatchService {
    public List<Match> getAllMatches(Session session) {
        List<Match> matches = session.createQuery("from Match").list();
        return matches;
    }

    public void createMatch(Session session){

        Match match = new Match();

        TournamentService tournamentService = new TournamentService();
        TeamTournamentService teamTournamentService = new TeamTournamentService();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a tournament id in which this match will take place:");

        List<Tournament> tournamentList = tournamentService.getAllTournaments(session);
        for (Tournament tournament : tournamentList){
            System.out.println(tournament.getId()+" "+tournament.getTitle());
        }
        Long chosenTournamentId = scanner.nextLong();
        scanner.nextLine();

        List<Tournament> chosenTournament = tournamentList
                .stream()
                .filter(tournament -> tournament.getId().equals(chosenTournamentId))
                .collect(Collectors.toList());
        Tournament chosenTournamentObject = chosenTournament.get(0);

        match.setTournament(chosenTournamentObject);

        System.out.println("In chosen tournament "+ chosenTournament.get(0).getTitle()+" playing teams are:");
        List<Team> playingTeamsList = chosenTournamentObject.getTeamTournaments()
                .stream()
                .map(teamTournament -> teamTournament.getTeam())
                .collect(Collectors.toList());

        for (Team team: playingTeamsList){
            System.out.println(team.getId()+" "+team.getName()+" "+team.getCountry());
        }

        System.out.println("Choose the first playing team (ID)");
        Long chosenTeam1Id = scanner.nextLong();
        scanner.nextLine();
        List<Team> chosenTeam1 = playingTeamsList
                .stream()
                .filter(team -> team.getId().equals(chosenTeam1Id))
                .collect(Collectors.toList());

        System.out.println("Choose the second playing team (ID)");
        Long chosenTeam2Id = scanner.nextLong();
        scanner.nextLine();
        List<Team> chosenTeam2 = playingTeamsList
                .stream()
                .filter(team -> team.getId().equals(chosenTeam2Id))
                .collect(Collectors.toList());

        match.setTeam1(chosenTeam1.get(0));
        match.setTeam2(chosenTeam2.get(0));

        System.out.println("Game begins!");
        System.out.println("----------------------------");

        Random random = new Random();
        int randomTeam1Points = random.nextInt(50,120);
        //Random random2 = new Random();
        int randomTeam2Points = random.nextInt(50,120);

        System.out.println("Game is finished! The score is:");
        System.out.println("Team 1 "+ match.getTeam1().getName()+" scored: "+ randomTeam1Points);
        System.out.println("Team 2 "+ match.getTeam2().getName()+" scored: "+ randomTeam2Points);

        if (randomTeam1Points==randomTeam2Points){
            System.out.println("It looks like a tie! Let's continue this match!");
            randomTeam1Points = random.nextInt(randomTeam1Points, 150);
            randomTeam2Points = random.nextInt(randomTeam2Points, 150);
            System.out.println("Team 1 "+ match.getTeam1().getName()+" scored: "+ randomTeam1Points);
            System.out.println("Team 2 "+ match.getTeam2().getName()+" scored: "+ randomTeam1Points);

        }
        match.setTeam1Points(randomTeam1Points);
        match.setTeam2Points(randomTeam2Points);

        Team winnerTeam = new Team();
        if (randomTeam1Points>randomTeam2Points){
            match.setWinnerTeamId(match.getTeam1().getId());
            winnerTeam = match.getTeam1();
        } else {
            match.setWinnerTeamId(match.getTeam2().getId());
            winnerTeam = match.getTeam2();
        }
        System.out.println("The winner of this match is: "+ winnerTeam.getName());

        List<TeamTournament> teamTournamentList = teamTournamentService.getAllTeamTournaments(session);

        Team filteredWinnerTeam = winnerTeam;

        List<TeamTournament> teamTournamentsByTournament = teamTournamentList
                .stream()
                        .filter(teamTournament -> teamTournament.getTournament().getId().equals(chosenTournamentObject.getId()))
                .filter(teamTournament -> teamTournament.getTeam().getId().equals(filteredWinnerTeam.getId()))
                                .collect(Collectors.toList());
        int points = teamTournamentsByTournament.get(0).getTeamPoints();
      teamTournamentsByTournament.get(0).setTeamPoints(points+1);

        session.beginTransaction();
        session.save(match);
        session.saveOrUpdate(teamTournamentsByTournament.get(0));
        session.getTransaction().commit();
    }

}
