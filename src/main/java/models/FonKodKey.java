package models;

import java.io.Serializable;
import java.time.LocalDate;

public class FonKodKey implements Serializable {
	private static final long serialVersionUID = 3384594070697628419L;

	private String FonKodu;
	
	private LocalDate Tarih;
	
	public FonKodKey(){
		FonKodu = "";
		Tarih = LocalDate.now();
	}
	
    public FonKodKey(String FonKodu, LocalDate Tarih) {
        this.FonKodu = FonKodu;
        this.Tarih = Tarih;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (FonKodu.hashCode());
        result = prime * result + ((Tarih == null) ? 0 : Tarih.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FonKodKey other = (FonKodKey) obj;
        if (this.FonKodu == null) {
            if (other.FonKodu != null)
                return false;
        } else if (!this.FonKodu.equals(other.FonKodu))
            return false;
        
        if (this.Tarih == null) {
            if (other.Tarih != null)
                return false;
        } else if (!this.Tarih.equals(other.Tarih))
            return false;
        return true;
    }
}
