package se.yrgo.domain;

import java.util.Date;




//@Table(name="TBL_CALL")
public class Call {

	
	private int id;

	private Date timeAndDate;

	private String notes;

	public Call(String notes){
		// this defaults to a timestamp of "now"
		this (notes, new java.util.Date());
	}

	public Call(String notes, Date timestamp){
		// this defaults to a timestamp of "now"
		this.timeAndDate = timestamp;
		this.notes = notes;
	}

	public String toString()	{
		return this.timeAndDate + " : " + this.notes;
	}

	public Date getTimeAndDate() {
		return timeAndDate;
	}

	public void setTimeAndDate(Date timeAndDate) {
		this.timeAndDate = timeAndDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public Call() {}

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Call other = (Call) obj;
        if (notes == null) {
            if (other.notes != null)
                return false;
        } else if (!notes.equals(other.notes))
            return false;
        return true;
    }
}
