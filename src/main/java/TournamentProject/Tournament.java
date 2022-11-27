package TournamentProject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tournament")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "title")
    private String title;

    @Column (name = "prize")
    private int prize;

    /*@ManyToMany(cascade = CascadeType.ALL, mappedBy = "tournaments")
    private List<Team> teams;*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournament")
    private List<Match> matches;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "tournament")
    private List<TeamTournament> teamTournaments;


    public  Tournament (){}

    public Tournament(String title, int prize) {
        this.title = title;
        this.prize = prize;
    }

    public List<TeamTournament> getTeamTournaments() {
        return teamTournaments;
    }

    public void setTeamTournaments(List<TeamTournament> teamTournaments) {
        this.teamTournaments = teamTournaments;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    /*public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }
}
