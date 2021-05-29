package projethotel.pignus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projethotel.pignus.dao.HotelRepository;
import projethotel.pignus.entities.Hotel;
import projethotel.pignus.entities.Utilisateurs;

import javax.transaction.Transactional;

@Service
@Transactional
public class HotelServiceImpl implements HotelService{
    @Autowired
    private HotelRepository hotelRepository;
    @Override
    public Hotel saveHotel(String hotelName, String adresse, int nbrePlace) {
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelName);
        hotel.setAdresse(adresse);
        hotel.setNbrePlace(nbrePlace);
        hotelRepository.save(hotel);
        return hotel;
    }
}
