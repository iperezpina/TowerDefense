package controller;

import java.io.File;

import Towers.Tower;
import Towers.Tower1;
import Towers.Tower2;
import Towers.Tower3;
import Towers.Tower4;
import Towers.Tower5;
import Towers.Tower6;
import Towers.Tower7;
import Towers.Tower8;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.AudioClip;
import model.GameState;
import model.TimerAll;
import model.TowerHolder;
import model.Upgrade;
import view.TowerDefenseView;

/**
 * Player class that represents the things a player can do
 * 
 * @author Ivan, Marisa, Laura, Albert
 *
 */
public class Player {
	private static int health;
	private static int coins;
	private static TowerDefenseView tdv;
	private ImageView currTowerImgView;
	private Image currTowerImg;
	private static Tower currTower;
	private String currURL;
	private static String id;
	private static GameState gameState = GameState.gameready;
	private static boolean towerSelected = false;
	private static int selectX, selectY;

	/**
	 * Constructor, set the health and the coins
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public Player() {
		health = 100;
		coins = 1000;
	}

	// Getter
	public int getHP() {
		return health;
	}

	// Getter
	public int getCoins() {
		return coins;
	}

	// Getter
	public TowerDefenseView getTdv() {
		return tdv;
	}

	/**
	 * This function decreases healh from the player, and updates the GUI in turn
	 * and if the game is over, calls Game OVer
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void takeDmg() {
		health -= 1;
		updatePlayerGUI();
		gameOver();
	}

	/**
	 * This function serves calls game over as a Alert It checks to see if the
	 * health is under zero and then closes the game
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void gameOver() {
		if (health <= 0) {

			tdv.gameOver();
			TimerAll.pause();
			Player.setGameState(GameState.gamepaused);
			// Platform.exit();

		}
	}

	// Setter
	public void setTdv(TowerDefenseView tdv) {
		this.tdv = tdv;
	}

	/**
	 * This function serves as all purpose function that draws on the GUI the image,
	 * the location, and the postion
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Image img, int x, int y, double d, double e
	 * @return n/a
	 * @throws n/a
	 */
	public static void decreaseCoins(int shift) {
		coins -= shift;
		updatePlayerGUI();
	}

	/**
	 * This function adds coins to the Player
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param int shift
	 * @return n/a
	 * @throws n/a
	 */
	public void increaseCoins(int shift) {
		coins += shift;
	}

	/**
	 * This function adds Cash to the player or coins to the player
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void addCash(int cash) {
		coins += cash;
		updatePlayerGUI();
	}

	// Getter
	public static GameState getGameState() {
		// System.out.println("Gamestate: " + gameState);
		return gameState;
	}

	// Setter
	public static void setGameState(GameState state) {
		// System.out.println("Changing " + gameState + " to " + state);
		gameState = state;

	}

	/**
	 * This function updates the GUI from with new coins and health
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param n/a
	 * @return n/a
	 * @throws n/a
	 */
	public static void updatePlayerGUI() {
		// tdv.getRightLabel().setText("Money: " + coins + "\nHealth: " + health);
		tdv.updatePlayerInfo(coins, health);
	}

	/**
	 * This EventHandler chooses the Tower and sends it back
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param MouseEvent event
	 * @return n/a
	 * @throws n/a
	 */
	public EventHandler<MouseEvent> chooseTower = new EventHandler<MouseEvent>() {

		@SuppressWarnings("deprecation")
		@Override
		public void handle(MouseEvent event) {
			currTowerImgView = (ImageView) event.getSource();
			currTowerImg = (Image) currTowerImgView.getImage();
			currURL = currTowerImg.impl_getUrl();
			id = currURL.substring(currURL.length() - 10);
			if (id != null && id.length() > 6) {
				id = id.substring(0, id.length() - 4);
			}
			Tower temp = makeTempTower(id, 0, 0);
			String result = temp.getTowerName() + " costs $" + temp.getTowerCost();
			tdv.updateTowerLabel(result);

		}
	};

