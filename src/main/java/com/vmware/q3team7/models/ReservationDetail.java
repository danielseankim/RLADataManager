package com.vmware.q3team7.models;

import java.util.Calendar;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author kdaniel
 *
 */

@XmlRootElement
public class ReservationDetail {
    public enum ReservationStatus {
        APPROVED, PENDING, REJECTED
    }

    private String reservationId;
    private ReservationStatus status;
    private Calendar startDate;
    private Calendar endDate;

    /**
     * @return the reservationId
     */
    public String getReservationId() {
        return reservationId;
    }
    /**
     * @param reservationId the reservationId to set
     */
    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
    /**
     * @return the startDate
     */
    public Calendar getStartDate() {
        return startDate;
    }
    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }
    /**
     * @return the endDate
     */
    public Calendar getEndDate() {
        return endDate;
    }
    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }
    /**
     * @return the status
     */
    public ReservationStatus getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(ReservationStatus status) {
        this.status = status;
    }
}
