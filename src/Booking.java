import java.util.ArrayList;
import java.util.Date;

public class Booking implements  ITestable{
    private Date date;
    private Room room;
    private ArrayList<HotelService> services;
    private Reservation reservation;
    private Review review;


    public Booking(Date a_date, Room a_room){
        date = a_date;
        room = a_room;
        services = new ArrayList<HotelService>();
    }

    public void addService(HotelService s){
        services.add(s);
    }

    public void addReview(Review a_review) {review  = a_review; }

    public void addReservation(Reservation r){
        reservation = r;
    }

    public void assignRoom(Room room){
        this.room = room;
    }


    // getters

    public Date getDate() {
        return date;
    }

    public Room getRoom() {
        return room;
    }

    public ArrayList<HotelService> getServices() {
        return services;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public Review getReview() {
        return review;
    }


    @Override
    public boolean checkConstraints() {
        //constraint 3
        if(room.getHotel() != null && reservation.getReservationSet().getHotel() != null){
            if(!(room.getHotel().getName()).equals(reservation.getReservationSet().getHotel().getName())){
                return false;
            }
        }




        //constraint 13

        for (HotelService hotelService: services){
            if (!(hotelService.getHotel().getName().equals(reservation.getReservationSet().getHotel().getName()))){
                return false;
            }
        }

        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        boolean flag = true;
        for (Object obj : model.allObjects)
        {
            if(obj instanceof Booking){
                if(!((Booking)obj).checkConstraints()){
                    flag=false;
                }
            }
        }
        return flag;

    }
}
