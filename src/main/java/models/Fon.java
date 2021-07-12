package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "Fon")
public class Fon {

	@Id
	@Column(name = "FonKodu")
	private String fonKodu;
	
	@Column(name = "companyType")
	private int companyType;
	
/*	@OneToMany(fetch = FetchType.LAZY, mappedBy = "FonKodu")
	private List<HistoricData> historyData;*/
	
	@Column(name = "FonAdi")
	private String fonAdi;
	
	@Column(name = "FonTuru")
	private String fonTuru;
	
	@Column(name = "FonTipi")
	private String fonTipi;
	
	public Fon(){
		companyType = -1;
		fonKodu = "";
		fonAdi = "";
		fonTuru = "";
		fonTipi = "";
/*		historyData = null;*/
	}
	public Fon(int companyType, String FonKodu, String FonAdi, String FonTuru, String FonTipi, List<HistoricData> historyData){
		this.companyType = companyType;
		this.fonKodu = FonKodu;
		this.fonAdi = FonAdi;
		this.fonTuru = FonTuru;
		this.fonTipi = FonTipi;
		/*this.historyData = historyData;*/
	}
	
	public int getCompanyType() {
		return companyType;
	}
	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}
	
	public String getFonKodu() {
		return fonKodu;
	}
	public void setFonKodu(String fonKodu) {
		this.fonKodu = fonKodu;
	}
	
	public String getFonAdi() {
		return fonAdi;
	}
	public void setFonAdi(String fonAdi) {
		this.fonAdi = fonAdi;
	}
	
	public String getFonTuru() {
		return fonTuru;
	}
	public void setFonTuru(String fonTuru) {
		this.fonTuru = fonTuru;
	}
	
	public String getFonTipi() {
		return fonTipi;
	}
	public void setFonTipi(String fonTipi) {
		this.fonTipi = fonTipi;
	}
	
}
