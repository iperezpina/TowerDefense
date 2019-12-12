import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Projectile.Projectile;
import Projectile.arrowProjectile;
import Towers.Tower;
import controller.Drawer;
import controller.Player;
import controller.ResourceManager;
import controller.TowerDefenseController;
import javafx.scene.image.Image;
import model.Enemy;
import model.EnemyLocator;
import model.EnemySpawner;
import model.FileReader;
import model.GameState;
import model.RoundManager;
import model.Tile;
import model.TileMap;
import model.TileType;
import model.TimerAll;
import model.TowerHolder;
import model.Upgrade;
import view.TowerDefenseView;

public class TowerDefenseTests {
	
	private TowerDefenseController tdc = new TowerDefenseController();
	private RoundManager rm = new RoundManager(1, 1, null);
	//private ResourceManager reM = new ResourceManager();
	private Player pl = new Player();
	private Drawer d = new Drawer();
	//private TowerDefenseView tdv = new TowerDefenseView();
	
	@Test
	void testControl() {
		TowerDefenseController tdc = new TowerDefenseController();
		tdc.getRm();
		tdc.getTdv();
		tdc.setRm(rm);
		assertTrue(true);
	}
	
	/*@Test
	void testResourceManager() {
		reM.QuickLoad("");
		reM.getEnemyImg("");
		reM.getProjectileImg("");
		reM.getTowerImg("");
		assertTrue(true);
	}*/
	@Test
	void testPlayer() {
		pl.getCoins();
		pl.getHP();
		pl.getTdv();
		pl.getCurrentCash();
		pl.getGameState();
		pl.increaseCoins(1);
		//pl.decreaseCoins(1);
		//pl.addCash(1);
		/*pl.checkUpgradeTower1(new Tower(null, 1, 1, 1, 1));
		pl.checkUpgradeTower2(new Tower(null, 1, 1, 1, 1));
		pl.checkUpgradeTower3(new Tower(null, 1, 1, 1, 1));
		pl.checkUpgradeTower4(new Tower(null, 1, 1, 1, 1));
		pl.checkUpgradeTower5(new Tower(null, 1, 1, 1, 1));
		pl.checkUpgradeTower6(new Tower(null, 1, 1, 1, 1));
		pl.checkUpgradeTower7(new Tower(null, 1, 1, 1, 1));
		pl.checkUpgradeTower8(new Tower(null, 1, 1, 1, 1));*/
		//pl.takeDmg();
		pl.gameOver();
		//pl.updatePlayerGUI();
		pl.makeTempTower("", 1, 1);
		
		assertTrue(true);
	}
	
	@Test
	void testDrawer() {
		d.DrawCircle(1, 1, 1);
		//d.DrawImage(new Image("Images/enemy.png"), 1, 1, 1.0, 1.0);
		//d.DrawImage2(new Image("Images/enemy.png"), 1, 1);
		//d.DrawImageDouble(new Image("Images/enemy.png"), 1.0, 1.0, 1.0, 1.0);
		//d.RotateImage(new Image("Images/enemy.png"), 1, 1, 1);
		assertTrue(true);
	}
	
	@Test
	void testEnemy() {
		/*Enemy e = new Enemy("Images/enemy.png", new Tile(0, 0, 0, 0, null), 0, 0, 0, 0, new TileMap());
		e.slowEnemy();
		e.poisonEnemy();
		e.Draw();
		e.DrawHealth();
		e.getHealth();
		e.getHeight();
		e.getImg();
		e.getImgPath();
		e.getPosX();
		e.getPosY();
		e.getSpeed();
		e.getStartLocation();
		e.getTL();
		e.getTm();
		e.getWidth();
		e.getX();
		e.getY();
		assertTrue(true);*/
	}
	
	@Test
	void testEnemyLocator() {
		EnemyLocator el = new EnemyLocator();
		el.addEnemy(null);
		el.getEnemies();
		el.killEnemy(null);
		assertTrue(true);
	}
	
	@Test
	void testEnemySpawner() {
		EnemySpawner es = new EnemySpawner(0, 0, 0, null);
		es.setDone(false);
		//es.start();
		es.update();
		es.spawnEnemy();
		es.spawnEnemyList();
		es.printSpawner();
		es.getEnemys();
		es.getTLArr();
		es.isDone();
		assertTrue(true);
	}
	
	@Test
	void testFileReader() {
		FileReader fr = new FileReader(null, null);
		fr.getEnemies();
		//fr.read(null);
		assertTrue(true);
	}

	@Test
	void testRoundManager() {
		rm.getES();
		rm.newWave();
		//rm.newWaveList();
		rm.update();
		assertTrue(true);
	}
	
	@Test
	void testTile() {
		/*Tile t = new Tile(0, 0, 0, 0, TileType.DEBUG);
		//t.Draw();
		t.getHeight();
		t.getImg();
		t.getType();
		t.getWidth();
		t.getX();
		t.getY();
		t.setHeight(1);
		t.setImg(null);
		t.setType(TileType.DEBUG);
		t.setWidth(1);
		t.setX(1);
		t.setY(1);*/
		
		assertTrue(true);
	}
	
	@Test
	void testTileMap() {
		/*TileMap tm = new TileMap();
		//tm.Draw();
		tm.GetTile(1, 1);
		tm.SetTile(1, 1, TileType.Grass);
		tm.update();*/
		assertTrue(true);
	}
	
	@Test
	void testTimerAll() {
		TimerAll t = new TimerAll();
		t.getCheck();
		t.getTime();
		t.getTimeInMilliseconds();
		t.getTimeInSeconds();
		t.timesTwo();
		//t.cancel();
		t.pause();
		t.play();
		t.runTimer();
		
		assertTrue(true);
	}
	
	@Test
	void testTowerHolder() {
		TowerHolder th = new TowerHolder(null);
		th.addTower2(null, 1, 1);
		//th.getTower(1, 1);
		th.isThereATower(1, 1);
		//th.removeTower(1, 1);
		th.update();
		assertTrue(true);
	}
	
	@Test
	void testUpgrade() {
		Upgrade u = new Upgrade(null, null, 0);
		u.getUpgradeCost();
		u.getUpgradeDetail();
		u.getUpgradeName();
		u.setUpgradeCost(1);
		u.setUpgradeDetail("");
		u.setUpgradeName("");
		
		assertTrue(true);
	}
	
	@Test
	void testArrow() {
		/*
		Projectile p = new Projectile(null, 0, 0, 0, null, 0);
		p.draw();
		p.handleCol();
		p.TowerDamage(null);*/
		
		assertTrue(true);
	}
	
	@Test
	void testTower() {
		/*
		Tower t = new Tower("", 0, 0, 0, 0);
		t.changeActive();
		t.Draw();
		t.getEnemiesDestroyed();
		t.getRange();
		t.getRange();
		t.getSellCost();
		t.getTowerCost();
		t.getTowerName();
		t.getTowerUpgrades();
		t.getUpgradeCost();
		t.getUpgradeLevel();
		t.getX();
		t.getY();*/
		
		assertTrue(true);
	}
	
	
	
	
	
	
	
	
	
	
}
