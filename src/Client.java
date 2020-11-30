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

        boolean bookedVipRoom = true;
        if (largeReservationSets.size() > 0) {
            for (ReservationSet reservationSet : largeReservationSets) {
                bookedVipRoom = false;

                for (Reservation reservation : reservationSet.getReservations()) {

                    if (reservation.getBookings().getRoom().getRoomCategory().getType() == RoomCategory.RoomType.VIP) {
                       bookedVipRoom =true;
                       break;
                    }
                }
                if (!bookedVipRoom)
                    break;
            }
        }

        return bookedVipRoom ;

    }

    public static boolean checkAllIntancesConstraints(Model model){
        boolean flag = true;
        for (Object obj : model.allObjects)
        {
            if(obj instanceof Client){
                if(!((Client)obj).checkConstraints()){
                    flag=false;
                }
            }
        }
        return flag;
    }
}
