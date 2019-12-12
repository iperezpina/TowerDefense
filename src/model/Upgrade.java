package model;

/**
 * This class handles the Upgrade methods for the WOW factor
 * 
 * @author Ivan, Marisa, Laura, Albert
 *
 */
public class Upgrade {

	private String upgradeName;
	private String upgradeDetail;
	private int upgradeCost;

	/**
	 * Upgrade Constructor
	 * 
	 * @author Ivan, Marisa, Laura, Albert
	 * @param String name, String detail, int cost
	 * @return n/a
	 * @throws n/a
	 */
	public Upgrade(String name, String detail, int cost) {
		this.upgradeName = name;
		this.upgradeDetail = detail;
		this.upgradeCost = cost;
	}

	// Getter
	public String getUpgradeName() {
		return upgradeName;
	}

	// Getter
	public String getUpgradeDetail() {
		return upgradeDetail;
	}

	// Getter
	public int getUpgradeCost() {
		return upgradeCost;
	}

	// Setter
	public void setUpgradeName(String upgradeName) {
		this.upgradeName = upgradeName;
	}

	// Setter
	public void setUpgradeDetail(String upgradeDetail) {
		this.upgradeDetail = upgradeDetail;
	}

	// Setter
	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

}
