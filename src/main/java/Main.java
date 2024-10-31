import com.worldcup.scoreboard.model.Game;
import com.worldcup.scoreboard.repository.GameRepository;
import com.worldcup.scoreboard.repository.InMemoryGameRepository;
import com.worldcup.scoreboard.service.FootballScoreBoard;
import com.worldcup.scoreboard.service.ScoreBoard;
import com.worldcup.scoreboard.service.ScoreBoardValidator;

public class Main {
    public static void main(String[] args) {
        GameRepository gameRepository = new InMemoryGameRepository();
        ScoreBoardValidator scoreBoardValidator = new ScoreBoardValidator();
        ScoreBoard footballScoreBoard = new FootballScoreBoard(gameRepository, scoreBoardValidator);

        initializeGames(footballScoreBoard);

        footballScoreBoard.getSummary().forEach(System.out::println);
    }

    private static void initializeGames(ScoreBoard footballScoreBoard) {
        Game mexicoCanadaGame = footballScoreBoard.startGame("Mexico", "Canada");
        footballScoreBoard.updateScore(mexicoCanadaGame, 0, 5);

        Game spainBrazilGame = footballScoreBoard.startGame("Spain", "Brazil");
        footballScoreBoard.updateScore(spainBrazilGame, 10, 2);

        Game germanyFranceGame = footballScoreBoard.startGame("Germany", "France");
        footballScoreBoard.updateScore(germanyFranceGame, 2, 2);

        Game uruguayItalyGame = footballScoreBoard.startGame("Uruguay", "Italy");
        footballScoreBoard.updateScore(uruguayItalyGame, 6, 6);

        Game argentinaAustraliaGame = footballScoreBoard.startGame("Argentina", "Australia");
        footballScoreBoard.updateScore(argentinaAustraliaGame, 3, 1);
    }
}