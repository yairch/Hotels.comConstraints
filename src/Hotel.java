import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class Hotel implements  ITestable{
    private String name;
    private HashMap<Client, ReservationSet> allReservation;
    private HashMap<Service, HotelService> services;
    private HashMap<Integer,Room> rooms;
    private String city;
    private Group group;
    private int rate;
    private HashMap<Integer,Integer> sumOfYears;
    private ArrayList<Integer> years;


    public Hotel(String city, String name,int rate){
        this.city = city;
        this.name = name;
        this.rate = rate;
        rooms = new HashMap<Integer,Room>();
        allReservation = new HashMap<Client, ReservationSet>();
        services = new HashMap<Service, HotelService>();
        sumOfYears = new HashMap<>();
        years = new ArrayList<>();
    }

    public void addReservationSet(Client client,ReservationSet reservationSet){
        allReservation.put(client,reservationSet);
    }

    public void addService(Service service, HotelService hotelService){
        services.put(service,hotelService);
    }

    public void addRoom(int roomNumber, Room room){
        rooms.put(roomNumber,room);
    }


    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public HashMap<Client, ReservationSet> getAllReservation(){return allReservation;}

    public HashMap<Service, HotelService> getServices(){return services;}

    public int getRate(){return rate;}

    @Override
    public boolean checkConstraints() {
        //constraint 6
        float sizeOfRoom = (rooms.values()).size();
        sizeOfRoom = (float) (sizeOfRoom*0.1);
        int countRoomVip=0;
        for(Room room: rooms.values()){
            if((room.getRoomCategory().getType()).equals(RoomCategory.RoomType.VIP)){
                countRoomVip++;
            }
        }
        if (countRoomVip>sizeOfRoom){
            return false;
        }

        //constraint 7

        if (city.equals("LAS VEGAS")){
            for (Client client : allReservation.keySet()){
                if (client.getAge()<21){
                    return false;
                }
            }
        }

        //constraint 10
       if(allReservation.size()>0){
           int count=0;
           int sum = 0;
           if ( rate == 5){
               for(ReservationSet reservationSet: allReservation.values()){
                   for (Reservation reservation : reservationSet.getReservations()){
                       if(reservation.getBookings().getReview() != null) {
                           sum += reservation.getBookings().getReview().getRank();
                           count++;
                       }
                   }
               }
               if(count== 0){
                   return false;
               }
               float total = sum/count;
               if (total<=7.5){
                   return false;
               }

           }
       }

        // constraint 11
        for(HotelService hotelService: services.values()){
            int count1 =0;
            for(HotelService hotelService1: services.values()) {
                if ((hotelService.getService().serviceName).equals(hotelService1.getService().serviceName))
                    count1++;
            }
            if (count1>1){
                return false;
            }
        }

        //constraint 12/14
        for (Room room:rooms.values()){
            for(Date date: room.getBookings().keySet()) {
                String year1 = date.toString().split(" ")[5];
                Integer year = Integer.parseInt(year1);
                for(HotelService hotelService:room.getBookings().get(date).getServices()){
                    if (!sumOfYears.containsKey(year)){
                        sumOfYears.put(year,hotelService.getPrice());
                        years.add(year);
                    }
                    else {
                        int total = sumOfYears.get(year);
                        total+=hotelService.getPrice();
                        sumOfYears.put(year,total);
                    }
                }
                Collections.sort(years);
                for (int i = 0; i<years.size()-1;i++){
                    int year12= sumOfYears.get(years.get(i));
                    int year2= sumOfYears.get(years.get(i+1));
                    if(year12>year2){
                        return false;
                    }
                }
            }
        }



        return true;
    }

    public static boolean checkAllIntancesConstraints(Model model){
        boolean flag = true;
        for (Object obj : model.allObjects)
        {
            if(obj instanceof Hotel){
                if(!((Hotel)obj).checkConstraints()){
                    flag=false;
                }
            }
        }
        return flag;

    }
}
