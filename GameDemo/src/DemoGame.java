
public class DemoGame extends Game {

	public DemoGame() {
		GameState welcome = new WelcomeState();
		GameState easy = new EasyState();
		GameState hard = new HardState();
		GameState finish = new FinishState();
		stateMachine.installState("Welcome", welcome);
		stateMachine.installState("Easy", easy);
		stateMachine.installState("Hard", hard);
		stateMachine.installState("Finish", finish);
		stateMachine.setStartState(welcome);
	}
	
	public static void main( String[] args ) {
	    Game app = new DemoGame();
	    app.setTitle( "Pong" );
	    app.setVisible( true );
	    app.run();
	    System.exit( 0 );
	  }
}
