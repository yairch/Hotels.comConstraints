import java.util.Date;

public class Reservation implements  ITestable {
    private int id;
    private RoomCategory roomCategory;
    private Date orderDate;
    private Date requestDate;
    private Booking booking;
    private ReservationSet reservationSet;


    public Reservation(Date ordDate, Date reqDate, int id) {
        this.id = id;
        orderDate = ordDate;
        requestDate = reqDate;
    }

    public void setReservationSet(ReservationSet reservationSet){
        this.reservationSet = reservationSet;
    }


    public void addRoomCategory(RoomCategory roomCategory) {
        this.roomCategory = roomCategory;
    }

    public void addBooking(Booking _booking) {
        booking = _booking;
    }


    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public Booking getBookings() {
        return booking;
    }

    public int getId() {
        return id;
    }

    public ReservationSet getReservationSet(){return reservationSet;}

    @Override
    public boolean checkConstraints() {
        //constraint8
        if(roomCategory.getType().equals(RoomCategory.RoomType.SUITE)){
            if(booking.getRoom().getRoomCategory().getType().equals(RoomCategory.RoomType.BASIC)){
                return false;
            }

        }
        if(roomCategory.getType().equals(RoomCategory.RoomType.VIP)){
            if(booking.getRoom().getRoomCategory().getType().equals(RoomCategory.RoomType.SUITE) ||booking.getRoom().getRoomCategory().getType().equals(RoomCategory.RoomType.BASIC) ){
                return false;
            }

        }


        //constraint 9
        for (HotelService hotelService: booking.getServices() ){
            if (hotelService.getService() instanceof VipService){
                if (booking.getReview() == null){
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model) {
        boolean flag = true;
        for (Object obj : model.allObjects)
        {
            if(obj instanceof Reservation){
                if(!((Reservation)obj).checkConstraints()){
                    flag=false;
                }
            }
        }
        return flag;

    }


}
    