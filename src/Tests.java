import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;

public class Tests {

    /**
     * Test constraint - A client who stayed at a hotel at at least 5 different reservations, was booked at least once a VIP room
     */
    @Test
    public void largeResrvationSetVIP(){

        Model model = new Model();
        Hotel hotel = new Hotel("Haifa", "Dan Panorama", 4);
        Client client = new Client(1234, 30, "Yossi", "Tel Aviv");
        ReservationSet reservationSet = new ReservationSet();
        Reservation r1 = new Reservation(new Date(), new Date(), 12340);
        Reservation r2 = new Reservation(new Date(), new Date(), 146534);
        Reservation r3 = new Reservation(new Date(), new Date(), 8451);
        Reservation r4 = new Reservation(new Date(), new Date(), 1276870);
        Reservation r5 = new Reservation(new Date(), new Date(), 12889);

        RoomCategory vipRoom = new RoomCategory(50, RoomCategory.RoomType.VIP);
        RoomCategory basicRoom = new RoomCategory(10, RoomCategory.RoomType.BASIC);

        Room room1 = new Room(1);
        Room room2 = new Room(2);
        Room room3 = new Room(3);
        Room room4 = new Room(4);
        Room room5 = new Room(5);

        Booking booking1 = new Booking(new Date(), room1);
        Booking booking2 = new Booking(new Date(), room2);
        Booking booking3 = new Booking(new Date(), room3);
        Booking booking4 = new Booking(new Date(), room4);
        Booking booking5 = new Booking(new Date(), room5);

        model.addObjectToModel(hotel);
        model.addObjectToModel(client);
        model.addObjectToModel(reservationSet);
        model.addObjectToModel(r1);
        model.addObjectToModel(r2);
        model.addObjectToModel(r3);
        model.addObjectToModel(r4);
        model.addObjectToModel(r5);
        model.addObjectToModel(vipRoom);
        model.addObjectToModel(basicRoom);
        model.addObjectToModel(room1);
        model.addObjectToModel(room2);
        model.addObjectToModel(room3);
        model.addObjectToModel(room4);
        model.addObjectToModel(room5);
        model.addObjectToModel(booking1);
        model.addObjectToModel(booking2);
        model.addObjectToModel(booking3);
        model.addObjectToModel(booking4);
        model.addObjectToModel(booking5);

        model.create_link_client_hotel_reservationSet(client, hotel, reservationSet);
        model.create_link_reservationSet_reservation(reservationSet,r1);
        model.create_link_reservationSet_reservation(reservationSet,r2);
        model.create_link_reservationSet_reservation(reservationSet,r3);
        model.create_link_reservationSet_reservation(reservationSet,r4);
        model.create_link_reservationSet_reservation(reservationSet,r5);


        assertFalse(model.checkModelConstraints());

    }
}
