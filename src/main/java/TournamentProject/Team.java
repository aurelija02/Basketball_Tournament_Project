package TournamentProject;

import javax.persistence.*;
import java.util.List;

@Entity
@Table (name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name")
    private String name;

    @Column (name = "country")
    private String country;

    @Column (name = "budget")
    private int budget;

    @OneToMany (cascade = CascadeType.ALL, mappedBy = "team")
    private List<Player> players;

   @OneToMany (cascade = CascadeType.ALL, mappedBy = "team")
    private List<TeamTournament> teamTournaments;

  /* @ManyToMany (cascade = CascadeType.ALL) //kaip zinot kur deti toki manytomany, o kur trumpesni?
    @JoinTable(name = "team_tournament",
            joinColumns = @JoinColumn(name = "team_id"),
            inverseJoinColumns = @JoinColumn(name = "tournament_id"))
    private List<Tournament> tournaments;*/


    public Team(){}

    public Team(String name, String country, int budget) {
        this.name = name;
        this.country = country;
        this.budget = budget;
    }

    public List<TeamTournament> getTeamTournaments() {
        return teamTournaments;
    }

    public void setTeamTournaments(List<TeamTournament> teamTournaments) {
        this.teamTournaments = teamTournaments;
    }

    /* public List<Tournament> getTournaments() {
            return tournaments;
        }

        public void setTournaments(List<Tournament> tournaments) {
            this.tournaments = tournaments;
        }*/


    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}
