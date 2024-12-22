package DTOS.seance;

import java.io.Serializable;

public class SeanceResponse implements Serializable{
	    
	    private String date;
	    private String heureDebut;
	    private String heureFin;
	    private long filiereId;
	    private String type;
		
		
		public String getDate() {
			return date;
		}
		public void setDate(String date) {
			this.date = date;
		}
		public String getHeureDebut() {
			return heureDebut;
		}
		public void setHeureDebut(String heureDebut) {
			this.heureDebut = heureDebut;
		}
		public String getHeureFin() {
			return heureFin;
		}
		public void setHeureFin(String heureFin) {
			this.heureFin = heureFin;
		}
		public long getFiliereId() {
			return filiereId;
		}
		public void setFiliereId(long filiereId) {
			this.filiereId = filiereId;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public SeanceResponse( String date, String heureDebut, String heureFin, long filiereId,
				String type) {
			super();
			
			this.date = date;
			this.heureDebut = heureDebut;
			this.heureFin = heureFin;
			this.filiereId = filiereId;
			this.type = type;
		}
	    
	    public SeanceResponse() {
			// TODO Auto-generated constructor stub
		}

}
