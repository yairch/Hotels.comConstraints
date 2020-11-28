import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Group implements  ITestable{
    private int groupId;
    HashSet<Hotel> hotels;

    public Group(int id){
        hotels = new HashSet<Hotel>();
        groupId = id;
    }



    public void addHotelToGroup(Hotel hotel){
        hotels.add(hotel);
    }

    //getters

    public int getGroupId() {
        return groupId;
    }

    public HashSet<Hotel> getHotels() {
        return hotels;
    }

    @Override
    public boolean checkConstraints() {
        //constrain 1
        for (Hotel hotel : hotels)
        {
            int count= 0;
            for(Hotel hotel1: hotels){
                if((hotel.getCity()).equals(hotel1.getCity())){
                    count++;
                }
            }
            if (count>1){
                return false;
            }
        }
        //constrain 4

        for(Hotel hotel : hotels){
            for (Hotel hotel1 : hotels){
                List<HotelService> list = new ArrayList<HotelService>(hotel.getServices().values());
                List<HotelService> list1 = new ArrayList<HotelService>(hotel1.getServices().values());
                boolean isEqual = list.equals(list1);
                if (!isEqual){
                    return false;
                }
            }
        }


        return true;
    }
    public static boolean checkAllIntancesConstraints(Model model){
        boolean flag = true;
        for (Object obj : model.allObjects)
        {
            if(obj instanceof Group){
                if(!((Group)obj).checkConstraints()){
                    flag=false;
                }
            }
        }
        return flag;

    }
}
