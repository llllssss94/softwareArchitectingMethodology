package ramenmachine.controller;

public class Start {

	public static void main(String[] args) 
	{
		MainController mainController = new MainController();
		mainController.initMachine();
		mainController.showInitScreen();
	}

}
