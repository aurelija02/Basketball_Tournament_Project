package TournamentProject;

import javax.persistence.*;

@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "winner_team_id")
    private Long winnerTeamId;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "team1_id")
    private Team team1;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "team2_id")
    private Team team2;

    @Column(name = "team1_points")
    private int team1Points;

    @Column(name = "team2_points")
    private int team2Points;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    public Match (){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWinnerTeamId() {
        return winnerTeamId;
    }

    public void setWinnerTeamId(Long winnerTeamId) {
        this.winnerTeamId = winnerTeamId;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getTeam1Points() {
        return team1Points;
    }

    public void setTeam1Points(int team1Points) {
        this.team1Points = team1Points;
    }

    public int getTeam2Points() {
        return team2Points;
    }

    public void setTeam2Points(int team2Points) {
        this.team2Points = team2Points;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
