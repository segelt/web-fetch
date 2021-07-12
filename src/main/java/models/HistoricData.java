package models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "HistoricData")
@IdClass(FonKodKey.class)
public class HistoricData{
	@Id
	private String FonKodu;
	
	@Id
	private LocalDate Tarih;
	
/*	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name="FonKodu")
	private Fund fund;*/
	
	@Column(name = "ToplamDeger")
	@ColumnDefault("-1")
	private BigDecimal ToplamDeger;
	
	@Column(name = "BirimPayDegeri")
	@ColumnDefault("-1")
	private float BirimPayDegeri;
	
	@Column(name = "DolasimdakiPaySayisi")
	@ColumnDefault("-1")
	private BigDecimal DolasimdakiPaySayisi; 
	
	@Column(name = "YatirimciSayisi")
	@ColumnDefault("-1")
	private long YatirimciSayisi;
	
	@Column(name = "BankaBonosu")
	@ColumnDefault("-1")
	private float BankaBonosu;
	
	@Column(name = "Diger")
	@ColumnDefault("-1")
	private float Diger;
	
	@Column(name = "DevletTahvili")
	@ColumnDefault("-1")
	private float DevletTahvili;
	
	@Column(name = "DovizOdemeliBono")
	@ColumnDefault("-1")
	private float DovizOdemeliBono;
	
	@Column(name = "DovizOdemeliTahvil")
	@ColumnDefault("-1")
	private float DovizOdemeliTahvil;
	
	@Column(name = "Eurobond")
	@ColumnDefault("-1")
	private float Eurobond;
	
	@Column(name = "FinansmanBonosu")
	@ColumnDefault("-1")
	private float FinansmanBonosu;
	
	@Column(name = "FonKatilmaBelgesi")
	@ColumnDefault("-1")
	private float FonKatilmaBelgesi;
	
	@Column(name = "GayrimenkulSertifikasi")
	@ColumnDefault("-1")
	private float GayrimenkulSertifikasi;
	
	@Column(name = "HazineBonosu")
	@ColumnDefault("-1")
	private float HazineBonosu;
	
	@Column(name = "HisseSenedi")
	@ColumnDefault("-1")
	private float HisseSenedi;
	
	@Column(name = "KamuDisBorclanmaAraci")
	@ColumnDefault("-1")
	private float KamuDisBorclanmaAraci;
	
	@Column(name = "KamuKiraSertifikasi")
	@ColumnDefault("-1")
	private float KamuKiraSertifikasi;
	
	@Column(name = "KatilimHesabi")
	@ColumnDefault("-1")
	private float KatilimHesabi;
	
	@Column(name = "KiymetliMaden")
	@ColumnDefault("-1")
	private float KiymetliMaden;
	
	@Column(name = "OzelSektorKiraSertifikasi")
	@ColumnDefault("-1")
	private float OzelSektorKiraSertifikasi;
	
	@Column(name = "OzelSektorTahvili")
	@ColumnDefault("-1")
	private float OzelSektorTahvili;
	
	@Column(name = "TersRepo")
	@ColumnDefault("-1")
	private float TersRepo;

	@Column(name = "TPP")
	@ColumnDefault("-1")
	private float TPP;
	
	@Column(name = "TurevAraci")
	@ColumnDefault("-1")
	private float TurevAraci;
	
	@Column(name = "VarligaDayaliMenkulKiymet")
	@ColumnDefault("-1")
	private float VarligaDayaliMenkulKiymet;
	
	@Column(name = "VadeliMevduat")
	@ColumnDefault("-1")
	private float VadeliMevduat;
	
	@Column(name = "YabanciBorclanmaAraci")
	@ColumnDefault("-1")
	private float YabanciBorclanmaAraci;
	
	@Column(name = "YabanciHisseSenedi")
	@ColumnDefault("-1")
	private float YabanciHisseSenedi;

	@Column(name = "YabanciMenkulKiymet")
	@ColumnDefault("-1")
	private float YabanciMenkulKiymet;
	
	
	public HistoricData(){	}


	public String getFonKodu() {
		return FonKodu;
	}
	public void setFonKodu(String fonKodu) {
		this.FonKodu = fonKodu;
	}

	public LocalDate getTarih() {
		return Tarih;
	}
	public void setTarih(LocalDate tarih) {
		this.Tarih = tarih;
	}

/*	public Fund getFund() {
		return fund;
	}

	public void setFund(Fund fund) {
		this.fund = fund;
	}*/
	
	public BigDecimal getToplamDeger() {
		return ToplamDeger;
	}
	public void setToplamDeger(BigDecimal toplamDeger) {
		this.ToplamDeger = toplamDeger;
	}


	public float getBirimPayDegeri() {
		return BirimPayDegeri;
	}
	public void setBirimPayDegeri(float birimPayDegeri) {
		this.BirimPayDegeri = birimPayDegeri;
	}


	public BigDecimal getDolasimdakiPaySayisi() {
		return DolasimdakiPaySayisi;
	}
	public void setDolasimdakiPaySayisi(BigDecimal dolasimdakiPaySayisi) {
		this.DolasimdakiPaySayisi = dolasimdakiPaySayisi;
	}


	public long getYatirimciSayisi() {
		return YatirimciSayisi;
	}
	public void setYatirimciSayisi(long yatirimciSayisi) {
		this.YatirimciSayisi = yatirimciSayisi;
	}


