package com.mahir.Service.imp;

import java.util.List;

import com.mahir.DTO.GalleristCarRequest;
import com.mahir.Entity.GalleristCar;

public interface IGalleristCarService {
  public GalleristCar saveGalleristCar(GalleristCarRequest galleristCarRequest);

    List<GalleristCar> getAllGalleristCars();
    public List<com.mahir.DTO.GalleristCarDetailsDTO> getSalableCarsByUserId(int userId);

       public int getSalableCarCountByUserId(int userId) ;
       public List<com.mahir.DTO.GalleristCarDetailsDTO> getNewestCarsByUserId(int userId) ;
           public List<com.mahir.DTO.GalleristCarDetailsDTO> getOldestCarsByUserId(int userId) ;
           public void deleteCarById(int carId);
}