	/**
	 * This function places a Tower at the location specified at that point
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param MouseEvent event
	 * @return n/a
	 * @throws n/a
	 */
	public static EventHandler<MouseEvent> placeTower = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			int x = (int) event.getX() / 32;
			int y = (int) event.getY() / 32;

			// Checks if we can shorten the id to remove the ".png"
			if (id != null && id.length() > 6) {
				id = id.substring(0, id.length() - 4);
			}
			// If id is null we will clear the tower selected pane
			if (id == null) {
				tdv.setAllBlank();
				towerSelected = false;
			}
			// If the tile we click on can have towers placed on it and the id isn't null we
			// will create a temp Tower,
			// then check if we have enough money to place the tower. Then update our cash
			// accordingly.
			if (tdv.getTm().GetTile(x, y).getType().isCanPlace() && id != null) {

				currTower = makeTempTower(id, x, y);

				if (currTower != null && !TowerHolder.isThereATower(x, y)) {
					if (coins >= currTower.getTowerCost()) {
						AudioClip coin = new AudioClip(new File("src/Sounds/coin.wav").toURI().toString());
						coin.play();
						decreaseCoins(currTower.getTowerCost());
						TowerHolder.addTower2(currTower, x, y);
						id = null;
					}
				}
			}
			if (TowerHolder.isThereATower(x, y)) {
				// Shows the tower info
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						Tower tower = TowerHolder.getTower(x, y);

						if (tower != null) {
							selectX = x;
							selectY = y;
							tower.setIsSelected(!tower.isSelected());
							String name = tower.getTowerName();
							int Ucost = tower.getUpgradeCost();
							int enemy = tower.getEnemiesDestroyed();
							int range = tower.getRange();
							tower.setSellCost();
							int sell = tower.getSellCost();
							int upgradeLevel = tower.getUpgradeLevel();
							Upgrade currentUpgrade = null;
							if (upgradeLevel >= 4) {
								currentUpgrade = new Upgrade("", "No more available upgrades", -1);
							} else {
								currentUpgrade = tower.getTowerUpgrades()[upgradeLevel];
							}

							tdv.setTowerSpecification(name, enemy, Ucost, range, sell, currentUpgrade);
							towerSelected = true;

						}
					}
				});

			}

		}

	};

	/**
	 * This makes a tempTower tower to create the classes
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String towerId, int x, int y
	 * @return Tower currTower
	 * @throws n/a
	 */
	public static Tower makeTempTower(String towerId, int x, int y) {
		Tower currTower = null;

		if (towerId.equals("tower1")) {
			currTower = new Tower1(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower2")) {
			currTower = new Tower2(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower3")) {
			currTower = new Tower3(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower4")) {
			currTower = new Tower4(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower5")) {
			currTower = new Tower5(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower6")) {
			currTower = new Tower6(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower7")) {
			currTower = new Tower7(towerId, x * 32, y * 32, 32, 32);
		}
		if (towerId.equals("tower8")) {
			currTower = new Tower8(towerId, x * 32, y * 32, 32, 32);
		}
		return currTower;
	}

	/**
	 * This Event Handler that selects the Tower
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Image img, int x, int y, double d, double e
	 * @return n/a
	 * @throws n/a
	 */
	public static EventHandler<MouseEvent> upgradeTower = new EventHandler<MouseEvent>() {

		@SuppressWarnings("deprecation")
		@Override
		public void handle(MouseEvent event) {
			if (towerSelected) {

				System.out.println(TowerHolder.isThereATower(selectX, selectY));
				// Check if there is a tower from the selectX and selectY variables
				if (TowerHolder.isThereATower(selectX, selectY)) {

					// Will attempt to update a tower if possible
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Tower tower = TowerHolder.getTower(selectX, selectY);
							if (tower != null) {
								if (tower instanceof Tower1) {
									checkUpgradeTower1(tower);
								}
								if (tower instanceof Tower2) {
									checkUpgradeTower2(tower);
								}
								if (tower instanceof Tower3) {
									checkUpgradeTower3(tower);
								}
								if (tower instanceof Tower4) {
									checkUpgradeTower4(tower);
								}
								if (tower instanceof Tower5) {
									checkUpgradeTower5(tower);
								}
								if (tower instanceof Tower6) {
									checkUpgradeTower6(tower);
								}
								if (tower instanceof Tower7) {
									checkUpgradeTower7(tower);
								}
								if (tower instanceof Tower8) {
									checkUpgradeTower8(tower);
								}

							}
						}
					});

				}
			}

		}
	};

	/**
	 * Deals with calculations for upgrading a tower that is from the Tower1 class
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Tower tower
	 * @return n/a
	 * @throws n/a
	 */

	public static void checkUpgradeTower1(Tower tower) {
		Tower1 temp1 = (Tower1) tower;
		if (temp1.getUpgradeLevel() == 0) {
			temp1.upgrade1();
		} else if (temp1.getUpgradeLevel() == 1) {
			temp1.upgrade2();
		} else if (temp1.getUpgradeLevel() == 2) {
			temp1.upgrade3();
		} else if (temp1.getUpgradeLevel() == 3) {
			temp1.upgrade4();
		}
		int upgradeLevel = temp1.getUpgradeLevel();
		Upgrade currentUpgrade = new Upgrade("", "", -1);
		if (upgradeLevel >= 4) {
			currentUpgrade = new Upgrade("", "No more available upgrades", -1);
		} else {
			currentUpgrade = temp1.getTowerUpgrades()[upgradeLevel];
		}
		String name = temp1.getTowerName();
		int Ucost = temp1.getUpgradeCost();
		int enemy = temp1.getEnemiesDestroyed();
		int range = temp1.getRange();
		temp1.setSellCost();
		int sell = temp1.getSellCost();
		tdv.setTowerSpecification(name, enemy, Ucost, range, sell, currentUpgrade);
		towerSelected = true;
	}

	/**
	 * Deals with calculations for upgrading a tower that is from the Tower2 class
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Tower tower
	 * @return n/a
	 * @throws n/a
	 */

	public static void checkUpgradeTower2(Tower tower) {
		Tower2 temp2 = (Tower2) tower;
		if (temp2.getUpgradeLevel() == 0) {
			temp2.upgrade1();
		} else if (temp2.getUpgradeLevel() == 1) {
			temp2.upgrade2();
		} else if (temp2.getUpgradeLevel() == 2) {
			temp2.upgrade3();
		} else if (temp2.getUpgradeLevel() == 3) {
			temp2.upgrade4();
		}
		int upgradeLevel = temp2.getUpgradeLevel();
		Upgrade currentUpgrade = new Upgrade("", "", -1);
		if (upgradeLevel >= 4) {
			currentUpgrade = new Upgrade("", "No more available upgrades", -1);
		} else {
			currentUpgrade = temp2.getTowerUpgrades()[upgradeLevel];
		}
		String name = temp2.getTowerName();
		int Ucost = temp2.getUpgradeCost();
		int enemy = temp2.getEnemiesDestroyed();
		int range = temp2.getRange();
		temp2.setSellCost();
		int sell = temp2.getSellCost();
		tdv.setTowerSpecification(name, enemy, Ucost, range, sell, currentUpgrade);
		towerSelected = true;
	}

	/**
	 * Deals with calculations for upgrading a tower that is from the Tower3 class
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Tower tower
	 * @return n/a
	 * @throws n/a
	 */
	public static void checkUpgradeTower3(Tower tower) {
		Tower3 temp3 = (Tower3) tower;
		if (temp3.getUpgradeLevel() == 0) {
			temp3.upgrade1();
		} else if (temp3.getUpgradeLevel() == 1) {
			temp3.upgrade2();
		} else if (temp3.getUpgradeLevel() == 2) {
			temp3.upgrade3();
		} else if (temp3.getUpgradeLevel() == 3) {
			temp3.upgrade4();
		}
		int upgradeLevel = temp3.getUpgradeLevel();
		Upgrade currentUpgrade = new Upgrade("", "", -1);
		if (upgradeLevel >= 4) {
			currentUpgrade = new Upgrade("", "No more available upgrades", -1);
		} else {
			currentUpgrade = temp3.getTowerUpgrades()[upgradeLevel];
		}
		String name = temp3.getTowerName();
		int Ucost = temp3.getUpgradeCost();
		int enemy = temp3.getEnemiesDestroyed();
		int range = temp3.getRange();
		temp3.setSellCost();
		int sell = temp3.getSellCost();
		tdv.setTowerSpecification(name, enemy, Ucost, range, sell, currentUpgrade);
		towerSelected = true;
	}

	/**
	 * Deals with calculations for upgrading a tower that is from the Tower4 class
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Tower tower
	 * @return n/a
	 * @throws n/a
	 */
	public static void checkUpgradeTower4(Tower tower) {
		Tower4 temp4 = (Tower4) tower;
		if (temp4.getUpgradeLevel() == 0) {
			temp4.upgrade1();
		} else if (temp4.getUpgradeLevel() == 1) {
			temp4.upgrade2();
		} else if (temp4.getUpgradeLevel() == 2) {
			temp4.upgrade3();
		} else if (temp4.getUpgradeLevel() == 3) {
			temp4.upgrade4();
		}
		int upgradeLevel = temp4.getUpgradeLevel();
		Upgrade currentUpgrade = new Upgrade("", "", -1);
		if (upgradeLevel >= 4) {
			currentUpgrade = new Upgrade("", "No more available upgrades", -1);
		} else {
			currentUpgrade = temp4.getTowerUpgrades()[upgradeLevel];
		}
		String name = temp4.getTowerName();
		int Ucost = temp4.getUpgradeCost();
		int enemy = temp4.getEnemiesDestroyed();
		int range = temp4.getRange();
		temp4.setSellCost();
		int sell = temp4.getSellCost();
		tdv.setTowerSpecification(name, enemy, Ucost, range, sell, currentUpgrade);
		towerSelected = true;
	}

	/**
	 * Deals with calculations for upgrading a tower that is from the Tower5 class
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Tower tower
	 * @return n/a
	 * @throws n/a
	 */
	public static void checkUpgradeTower5(Tower tower) {
		Tower5 temp5 = (Tower5) tower;
		if (temp5.getUpgradeLevel() == 0) {
			temp5.upgrade1();
		} else if (temp5.getUpgradeLevel() == 1) {
			temp5.upgrade2();
		} else if (temp5.getUpgradeLevel() == 2) {
			temp5.upgrade3();
		} else if (temp5.getUpgradeLevel() == 3) {
			temp5.upgrade4();
		}
		int upgradeLevel = temp5.getUpgradeLevel();
		Upgrade currentUpgrade = new Upgrade("", "", -1);
		if (upgradeLevel >= 4) {
			currentUpgrade = new Upgrade("", "No more available upgrades", -1);
		} else {
			currentUpgrade = temp5.getTowerUpgrades()[upgradeLevel];
		}
		String name = temp5.getTowerName();
		int Ucost = temp5.getUpgradeCost();
		int enemy = temp5.getEnemiesDestroyed();
		int range = temp5.getRange();
		temp5.setSellCost();
		int sell = temp5.getSellCost();
		tdv.setTowerSpecification(name, enemy, Ucost, range, sell, currentUpgrade);
		towerSelected = true;
	}

	/**
	 * Deals with calculations for upgrading a tower that is from the Tower6 class
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Tower tower
	 * @return n/a
	 * @throws n/a
	 */
	public static void checkUpgradeTower6(Tower tower) {
		Tower6 temp6 = (Tower6) tower;
		if (temp6.getUpgradeLevel() == 0) {
			temp6.upgrade1();
		} else if (temp6.getUpgradeLevel() == 1) {
			temp6.upgrade2();
		} else if (temp6.getUpgradeLevel() == 2) {
			temp6.upgrade3();
		} else if (temp6.getUpgradeLevel() == 3) {
			temp6.upgrade4();
		}
		int upgradeLevel = temp6.getUpgradeLevel();
		Upgrade currentUpgrade = new Upgrade("", "", -1);
		if (upgradeLevel >= 4) {
			currentUpgrade = new Upgrade("", "No more available upgrades", -1);
		} else {
			currentUpgrade = temp6.getTowerUpgrades()[upgradeLevel];
		}
		String name = temp6.getTowerName();
		int Ucost = temp6.getUpgradeCost();
		int enemy = temp6.getEnemiesDestroyed();
		int range = temp6.getRange();
		temp6.setSellCost();
		int sell = temp6.getSellCost();
		tdv.setTowerSpecification(name, enemy, Ucost, range, sell, currentUpgrade);
		towerSelected = true;
	}

	/**
	 * Deals with calculations for upgrading a tower that is from the Tower7 class
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Tower tower
	 * @return n/a
	 * @throws n/a
	 */
	public static void checkUpgradeTower7(Tower tower) {
		Tower7 temp7 = (Tower7) tower;
		if (temp7.getUpgradeLevel() == 0) {
			temp7.upgrade1();
		} else if (temp7.getUpgradeLevel() == 1) {
			temp7.upgrade2();
		} else if (temp7.getUpgradeLevel() == 2) {
			temp7.upgrade3();
		} else if (temp7.getUpgradeLevel() == 3) {
			temp7.upgrade4();
		}
		int upgradeLevel = temp7.getUpgradeLevel();
		Upgrade currentUpgrade = new Upgrade("", "", -1);
		if (upgradeLevel >= 4) {
			currentUpgrade = new Upgrade("", "No more available upgrades", -1);
		} else {
			currentUpgrade = temp7.getTowerUpgrades()[upgradeLevel];
		}
		String name = temp7.getTowerName();
		int Ucost = temp7.getUpgradeCost();
		int enemy = temp7.getEnemiesDestroyed();
		int range = temp7.getRange();
		temp7.setSellCost();
		int sell = temp7.getSellCost();
		tdv.setTowerSpecification(name, enemy, Ucost, range, sell, currentUpgrade);
		towerSelected = true;
	}

	/**
	 * Deals with calculations for upgrading a tower that is from the Tower8 class
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param Tower tower
	 * @return n/a
	 * @throws n/a
	 */
	public static void checkUpgradeTower8(Tower tower) {
		Tower8 temp8 = (Tower8) tower;
		if (temp8.getUpgradeLevel() == 0) {
			temp8.upgrade1();
		} else if (temp8.getUpgradeLevel() == 1) {
			temp8.upgrade2();
		} else if (temp8.getUpgradeLevel() == 2) {
			temp8.upgrade3();
		} else if (temp8.getUpgradeLevel() == 3) {
			temp8.upgrade4();
		}
		int upgradeLevel = temp8.getUpgradeLevel();
		Upgrade currentUpgrade = new Upgrade("", "", -1);
		if (upgradeLevel >= 4) {
			currentUpgrade = new Upgrade("", "No more available upgrades", -1);
		} else {
			currentUpgrade = temp8.getTowerUpgrades()[upgradeLevel];
		}
		String name = temp8.getTowerName();
		int Ucost = temp8.getUpgradeCost();
		int enemy = temp8.getEnemiesDestroyed();
		int range = temp8.getRange();
		temp8.setSellCost();
		int sell = temp8.getSellCost();
		tdv.setTowerSpecification(name, enemy, Ucost, range, sell, currentUpgrade);
		towerSelected = true;
	}

	// Getter
	public static int getCurrentCash() {
		return coins;
	}

	/**
	 * This EventHandler handles the sell Tower methods
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param ActionEvent event
	 * @return n/a
	 * @throws n/a
	 */
	public static EventHandler<ActionEvent> sellTower = new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent event) {
			// System.out.println("hello");
			// Tower tobeRem = TowerHolder.getTower(selectX, selectY);
			if (TowerHolder.isThereATower(selectX, selectY)) {
				TowerHolder.getTower(selectX, selectY).changeActive();
				TowerHolder.removeTower(selectX, selectY);
				tdv.setAllBlank();
			}

		}

	};

}
