package model;

public class Upgrade {

	private String upgradeName;
	private String upgradeDetail;
	private int upgradeCost;
	
	public Upgrade(String name, String detail, int cost) {
		this.upgradeName = name;
		this.upgradeDetail = detail;
		this.upgradeCost = cost;
	}
	
	public String getUpgradeName() {
		return upgradeName;
	}

	public String getUpgradeDetail() {
		return upgradeDetail;
	}

	public int getUpgradeCost() {
		return upgradeCost;
	}

	public void setUpgradeName(String upgradeName) {
		this.upgradeName = upgradeName;
	}

	public void setUpgradeDetail(String upgradeDetail) {
		this.upgradeDetail = upgradeDetail;
	}

	public void setUpgradeCost(int upgradeCost) {
		this.upgradeCost = upgradeCost;
	}

	
	
}
