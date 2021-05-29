package projethotel.pignus.services;

import projethotel.pignus.entities.AppUser;
import projethotel.pignus.entities.Hotel;
import projethotel.pignus.entities.Utilisateurs;

public interface HotelService {
    Hotel saveHotel(String hotelName, String adresse, int nbrePlace);

}
