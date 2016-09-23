package com.vmware.q3team7.util;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

import com.vmware.q3team7.models.RLAData;
import com.vmware.q3team7.models.ReservationDetail;

/**
 * @author kdaniel
 *
 */
public class MarshalUtilTest {

    @Test
    public void marshalWholeData0() {
        RLAData data = new RLAData();
        data.setContent("");
        data.setId("7");
        data.setName("test");
        data.setTag("tag");
        data.setVersion("1");

        String xmlString = MarshalUtil.marshal(data);
        String expected = 
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
                + "<rlaData>"
                + "<content>"
                + "</content>"
                + "<id>7</id>"
                + "<name>test</name>"
                + "<tag>tag</tag>"
                + "<version>1</version>"
                + "</rlaData>";
        assertEquals(expected, xmlString);
    }

    @Test
    public void marshalContent0(){
        ReservationDetail rd = new ReservationDetail();
        Calendar start = new GregorianCalendar(2016, 10, 1);
        rd.setStartDate(start);
        Calendar end = new GregorianCalendar(2016, 11, 1);
        rd.setEndDate(end);
        rd.setReservationId("123451");

        String jsonString = MarshalUtil.marshalContent(rd);
        String expected = "{\"reservationDetail\":{\"endDate\":\"2016-12-01T00:00:00-06:00\",\"reservationId\":123451,\"startDate\":\"2016-11-01T00:00:00-05:00\"}}";;
        assertEquals(expected, jsonString);
    }
}
