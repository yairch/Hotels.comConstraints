import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Client implements  ITestable {
    private HashMap<Hotel,ReservationSet> reservationsHistory;
    private int id;
    private int age;
    private String name;
    private String city;

    public HashMap<Hotel, ReservationSet> getReservationsHistory() {
        return reservationsHistory;
    }

    public Client(int a_id, int a_age, String a_name, String a_city){
        reservationsHistory = new HashMap<Hotel,ReservationSet>();
        id = a_id;
        age = a_age;
        city = a_city;
        name = a_name;
    }

    public void addReservationSet(Hotel hotel, ReservationSet reset){
        reservationsHistory.put(hotel,reset);
    }

    // getters

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean checkConstraints() {

        // A client who stayed at a hotel at at least 5 different reservations, was booked at least once a VIP room
        List<ReservationSet> largeReservationSets = new ArrayList<>(); // ReservationSets with at least 5 different reservations
        for (ReservationSet reservationSet : this.getReservationsHistory().values()){

            if (reservationSet.getReservations().size() >= 5)
                largeReservationSets.add(reservationSet);
        }


        boolean bookedVipRoom = false;
        for (ReservationSet reservationSet : largeReservationSets){

            bookedVipRoom = false;
            for (Reservation reservation : reservationSet.getReservations()){

                if (reservation.getBookings().getRoom().getRoomCategory().getType() == RoomCategory.RoomType.VIP){
                    bookedVipRoom = true;
                    break;
                }
            }

            if (!bookedVipRoom)
                break;
        }


        // A client who ordered a VIP service a obliged to add a review on the hotel
       /* boolean addedVipReview = true;
        for (ReservationSet reservationSet : this.getReservationsHistory().values()){
            for (Reservation reservation : reservationSet.getReservations()){

                Booking booking = reservation.getBookings();
                for (HotelService hotelService : booking.getServices()){

                    if (hotelService.getService() instanceof  VipService)
                        if (booking.getReview() == null){
                            addedVipReview = false;
                            break;
                        }
                }

                if (!addedVipReview)
                    break;
            }
            if (!addedVipReview)
                break;
        }

        return bookedVipRoom && addedVipReview;*/

        //constraint 2
        int count =0;
        for(ReservationSet reservationSet:reservationsHistory.values()){
            count=0;
            if(reservationSet.getReservations().size()>=5){
                for(Reservation reservation : reservationSet.getReservations()){
                    if((reservation.getBookings().getRoom().getRoomCategory().getType()).equals("VIP")){
                        count++;
                    }
                }
                if (count<1){
                    return false;
                }
            }

        }

        return true;

    }

    public static boolean checkAllIntancesConstraints(Model model){

        for (Client client : model.ClientAllInstances()){
            if (!client.checkConstraints())
                return false;
        }
        return true;
    }
}