	public float getBankaBonosu() {
		return BankaBonosu;
	}
	public void setBankaBonosu(float bankaBonosu) {
		this.BankaBonosu = bankaBonosu;
	}


	public float getDiger() {
		return Diger;
	}
	public void setDiger(float diger) {
		this.Diger = diger;
	}


	public float getDevletTahvili() {
		return DevletTahvili;
	}
	public void setDevletTahvili(float devletTahvili) {
		this.DevletTahvili = devletTahvili;
	}


	public float getDovizOdemeliBono() {
		return DovizOdemeliBono;
	}
	public void setDovizOdemeliBono(float dovizOdemeliBono) {
		this.DovizOdemeliBono = dovizOdemeliBono;
	}


	public float getDovizOdemeliTahvil() {
		return DovizOdemeliTahvil;
	}
	public void setDovizOdemeliTahvil(float dovizOdemeliTahvil) {
		this.DovizOdemeliTahvil = dovizOdemeliTahvil;
	}


	public float getEurobond() {
		return Eurobond;
	}
	public void setEurobond(float eurobond) {
		this.Eurobond = eurobond;
	}


	public float getFinansmanBonosu() {
		return FinansmanBonosu;
	}
	public void setFinansmanBonosu(float finansmanBonosu) {
		this.FinansmanBonosu = finansmanBonosu;
	}


	public float getFonKatilmaBelgesi() {
		return FonKatilmaBelgesi;
	}
	public void setFonKatilmaBelgesi(float fonKatilmaBelgesi) {
		this.FonKatilmaBelgesi = fonKatilmaBelgesi;
	}


	public float getGayrimenkulSertifikasi() {
		return GayrimenkulSertifikasi;
	}
	public void setGayrimenkulSertifikasi(float gayrimenkulSertifikasi) {
		this.GayrimenkulSertifikasi = gayrimenkulSertifikasi;
	}


	public float getHazineBonosu() {
		return HazineBonosu;
	}
	public void setHazineBonosu(float hazineBonosu) {
		this.HazineBonosu = hazineBonosu;
	}


	public float getHisseSenedi() {
		return HisseSenedi;
	}
	public void setHisseSenedi(float hisseSenedi) {
		this.HisseSenedi = hisseSenedi;
	}


	public float getKamuDisBorclanmaAraci() {
		return KamuDisBorclanmaAraci;
	}
	public void setKamuDisBorclanmaAraci(float kamuDisBorclanmaAraci) {
		this.KamuDisBorclanmaAraci = kamuDisBorclanmaAraci;
	}


	public float getKamuKiraSertifikasi() {
		return KamuKiraSertifikasi;
	}
	public void setKamuKiraSertifikasi(float kamuKiraSertifikasi) {
		this.KamuKiraSertifikasi = kamuKiraSertifikasi;
	}


	public float getKatilimHesabi() {
		return KatilimHesabi;
	}
	public void setKatilimHesabi(float katilimHesabi) {
		this.KatilimHesabi = katilimHesabi;
	}


	public float getKiymetliMaden() {
		return KiymetliMaden;
	}
	public void setKiymetliMaden(float kiymetliMaden) {
		this.KiymetliMaden = kiymetliMaden;
	}


	public float getOzelSektorKiraSertifikasi() {
		return OzelSektorKiraSertifikasi;
	}
	public void setOzelSektorKiraSertifikasi(float ozelSektorKiraSertifikasi) {
		this.OzelSektorKiraSertifikasi = ozelSektorKiraSertifikasi;
	}


	public float getOzelSektorTahvili() {
		return OzelSektorTahvili;
	}
	public void setOzelSektorTahvili(float ozelSektorTahvili) {
		this.OzelSektorTahvili = ozelSektorTahvili;
	}


	public float getTersRepo() {
		return TersRepo;
	}
	public void setTersRepo(float tersRepo) {
		this.TersRepo = tersRepo;
	}


	public float getTPP() {
		return TPP;
	}
	public void setTPP(float tPP) {
		this.TPP = tPP;
	}


	public float getTurevAraci() {
		return TurevAraci;
	}
	public void setTurevAraci(float turevAraci) {
		this.TurevAraci = turevAraci;
	}


	public float getVarligaDayaliMenkulKiymet() {
		return VarligaDayaliMenkulKiymet;
	}
	public void setVarligaDayaliMenkulKiymet(float varligaDayaliMenkulKiymet) {
		this.VarligaDayaliMenkulKiymet = varligaDayaliMenkulKiymet;
	}


	public float getVadeliMevduat() {
		return VadeliMevduat;
	}
	public void setVadeliMevduat(float vadeliMevduat) {
		this.VadeliMevduat = vadeliMevduat;
	}


	public float getYabanciBorclanmaAraci() {
		return YabanciBorclanmaAraci;
	}
	public void setYabanciBorclanmaAraci(float yabanciBorclanmaAraci) {
		this.YabanciBorclanmaAraci = yabanciBorclanmaAraci;
	}


	public float getYabanciHisseSenedi() {
		return YabanciHisseSenedi;
	}
	public void setYabanciHisseSenedi(float yabanciHisseSenedi) {
		this.YabanciHisseSenedi = yabanciHisseSenedi;
	}


	public float getYabanciMenkulKiymet() {
		return YabanciMenkulKiymet;
	}
	public void setYabanciMenkulKiymet(float yabanciMenkulKiymet) {
		this.YabanciMenkulKiymet = yabanciMenkulKiymet;
	}
}
