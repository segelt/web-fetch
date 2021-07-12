package models;

import java.time.LocalDate;

public class DateValueDTO {
		private float value;
		private LocalDate date;
		
		public DateValueDTO() {
			super();
		}
		
		public DateValueDTO(float value, LocalDate date) {
			super();
			this.value = value;
			this.date = date;
		}
		
		public float getValue() {
			return value;
		}
		public void setValue(float value) {
			this.value = value;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}

		@Override
		public String toString() {
			return "DateValueDTO [value=" + value + ", date=" + date + "]";
		}
		
		
}
