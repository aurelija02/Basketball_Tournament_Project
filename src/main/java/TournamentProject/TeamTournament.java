package TournamentProject;

import javax.persistence.*;

@Entity
@Table(name = "team_tournament")
public class TeamTournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "points")
    private int teamPoints;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "team_id")
    private Team team;

    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "tournament_id")
    private Tournament tournament;

    public TeamTournament() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTeamPoints() {
        return teamPoints;
    }

    public void setTeamPoints(int teamPoints) {
        this.teamPoints = teamPoints;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
