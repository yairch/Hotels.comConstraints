import jdk.jfr.Description;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;

public class Tests {

    Group group1 = new Group(1);

    Hotel hotel1 = new Hotel("Haifa", "Dan Panorama", 4);
    Hotel hotel2 = new Hotel("Haifa", "Dan Music", 4);

    Client client1 = new Client(1234, 30, "Yossi", "Tel Aviv");
    ReservationSet reservationSet = new ReservationSet();
    Reservation r1 = new Reservation(new Date(), new Date(), 12340);
    Reservation r2 = new Reservation(new Date(), new Date(), 146534);
    Reservation r3 = new Reservation(new Date(), new Date(), 8451);
    Reservation r4 = new Reservation(new Date(), new Date(), 1276870);
    Reservation r5 = new Reservation(new Date(), new Date(), 12889);

    RoomCategory vipRoom = new RoomCategory(50, RoomCategory.RoomType.VIP);
    RoomCategory basicRoom = new RoomCategory(10, RoomCategory.RoomType.BASIC);
    RoomCategory suiteRoom = new RoomCategory(30, RoomCategory.RoomType.SUITE);

    Room room1 = new Room(1);
    Room room2 = new Room(2);
    Room room3 = new Room(3);
    Room room4 = new Room(4);
    Room room5 = new Room(5);

    Booking booking1 = new Booking(Model.getDateFromString("10-11-2020"), room1);
    Booking booking2 = new Booking(Model.getDateFromString("11-10-2020"), room2);
    Booking booking3 = new Booking(Model.getDateFromString("11-12-2020"), room3);
    Booking booking4 = new Booking(Model.getDateFromString("10-11-2019"), room4);
    Booking booking5 = new Booking(Model.getDateFromString("11-12-2018"), room5);

    @Description("Two Hotels in the same Group in the same town")
    @Test
    public void constraint1(){
        Model model = new Model();
        model.addObjectToModel(hotel1);
        model.addObjectToModel(hotel2);
        model.create_link_group_hotel(hotel1, group1);
        model.create_link_group_hotel(hotel2, group1);
        boolean result = model.checkModelConstraints();
        assertFalse(result);
    }

    @Description("5 times bookings in Hotel -> VIP Room")
    @Test
    public void constraint2(){
        Model model = new Model();
        model.addObjectToModel(hotel1);
        model.addObjectToModel(client1);
        model.addObjectToModel(reservationSet);
        model.addObjectToModel(r1);
        model.addObjectToModel(r2);
        model.addObjectToModel(r3);
        model.addObjectToModel(r4);
        model.addObjectToModel(r5);
        model.addObjectToModel(suiteRoom);
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

        model.create_link_client_hotel_reservationSet(client1, hotel1, reservationSet);
        model.create_link_reservationSet_reservation(reservationSet,r1);
        model.create_link_reservationSet_reservation(reservationSet,r2);
        model.create_link_reservationSet_reservation(reservationSet,r3);
        model.create_link_reservationSet_reservation(reservationSet,r4);
        model.create_link_reservationSet_reservation(reservationSet,r5);

        model.create_link_reservation_booking(booking1, r1);
        model.create_link_reservation_booking(booking2, r2);
        model.create_link_reservation_booking(booking3, r3);
        model.create_link_reservation_booking(booking4, r4);
        model.create_link_reservation_booking(booking5, r5);

        model.create_link_room_Booking(room1, booking1);
        model.create_link_room_Booking(room2, booking2);
        model.create_link_room_Booking(room3, booking3);
        model.create_link_room_Booking(room4, booking4);
        model.create_link_room_Booking(room5, booking5);

        model.create_link_hotel_room(room1, hotel1);
        model.create_link_hotel_room(room2, hotel1);
        model.create_link_hotel_room(room3, hotel1);
        model.create_link_hotel_room(room4, hotel1);
        model.create_link_hotel_room(room5, hotel1);

        model.create_link_room_roomCategory(room1, basicRoom);
        model.create_link_room_roomCategory(room2, basicRoom);
        model.create_link_room_roomCategory(room3, suiteRoom);
        model.create_link_room_roomCategory(room4, basicRoom);
        model.create_link_room_roomCategory(room5, suiteRoom);

        boolean result = model.checkModelConstraints();
        assertFalse(result);
    }

    @Description("Resevation and Booking to the same Hotel")
    @Test
    public void constraint3(){}

    @Description("same Services for all Hotels in the same Group")
    @Test
    public void constraint4(){}

    @Description("all Services for a VIP Room are VIP Services")
    @Test
    public void constraint5(){}

    @Description("VIP Rooms not up then 10% of all Rooms in a Hotel")
    @Test
    public void constraint6(){}

    @Description("The age of a guest in a Las Vegas Hotel should be bigger than 21")
    @Test
    public void constraint7(){}

    @Description("Room Type should be as in the Reservation or higher")
    @Test
    public void constraint8(){}

    @Description("VIP costumer should add Review")
    @Test
    public void constraint9(){}

    @Description("The average rate of 5 stars Hotel is above 7.5")
    @Test
    public void constraint10(){}

    @Description("Two Services in the same Hotel should be in different names")
    @Test
    public void constraint11(){}

    @Description("The income of a Hotel will be higher in every year than the last year")
    @Test
    public void constraint12(){}

    @Description("All Services given in Booking should be of the same Hotel")
    @Test
    public void constraint13(){}
}
