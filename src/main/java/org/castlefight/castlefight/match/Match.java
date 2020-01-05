package org.castlefight.castlefight.match;

public class Match {
    private PlayerPlaying player1;
    private PlayerPlaying player2;
    private String status;
    private Integer id;
    public PlayerPlaying getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerPlaying player1) {
        this.player1 = player1;
    }

    public PlayerPlaying getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerPlaying player2) {
        this.player2 = player2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
