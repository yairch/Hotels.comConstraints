import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Room implements  ITestable{
    private RoomCategory roomCategory;
    private HashMap<Date,Booking> bookings;
    private int number;
    private Hotel hotel;


    public Room(int number){
        this.number = number;
        bookings = new HashMap<Date,Booking>();
    }

    public void setHotel(Hotel h){ hotel = h; }

    public void asignRoomCategory(RoomCategory roomCategory){
        this.roomCategory = roomCategory;
    }

    public void addBooking(Booking booking, Date date) {
        bookings.put(date, booking);
    }

    public RoomCategory getRoomCategory() {
        return roomCategory;
    }

    public HashMap<Date, Booking> getBookings() {
        return bookings;
    }

    public int getNumber() {
        return number;
    }

    public Hotel getHotel(){ return hotel; }

    @Override
    public boolean checkConstraints() {
        //constraint 5
        if (roomCategory.getType().equals(RoomCategory.RoomType.VIP)){
            for (Booking bookings: bookings.values()){
                List<HotelService> list = bookings.getServices();
                for(int i=0; i<list.size();i++){
                    if(!( list.get(i).getService() instanceof VipService)){
                        return false;
                    }

                }
            }
        }

        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        boolean flag = true;
//        for (Object obj : model.allObjects)
//        {
//            if(obj instanceof Room){
//                if(!((Room)obj).checkConstraints()){
//                    flag=false;
//                }
//            }
//        }
        return flag;

    }
}
