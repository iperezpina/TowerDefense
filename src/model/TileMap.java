package model;

import java.util.ArrayList;
import java.util.List;

public class TileMap {

	private List<List<Tile>> tileGrid;
	
	public TileMap() {
		this.tileGrid = new ArrayList<List<Tile>>();
		
		for (int i = 0; i < 20; i++) {
			ArrayList<Tile> temp = new ArrayList<Tile>();
			for (int j = 0; j < 15; j++) {
				temp.add( new Tile(i * 32, j * 32, 32, 32, TileType.Grass));
			}
			this.tileGrid.add(temp);
			
		}
	}
	
	public TileMap(int[][] tileMap) {
		this.tileGrid = new ArrayList<List<Tile>>();
		
		for (int i = 0; i < 20; i++) {
			ArrayList<Tile> temp = new ArrayList<Tile>();
			for (int j = 0; j < 15; j++) {
				int value = tileMap[j][i];
				switch(value){
				case 0:
					temp.add( new Tile(i * 32, j * 32, 32, 32, TileType.Grass));
					break;
					
				case 1:
					temp.add( new Tile(i * 32, j * 32, 32, 32, TileType.Dirt));
					break;
				
				case 2:
					temp.add( new Tile(i * 32, j * 32, 32, 32, TileType.Water));
					break;
			}
				
			}
			this.tileGrid.add(temp);
			
		}
	}
	
	public void SetTile(int x, int y, TileType type) {
		Tile newTile = new Tile(x * 32, y * 32, 32, 32, type);
		tileGrid.get(x).set(y, newTile);
	}

	public Tile GetTile(int x, int y) {
		return tileGrid.get(x).get(y);
	}
	
	public void Draw() {
		for (List<Tile> list : tileGrid) {
			for (Tile tile: list) {
				tile.Draw();
			}
		}
	}
}
