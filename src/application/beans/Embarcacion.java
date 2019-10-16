package application.beans;

public class Embarcacion {

	private Integer imoNumber;

	private Double loa;

	private Double breath;

	private Double depth;

	private Double summerDraft;

	private Double dw;

	private Integer buildYear;

	private String previousName;

	public Embarcacion() {
		this.imoNumber = 0;
		this.loa = 0.0;
		this.breath = 0.0;
		this.depth = 0.0;
		this.summerDraft = 0.0;
		this.dw = 0.0;
		this.buildYear = 0;
		this.previousName = "";
	}

	public Embarcacion(Integer imoNumber, Double loa, Double breath, Double depth, Double summerDraft, Double dw,
			Integer buildYear, String previousName) {
		this.imoNumber = imoNumber;
		this.loa = loa;
		this.breath = breath;
		this.depth = depth;
		this.summerDraft = summerDraft;
		this.dw = dw;
		this.buildYear = buildYear;
		this.previousName = previousName;
	}

	public Integer getImoNumber() {
		return imoNumber;
	}

	public void setImoNumber(Integer imoNumber) {
		this.imoNumber = imoNumber;
	}

	public Double getLoa() {
		return loa;
	}

	public void setLoa(Double loa) {
		this.loa = loa;
	}

	public Double getBreath() {
		return breath;
	}

	public void setBreath(Double breath) {
		this.breath = breath;
	}

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}

	public Double getSummerDraft() {
		return summerDraft;
	}

	public void setSummerDraft(Double summerDraft) {
		this.summerDraft = summerDraft;
	}

	public Double getDw() {
		return dw;
	}

	public void setDw(Double dw) {
		this.dw = dw;
	}

	public Integer getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(Integer buildYear) {
		this.buildYear = buildYear;
	}

	public String getPreviousName() {
		return previousName;
	}

	public void setPreviousName(String previousName) {
		this.previousName = previousName;
	}

	@Override
	public String toString() {
		return "Embarcacion [imoNumber=" + imoNumber + ", loa=" + loa + ", breath=" + breath + ", depth=" + depth
				+ ", summerDraft=" + summerDraft + ", dw=" + dw + ", buildYear=" + buildYear + ", previousName="
				+ previousName + "]";
	}
}
